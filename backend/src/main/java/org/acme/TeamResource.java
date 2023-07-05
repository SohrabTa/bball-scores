package org.acme;

import java.util.List;

import jakarta.inject.Inject;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/teams")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TeamResource {

    @Inject
    TeamRepository teamRepository;

    @GET
    public List<TeamEntity> list() {
        return teamRepository.list();
    }

    @GET
    @Path("/{id}")
    public TeamEntity get(String name) {
        return teamRepository.findByName(name);
    }

}
