package com.ecore.codingInterview.builder;

import com.ecore.codingInterview.dto.RoleRequestDTO;
import com.ecore.codingInterview.dto.RoleResponseDTO;
import com.ecore.codingInterview.dto.UserRolesRequestDTO;
import com.ecore.codingInterview.model.Role;
import com.ecore.codingInterview.model.User;
import com.ecore.codingInterview.model.UserRoles;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public class RoleBuilder {

    public static RoleRequestDTO createRoleRequest(){

        return RoleRequestDTO.builder()
                .description("teste")
                .build();

    }

    public static RoleResponseDTO createRoleResponse(){

        return RoleResponseDTO.builder()
                .roleId("123123")
                .description("teste")
                .build();

    }

    public static Mono<Role> createMonoRole(){
        var role = new Role("teste", "teste", Boolean.TRUE, LocalDateTime.now());

        return Mono.fromCallable(() -> role);

    }

    public static Flux<Role> createFluxWithRoles(){

        var role = new Role("teste", "teste", Boolean.TRUE, LocalDateTime.now());
        var role1 = new Role("teste1", "teste1", Boolean.TRUE, LocalDateTime.now());

        return Flux.just(role, role1);

    }

    public static UserRolesRequestDTO createUserRolesRequest(){

        return UserRolesRequestDTO.builder()
                .roleId("123fdhfudsh")
                .userId("13456")
                .build();

    }

    public static User createUser(){

        return User.builder()
                .id("13456")
                .firstName("teste")
                .lastName("tester")
                .displayName("testetester")
                .build();

    }

    public static Mono<UserRoles> createMonoUserRole(){

        var role = new UserRoles("123456", createUser(), createMonoRole().block());

        return Mono.fromCallable(() -> role);

    }


}
