package com.ecore.codingInterview.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CustomException extends RuntimeException {

    private final String message;


}
