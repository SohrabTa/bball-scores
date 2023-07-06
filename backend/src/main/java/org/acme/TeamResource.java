package org.acme;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/teams")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TeamResource {

    @Inject
    TeamRepository teamRepository;

    @GET
    public List<Team> list() {
        return teamRepository.list();
    }

    @Path("/add")
    @POST
    @Transactional
    public void addTeam(Team team) {
        teamRepository.addTeam(team);
    }

    @Path("/delete")
    @POST
    @Transactional
    public void deleteTeam(Team team) {
        teamRepository.deleteTeam(team);
    }

}
