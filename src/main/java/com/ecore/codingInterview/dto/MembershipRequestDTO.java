package com.ecore.codingInterview.dto;

import com.ecore.codingInterview.enums.RoleEnum;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MembershipRequestDTO {

    @NotEmpty(message = "role description must not be empty")
    @NotNull(message = "role description must not be null")
    private String userId;

    @NotEmpty(message = "role description must not be empty")
    @NotNull(message = "role description must not be null")
    private String teamId;

    private String roleId;


    public void setRoleId(String roleId) {
        if(roleId.isEmpty() || roleId.isBlank())
            this.roleId = RoleEnum.DEVELOPER.getRoleId();
        else
            this.roleId = roleId;
    }
}
