package bourse.bourse.project;

import bourse.bourse.project.entities.*;
import bourse.bourse.project.services.interfaces.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
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
			User user = new User();
			user.setNom("informatique");
			user.setPrenom("info");
			user.setAdresse("tunis");
			user.setEmail("if5@gmail.com");
			user.setPassword("fstif5");
			user.setTelephone(56369214);
			final int[] i = {0};
			Stream.of("create","read","update","delete","manager")
					.forEach(aut ->{
						Autority autority = new Autority();
						autority.setId(null);
						autority.setNom(aut);
						autorityService.saveAutority(autority)
								.subscribe(autt ->{
									Stream.of("root", "admin")
											.forEach(rol->{
												Roles role = new Roles();
												role.setId(null);
												role.setNom(rol);
												role.getAutorities().add(autt);
												rolesService.saveRole(role)
														.subscribe(rle ->{
															user.getRoles().add(rle);
															i[0]++;
														});
											});
								});
						if (i[0] >= 10){
							userService.saveUser(user)
									.subscribe(user1 -> { });
						}
					});

		};
	}

}
