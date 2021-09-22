package bourse.bourse.project.ressources;

import bourse.bourse.project.entities.Societe;
import bourse.bourse.project.services.interfaces.SocieteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController

public class SocieteController {
    @Autowired
    private SocieteService societeService;

    @PostMapping("/societe/save")
    public Mono<Societe> saveSociete(@RequestBody Societe societe){
        return societeService.saveSociete(societe);
    }
    @GetMapping("/societes")
    public Flux<Societe> getSocietes(){
        return societeService.getSocietes();
    }
    @GetMapping("/societe/{id}")
    public Mono<Societe> getSocieteById(@PathVariable String id){
        return societeService.getSocieteById(id);
    }
    @DeleteMapping("/societe/delete/{id}")
    public Mono<Void> deleteSocieteById(@PathVariable String id){
        return societeService.deleteSocieteById(id);
    }
    @PutMapping("/societe/update/{id}")
    public Mono<Societe> updateSociete(@PathVariable String id, @RequestBody Societe societe){
        return societeService.updateSociete(id, societe);
    }


}
