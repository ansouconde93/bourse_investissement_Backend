package bourse.bourse.project.services.implementations;

import bourse.bourse.project.DAO.RolesRepository;
import bourse.bourse.project.entities.Autority;
import bourse.bourse.project.entities.Roles;
import bourse.bourse.project.entities.User;
import bourse.bourse.project.services.interfaces.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional

public class RolesServiceImp implements RolesService {

    @Autowired
    private RolesRepository rolesRepository;
    @Override
    public Mono<Roles> saveRole(Roles role) {
        return rolesRepository.save(role);
    }

    @Override
    public Flux<Roles> getRoles() {
        return rolesRepository.findAll();
    }

    @Override
    public Mono<Roles> getRoleById(String id) {
        return rolesRepository.findById(id);
    }

    @Override
    public Mono<Void> deleteRoleById(String id) {
        return rolesRepository.deleteById(id);
    }

    @Override
    public Mono<Void> deleteRolesAll() {
        return rolesRepository.deleteAll();
    }

    @Override
    public Mono<Roles> updateRole(String idRole, Roles role) {
        role.setId(idRole);
        return rolesRepository.save(role);
    }

    @Override
    public Flux<Autority> findRolesByAutorities(Roles role) {
        return rolesRepository.findRolesByAutorities(role);
    }
}
