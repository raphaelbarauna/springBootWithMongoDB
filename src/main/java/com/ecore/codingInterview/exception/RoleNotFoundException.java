package com.ecore.codingInterview.exception;

import lombok.Data;

@Data
public class RoleNotFoundException extends RuntimeException {

    private final String message;


}
