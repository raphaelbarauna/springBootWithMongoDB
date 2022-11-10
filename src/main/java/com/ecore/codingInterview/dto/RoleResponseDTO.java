package com.ecore.codingInterview.dto;

import com.ecore.codingInterview.enums.RoleEnum;
import com.ecore.codingInterview.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleResponseDTO {

    private String roleId;
    private String description;

    public List<RoleResponseDTO> createRoleResponseList(List<Role> roles){

        List<RoleResponseDTO> rolesList = new ArrayList<>();

        for(RoleEnum r: RoleEnum.values()){
            var role = new RoleResponseDTO(r.getRoleId(),r.getDescription());
            rolesList.add(role);
        }

        rolesList.addAll(roles.stream()
                .map(r -> new RoleResponseDTO(r.getRoleId(), r.getDescription()))
                .collect(Collectors.toList()));

        return rolesList;

    }

    public RoleResponseDTO createResponseFromRoleModel(Role role){

        return RoleResponseDTO.builder().roleId(role.getRoleId()).description(role.getDescription()).build();

    }

}
