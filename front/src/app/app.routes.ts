import { Routes, Router } from '@angular/router';
import { HomeComponent } from './features/home/home.component';
import { LoginComponent } from './features/auth/login/login.component';
import { RegisterComponent } from './features/auth/register/register.component';
import { TopicComponent } from './features/topic/topic.component';
import { ListPostComponent } from './features/post/list-post/list-post.component';
import { PostComponent } from './features/post/post.component';
import { FormPostComponent } from './features/post/form-post/form-post.component';
import { MeComponent } from './features/me/me.component';
import { AuthGuard } from './guards/auth.guard';
import { NotAuthGuard } from './guards/not-auth.guard';

const routeConfig: Routes = [
  {
    path: '',
    component: HomeComponent,
    title: 'Accueil',
    canActivate: [NotAuthGuard]
    
  },
  {
    title: 'Connexion',
    path: 'login',
    component: LoginComponent,
    canActivate: [NotAuthGuard]
  },
  {
    title: 'Inscription',
    path: 'register',
    component: RegisterComponent,
    canActivate: [NotAuthGuard]
  },
  {
    title: 'Thèmes',
    path: 'topics',
    component: TopicComponent,
    canActivate: [AuthGuard]
  },
  {
    title: 'Articles',
    path: 'posts',
    component: ListPostComponent,
    canActivate: [AuthGuard]
  },
  {
    title: 'Article',
    path: 'post/:id',
    component: PostComponent,
    canActivate: [AuthGuard]
  },
  {
    title: 'Créer un article',
    path: 'posts/create',
    component: FormPostComponent,
    canActivate: [AuthGuard]
  },
  {
    title: 'Profil',
    path: 'me',
    component: MeComponent,
    canActivate: [AuthGuard]
  }
];

export default routeConfig;