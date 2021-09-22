package bourse.bourse.project.services.implementations;

import bourse.bourse.project.DAO.SocieteRepository;
import bourse.bourse.project.entities.Societe;
import bourse.bourse.project.services.interfaces.SocieteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class SocieteServiceImp implements SocieteService {
    @Autowired
    private SocieteRepository societeRepository;

    @Override
    public Mono<Societe> saveSociete(Societe societe) {
        return societeRepository.save(societe);
    }

    @Override
    public Flux<Societe> getSocietes() {
        return societeRepository.findAll();
    }

    @Override
    public Mono<Societe> getSocieteById(String idSociete) {
        return societeRepository.findById(idSociete);
    }

    @Override
    public Mono<Void> deleteSocieteById(String idSociete) {
        return societeRepository.deleteById(idSociete);
    }

    @Override
    public Mono<Void> deleteSocieteAll() {
        return societeRepository.deleteAll();
    }

    @Override
    public Mono<Societe> updateSociete(String idSociete, Societe societe) {
        societe.setId(idSociete);
        return societeRepository.save(societe);
    }
}

