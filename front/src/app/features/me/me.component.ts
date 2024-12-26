import { Component } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators, AbstractControl } from '@angular/forms';
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
import { PasswordUpdateRequest } from '../../interfaces/passwordUpdateRequest.interface';
import { User } from '../../interfaces/user.interface';
import { SubscriptionComponent } from './subscription/subscription.component';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-me',
  standalone: true,
  imports: [RouterModule, CommonModule, MatButtonModule, MatIconModule, MatFormFieldModule, MatInputModule, MatSelectModule, ReactiveFormsModule, SubscriptionComponent],
  templateUrl: './me.component.html',
  styleUrl: './me.component.scss'
})
export class MeComponent {
  user$ = new BehaviorSubject<User | null>(null); // Info utilisateur
  errorMessage: string | null = null; // Gestion des erreurs

  public form = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    username: ['', [Validators.required]]
  });

  public formPassword = this.fb.group({
    oldPassword: ['', [Validators.required]],
    newPassword: ['', [Validators.required,
      Validators.minLength(8),
      Validators.pattern(/[A-Z]/), // Au moins une majuscule
      Validators.pattern(/[a-z]/), // Au moins une minuscule
      Validators.pattern(/[0-9]/), // Au moins un chiffre
      Validators.pattern(/[\W_]/), // Au moins un caractère spécial
      ]]
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
        this.user$.next(response); // Met à jour les données utilisateur
        // Met à jour le formulaire
        this.form.patchValue({
          email: response.email,
          username: response.username
        });
      },
      error: (error) => {
        this.errorMessage = 'Une erreur est survenue lors du chargement des données utilisateur.';
      }
    });
  }

  public submit(): void {
    const userUpdateRequest = this.form.value as UserUpdateRequest;
    this.userService.update(userUpdateRequest).subscribe({
      next: (response) => {
        this.snackBar.open(response.message, 'Fermer', { duration: 3000 }); // Notification
        this.form.reset();
        this.loadUser();
      },
      error: (error) => {
      }
    });
  }

  public submitPassword(): void {
    const passwordUpdateRequest = this.formPassword.value as PasswordUpdateRequest;
    this.userService.updatePassword(passwordUpdateRequest).subscribe({
      next: (response) => {
        this.snackBar.open(response.message, 'Fermer', { duration: 3000 }); // Notification
        this.form.reset();
        this.loadUser();
      },
      error: (error) => {
      }
    });
  }

  logout() {
    this.authService.clearToken();
    this.router.navigate(['/']);
  }

  // Getters pour les validations
  get passwordControl(): AbstractControl | null {
    return this.formPassword.get('newPassword');
  }

  has8Caracteres(): boolean {
    return (this.passwordControl?.value || '').length >= 8;
  }
  
  hasUpperCase(): boolean {
    return /[A-Z]/.test(this.passwordControl?.value || '');
  }

  hasLowerCase(): boolean {
    return /[a-z]/.test(this.passwordControl?.value || '');
  }

  hasNumber(): boolean {
    return /[0-9]/.test(this.passwordControl?.value || '');
  }

  hasSpecialCharacter(): boolean {
    return /[\W_]/.test(this.passwordControl?.value || '');
  }
}
