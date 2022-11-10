package com.ecore.codingInterview.service;


import com.ecore.codingInterview.builder.MembershipBuilder;
import com.ecore.codingInterview.builder.TeamBuilder;
import com.ecore.codingInterview.builder.UserBuilder;
import com.ecore.codingInterview.model.Membership;
import com.ecore.codingInterview.repository.MembershipRepository;
import com.ecore.codingInterview.repository.RoleRepository;
import com.ecore.codingInterview.service.impl.MembershipServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MembershipServiceTest {

    @InjectMocks
    private MembershipServiceImpl membershipService;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private MembershipRepository membershipRepository;

    @Mock
    private UserService userService;

    @Mock
    private TeamService teamService;

    @Test
    void testMethodCreate(){

        var requestDTO = MembershipBuilder.createRequest();

        when(userService.validateUser(requestDTO.getUserId())).thenReturn(UserBuilder.createUser());

        when(teamService.validateTeam(requestDTO.getTeamId())).thenReturn(TeamBuilder.createTeam());

        when(membershipRepository.save(any(Membership.class))).thenReturn(MembershipBuilder.createMonoMembership());

        var response = membershipService.create(requestDTO);

        assertEquals("1234567", response.getTeamId());

    }

    @Test
    void testMethodUpdate(){

        var requestDTO = MembershipBuilder.createRequest();

        when(membershipRepository.save(any(Membership.class))).thenReturn(MembershipBuilder.createMonoMembership());

        var response = membershipService.update(requestDTO, "12345678");

        assertEquals("1234567", response.getTeamId());

    }

    @Test
    void testSaveMembershipsForARole(){

        var requestDTO = MembershipBuilder.createMembershipRolesRequestDTO();

        var membershipFlux = MembershipBuilder.createFlux();
        var list = MembershipBuilder.createList();

        when(membershipRepository.findAllById(requestDTO.getMembershipIds())).thenReturn(membershipFlux);
        when(membershipRepository.saveAll(anyList())).thenReturn(membershipFlux);

        var response = membershipService.saveMembershipsForARole(requestDTO);

        assertNotNull(response);

    }

    @Test
    void testGetMemberships(){

        var membershipFlux = MembershipBuilder.createFlux();

        when(membershipRepository.findAll()).thenReturn(membershipFlux);

        var response = membershipService.getMemberships();

        assertNotNull(response);

    }
}
