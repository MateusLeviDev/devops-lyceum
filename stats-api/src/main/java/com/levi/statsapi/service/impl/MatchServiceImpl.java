package com.levi.statsapi.service.impl;

import com.levi.statsapi.domain.Match;
import com.levi.statsapi.domain.Team;
import com.levi.statsapi.dto.Match.MatchRequestDTO;
import com.levi.statsapi.repository.MatchRepository;
import com.levi.statsapi.repository.TeamRepository;
import com.levi.statsapi.service.MatchService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;
    private final ModelMapper modelMapper;

    public MatchServiceImpl(MatchRepository matchRepository, TeamRepository teamRepository, ModelMapper modelMapper) {
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
        this.modelMapper = modelMapper;
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

        matchRequestDTO.setDate(LocalDateTime.now());

        Team teamOne = teamRepository.findById(matchRequestDTO.getTeamOneId()).orElseThrow(() -> new IllegalArgumentException("Time 1 não encontrado"));
        Team teamTwo = teamRepository.findById(matchRequestDTO.getTeamTwoId()).orElseThrow(() -> new IllegalArgumentException("Time 2 não encontrado"));
        Team supportedTeam = teamRepository.findById(matchRequestDTO.getSupportedTeamId()).orElseThrow(() -> new IllegalArgumentException("Time de torcida não encontrado"));

        return matchRepository.save(modelMapper.map(matchRequestDTO, Match.class));
    }

    @Override
    public void updateMatch(MatchRequestDTO matchRequestDTO) {

    }

    @Override
    public void deleteMatch(Long matchId) {

    }
}
