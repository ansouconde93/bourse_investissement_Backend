package bourse.bourse.project.ressources;

import bourse.bourse.project.entities.User;
import bourse.bourse.project.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController

public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user/save")
    public Mono<User> saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }
    @GetMapping("/users")
    public Flux<User> getUsers(){
        return userService.getUsers();
    }
    @GetMapping("/user/{id}")
    public Mono<User> getUserById(@PathVariable String id){
        return userService.getUserById(id);
    }
    @DeleteMapping("/user/delete/{id}")
    public Mono<Void> deleteUserById(@PathVariable String id){
        return userService.deleteUserById(id);
    }
    @PutMapping("/user/update/{id}")
    public Mono<User> updateUser(@PathVariable String id, @RequestBody User user){
        return userService.updateUser(id, user);
    }
}
