package org.acme;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TeamResource {

    @Inject
    TeamRepository teamRepository;

    @Path("/get_teams")
    @GET
    public List<Team> list() {
        return teamRepository.list();
    }

    @Path("/add_team")
    @POST
    @Transactional
    public Team addTeam(Team team) {
        teamRepository.addTeam(team);
        return team;
    }

    @Path("/delete_team")
    @POST
    @Transactional
    public void deleteTeam(Long team_id) {
        teamRepository.deleteTeam(team_id);
    }

    @Path("/update_team")
    @POST
    @Transactional
    public Team updateTeam(Team team) {
        Team existingTeam = teamRepository.findById(team.getId());
        if (existingTeam != null) {
            existingTeam.setName(team.getName());
            existingTeam.setWins(team.getWins());
            existingTeam.setLosses(team.getLosses());
            existingTeam.setPointsScored(team.getPointsScored());
            existingTeam.setPointsAllowed(team.getPointsAllowed());
            return existingTeam;
        }
        throw new NotFoundException("Team not found");
    }

}
