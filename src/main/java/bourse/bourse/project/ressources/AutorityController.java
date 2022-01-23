package bourse.bourse.project.ressources;

import bourse.bourse.project.entities.Autority;
import bourse.bourse.project.entities.Roles;
import bourse.bourse.project.services.interfaces.AutorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController

public class AutorityController {
    @Autowired
    private AutorityService autorityService;

    @PostMapping("/autority/save")
    public Mono<Autority> saveAutority(@RequestBody Autority autority){
        return autorityService.saveAutority(autority);
    }
    @GetMapping("/autorities")
    public Flux<Autority> getAutorities(){
        return autorityService.getAutorities();
    }
    @GetMapping("/autority/{id}")
    public Mono<Autority> getAutorityById(@PathVariable String id){
        return autorityService.getAutorityById(id);
    }
    @DeleteMapping("/autority/delete/{id}")
    public Mono<Void> deleteAutorityById(@PathVariable String id){
        return autorityService.deleteAutorityById(id);
    }
    @PutMapping("/autority/update/{id}")
    public Mono<Autority> updateAutority(@PathVariable String id, @RequestBody Autority autority){
        return autorityService.updateAutority(id, autority);
    }
}
