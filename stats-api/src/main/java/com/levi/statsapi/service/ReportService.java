package com.levi.statsapi.service;

import com.levi.statsapi.domain.Team;
import org.springframework.stereotype.Service;

@Service
public interface ReportService {

    Integer getMatchesQuantity();

    Integer getWinsQuantity();

    int getWinPercentage();

    Team getMostWatchedTeam();

    Integer getDaysWithoutWatching();

}
