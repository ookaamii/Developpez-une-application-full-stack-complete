import { Component } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { AuthResponse } from '../../../interfaces/authResponse.interface';
import { UserRequest } from '../../../interfaces/userRequest.interface'; 
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [RouterModule, CommonModule, MatButtonModule, MatIconModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  public form = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(8)]]
});

  constructor(
    private authService: AuthService,
    private router: Router,
    private fb: FormBuilder
  ) { }

  public submit(): void {
    const loginRequest = this.form.value as UserRequest;
    this.authService.login(loginRequest).subscribe(
      (response: AuthResponse) => {
        this.authService.setToken(response.token);
        this.router.navigate(['/posts']);
      },
    );
  }

  public back() {
    window.history.back();
  }
}
