package com.ecore.codingInterview.service;

import com.ecore.codingInterview.builder.RoleBuilder;
import com.ecore.codingInterview.model.Role;
import com.ecore.codingInterview.model.UserRoles;
import com.ecore.codingInterview.repository.RoleRepository;
import com.ecore.codingInterview.repository.UserRolesRepository;
import com.ecore.codingInterview.service.impl.RoleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

    @InjectMocks
    private RoleServiceImpl roleService;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserRolesRepository userRolesRepository;

    @Mock
    private UserService userService;

    @Test
    void testMethodCreate(){

        var monoRole = RoleBuilder.createMonoRole();

        var requestDto = RoleBuilder.createRoleRequest();

        when(roleRepository.save(any(Role.class))).thenReturn(monoRole);

        var response = roleService.create(requestDto);

        assertNotNull(response);

    }

    @Test
    void testMethodGetRoles(){

        var fluxRoles = RoleBuilder.createFluxWithRoles();

        when(roleRepository.findAllByIsActive(Boolean.TRUE)).thenReturn(fluxRoles);

        var response = roleService.getRoles();

        assertNotNull(response);

    }

    @Test
    void testMethodAssignRoleTeamMember(){

        var userRolesRequest = RoleBuilder.createUserRolesRequest();

        var user = RoleBuilder.createUser();

        when(userService.validateUser(userRolesRequest.getUserId())).thenReturn(user);

        when(userRolesRepository.save(any(UserRoles.class))).thenReturn(RoleBuilder.createMonoUserRole());

        var response = roleService.assignRoleTeamMember(userRolesRequest);

        assertNotNull(response);

    }

}
