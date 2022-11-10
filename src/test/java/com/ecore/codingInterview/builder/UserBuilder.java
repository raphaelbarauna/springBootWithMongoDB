package com.ecore.codingInterview.builder;

import com.ecore.codingInterview.dto.UserRolesRequestDTO;
import com.ecore.codingInterview.model.Role;
import com.ecore.codingInterview.model.User;
import com.ecore.codingInterview.model.UserRoles;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public class UserBuilder {

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

}
