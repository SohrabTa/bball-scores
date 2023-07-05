package org.acme;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TeamRepository implements PanacheRepository<TeamEntity> {

    public TeamEntity findByName(String name) {
        return find("name", name).firstResult();
    }

    public void addTeam(TeamEntity team) {
        persist(team);
    }

    public void deleteTeam(TeamEntity team) {
        delete("team", team);
    }

    public List<TeamEntity> list() {
        return listAll();
    }
    
}
