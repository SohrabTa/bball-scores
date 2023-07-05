package org.acme;

import jakarta.persistence.*;

@Entity(name = "Team")
@Table(name = "teams")
public class TeamEntity {

    // attributes

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
    private int points_scored;

    @Column(name = "team_points_allowed")
    private int points_allowed;

    public TeamEntity() {

    }

    public TeamEntity(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getWins() {
        return this.wins;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getLosses() {
        return this.losses;
    }

    public void setPointsScored(int points_scored) {
        this.points_scored = points_scored;
    }

    public int getPointsScored() {
        return this.points_scored;
    }

    public void setPointsAllowed(int points_allowed) {
        this.points_allowed = points_allowed;
    }

    public int getPointsAllowed() {
        return this.points_allowed;
    }

}
