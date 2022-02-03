package bourse.bourse.project.services.implementations;

import bourse.bourse.project.DAO.AutorityRepository;
import bourse.bourse.project.entities.Autority;
import bourse.bourse.project.entities.Roles;
import bourse.bourse.project.entities.Transactions;
import bourse.bourse.project.services.interfaces.AutorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional

public class AutorityServiceImp implements AutorityService {

    @Autowired
    private AutorityRepository autorityRepository;

    @Override
    public Mono<Autority> saveAutority(Autority autority) {
        //autority.setNom(autority.getNom().toUpperCase());
        return autorityRepository.save(autority);
    }

    @Override
    public Flux<Autority> getAutorities() {
        return autorityRepository.findAll();
    }

    @Override
    public Mono<Autority> getAutorityById(String id) {
        return autorityRepository.findById(id);
    }

    @Override
    public Mono<Void> deleteAutorityById(String id) {
        return autorityRepository.deleteById(id);
    }

    @Override
    public Mono<Void> deleteAutoritiesAll() {
        return autorityRepository.deleteAll();
    }

    @Override
    public Mono<Void> deleteByTransactions(Transactions transaction) {
        return autorityRepository.deleteByTransactions(transaction);
    }

    @Override
    public Mono<Autority> updateAutority(String idAutority, Autority autority) {
        autority.setId(idAutority);
        return autorityRepository.save(autority);
    }
}
