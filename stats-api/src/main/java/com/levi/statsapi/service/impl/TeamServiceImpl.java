package com.levi.statsapi.service.impl;

import com.levi.statsapi.domain.Team;
import com.levi.statsapi.repository.TeamRepository;
import com.levi.statsapi.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Team getTeamById(Long teamId) {
        return this.teamRepository.findById(teamId).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Error"));
    }
}
