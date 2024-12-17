import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const publicRoutes = ['/login', '/register'];

    // Vérifie si la route est publique
    if (publicRoutes.includes(state.url)) {
      return true; // Autorise l'accès
    }

    // Sinon, vérifier si l'utilisateur est authentifié
    if (this.authService.isAuthenticated()) {
      return true;
    }

    // Redirige vers login si non authentifié
    this.router.navigate(['/login']);
    return false;
  }
}
