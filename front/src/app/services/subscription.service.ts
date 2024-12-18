import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Response } from '../interfaces/response.interface';

@Injectable({
  providedIn: 'root'
})
export class SubscriptionService {

  private readonly apiUrl = 'http://localhost:8080/api/topic';

  constructor(private http: HttpClient) {}

  subscribe(id: number): Observable<Response> {
    return this.http.post<Response>(`${this.apiUrl}/${id}/sub`, {});
  }
}
