package com.ecore.codingInterview.controller;

import com.ecore.codingInterview.dto.RoleRequestDTO;
import com.ecore.codingInterview.dto.RoleResponseDTO;
import com.ecore.codingInterview.dto.UserRolesRequestDTO;
import com.ecore.codingInterview.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    private ResponseEntity<RoleResponseDTO> create(@Valid @RequestBody RoleRequestDTO requestDTO){

      var responseDTO = roleService.create(requestDTO);

      return ResponseEntity.ok(responseDTO);

    }

    @GetMapping("/all")
    private ResponseEntity<List<RoleResponseDTO>> getRoles(){

        var roles = roleService.getRoles();

        if(roles.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(roles);

    }

    @PostMapping("/user")
    private ResponseEntity<String> assignRoleTeamMember(@RequestBody @Validated UserRolesRequestDTO requestDTO){

        var role = roleService.assignRoleTeamMember(requestDTO);

        return ResponseEntity.ok(role);

    }

}
