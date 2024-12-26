import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

export const NotAuthGuard = () => {
  const authService = inject(AuthService);
  const router = inject(Router);

  if (!authService.getToken()) {
    return true;
  } else {
    // L'utilisateur est déjà connecté, rediriger vers la liste des articles
    router.navigate(['/posts']);
    return false;
  }
}

