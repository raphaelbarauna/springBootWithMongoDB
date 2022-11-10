package com.ecore.codingInterview.service.impl;

import com.ecore.codingInterview.dto.*;
import com.ecore.codingInterview.exception.CustomException;
import com.ecore.codingInterview.model.Membership;
import com.ecore.codingInterview.model.Role;
import com.ecore.codingInterview.repository.MembershipRepository;
import com.ecore.codingInterview.repository.RoleRepository;
import com.ecore.codingInterview.service.MembershipService;
import com.ecore.codingInterview.service.TeamService;
import com.ecore.codingInterview.service.UserService;
import com.ecore.codingInterview.service.impl.utils.ServiceUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Log4j2
public class MembershipServiceImpl implements MembershipService {

    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TeamService teamService;


    /**
     * Method responsible for create a new Membership
     * @param requestDTO
     * @return MembershipResponseDTO
     */
    public MembershipResponseDTO create(MembershipRequestDTO requestDTO) {

        var role = ServiceUtils.validateRole(requestDTO.getRoleId());

        userService.validateUser(requestDTO.getUserId());

        teamService.validateTeam(requestDTO.getTeamId());

        var membership = Membership.createNewMembershipFromRequest(requestDTO, role, "");

        return membershipRepository.save(membership)
                .map(m -> new MembershipResponseDTO().createResponseFromModel(m))
                .onErrorMap(original -> new CustomException(original.getMessage()))
                .doOnError(error -> log.error("Error to save the new Membership"))
                .log("Membership saved.")
                .block();

    }

    /**
     * Method responsible for assign a role for a membership
     * @param requestDTO
     * @param memberId
     * @return MembershipResponseDTO
     */
    public MembershipResponseDTO update(MembershipRequestDTO requestDTO, String memberId) {

        var role = ServiceUtils.validateRole(requestDTO.getRoleId());

        var membership = Membership.createNewMembershipFromRequest(requestDTO, role, memberId);

       return membershipRepository.save(membership)
                .map(m -> new MembershipResponseDTO().createResponseFromModel(m))
                .onErrorMap(original -> new CustomException(original.getMessage()))
                .doOnError(error -> log.error("Error to save the new Membership"))
                .log("New role assigned to the membership.")
                .block();
    }

    /**
     * Method responsible for Look up memberships for a role
     * @param requestDTO
     * @return List<MembershipResponseDTO>
     */
    public List<MembershipResponseDTO> saveMembershipsForARole
            (MembershipRolesRequestDTO requestDTO) {

        Role role = ServiceUtils.validateRole(requestDTO.getRoleId());

        var memberShipList = membershipRepository.findAllById(requestDTO.getMembershipIds()).collectList().block();

        if(memberShipList == null)
            return new ArrayList<>();

        var newMembershipList = memberShipList.stream().map(m -> Membership.builder()
                .membershipId(m.getMembershipId())
                .teamId(m.getTeamId())
                .userId(m.getUserId())
                .role(role).build()).collect(Collectors.toList());

        final Flux<Membership> membershipFlux = membershipRepository.saveAll(newMembershipList)
                .onErrorMap(original -> new CustomException(original.getMessage()))
                .doOnError(error -> log.error("Error to assign an role to the membership."));;

        return new MembershipResponseDTO().createListFromModel(Objects.requireNonNull(membershipFlux.collectList().block()));

    }

    /**
     * Method responsible for retrieve Memberships
     * @return List<MembershipResponseDTO>
     */
    public List<MembershipResponseDTO> getMemberships(){

        var membershipFlux =  membershipRepository.findAll()
                .onErrorMap(original -> new CustomException(original.getMessage()))
                .doOnError(error -> log.error("Error to get roles."))
                .log("Returning memberships");

        return new MembershipResponseDTO().createListFromModel(Objects.requireNonNull(membershipFlux.collectList().block()));

    }

}
