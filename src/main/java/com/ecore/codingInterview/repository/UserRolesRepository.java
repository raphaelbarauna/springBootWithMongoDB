package com.ecore.codingInterview.repository;

import com.ecore.codingInterview.model.UserRoles;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRolesRepository extends ReactiveMongoRepository<UserRoles, String> {


}
