package org.acme;

import java.util.List;

import com.oracle.svm.core.annotate.Inject;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/teams")
public class TeamResource {

    @Inject
    TeamRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Team> list() {
        return repository.listAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void add(Team team) {
        repository.persist(team);
    }
}
