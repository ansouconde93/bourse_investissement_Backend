package bourse.bourse.project.services.implementations;

import bourse.bourse.project.DAO.UserRepository;
import bourse.bourse.project.entities.User;
import bourse.bourse.project.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional

public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Mono<User> saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Flux<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Mono<User> getUserById(String idUser) {
        return userRepository.findById(idUser);
    }

    @Override
    public Mono<Void> deleteUserById(String idUser) {
        return userRepository.deleteById(idUser);
    }

    @Override
    public Mono<Void> deleteUserAll() {
        return userRepository.deleteAll();
    }

    @Override
    public Mono<User> updateUser(String idUser, User user) {
        user.setId(idUser);
        return userRepository.save(user);
    }
}
