import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PostService } from '../../../services/post.service';
import { PostResponse } from '../../../interfaces/postResponse.interface';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { RouterModule, Router } from '@angular/router';

@Component({
  selector: 'app-list-post',
  standalone: true,
  imports: [CommonModule, MatButtonModule, RouterModule, MatIconModule],
  templateUrl: './list-post.component.html',
  styleUrl: './list-post.component.scss'
})
export class ListPostComponent {
  posts: PostResponse[] = []; // Liste des posts
  isLoading: boolean = false; // Indicateur de chargement
  errorMessage: string | null = null; // Gestion des erreurs
  sort: string = 'desc';
  arrowicon: string = 'arrow_downward';

  constructor(
    private postService: PostService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadPosts(this.sort);
  }

  loadPosts(sort: string): void {
    console.log(sort);
    this.isLoading = true; // Active le spinner ou indique le chargement
    this.postService.findAllByPost(sort).subscribe({
      next: (response: PostResponse[]) => {
        this.posts = response; // Récupère les données
        this.isLoading = false; // Désactive le chargement
      },
      error: (error) => {
        this.errorMessage = 'Une erreur est survenue lors du chargement des articles.';
        this.isLoading = false;
      }
    });
  }

  detail(id: number) {
    this.router.navigate(['/post/' + id]);
  }

  changerSort() {
    this.sort = (this.sort === 'asc' ? 'desc' : 'asc');
    this.loadPosts(this.sort);
    this.arrowicon = (this.sort === 'asc' ? 'arrow_upward' : 'arrow_downward');
  }

}
