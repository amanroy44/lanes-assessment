import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Lane } from '../models/lane.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LaneService {

  private apiUrl = 'http://localhost:8080/api/lanes';

  constructor(private http: HttpClient) {}

  getAllLanes(): Observable<Lane[]> {
    return this.http.get<Lane[]>(this.apiUrl);
  }

  getLaneById(id: number): Observable<Lane> {
    return this.http.get<Lane>(`${this.apiUrl}/${id}`);
  }

  createLane(lane: Lane): Observable<Lane> {
    return this.http.post<Lane>(this.apiUrl, lane);
  }

  updateLane(id: number, lane: Lane): Observable<Lane> {
    return this.http.put<Lane>(`${this.apiUrl}/${id}`, lane);
  }

  deleteLane(id: number) {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}