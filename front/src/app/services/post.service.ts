import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PostResponse } from '../interfaces/postResponse.interface';
import { PostRequest } from '../interfaces/postRequest.interface';
import { Response } from '../interfaces/response.interface';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  private readonly apiUrl = 'http://localhost:8080/api/posts';

  constructor(private http: HttpClient) {}

  findAllByPost(): Observable<PostResponse[]> {
    return this.http.get<PostResponse[]>(`${this.apiUrl}/`);
  }

  getPost(id: number): Observable<PostResponse> {
    return this.http.get<PostResponse>(`${this.apiUrl}/${id}`);
  }

  create(postRequest: PostRequest): Observable<Response> {
    return this.http.post<Response>(`${this.apiUrl}/create`, postRequest);
  }  

}
