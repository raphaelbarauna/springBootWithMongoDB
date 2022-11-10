package com.ecore.codingInterview.builder;

import com.ecore.codingInterview.dto.MembershipRequestDTO;
import com.ecore.codingInterview.dto.MembershipResponseDTO;
import com.ecore.codingInterview.dto.MembershipRolesRequestDTO;
import com.ecore.codingInterview.model.Membership;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class MembershipBuilder {

    public static MembershipRequestDTO createRequest(){

        return MembershipRequestDTO.builder()
                .roleId("123fdhfudsh")
                .teamId("teste")
                .userId("teste")
                .build();

    }

    public static MembershipResponseDTO createResponse(){

        return MembershipResponseDTO.builder()
                .membershipId("123fdhfudsh")
                .teamId("teste")
                .userId("teste")
                .role(RoleBuilder.createRoleResponse())
                .build();

    }

    public static MembershipRolesRequestDTO createMembershipRolesRequestDTO(){

        List<String> membershipIds = new ArrayList<>();
        membershipIds.add("12345");
        membershipIds.add("123456");

        return MembershipRolesRequestDTO.builder()
                .membershipIds(membershipIds)
                .roleId("123fdhfudsh")
                .build();

    }

    public static Mono<Membership> createMonoMembership(){
        var membership = new Membership("12345", "123456", "1234567", RoleBuilder.createMonoRole().block());

        return Mono.fromCallable(() -> membership);

    }

    public static List<Membership> createList(){

        List<Membership> list = new ArrayList<>();
        list.add(createMonoMembership().block());
        list.add(createMonoMembership().block());

        return list;

    }

    public static Flux<Membership> createFlux(){

        var membership = createMonoMembership().block();
        var membership1 = createMonoMembership().block();

        return Flux.just(membership, membership1);

    }


}
