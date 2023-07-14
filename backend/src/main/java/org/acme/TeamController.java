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

    @Path("/teams")
    @GET
    public List<Team> list() {
        return teamRepository.list();
    }

    @Path("/team")
    @POST
    public Team addTeam(Team team) {
        teamRepository.add(team);
        return team;
    }

    @Path("/team/{id}")
    @DELETE
    public void deleteTeam(@PathParam("id") Long team_id) {
        teamRepository.delete(team_id);
    }

    @Path("/team")
    @PUT
    public void updateTeam(Team team) {
        teamRepository.update(team);
    }

}