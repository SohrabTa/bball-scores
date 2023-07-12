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

    public Team() {

    }

    public Team(String name) {
        this.name = name;
        this.wins = 0;
        this.losses = 0;
        this.pointsScored = 0;
        this.pointsAllowed = 0;
    }
}
