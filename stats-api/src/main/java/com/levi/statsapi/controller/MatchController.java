package com.levi.statsapi.controller;

import com.levi.statsapi.domain.Match;
import com.levi.statsapi.dto.Match.MatchResponseDTO;
import com.levi.statsapi.service.MatchService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/match")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService, ModelMapper modelMapper) {
        this.matchService = matchService;
    }

    @GetMapping("/test")
    public String testEndPoint() {
        return "Actuator test";
    }

    @GetMapping
    public ResponseEntity<Page<MatchResponseDTO>> getAllMatchesPageable(@PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 15) Pageable pageable) {
        Page<Match> allPageable = this.matchService.getAllPageable(pageable);
        return ResponseEntity.ok(MatchResponseDTO.convert(allPageable));
    }

    @GetMapping("/{matchId}")
    public ResponseEntity<Match> getMatchById(@PathVariable Long matchId) {
        return ResponseEntity.ok(this.matchService.getByIdOrThrowBadRequestException(matchId));
    }
}
