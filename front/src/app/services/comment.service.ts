import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CommentResponse } from '../interfaces/commentResponse.interface';
import { CommentRequest } from '../interfaces/commentRequest.interface';
import { Response } from '../interfaces/response.interface';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private readonly apiUrl = 'http://localhost:8080/api/post';

  constructor(private http: HttpClient) { }

  findAllByPost(id: number): Observable<CommentResponse[]> {
    return this.http.get<CommentResponse[]>(`${this.apiUrl}/${id}/comments/`);
  }

  create(id: number, comment: CommentRequest): Observable<Response> {
    return this.http.post<Response>(`${this.apiUrl}/${id}/comments/create`, comment);
  }

}
