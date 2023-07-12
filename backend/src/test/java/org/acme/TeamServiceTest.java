package org.acme;

import io.quarkus.test.junit.QuarkusTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;
import jakarta.transaction.SystemException;
import jakarta.transaction.TransactionManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@QuarkusTest
@Transactional
public class TeamServiceTest {
    @Inject
    TeamRepository teamRepository;

    @Inject
    TransactionHelper transactionHelper;


    @Test
    public void testListTeamsEndpoint() {
        given()
            .when()
            .get("/api/teams")
            .then()
            .statusCode(200);
    }

    @Test
    public void testAddTeamEndpoint() throws IllegalStateException, SystemException {
        Team team = Team.createTeamByName("Test Add Team");
        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(team)
            .when()
            .post("/api/team")
            .then()
            .statusCode(200)
            .body("name", equalTo("Test Add Team"));

        // Verify the team is persisted in the repository
        assert teamRepository.findByName(team.getName()) != null;

    }

    @Test
    public void testDeleteTeamEndpoint() {
        // Add a team to the repository for deletion
        Team team = Team.createTeamByName("Test Delete Team");
        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(team)
            .when()
            .post("/api/team")
            .then()
            .statusCode(200)
            .body("name", equalTo("Test Delete Team"));

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(teamRepository.findByName(team.getName()).getId())
            .when()
            .post("/api/team")
            .then()
            .statusCode(204);

        // Verify the team is deleted from the repository
        // Assert that findByName throws a NotFoundException
        assertThrows(NotFoundException.class, () -> {
            teamRepository.findByName(team.getName());
        });
    }

    @Test
    public void testUpdateTeamEndpoint() {
        // Add a team to the repository for updating
        Team team = Team.createTeamByName("Test Update Team");
        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(team)
            .when()
            .post("/api/team")
            .then()
            .statusCode(200)
            .body("name", equalTo("Test Update Team"));
        
        team.setId(teamRepository.findByName(team.getName()).getId());

        // Update the team
        team.setName("Updated Test Update Team");
        team.setWins(5);
        team.setLosses(2);
        team.setPointsScored(100);
        team.setPointsAllowed(50);

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(team)
            .when()
            .post("/api/team")
            .then()
            .statusCode(204);

        // Verify the team is updated in the repository
        Team updatedTeam = transactionHelper.withNewTransaction(() -> teamRepository.findById(team.getId()));
        assert updatedTeam.getName().equals("Updated Test Update Team");
        assert updatedTeam.getWins() == 5;
        assert updatedTeam.getLosses() == 2;
        assert updatedTeam.getPointsScored() == 100;
        assert updatedTeam.getPointsAllowed() == 50;
    }
}