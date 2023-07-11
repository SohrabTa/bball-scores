package org.acme;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TeamController {

    @Inject
    TeamRepository teamRepository;

    @Path("/get_teams")
    @GET
    public List<Team> list() {
        return teamRepository.list();
    }

    @Path("/add_team")
    @POST
    public Team addTeam(Team team) {
        teamRepository.add(team);
        return team;
    }

    @Path("/delete_team")
    @POST
    public void deleteTeam(Long team_id) {
        teamRepository.delete(team_id);
    }

    @Path("/update_team")
    @POST
    public void updateTeam(Team team) {
        teamRepository.update(team);
    }

}
