package bourse.bourse.project.services.interfaces;

import bourse.bourse.project.entities.Autority;
import bourse.bourse.project.entities.Roles;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RolesService {

    Mono<Roles> saveRole(Roles role);

    Flux<Roles> getRoles();
    Mono<Roles> getRoleById(String id);

    Mono<Void> deleteRoleById(String id);
    Mono<Void> deleteRolesAll();

    Mono<Roles> updateRole(String idRole, Roles role);

    Flux<Autority> findRolesByAutorities(Roles role);
}
