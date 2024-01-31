package com.levi.statsapi.util;

import com.levi.statsapi.domain.Match;

import java.time.LocalDate;

public class MatchCreator {

    public static Match createMatchToBeSaved() {
        return Match.builder()
                .id(1L)
                .date(LocalDate.now())
                .teamOne(TeamCreator.createTeamOneToBeSaved())
                .teamTwo(TeamCreator.createTeamTwoToBeSaved())
                .scoreTeamOne(1)
                .scoreTeamTwo(2)
                .supportedTeam(TeamCreator.createTeamOneToBeSaved())
                .build();
    }

    public static Match createValidMatch() {
        return Match.builder()
                .id(1L)
                .date(LocalDate.now())
                .teamOne(TeamCreator.createTeamOneToBeSaved())
                .teamTwo(TeamCreator.createTeamTwoToBeSaved())
                .scoreTeamOne(1)
                .scoreTeamTwo(2)
                .supportedTeam(TeamCreator.createTeamOneToBeSaved())
                .build();
    }

    public static Match createValidUpdatedMatch() {
        return Match.builder()
                .id(1L)
                .date(LocalDate.now())
                .teamOne(TeamCreator.createTeamOneToBeSaved())
                .teamTwo(TeamCreator.createTeamTwoToBeSaved())
                .scoreTeamOne(1)
                .scoreTeamTwo(2)
                .supportedTeam(TeamCreator.createTeamOneToBeSaved())
                .build();
    }
}
