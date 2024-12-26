import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { Router } from '@angular/router';
import { UserRequest } from '../interfaces/userRequest.interface';
import { AuthResponse } from '../interfaces/authResponse.interface';
import { RegisterRequest } from '../interfaces/registerRequest.interface';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly apiUrl = 'http://localhost:8080/api/auth';

   // BehaviorSubject pour suivre l'état d'authentification
   private isAuthenticatedSubject = new BehaviorSubject<boolean>(this.isAuthenticated());
   isAuthenticated$ = this.isAuthenticatedSubject.asObservable(); // Observable à exposer

  constructor(private http: HttpClient, private router: Router) {}

  login(userRequest: UserRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/login`, userRequest);
  }

  register(registerRequest: RegisterRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/register`, registerRequest)
  }

  setToken(token: string): void {
    localStorage.setItem('token', token);
    this.isAuthenticatedSubject.next(true); // Met à jour l'état d'authentification
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  clearToken(): void {
    localStorage.removeItem('token');
    this.isAuthenticatedSubject.next(false); // Met à jour l'état d'authentification
  }

  isAuthenticated(): boolean {
    const token = this.getToken();
    return !!token;
  }
  
  isNotAuthenticated(): void {
    if(this.isAuthenticated()){
      this.router.navigate(['/posts']);
    }
  }
}
