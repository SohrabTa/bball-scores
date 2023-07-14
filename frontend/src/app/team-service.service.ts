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
    const url = `${environment.apiUrl}/teams`;
    return this.http.get<Team[]>(url);
  }

  addTeam(team: Partial<Team>): Observable<Team> {
    const url = `${environment.apiUrl}/team`;
    return this.http.post<Team>(url, team);
  }

  deleteTeam(teamId: number): Observable<Team> {
    const url = `${environment.apiUrl}/team/${teamId}`;
    return this.http.delete<Team>(url);
  }

  updateTeam(team: Partial<Team>): Observable<Team> {
    const url = `${environment.apiUrl}/team`;
    return this.http.put<Team>(url, team);
  }
}
