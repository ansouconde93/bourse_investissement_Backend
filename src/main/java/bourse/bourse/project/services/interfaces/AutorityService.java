package bourse.bourse.project.services.interfaces;

import bourse.bourse.project.entities.Autority;
import bourse.bourse.project.entities.Transactions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AutorityService {

    Mono<Autority> saveAutority(Autority autority);

    Flux<Autority> getAutorities();
    Mono<Autority> getAutorityById(String id);

    Mono<Void> deleteAutorityById(String id);
    Mono<Void> deleteAutoritiesAll();
    Mono<Void> deleteByTransactions(Transactions transaction);

    Mono<Autority> updateAutority(String idAutority, Autority autority);
}
