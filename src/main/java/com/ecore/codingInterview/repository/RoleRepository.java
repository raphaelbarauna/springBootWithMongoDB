package com.ecore.codingInterview.repository;

import com.ecore.codingInterview.model.Role;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface RoleRepository extends ReactiveMongoRepository<Role, String> {


    Flux<Role> findAllByIsActive(Boolean isActive);


}
