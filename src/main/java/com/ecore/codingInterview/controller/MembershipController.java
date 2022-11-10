package com.ecore.codingInterview.controller;

import com.ecore.codingInterview.dto.MembershipRequestDTO;
import com.ecore.codingInterview.dto.MembershipResponseDTO;
import com.ecore.codingInterview.dto.MembershipRolesRequestDTO;
import com.ecore.codingInterview.service.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/membership")
public class MembershipController {

    @Autowired
    private MembershipService membershipService;

    @PostMapping
    private ResponseEntity<MembershipResponseDTO> create(@RequestBody @Validated MembershipRequestDTO requestDTO) throws Exception {

      var membershipResponseDTO = membershipService.create(requestDTO);

      return ResponseEntity.ok(membershipResponseDTO);

    }

    @PostMapping("/role")
    private ResponseEntity<List<MembershipResponseDTO>> saveMembershipsForARrole(@RequestBody @Validated MembershipRolesRequestDTO requestDTO) throws Exception {

        var membershipResponseDTO = membershipService.saveMembershipsForARole(requestDTO);

        return ResponseEntity.ok(membershipResponseDTO);

    }

    @PutMapping("/{id}")
    private ResponseEntity<MembershipResponseDTO> update(@PathVariable String id, @RequestBody @Validated MembershipRequestDTO requestDTO) throws Exception {

        var membershipResponseDTO = membershipService.update(requestDTO, id);

        if(membershipResponseDTO == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(membershipResponseDTO);

    }

    @GetMapping("/all")
    private ResponseEntity<List<MembershipResponseDTO>> getMemberships(){

        var responseDTOList = membershipService.getMemberships();

        if(responseDTOList.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(responseDTOList);

    }

}
