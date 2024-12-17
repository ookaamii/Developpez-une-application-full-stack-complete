import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, catchError, map } from 'rxjs';
import { UserRequest } from '../interfaces/userRequest.interface';
import { AuthResponse } from '../interfaces/authResponse.interface';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly apiUrl = 'http://localhost:8080/api/auth';
  private authStatus = new BehaviorSubject<boolean>(this.isAuthenticated());

  constructor(private http: HttpClient) {}

  login(userRequest: UserRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/login`, userRequest).pipe(
      map((response) => {
        localStorage.setItem('token', response.token); // Stocke le token
        this.authStatus.next(true); // Met à jour le statut de connexion
        return response;
      }),
      catchError((error) => {
        console.error('Erreur de connexion:', error);
        throw error;
      })
    );
  }

  // Vérifie si l'utilisateur est connecté
  isAuthenticated(): boolean {
    const token = localStorage.getItem('token');
    return !!token; // Retourne true si un token est présent
  }

  // Accède au statut d'authentification
  getAuthStatus(): Observable<boolean> {
    return this.authStatus.asObservable();
  }

  // Récupère le token JWT
  getToken(): string | null {
    return localStorage.getItem('token');
  }

}
