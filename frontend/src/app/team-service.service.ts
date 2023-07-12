import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Team } from './team-table/team-table.component';

@Injectable({
  providedIn: 'root'
})
export class TeamService {
  private apiUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  getTeams(): Observable<Team[]> {
    const url = `${this.apiUrl}/teams`;
    return this.http.get<Team[]>(url);
  }

  addTeam(team: Partial<Team>): Observable<Team> {
    const url = `${this.apiUrl}/team`;
    return this.http.post<Team>(url, team);
  }

  deleteTeam(teamId: number) {
    const url = `${this.apiUrl}/team/${teamId}`;
    return this.http.delete<Team>(url);
  }

  updateTeam(team: Partial<Team>): Observable<Team> {
    const url = `${this.apiUrl}/team`;
    return this.http.put<Team>(url, team);
  }
}
