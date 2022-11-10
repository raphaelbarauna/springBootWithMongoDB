package com.ecore.codingInterview.dto;

import com.ecore.codingInterview.enums.RoleEnum;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MembershipRolesRequestDTO {

    @NotEmpty(message = "role description must not be empty")
    @NotNull(message = "role description must not be null")
    private List<String> membershipIds;

    private String roleId;


    public void setRoleId(String roleId) {
        if(roleId.isEmpty() || roleId.isBlank())
            this.roleId = RoleEnum.DEVELOPER.getRoleId();
        else
            this.roleId = roleId;
    }


}
