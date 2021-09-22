package bourse.bourse.project;

import bourse.bourse.project.entities.Societe;
import bourse.bourse.project.entities.Transactions;
import bourse.bourse.project.services.interfaces.SocieteService;
import bourse.bourse.project.services.interfaces.TransactionsService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
import java.util.stream.Stream;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Bean
	CommandLineRunner start(SocieteService societeService, TransactionsService transactionsService){
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
		};
	}

}
