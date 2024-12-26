import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Topic } from '../interfaces/topic.interface';

@Injectable({
  providedIn: 'root'
})
export class TopicService {

  private readonly apiUrl = 'http://localhost:8080/api/topics';

  constructor(private http: HttpClient) {}

  findAll(): Observable<Topic[]> {
    return this.http.get<Topic[]>(`${this.apiUrl}/`);
  }

}
