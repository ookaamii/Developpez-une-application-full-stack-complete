import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { PostService } from '../../services/post.service';
import { PostResponse } from '../../interfaces/postResponse.interface';

@Component({
  selector: 'app-post',
  standalone: true,
  imports: [CommonModule, MatIconModule, MatButtonModule],
  templateUrl: './post.component.html',
  styleUrl: './post.component.scss'
})
export class PostComponent {
  post: PostResponse | null = null; // Info du post
  errorMessage: string | null = null; // Gestion des erreurs
  postId: number;


  constructor(
    private postService: PostService,
    private route: ActivatedRoute
  ) { this.postId = Number(this.route.snapshot.paramMap.get('id')); }

  ngOnInit(): void {
    this.loadPost();
  }

  loadPost(): void {
    this.postService.getPost(this.postId).subscribe({
      next: (response: PostResponse) => {
        this.post = response; // Récupère les données
      },
      error: (error) => {
        this.errorMessage = 'Une erreur est survenue lors du chargement des articles.';
      }
    });
  }

  public back() {
    window.history.back();
  }

}
