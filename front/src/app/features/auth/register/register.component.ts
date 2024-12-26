import { Component } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators, AbstractControl } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { AuthResponse } from '../../../interfaces/authResponse.interface';
import { RegisterRequest } from '../../../interfaces/registerRequest.interface';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [RouterModule, CommonModule, MatButtonModule, MatIconModule, MatFormFieldModule, ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {

  public form = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    username: ['', [Validators.required]],
    password: [
      '',
      [
        Validators.required,
        Validators.minLength(8),
        Validators.pattern(/[A-Z]/), // Au moins une majuscule
        Validators.pattern(/[a-z]/), // Au moins une minuscule
        Validators.pattern(/[0-9]/), // Au moins un chiffre
        Validators.pattern(/[\W_]/), // Au moins un caractère spécial
      ],
    ],
  });

  constructor(
    private authService: AuthService,
    private router: Router,
    private fb: FormBuilder
  ) { }

  public submit(): void {
    const registerRequest = this.form.value as RegisterRequest;
    this.authService.register(registerRequest).subscribe(
      (response: AuthResponse) => {
        this.authService.setToken(response.token);
        this.router.navigate(['/posts'])
      },
    );
  }

  public back() {
    window.history.back();
  }

  // Getters pour les validations
  get passwordControl(): AbstractControl | null {
    return this.form.get('password');
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
