package com.levi.statsapi.dto.Match;

import com.levi.statsapi.domain.Team;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchRequestDTO {

    private Long id;
    private LocalDate date;
    private Integer scoreTeamOne;
    private Integer scoreTeamTwo;
    private Long teamOneId;
    private Long teamTwoId;
    private Long supportedTeamId;
}
