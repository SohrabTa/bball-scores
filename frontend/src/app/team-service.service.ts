import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TeamEntity } from './team-table/team-table.component';

@Injectable({
  providedIn: 'root'
})
export class TeamService {
  private apiUrl = 'http://localhost:8080/teams';

  constructor(private http: HttpClient) {}

  getTeams(): Observable<TeamEntity[]> {
    return this.http.get<TeamEntity[]>(this.apiUrl);
  }

  addTeam(team: Partial<TeamEntity>): Observable<TeamEntity> {
    return this.http.post<TeamEntity>(this.apiUrl, team);
  }

  deleteTeam(teamId: number): Observable<void> {
    const url = `${this.apiUrl}/${teamId}`;
    return this.http.delete<void>(url);
  }
}
