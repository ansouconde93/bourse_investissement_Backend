package bourse.bourse.project;

import bourse.bourse.project.entities.*;
import bourse.bourse.project.security.Login;
import bourse.bourse.project.security.MyJWTTokenManager;
import bourse.bourse.project.services.interfaces.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

@SpringBootApplication//(exclude = ReactiveSecurityAutoConfiguration.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Bean
	CommandLineRunner start(SocieteService societeService, TransactionsService transactionsService,
							UserService userService, RolesService rolesService, AutorityService autorityService){
		return args->{
			societeService.deleteSocieteAll()
					.subscribe(null,null,()->{
						Stream.of("IBM","MIC","AMA","ALY","FAC","MAC","SAM","SON","TEC","ASU","DEL")
								.forEach(societe ->{
									societeService.saveSociete(new Societe(societe,societe,1000+Math.random()*9999))
											.subscribe(societe1 -> {
												transactionsService.deleteSocieteAll()
														.subscribe(null,null,()->{
															for (int i = 0; i < 6; i++) {
																transactionsService.saveTransaction(
																		new Transactions(null,societe,societe1.getActionPrice()*(1+(Math.random()*10-5)/100),i*11+5, Instant.now(),societe1))
																		.subscribe(transaction1 -> {

																		});
															}
														});
											});
								});
					});
			AtomicReference<User> user = new AtomicReference<>(new User());
			user.get().setNom("informatique");
			user.get().setPrenom("info");
			user.get().setAdresse("tunis");
			user.get().setEmail("if5@gmail.com");
			user.get().setPassword("fstif5");
			user.get().setTelephone(56369214);

			Roles role = new Roles();
			role.setId(null);
			role.setNom("ROOT");
			AtomicInteger i = new AtomicInteger();
			autorityService.deleteAutoritiesAll()
					.subscribe(null,null,()->{
						Stream.of("create","read","update","delete","manager")
								.forEach(aut ->{
									Autority autority = new Autority();
									autority.setId(null);
									autority.setNom(aut);
									autorityService.saveAutority(autority)
											.subscribe(autt ->{
												role.getAutorities().add(autt);
												i.getAndIncrement();
												if (i.get() >= 5){
													rolesService.deleteRolesAll()
															.subscribe(null,null,()->{
																rolesService.saveRole(role)
																		.subscribe(rle ->{
																			userService.deleteUserAll()
																					.subscribe(null, null, ()->{
																						user.get().getRoles().add(rle);
																						userService.saveUser(user.get())
																								.subscribe(user1 -> {
																									Login usr = new Login();
																									usr.setUsername(user1.getEmail());
																									usr.setPassword(user1.getPassword());
																									//System.out.println(usr);
																									//System.out.println("\n\n\n\n"+new MyJWTTokenManager().genereteToken(usr)+"\n\n\n\n");
																								});
																					});
																		});
															});
												}
											});
								});
					});
		};
	}

}
