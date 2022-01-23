package bourse.bourse.project.DAO;

import bourse.bourse.project.entities.Autority;
import bourse.bourse.project.entities.Roles;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RolesRepository extends ReactiveMongoRepository<Roles, String> {
    Mono<Void> deleteByAutorities(Autority autority);

    Flux<Autority> findRolesByAutorities(Roles role);
}
