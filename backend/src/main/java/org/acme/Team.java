package org.acme;

import jakarta.persistence.*;

@Entity(name = "Team")
@Table(name = "teams")
public class Team {

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

    public void setPointsScored(int pointsScored) {
        this.pointsScored = pointsScored;
    }

    public int getPointsScored() {
        return this.pointsScored;
    }

    public void setPointsAllowed(int pointsAllowed) {
        this.pointsAllowed = pointsAllowed;
    }

    public int getPointsAllowed() {
        return this.pointsAllowed;
    }

}
