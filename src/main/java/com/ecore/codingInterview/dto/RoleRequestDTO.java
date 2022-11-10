package com.ecore.codingInterview.dto;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RoleRequestDTO {



    @NotEmpty(message = "role description must not be empty")
    @NotNull(message = "role description must not be null")
    @Valid
    @Pattern(regexp = "^[a-zA-Z]+")
    private String description;


}
