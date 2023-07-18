import { Component, OnInit } from '@angular/core';
import { TeamService } from '../team-service.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';

import { AddTeamDialogComponent } from '../add-team-dialog/add-team-dialog.component';

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
  dataSource!: MatTableDataSource<Team>;
  newTeam: Partial<Team> = {}; // Partial type for the new team form
  displayedColumns: string[] = ['Name', 'Wins', 'Losses', 'Points Scored', 'Points Allowed', 'Point Differential', 'Actions'];

  constructor(private teamService: TeamService, private dialog: MatDialog) {}

  ngOnInit() {
    this.dataSource = new MatTableDataSource<Team>();
    this.fetchTeams();
  }

  fetchTeams() {
    this.teamService.getTeams().subscribe({
      next: (teams: Team[]) => {
        this.sortTeams(teams);
        this.dataSource = new MatTableDataSource<Team>(teams);
      },
      error: (error: any) => {
        console.error('Failed to fetch teams:', error);
      }
    });
  }

  openAddTeamDialog() {
    const dialogRef: MatDialogRef<AddTeamDialogComponent> = this.dialog.open(AddTeamDialogComponent, {
      width: '400px'
    });
  
    dialogRef.afterClosed().subscribe({
      next: (newTeam: Team) => {
        if (newTeam) {
          this.teamService.addTeam(newTeam).subscribe((team: Team) => {
            this.dataSource.data.push(team);
            this.fetchTeams();
          });
        }
      },
      error: (error: any) => {
        console.error('Failed to add team:', error);
      }
    })
  }
  
  addTeam() {
    this.teamService.addTeam(this.newTeam).subscribe({
      next: (team: Team) => {
        this.dataSource.data.push(team);
        this.fetchTeams();
      },
      error: (error: any) => {
        console.error('Failed to add team:', error);
      }
    })
  }

  deleteTeam(team: Team) {
    this.teamService.deleteTeam(team.id).subscribe({
      next: () => {
        this.fetchTeams();
      },
      error: (error: any) => {
        console.error('Failed to delete team:', error);
      }
    });
  }

  sortTeams(teams: Team[]) {
    teams.sort((a, b) => {
      // Point differential is only relevant when the wins and losses of the teams are the same
      if (a.wins == b.wins && a.losses == b.losses) {
        // Sort by point differential in descending order
        const differentialA = a.pointsScored - a.pointsAllowed;
        const differentialB = b.pointsScored - b.pointsAllowed;
        if (differentialA > differentialB) {
          return -1;
        }  
        else if (differentialA < differentialB) {
          return 1;
        }
      }
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
        this.fetchTeams();
      },
      error : (error: any) => {
        console.error('Failed to save changes:', error);
      }
    });
  }
}
