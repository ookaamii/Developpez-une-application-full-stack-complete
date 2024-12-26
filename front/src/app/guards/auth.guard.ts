import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

export const AuthGuard = () => {
  const authService = inject(AuthService);
  const router = inject(Router);

  if (authService.getToken()) {
    return true;
  } else {
    // L'utilisateur n'est pas connecté, rediriger vers /login
    router.navigate(['/login']);
    return false;
  }
}

