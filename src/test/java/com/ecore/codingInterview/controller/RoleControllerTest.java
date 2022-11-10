package com.ecore.codingInterview.controller;

import com.ecore.codingInterview.builder.MembershipBuilder;
import com.ecore.codingInterview.builder.RoleBuilder;
import com.ecore.codingInterview.dto.MembershipResponseDTO;
import com.ecore.codingInterview.dto.RoleResponseDTO;
import com.ecore.codingInterview.service.RoleService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = RoleController.class)
public class RoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoleService roleService;


    @Test
    void testMethodCreate() throws Exception {

        var requestDTO = RoleBuilder.createRoleRequest();

        var responseDTO = RoleBuilder.createRoleResponse();

        when(roleService.create(any())).thenReturn(responseDTO);

        Gson gson = new Gson();

        this.mockMvc
                .perform(
                        post("/v1/roles")
                                .content(gson.toJson(requestDTO))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("teste")));

    }

    @Test
    void testMethodGetRoles() throws Exception {

        var responseDTO = RoleBuilder.createRoleResponse();
        List<RoleResponseDTO> responseDTOList = new ArrayList<>();
        responseDTOList.add(responseDTO);

        when(roleService.getRoles()).thenReturn(responseDTOList);

        this.mockMvc
                .perform(
                        get("/v1/roles/all")
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("123123")));

    }

    @Test
    void testMethodAssignRoleTeamMember() throws Exception {

        var requestDTO = RoleBuilder.createUserRolesRequest();

        var responseDTO = "created";

        when(roleService.assignRoleTeamMember(any())).thenReturn(responseDTO);

        Gson gson = new Gson();

        this.mockMvc
                .perform(
                        post("/v1/roles/user")
                                .content(gson.toJson(requestDTO))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("created")));

    }
}
