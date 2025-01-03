import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { CommentService } from '../../../services/comment.service';
import { CommentResponse } from '../../../interfaces/commentResponse.interface';
import { CommentRequest } from '../../../interfaces/commentRequest.interface';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-comment',
  standalone: true,
  imports: [CommonModule, MatFormFieldModule, MatInputModule, ReactiveFormsModule, MatButtonModule, MatIconModule],
  templateUrl: './comment.component.html',
  styleUrl: './comment.component.scss'
})
export class CommentComponent {
  comments$ = new BehaviorSubject<CommentResponse[]>([]); // Liste des posts
  isLoading: boolean = false; // Indicateur de chargement
  errorMessage: string | null = null; // Gestion des erreurs
  postId: number;

  public form = this.fb.group({
    content: ['', [Validators.required]]
  });

  constructor(
    private commentService: CommentService,
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private snackBar: MatSnackBar
  ) { this.postId = Number(this.route.snapshot.paramMap.get('id')); }

  ngOnInit(): void {
    this.loadComments();
  }

  loadComments(): void {
    this.isLoading = true;
    this.commentService.findAllByPost(this.postId).subscribe({
      next: (response: CommentResponse[]) => {
        this.comments$.next(response); // Met à jour les commentaires avec BehaviorSubject
        this.isLoading = false;
      },
      error: (error) => {
        this.errorMessage = 'Une erreur est survenue lors du chargement des commentaires.';
        this.isLoading = false;
      }
    });
  }

  public submit(): void {
    const comment = this.form.value as CommentRequest;
    this.commentService.create(this.postId, comment).subscribe({
      next: (response) => {
        this.snackBar.open(response.message, 'Fermer', { duration: 3000 }); // Notification
        this.form.reset(); // Réinitialise le formulaire après l'envoi
        this.loadComments(); // Recharge les commentaires pour afficher le nouveau
      },
      error: (error) => {
        this.errorMessage = '';
      }
    });
  }
}
