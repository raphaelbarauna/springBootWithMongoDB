package com.ecore.codingInterview.service.impl.utils;

import com.ecore.codingInterview.enums.RoleEnum;
import com.ecore.codingInterview.exception.CustomException;
import com.ecore.codingInterview.exception.RoleNotFoundException;
import com.ecore.codingInterview.model.Role;
import com.ecore.codingInterview.model.User;
import com.ecore.codingInterview.repository.RoleRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

@Log4j2
public class ServiceUtils {

    @Autowired
    private static RoleRepository roleRepository;

    public static Role validateRole(String roleId){
        Role role;
        if(RoleEnum.isDefaultRole(roleId)){
            role = RoleEnum.createRoleFromEnum(roleId);
        }else{
            var monoRole = roleRepository.findById(roleId);
            role = monoRole.block();
            if(monoRole.block() == null){
                log.error("Role with id {} was not found", roleId);
                throw new RoleNotFoundException("Role not found");
            }
        }
        return role;
    }

}
