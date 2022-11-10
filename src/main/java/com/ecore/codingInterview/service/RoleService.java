package com.ecore.codingInterview.service;

import com.ecore.codingInterview.dto.RoleRequestDTO;
import com.ecore.codingInterview.dto.RoleResponseDTO;
import com.ecore.codingInterview.dto.UserRolesRequestDTO;

import java.util.List;

public interface RoleService {

    RoleResponseDTO create(RoleRequestDTO requestDTO);

    List<RoleResponseDTO> getRoles();

    String assignRoleTeamMember(UserRolesRequestDTO requestDTO);

}
