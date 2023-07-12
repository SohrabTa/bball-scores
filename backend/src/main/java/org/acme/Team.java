package org.acme;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "Team")
@Table(name = "teams")
@Getter
@Setter
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    @Column(name = "team_name")
    private String name;

    @Column(name = "team_wins")
    private int wins;

    @Column(name = "team_losses")
    private int losses;

    @Column(name = "team_points_scored")
    private int pointsScored;

    @Column(name = "team_points_allowed")
    private int pointsAllowed;

    public static Team createTeamByName (String name) {
        Team team = new Team();
        team.name = name;
        team.wins = 0;
        team.losses = 0;
        team.pointsScored = 0;
        team.pointsAllowed = 0;
        return team;
    }
}
