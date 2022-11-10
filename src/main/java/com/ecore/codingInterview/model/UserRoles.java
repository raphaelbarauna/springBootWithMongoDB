package com.ecore.codingInterview.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "userRoles")
public class UserRoles {

    @Id
    private String id;
    private User user;
    private Role role;

}
