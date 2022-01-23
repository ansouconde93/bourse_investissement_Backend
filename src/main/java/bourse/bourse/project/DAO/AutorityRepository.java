package bourse.bourse.project.DAO;

import bourse.bourse.project.entities.Autority;
import bourse.bourse.project.entities.Transactions;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface AutorityRepository extends ReactiveMongoRepository<Autority, String> {
    Mono<Void> deleteByTransactions(Transactions transaction);
}
