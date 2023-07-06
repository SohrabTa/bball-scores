package org.acme;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TeamRepository implements PanacheRepository<Team> {

    public void addTeam(Team team) {
        persist(team);
    }

    public void deleteTeam(Team team) {
        delete(team);
    }

    public List<Team> list() {
        return listAll();
    }
    
}
