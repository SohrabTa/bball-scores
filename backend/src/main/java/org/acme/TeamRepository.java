package org.acme;

import java.util.List;
import java.util.Optional;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class TeamRepository implements PanacheRepository<Team> {

    public Team findByName(String name) {
        Optional<Team> teamOpt = listAll().stream().filter(team -> team.getName().equals(name)).findFirst();
        return teamOpt.orElseThrow(() -> new NotFoundException("Team " + name + " not found."));
    }

    @Transactional
    public void add(Team team) {
        persist(team);
    }

    @Transactional
    public void delete(Long team_id) {
        if (!deleteById(team_id)) {
            throw new NotFoundException("Team with ID " + team_id + " not found.");
        }
    }

    public List<Team> list() {
        Sort sort = Sort.descending("wins").and("losses");
        return listAll(sort);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void update(Team team) {
        Team existingTeam = findById(team.getId());
        if (existingTeam != null) {
            this.getEntityManager().merge(team);
        }
        else {
            throw new NotFoundException("Team " + team.getName() + " not found.");
        }
    }
}
