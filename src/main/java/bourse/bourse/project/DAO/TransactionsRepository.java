package bourse.bourse.project.DAO;

import bourse.bourse.project.entities.Societe;
import bourse.bourse.project.entities.Transactions;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionsRepository extends ReactiveMongoRepository<Transactions,String> {
    Flux<Transactions> findBySociete(Societe societe);
    Mono<Void> deleteBySociete(Societe societe);
}
