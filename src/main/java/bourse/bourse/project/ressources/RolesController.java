package bourse.bourse.project.ressources;

import bourse.bourse.project.DAO.RolesRepository;
import bourse.bourse.project.entities.Roles;
import bourse.bourse.project.entities.User;
import bourse.bourse.project.services.interfaces.RolesService;
import bourse.bourse.project.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController

public class RolesController {
    @Autowired
    private RolesService rolesService;

    @PostMapping("/role/save")
    public Mono<Roles> saveRole(@RequestBody Roles role){
        return rolesService.saveRole(role);
    }
    @GetMapping("/roles")
    public Flux<Roles> getRoles(){
        return rolesService.getRoles();
    }
    @GetMapping("/role/{id}")
    public Mono<Roles> getRoleById(@PathVariable String id){
        return rolesService.getRoleById(id);
    }
    @DeleteMapping("/role/delete/{id}")
    public Mono<Void> deleteRoleById(@PathVariable String id){
        return rolesService.deleteRoleById(id);
    }
    @PutMapping("/role/update/{id}")
    public Mono<Roles> updateRole(@PathVariable String id, @RequestBody Roles role){
        return rolesService.updateRole(id, role);
    }

}

