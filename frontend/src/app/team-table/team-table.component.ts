import { Component, OnInit } from '@angular/core';
import { TeamService } from '../team-service.service';

export interface Team {
  id: number;
  name: string;
  wins: number;
  losses: number;
  pointsScored: number;
  pointsAllowed: number;
  editMode: boolean;
}

@Component({
  selector: 'app-team-table',
  templateUrl: './team-table.component.html',
  styleUrls: ['./team-table.component.css']
})

export class TeamTableComponent implements OnInit {
  teams: Team[] = [];
  newTeam: Partial<Team> = {}; // Partial type for the new team form

  constructor(private teamService: TeamService) {}

  ngOnInit() {
    this.fetchTeams();
  }

  fetchTeams() {
    this.teamService.getTeams().subscribe({
      next: (teams: Team[]) => {
        this.teams = teams;
        this.sortTeams();
      },
      error: (error: any) => {
        console.error('Failed to fetch teams:', error);
      }
    });
  }

  addTeam() {
    if (!this.newTeam.name) {
      confirm('Team name cannot be empty!')
      return
    }
    if (!this.newTeam.wins) {
      this.newTeam.wins = 0;
    }
    if (!this.newTeam.losses) {
      this.newTeam.losses = 0;
    }
    if (!this.newTeam.pointsScored) {
      this.newTeam.pointsScored = 0;
    }
    if (!this.newTeam.pointsAllowed) {
      this.newTeam.pointsAllowed = 0;
    }
    if (this.newTeam.name) {
      this.teamService.addTeam(this.newTeam).subscribe({
        next: (team: Team) => {
          this.teams.push(team);
          this.newTeam = {}; // Reset the form
          //this.fetchTeams();
        },
        error: (error: any) => {
          console.error('Failed to add team:', error);
        }
      });
      
    }
  }

  deleteTeam(team: Team) {
    this.teamService.deleteTeam(team.id).subscribe({
      next: () => {
        this.teams = this.teams.filter(t => t !== team);
        this.sortTeams();
      },
      error: (error: any) => {
        console.error('Failed to delete team:', error);
      }
    });
  }

  sortTeams() {
    this.teams.sort((a, b) => {
      // Sort by wins in descending order
      if (a.wins > b.wins) return -1;
      if (a.wins < b.wins) return 1;
      
      // Sort by losses in ascending order
      if (a.losses < b.losses) return -1;
      if (a.losses > b.losses) return 1;
      
      // Sort by point differential in descending order
      const diffA = a.pointsScored - a.pointsAllowed;
      const diffB = b.pointsScored - b.pointsAllowed;
      if (diffA > diffB) return -1;
      if (diffA < diffB) return 1;
      
      // If all sorting criteria are equal, maintain the original order
      return 0;
    });
  }

  toggleEditMode(team: Team) {
    team.editMode = !team.editMode;
  }

  saveChanges(team: Team) {
    this.teamService.updateTeam(team).subscribe({
      next: (updatedTeam: Team) => {
        team.editMode = false;
        this.sortTeams();
      },
      error : (error: any) => {
        console.error('Failed to save changes:', error);
      }
    });
  }
}
