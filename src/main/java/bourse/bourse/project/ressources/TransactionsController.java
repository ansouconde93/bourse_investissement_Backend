package bourse.bourse.project.ressources;

import bourse.bourse.project.entities.Transactions;
import bourse.bourse.project.services.interfaces.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController

public class TransactionsController {
    @Autowired
    private TransactionsService transactionsService;

    @PostMapping("/transaction/save")
    public Mono<Transactions> saveTransaction(@RequestBody Transactions transaction){
        return transactionsService.saveTransaction(transaction);
    }

    @GetMapping("/transactions")
    public Flux<Transactions> getTransactions(){
        return transactionsService.getTransactions();
    }
    @GetMapping("/transaction/{id}")
    public Mono<Transactions> getTransactionById(@PathVariable String id){
        return transactionsService.getTransactionById(id);
    }
    @GetMapping("/transactions/societe/{id}")
    public Flux<Transactions> getTransactionBySociete(@PathVariable String idSociete){
        return transactionsService.getTransactionBySociete(idSociete);
    }

    @DeleteMapping("/transaction/delete/{id}")
    public Mono<Void> deleteTransactionById(@PathVariable String id){
        return transactionsService.deleteTransactionById(id);
    }

    @DeleteMapping("/transactions/societe/delete/{id}")
    public Mono<Void> deleteTransactionBySociete(@PathVariable String idSociete){
        return transactionsService.deleteTransactionBySociete(idSociete);
    }
    @PutMapping("/transaction/update/{id}")
    public Mono<Transactions> updateTransaction(@PathVariable String id, @RequestBody Transactions transaction){
        return transactionsService.updateTransaction(id, transaction);
    }
}
