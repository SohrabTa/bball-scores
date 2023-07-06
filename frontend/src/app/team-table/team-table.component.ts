import { Component, OnInit } from '@angular/core';
import { TeamService } from '../team-service.service';

export interface TeamEntity {
  id: number;
  name: string;
  wins: number;
  losses: number;
  points_scored: number;
  points_allowed: number;
}

@Component({
  selector: 'app-team-table',
  templateUrl: './team-table.component.html',
  styleUrls: ['./team-table.component.css']
})

export class TeamTableComponent implements OnInit {
  teams: TeamEntity[] = [];
  newTeam: Partial<TeamEntity> = {}; // Partial type for the new team form

  constructor(private teamService: TeamService) {}

  ngOnInit() {
    this.fetchTeams();
  }

  fetchTeams() {
    this.teamService.getTeams().subscribe(
      (teams: TeamEntity[]) => {
        this.teams = teams;
        this.sortTeams();
      },
      error => {
        console.error('Failed to fetch teams:', error);
      }
    );
  }
  

  sortTeams() {
    this.teams.sort((a, b) => {
      // Sort by wins (descending)
      if (a.wins > b.wins) {
        return -1;
      }
      if (a.wins < b.wins) {
        return 1;
      }

      // Sort by losses (ascending)
      if (a.losses < b.losses) {
        return -1;
      }
      if (a.losses > b.losses) {
        return 1;
      }

      // Sort by difference of points (scored - allowed) (descending)
      const aDifference = a.points_scored - a.points_allowed;
      const bDifference = b.points_scored - b.points_allowed;
      if (aDifference > bDifference) {
        return -1;
      }
      if (aDifference < bDifference) {
        return 1;
      }

      return 0;
    });
  }

  addTeam() {
    if (this.newTeam.name && this.newTeam.wins && this.newTeam.losses && this.newTeam.points_scored && this.newTeam.points_allowed) {
      this.teamService.addTeam(this.newTeam).subscribe(
        (team: TeamEntity) => {
          this.teams.push(team);
          this.sortTeams();
          this.newTeam = {}; // Reset the form
        },
        error => {
          console.error('Failed to add team:', error);
        }
      );
    }
  }

  deleteTeam(team: TeamEntity) {
    this.teamService.deleteTeam(team.id).subscribe(
      () => {
        this.teams = this.teams.filter(t => t !== team);
      },
      error => {
        console.error('Failed to delete team:', error);
      }
    );
  }
}
