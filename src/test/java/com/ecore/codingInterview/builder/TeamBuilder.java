package com.ecore.codingInterview.builder;

import com.ecore.codingInterview.model.Team;

public class TeamBuilder {

    public static Team createTeam(){

        return Team.builder()
                .id("123456")
                .name("teste")
                .build();

    }
}
