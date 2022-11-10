package com.ecore.codingInterview.dto;

import com.ecore.codingInterview.model.Membership;
import com.ecore.codingInterview.model.Role;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MembershipResponseDTO {

   private String membershipId;
   private String userId;
   private String teamId;
   private RoleResponseDTO role;

    public MembershipResponseDTO createResponseFromModel(Membership membership){

        return MembershipResponseDTO.builder()
                .membershipId(membership.getMembershipId())
                .userId(membership.getUserId())
                .teamId(membership.getTeamId())
                .role(new RoleResponseDTO().createResponseFromRoleModel(membership.getRole()))
                .build();

    }

    public List<MembershipResponseDTO> createListFromModel(List<Membership> memberships){

        return memberships.stream().map(m ->
                new MembershipResponseDTO(m.getMembershipId(), m.getUserId(), m.getTeamId(), new RoleResponseDTO().createResponseFromRoleModel(m.getRole())))
                .collect(Collectors.toList());
    }

}
