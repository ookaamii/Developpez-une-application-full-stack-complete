import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserRequest } from '../interfaces/userRequest.interface';
import { AuthResponse } from '../interfaces/authResponse.interface';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly apiUrl = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient) {}

  login(userRequest: UserRequest): Observable<AuthResponse> {
    console.log(userRequest);
    return this.http.post<AuthResponse>(`${this.apiUrl}/login`, userRequest);
  }

}
