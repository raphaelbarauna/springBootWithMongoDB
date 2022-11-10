package com.ecore.codingInterview.enums;

import com.ecore.codingInterview.model.Role;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum RoleEnum {

    DEVELOPER("123fdhfudsh", "Developer", Boolean.TRUE),
    PRODUCT_OWNER("124aas1233", "Product Owner", Boolean.TRUE),
    TESTER("5847isdfahd","Tester", Boolean.TRUE);


    private final String roleId;
    private final String description;
    private final Boolean isActive;

    RoleEnum(String roleId, String description, Boolean isActive) {
        this.roleId = roleId;
        this.description = description;
        this.isActive = isActive;
    }

    public static Boolean isDefaultRole(String roleId){

        return Arrays.stream(RoleEnum.values()).anyMatch(r -> r.getRoleId().equals(roleId));

    }

    public static Role createRoleFromEnum(String roleId){
        Role role = new Role();
        for(RoleEnum roleEnum : RoleEnum.values()){
            if(roleEnum.getRoleId().equals(roleId)){
                role.setRoleId(roleId);
                role.setDescription(roleEnum.getDescription());
                role.setIsActive(roleEnum.getIsActive());
            }
        }
        return role;
    }
}
