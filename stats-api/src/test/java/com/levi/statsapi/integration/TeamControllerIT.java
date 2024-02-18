package com.levi.statsapi.integration;

import com.levi.statsapi.domain.Team;
import com.levi.statsapi.dto.Team.TeamRequestDTO;
import com.levi.statsapi.repository.TeamRepository;
import com.levi.statsapi.util.Team.TeamCreator;
import com.levi.statsapi.util.Team.TeamPostCreator;
import com.levi.statsapi.wrapper.PageableResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TeamControllerIT {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private TeamRepository teamRepository;

    @Test
    @DisplayName("should return list of team inside a page object")
    void list_ReturnsListOfTeamsInsidePageObject_WhenSuccessful() {
        Team savedTeam = teamRepository.save(TeamCreator.createTeamOneToBeSaved());

        String expectedName = savedTeam.getName();

        PageableResponse<Team> teamPage = testRestTemplate.exchange("/api/team", HttpMethod.GET, null,
                new ParameterizedTypeReference<PageableResponse<Team>>() {
                }).getBody();

        //assertions
        Assertions.assertThat(teamPage).isNotNull();
        Assertions.assertThat(teamPage.toList())
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(teamPage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("should return team object")
    void findById_ReturnTeam_WhenSuccessful() {
        Team savedTeam = teamRepository.save(TeamCreator.createTeamOneToBeSaved());
        Long expectedId = savedTeam.getId();

        Team team = testRestTemplate.getForObject("/api/team/{teamId}", Team.class, expectedId);

        //assertions
        Assertions.assertThat(team).isNotNull();
        Assertions.assertThat(team.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("should return a saved team object")
    void save_ReturnsTeam_WhenSuccessful() {
        TeamRequestDTO teamPostRequest = TeamPostCreator.createTeamPostRequest();

        ResponseEntity<Team> teamResponseEntity = testRestTemplate
                .postForEntity("/api/team", teamPostRequest, Team.class);

        //assertions
        Assertions.assertThat(teamResponseEntity).isNotNull();
        Assertions.assertThat(teamResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(teamResponseEntity.getBody()).isNotNull();
        Assertions.assertThat(teamResponseEntity.getBody().getId()).isNotNull();
    }

    @Test
    @DisplayName("updates team when successful")
    void update_ReturnsUpdatedTeam_WhenSuccessful() {
        Team savedTeam = teamRepository.save(TeamCreator.createTeamOneToBeSaved());
        savedTeam.setName("new name");

        ResponseEntity<Void> teamResponseEntity = testRestTemplate
                .exchange("/api/team/update", HttpMethod.PUT, new HttpEntity<>(savedTeam), Void.class);

        //assertions
        Assertions.assertThat(teamResponseEntity).isNotNull();
        Assertions.assertThat(teamResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("removes team when successful")
    void delete_RemovesTeam_WhenSuccessful() {
        Team savedTeam = teamRepository.save(TeamCreator.createTeamOneToBeSaved());

        ResponseEntity<Void> teamResponseEntity = testRestTemplate
                .exchange("/api/team/{teamId}", HttpMethod.DELETE, null, Void.class, savedTeam.getId());

        //assertions
        Assertions.assertThat(teamResponseEntity).isNotNull();
        Assertions.assertThat(teamResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
