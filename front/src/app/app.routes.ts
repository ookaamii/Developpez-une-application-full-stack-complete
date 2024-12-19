import { inject } from '@angular/core';
import { Routes, Router } from '@angular/router';
import { HomeComponent } from './features/home/home.component';
import { LoginComponent } from './features/auth/login/login.component';
import { RegisterComponent } from './features/auth/register/register.component';
import { TopicComponent } from './features/topic/topic.component';
import { AuthService } from './services/auth.service';
import { ListPostComponent } from './features/post/list-post/list-post.component';
import { PostComponent } from './features/post/post.component';
import { FormPostComponent } from './features/post/form-post/form-post.component';

const routeConfig: Routes = [
  {
    path: '',
    component: HomeComponent,
    title: 'Accueil',
    canActivate: [() => inject(AuthService).isNotAuthenticated()]
    
  },
  {
    title: 'Connexion',
    path: 'login',
    component: LoginComponent,
    canActivate: [() => inject(AuthService).isNotAuthenticated()]
  },
  {
    title: 'Inscription',
    path: 'register',
    component: RegisterComponent,
    canActivate: [() => inject(AuthService).isNotAuthenticated()]
  },
  {
    title: 'Thèmes',
    path: 'topics',
    component: TopicComponent,
    canActivate: [() => inject(AuthService).isAuthenticated()]
  },
  {
    title: 'Articles',
    path: 'posts',
    component: ListPostComponent,
    canActivate: [() => inject(AuthService).isAuthenticated()]
  },
  {
    title: 'Article',
    path: 'post/:id',
    component: PostComponent,
    canActivate: [() => inject(AuthService).isAuthenticated()]
  },
  {
    title: 'Créer un article',
    path: 'posts/create',
    component: FormPostComponent,
    canActivate: [() => inject(AuthService).isAuthenticated()]
  }
];

export default routeConfig;