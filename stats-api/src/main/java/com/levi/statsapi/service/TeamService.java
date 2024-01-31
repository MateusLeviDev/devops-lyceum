package com.levi.statsapi.service;

import com.levi.statsapi.domain.Team;
import org.springframework.stereotype.Service;

@Service
public interface TeamService {

    Team getTeamById(Long id);
}
