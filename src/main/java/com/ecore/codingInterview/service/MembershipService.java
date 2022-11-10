package com.ecore.codingInterview.service;

import com.ecore.codingInterview.dto.*;

import java.util.List;

public interface MembershipService {

    MembershipResponseDTO create(MembershipRequestDTO requestDTO) throws Exception;

    MembershipResponseDTO update(MembershipRequestDTO requestDTO, String memberId) throws Exception;

    List<MembershipResponseDTO> saveMembershipsForARole(MembershipRolesRequestDTO requestDTO) throws Exception;

    List<MembershipResponseDTO> getMemberships();
}
