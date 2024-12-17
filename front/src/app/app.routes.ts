import { Routes } from '@angular/router';
import { HomeComponent } from './features/home/home.component';
import { LoginComponent } from './features/auth/login/login.component';
import { RegisterComponent } from './features/auth/register/register.component';
import { TopicComponent } from './features/topic/topic.component';
import { AuthGuard } from './services/auth.guard';

const routeConfig: Routes = [
  {
    path: '',
    component: HomeComponent,
    title: 'Accueil'
  },
  {
    title: 'Connexion',
    path: 'login',
    component: LoginComponent
  },
  {
    title: 'Inscription',
    path: 'register',
    component: RegisterComponent
  },
  {
    title: 'Thèmes',
    path: 'topics',
    component: TopicComponent,
    //canActivate: [AuthGuard]
  }
];

export default routeConfig;