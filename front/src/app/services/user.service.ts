import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../interfaces/user.interface';
import { UserUpdateRequest } from '../interfaces/userUpdateRequest.interface';
import { Response } from '../interfaces/response.interface';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private readonly apiUrl = 'http://localhost:8080/api/me';

  constructor(private http: HttpClient) {}

  getProfile(): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/`);
  }

  update(userUpdateRequest: UserUpdateRequest): Observable<Response> {
    return this.http.put<Response>(`${this.apiUrl}/update`, userUpdateRequest);
  }

}
