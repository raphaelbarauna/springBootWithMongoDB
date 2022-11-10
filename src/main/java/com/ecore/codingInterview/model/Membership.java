package com.ecore.codingInterview.model;

import com.ecore.codingInterview.dto.MembershipRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "membership")
public class Membership {

    @Id
    private String membershipId;
    private String userId;
    private String teamId;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "roleId", nullable = false)
    private Role role;


    public static Membership createNewMembershipFromRequest(MembershipRequestDTO requestDTO, Role role, String id){

        if(id.isBlank() || id.isEmpty())
            return Membership.builder()
                    .membershipId(UUID.randomUUID().toString())
                    .userId(requestDTO.getUserId())
                    .teamId(requestDTO.getTeamId())
                    .role(role)
                    .build();
        else
            return Membership.builder()
                    .membershipId(id)
                    .userId(requestDTO.getUserId())
                    .teamId(requestDTO.getTeamId())
                    .role(role)
                    .build();

    }
}
