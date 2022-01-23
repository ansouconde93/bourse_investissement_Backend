package bourse.bourse.project.services.interfaces;

import bourse.bourse.project.entities.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<User> saveUser(User user);

    Flux<User> getUsers();
    Mono<User> getUserById(String idUser);

    Mono<Void> deleteUserById(String idUser);
    Mono<Void> deleteUserAll();

    Mono<User> updateUser(String idUser, User user);

    User findUserByEmail(String email);
}
