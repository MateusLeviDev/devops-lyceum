package com.levi.statsapi.dto.Match;

import com.levi.statsapi.domain.Match;
import com.levi.statsapi.domain.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchResponseDTO {

    private Long id;
    private LocalDateTime date;
    private Integer scoreTeamOne;
    private Integer scoreTeamTwo;
    private Team teamOne;
    private Team teamTwo;
    private Team supportedTeam;

}
