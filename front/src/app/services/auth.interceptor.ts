import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.authService.getToken();

    // Liste des routes à exclure
    const excludedRoutes = ['/api/auth/login', '/api/auth/register'];

    // Si l'URL correspond à une route exclue, ne pas ajouter le token
    if (excludedRoutes.some(route => request.url.includes(route))) {
      return next.handle(request);
    }

    // Ajouter le token dans l'en-tête Authorization
    if (token) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`,
        },
      });
    }

    return next.handle(request);
  }
}
