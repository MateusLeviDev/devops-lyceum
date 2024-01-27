package com.levi.statsapi.service.impl;

import com.levi.statsapi.domain.Match;
import com.levi.statsapi.domain.Team;
import com.levi.statsapi.dto.Match.MatchRequestDTO;
import com.levi.statsapi.repository.MatchRepository;
import com.levi.statsapi.repository.TeamRepository;
import com.levi.statsapi.service.MatchService;
import com.levi.statsapi.service.TeamService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;

@Service
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;
    private final ModelMapper modelMapper;
    private final TeamService teamService;

    public MatchServiceImpl(MatchRepository matchRepository, TeamRepository teamRepository, ModelMapper modelMapper, TeamService teamService) {
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
        this.modelMapper = modelMapper;
        this.teamService = teamService;
    }

    @Override
    public Match getByIdOrThrowBadRequestException(Long matchId) {
        return matchRepository.findById(matchId).orElseThrow(() -> new RuntimeException("ERROR_"));
    }

    @Override
    public List<Match> getAllMatchNonPageable() {
        return matchRepository.findAll();
    }

    @Override
    public Page<Match> getAllPageable(Pageable pageable) {
        return matchRepository.findAll(pageable);
    }

    @Override
    public Match registerMatch(MatchRequestDTO matchRequestDTO) {
        if (matchRequestDTO.getTeamOneId().equals(matchRequestDTO.getTeamTwoId()))
            throw new RuntimeException("ERROR_2");

        matchRequestDTO.setDate(LocalDate.now());

        Team teamOne = teamRepository.findById(matchRequestDTO.getTeamOneId()).orElseThrow(() -> new IllegalArgumentException("Time 1 não encontrado"));
        Team teamTwo = teamRepository.findById(matchRequestDTO.getTeamTwoId()).orElseThrow(() -> new IllegalArgumentException("Time 2 não encontrado"));
        Team supportedTeam = teamRepository.findById(matchRequestDTO.getSupportedTeamId()).orElseThrow(() -> new IllegalArgumentException("Time de torcida não encontrado"));

        return matchRepository.save(modelMapper.map(matchRequestDTO, Match.class));
    }

    @Override
    public void updateMatch(MatchRequestDTO matchRequestDTO) {
        Match savedMatchObject = this.getByIdOrThrowBadRequestException(matchRequestDTO.getId());

        Match newMatch = modelMapper.map(matchRequestDTO, Match.class);
        newMatch.setId(savedMatchObject.getId());
        newMatch.setDate(matchRequestDTO.getDate() != null ? matchRequestDTO.getDate() : savedMatchObject.getDate());

        setTeamIfNotNull(matchRequestDTO.getTeamOneId(), newMatch::setTeamOne);
        setTeamIfNotNull(matchRequestDTO.getTeamTwoId(), newMatch::setTeamTwo);
        setTeamIfNotNull(matchRequestDTO.getSupportedTeamId(), newMatch::setSupportedTeam);

        newMatch.setScoreTeamOne(matchRequestDTO.getScoreTeamOne() != null ? matchRequestDTO.getScoreTeamOne() : savedMatchObject.getScoreTeamOne());
        newMatch.setScoreTeamTwo(matchRequestDTO.getScoreTeamTwo() != null ? matchRequestDTO.getScoreTeamTwo() : savedMatchObject.getScoreTeamTwo());

        matchRepository.save(newMatch);
    }

    @Override
    public void deleteMatch(Long matchId) {

    }

    //SUPPORT METHODS *______________________________________*

    private void setTeamIfNotNull(Long teamId, Consumer<Team> teamSetter) {
        if (teamId != null) {
            Team team = teamService.getTeamById(teamId);
            teamSetter.accept(team);
        }
    }
}