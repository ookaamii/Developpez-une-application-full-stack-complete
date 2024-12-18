import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PostService } from '../../../services/post.service';
import { PostResponse } from '../../../interfaces/postResponse.interface';

@Component({
  selector: 'app-list-post',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './list-post.component.html',
  styleUrl: './list-post.component.scss'
})
export class ListPostComponent {
  posts: PostResponse[] = []; // Liste des posts
  isLoading: boolean = false; // Indicateur de chargement
  errorMessage: string | null = null; // Gestion des erreurs

  constructor(
    private postService: PostService
  ) {}

  ngOnInit(): void {
    this.loadPosts();
  }

  loadPosts(): void {
    this.isLoading = true; // Active le spinner ou indique le chargement
    this.postService.findAllByPost().subscribe({
      next: (response: PostResponse[]) => {
        console.log(response);
        this.posts = response; // Récupère les données
        this.isLoading = false; // Désactive le chargement
      },
      error: (error) => {
        this.errorMessage = 'Une erreur est survenue lors du chargement des articles.';
        this.isLoading = false;
      }
    });
  }

}
