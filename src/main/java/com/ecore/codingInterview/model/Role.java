package com.ecore.codingInterview.model;

import com.ecore.codingInterview.dto.RoleRequestDTO;
import com.ecore.codingInterview.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Document(collection = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Role {

    @Id
    private String roleId;
    @NotNull(message = "description must not be null.")
    @NotEmpty(message = "description must not be empty.")
    @NotBlank(message = "description must not be blank.")
    private String description;
    private Boolean isActive;
    private LocalDateTime createdAt;


    public Role createRoleFromRequest(RoleRequestDTO requestDTO){

        return Role.builder()
                .roleId(UUID.randomUUID().toString())
                .description(requestDTO.getDescription())
                .isActive(Boolean.TRUE)
                .createdAt(LocalDateTime.now())
                .build();

    }


}
