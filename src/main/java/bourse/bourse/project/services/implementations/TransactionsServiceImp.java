package bourse.bourse.project.services.implementations;

import bourse.bourse.project.DAO.SocieteRepository;
import bourse.bourse.project.DAO.TransactionsRepository;
import bourse.bourse.project.entities.Societe;
import bourse.bourse.project.entities.Transactions;
import bourse.bourse.project.services.interfaces.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class TransactionsServiceImp implements TransactionsService {
    @Autowired
    private TransactionsRepository transactionsRepository;
    @Autowired
    private SocieteRepository societeRepository;

    @Override
    public Mono<Transactions> saveTransaction(Transactions transaction) {
        return transactionsRepository.save(transaction);
    }

    @Override
    public Flux<Transactions> getTransactions() {
        return transactionsRepository.findAll();
    }

    @Override
    public Flux<Transactions> getTransactionBySociete(String idSociete) {
        Societe societe = new Societe();
        societe.setId(idSociete);
        return transactionsRepository.findBySociete(societe);
    }

    @Override
    public Mono<Transactions> getTransactionById(String idTransaction) {
        return transactionsRepository.findById(idTransaction);
    }

    @Override
    public Mono<Void> deleteTransactionById(String idTransaction) {
        return transactionsRepository.deleteById(idTransaction);
    }

    @Override
    public Mono<Void> deleteTransactionBySociete(String idSociete) {
        Societe societe = new Societe();
        societe.setId(idSociete);
        return transactionsRepository.deleteBySociete(societe);
    }

    @Override
    public Mono<Void> deleteSocieteAll() {
        return transactionsRepository.deleteAll();
    }

    @Override
    public Mono<Transactions> updateTransaction(String idTransaction, Transactions transaction) {
        transaction.setId(idTransaction);
        return transactionsRepository.save(transaction);
    }
}
