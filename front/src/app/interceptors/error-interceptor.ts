import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';  // Pour afficher un message d'erreur
import { HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

export const errorInterceptor: HttpInterceptorFn = (req, next) => {
  const snackBar = inject(MatSnackBar); // Injecte MatSnackBar pour afficher des messages d'erreur

  return next(req).pipe(
    catchError((error: HttpErrorResponse) => {
      let errorMessage = '';

      // Vérifie si l'erreur est du type attendu (Backend renvoie un message d'erreur personnalisé)
      if (error.error && error.error.message) {
        errorMessage = error.error.message; // Récupère le message d'erreur du backend
      } else {
        // Sinon, utilise un message d'erreur par défaut
        errorMessage = 'Une erreur s\'est produite. Veuillez réessayer plus tard.';
      }

      // Affiche le message d'erreur avec MatSnackBar
      snackBar.open(errorMessage, 'Fermer', {
        duration: 3000,  // Affiche le message pendant 3 secondes
        panelClass: ['error-snackbar']  // Personnalisation de la classe CSS
      });

      // Utilisation de la nouvelle syntaxe de throwError avec une fonction qui crée l'erreur
      return throwError(() => new Error(errorMessage));
    })
  );
};
