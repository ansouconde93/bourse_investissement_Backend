package bourse.bourse.project.services.interfaces;

import bourse.bourse.project.entities.Transactions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionsService {
    Mono<Transactions> saveTransaction(Transactions transaction);

    Flux<Transactions> getTransactions();
    Flux<Transactions> getTransactionBySociete(String idSociete);
    Mono<Transactions> getTransactionById(String idTransaction);

    Mono<Void> deleteTransactionById(String idTransaction);
    Mono<Void> deleteTransactionBySociete(String idSociete);
    Mono<Void> deleteSocieteAll();

    Mono<Transactions> updateTransaction(String idTransaction, Transactions transaction);

}
