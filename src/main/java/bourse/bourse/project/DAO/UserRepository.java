package bourse.bourse.project.DAO;

import bourse.bourse.project.entities.Roles;
import bourse.bourse.project.entities.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
    Mono<Void> deleteByRoles(Roles role);

    //Mono<User> findUserByEmail(String email);
    User findUserByEmail(String username);
}
