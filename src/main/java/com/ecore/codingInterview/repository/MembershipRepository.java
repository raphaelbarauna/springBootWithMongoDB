package com.ecore.codingInterview.repository;

import com.ecore.codingInterview.model.Membership;
import com.ecore.codingInterview.model.Role;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.List;

public interface MembershipRepository extends ReactiveMongoRepository<Membership, String> {


}
