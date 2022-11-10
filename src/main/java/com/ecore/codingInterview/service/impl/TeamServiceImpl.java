package com.ecore.codingInterview.service.impl;

import com.ecore.codingInterview.exception.CustomException;
import com.ecore.codingInterview.model.Team;
import com.ecore.codingInterview.service.TeamService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
@Log4j2
public class TeamServiceImpl implements TeamService {

    private final WebClient webClient;

    public TeamServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://cgjresszgg.execute-api.eu-west-1.amazonaws.com").build();
    }

    public Team validateTeam(String teamId){

        Team team = webClient.get()
                   .uri("/teams/{id}", teamId)
                   .retrieve()
                   .bodyToMono(Team.class).block();

        if(team == null)
            throw new CustomException("Team with id " + teamId + " not found.");

        return team;
    }
}
