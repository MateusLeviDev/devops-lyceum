package com.levi.statsapi.dto.Match;

import com.levi.statsapi.domain.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchRequestDTO {

    private Long id;
    private LocalDate date;
    private Integer scoreTeamOne;
    private Integer scoreTeamTwo;
    private Long teamOneId;
    private Long teamTwoId;
    private Long supportedTeamId;
}
