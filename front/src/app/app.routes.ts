import { Routes } from '@angular/router';
import { HomeComponent } from './features/home/home.component';
import { LoginComponent } from './features/auth/login/login.component';
import { RegisterComponent } from './features/auth/register/register.component';

const routeConfig: Routes = [
  {
    path: '',
    component: HomeComponent,
    title: 'Home page'
  },
  {
    title: 'Login',
    path: 'login',
    component: LoginComponent
  },
  {
    title: 'Register',
    path: 'register',
    component: RegisterComponent
  }
];

export default routeConfig;