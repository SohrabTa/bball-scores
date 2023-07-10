package org.acme;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class TeamRepository implements PanacheRepository<Team> {

    public Team findByName(String name) {
        for (Team team : listAll()) {
            if (team.getName().equals(name)) {
                return team;
            }
        }
        throw new NotFoundException("Team " + name + " not found.");
    }

    @Transactional
    public void add(Team team) {
        persist(team);
    }

    @Transactional
    public void delete(Long team_id) {
        Team team = findById(team_id);
        if (team != null) {
            delete(team);
        }
        else {
            throw new NotFoundException("Team with ID " + team_id + " not found.");
        }
    }

    public List<Team> list() {
        return listAll();
    }

    @Transactional
    public void update(Team team) {
        Team existingTeam = findById(team.getId());
        if (existingTeam != null) {
            existingTeam.setName(team.getName());
            existingTeam.setWins(team.getWins());
            existingTeam.setLosses(team.getLosses());
            existingTeam.setPointsScored(team.getPointsScored());
            existingTeam.setPointsAllowed(team.getPointsAllowed());
        }
        else {
            throw new NotFoundException("Team " + team.getName() + " not found.");
        }
    }
}
