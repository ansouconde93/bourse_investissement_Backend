package bourse.bourse.project.services.interfaces;

import bourse.bourse.project.entities.Societe;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SocieteService {
    Mono<Societe> saveSociete(Societe societe);

    Flux<Societe> getSocietes();
    Mono<Societe> getSocieteById(String idSociete);

    Mono<Void> deleteSocieteById(String idSociete);
    Mono<Void> deleteSocieteAll();

    Mono<Societe> updateSociete(String idSociete, Societe societe);
}
