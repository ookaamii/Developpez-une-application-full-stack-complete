import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Response } from '../interfaces/response.interface';
import { Topic } from '../interfaces/topic.interface';

@Injectable({
  providedIn: 'root'
})
export class SubscriptionService {

  private readonly apiUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  subscribe(id: number): Observable<Response> {
    return this.http.post<Response>(`${this.apiUrl}/topic/${id}/sub`, {});
  }

  unSubscribe(id: number): Observable<Response> {
    return this.http.post<Response>(`${this.apiUrl}/topic/${id}/unsubscribe`, {});
  }

  findAllByUser(): Observable<Topic[]> {
    return this.http.get<Topic[]>(`${this.apiUrl}/me/subscriptions`);
  }
}
