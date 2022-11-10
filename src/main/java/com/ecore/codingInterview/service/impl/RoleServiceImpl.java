package com.ecore.codingInterview.service.impl;

import com.ecore.codingInterview.dto.RoleRequestDTO;
import com.ecore.codingInterview.dto.RoleResponseDTO;
import com.ecore.codingInterview.dto.UserRolesRequestDTO;
import com.ecore.codingInterview.exception.CustomException;
import com.ecore.codingInterview.model.Role;
import com.ecore.codingInterview.model.User;
import com.ecore.codingInterview.model.UserRoles;
import com.ecore.codingInterview.repository.RoleRepository;
import com.ecore.codingInterview.repository.UserRolesRepository;
import com.ecore.codingInterview.service.RoleService;
import com.ecore.codingInterview.service.UserService;
import com.ecore.codingInterview.service.impl.utils.ServiceUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;


@Service
@Log4j2
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRolesRepository userRolesRepository;

    @Autowired
    private UserService userService;

    public RoleResponseDTO create(RoleRequestDTO roleRequestDto){

        return roleRepository.save(new Role().createRoleFromRequest(roleRequestDto))
                .map(role1 -> new RoleResponseDTO(role1.getRoleId(), role1.getDescription()))
                .onErrorMap(original -> new CustomException(original.getMessage()))
                .doOnError(error -> log.error("Error to save the new Role"))
                .log()
                .block();

    }

    public List<RoleResponseDTO> getRoles(){

         var roleFlux =  roleRepository.findAllByIsActive(Boolean.TRUE)
                 .onErrorMap(original -> new CustomException(original.getMessage()))
                 .doOnError(error -> log.error("Error to get roles."))
                 .log();

         return new RoleResponseDTO().createRoleResponseList(roleFlux.collectList().block());

    }

    public String assignRoleTeamMember(UserRolesRequestDTO roleRequestDto){

        var role = ServiceUtils.validateRole(roleRequestDto.getRoleId());

        var user = userService.validateUser(roleRequestDto.getUserId());

        return userRolesRepository.save(new UserRoles(UUID.randomUUID().toString(), user, role))
                .map(m -> m.getUser() + " has been associated with role "+ m.getRole())
                .onErrorMap(original -> new CustomException(original.getMessage()))
                .doOnError(error -> log.error("Error when associating a role to the user"))
                .log()
                .block();

    }

}
