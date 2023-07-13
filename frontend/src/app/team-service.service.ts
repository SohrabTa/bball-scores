import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Team } from './team-table/team-table.component';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TeamService {

  constructor(private http: HttpClient) {}

  getTeams(): Observable<Team[]> {
    const url = `${environment.apiUrl}/get_teams`;
    return this.http.get<Team[]>(url);
  }

  addTeam(team: Partial<Team>): Observable<Team> {
    const url = `${environment.apiUrl}/add_team`;
    return this.http.post<Team>(url, team);
  }

  deleteTeam(teamId: number): Observable<Team> {
    const url = `${environment.apiUrl}/delete_team`;
    return this.http.post<Team>(url, teamId);
  }

  updateTeam(team: Partial<Team>): Observable<Team> {
    const url = `${environment.apiUrl}/update_team`;
    return this.http.post<Team>(url, team);
  }
}
