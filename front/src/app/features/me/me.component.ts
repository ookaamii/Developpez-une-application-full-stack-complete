import { Component } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { AuthService } from '../../services/auth.service';
import { UserService } from '../../services/user.service';
import { UserUpdateRequest } from '../../interfaces/userUpdateRequest.interface';
import { User } from '../../interfaces/user.interface';
import { SubscriptionComponent } from './subscription/subscription.component';

@Component({
  selector: 'app-me',
  standalone: true,
  imports: [RouterModule, CommonModule, MatButtonModule, MatIconModule, MatFormFieldModule, MatInputModule, MatSelectModule, ReactiveFormsModule, SubscriptionComponent],
  templateUrl: './me.component.html',
  styleUrl: './me.component.scss'
})
export class MeComponent {
  me: User | null = null; // Info utilisateur
  errorMessage: string | null = null; // Gestion des erreurs

  public form = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    username: ['', [Validators.required]],
    password: ['', [Validators.required]]
  });

  constructor(
    private userService: UserService,
    private authService: AuthService,
    private router: Router,
    private fb: FormBuilder,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
    this.loadUser();
  }

  loadUser(): void {
    this.userService.getProfile().subscribe({
      next: (response: User) => {
        this.me = response; // Récupère les données
        console.log(this.me);
        // Met à jour les valeurs du formulaire
        this.form.patchValue({
          email: this.me.email,
          username: this.me.username,
          password: this.me.password
        });
      },
      error: (error) => {
        this.errorMessage = 'Une erreur est survenue lors du chargement des articles.';
      }
    });
  }

  public submit(): void {
    const userUpdateRequest = this.form.value as UserUpdateRequest;
    this.userService.update(userUpdateRequest).subscribe({
      next: (response) => {
        this.snackBar.open(response.message, 'Fermer', { duration: 3000 }); // Notification
        this.router.navigate(['/me']);
      },
      error: (error) => {
      }
    });
  }

  logout() {
    this.authService.clearToken();
    this.router.navigate(['/']);
  }
}
