package com.ecore.codingInterview.controller;

import com.ecore.codingInterview.builder.MembershipBuilder;
import com.ecore.codingInterview.dto.MembershipResponseDTO;
import com.ecore.codingInterview.service.MembershipService;
import com.ecore.codingInterview.service.impl.MembershipServiceImpl;
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
@WebMvcTest(controllers = MembershipController.class)
public class MembershipControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MembershipService membershipService;

    @Test
    void testMethodCreate() throws Exception {

        var requestDTO = MembershipBuilder.createRequest();

        var responseDTO = MembershipBuilder.createResponse();

        when(membershipService.create(any())).thenReturn(responseDTO);

        Gson gson = new Gson();

        this.mockMvc
                .perform(
                    post("/v1/membership")
                        .content(gson.toJson(requestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("123fdhfudsh")));

    }

    @Test
    void testMethodSaveMembershipsForARrole() throws Exception {

        var requestDTO = MembershipBuilder.createMembershipRolesRequestDTO();

        var responseDTO = MembershipBuilder.createResponse();
        List<MembershipResponseDTO> responseDTOList = new ArrayList<>();
        responseDTOList.add(responseDTO);

        when(membershipService.saveMembershipsForARole(any())).thenReturn(responseDTOList);

        Gson gson = new Gson();

        this.mockMvc
                .perform(
                        post("/v1/membership/role")
                                .content(gson.toJson(requestDTO))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("123fdhfudsh")));

    }

    @Test
    void testMethodGetMemberships() throws Exception {

        var responseDTO = MembershipBuilder.createResponse();
        List<MembershipResponseDTO> responseDTOList = new ArrayList<>();
        responseDTOList.add(responseDTO);

        when(membershipService.getMemberships()).thenReturn(responseDTOList);

        this.mockMvc
                .perform(
                        get("/v1/membership/all")
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("123fdhfudsh")));

    }

}
