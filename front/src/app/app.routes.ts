import { inject } from '@angular/core';
import { Routes } from '@angular/router';
import { HomeComponent } from './features/home/home.component';
import { LoginComponent } from './features/auth/login/login.component';
import { RegisterComponent } from './features/auth/register/register.component';
import { TopicComponent } from './features/topic/topic.component';
import { AuthService } from './services/auth.service';
import { ListPostComponent } from './features/post/list-post/list-post.component';
import { PostComponent } from './features/post/post.component';

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
  }
];

export default routeConfig;