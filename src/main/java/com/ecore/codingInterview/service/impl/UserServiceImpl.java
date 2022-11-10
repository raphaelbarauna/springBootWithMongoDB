package com.ecore.codingInterview.service.impl;

import com.ecore.codingInterview.exception.CustomException;
import com.ecore.codingInterview.model.User;
import com.ecore.codingInterview.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
@Log4j2
public class UserServiceImpl implements UserService {

    private final WebClient webClient;

    public UserServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://cgjresszgg.execute-api.eu-west-1.amazonaws.com").build();
    }


    public User validateUser(String useId){

        User user = webClient.get()
                   .uri("/users/{id}", useId)
                   .retrieve()
                   .bodyToMono(User.class).block();

        if(user == null)
            throw new CustomException("User id not found");

        return user;
    }
}
