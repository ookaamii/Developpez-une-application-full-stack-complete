import { Component } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { PostService } from '../../../services/post.service';
import { PostRequest } from '../../../interfaces/postRequest.interface';
import { TopicService } from '../../../services/topic.service';
import { Topic } from '../../../interfaces/topic.interface';
import { Response } from '../../../interfaces/response.interface';

@Component({
  selector: 'app-form-post',
  standalone: true,
  imports: [RouterModule, CommonModule, MatButtonModule, MatIconModule, MatFormFieldModule, MatInputModule, MatSelectModule, ReactiveFormsModule],
  templateUrl: './form-post.component.html',
  styleUrl: './form-post.component.scss'
})
export class FormPostComponent {
  errorMessage: string | null = null; // Gestion des erreurs
  
  public topics$ = this.topicService.findAllNotSub();

  public form = this.fb.group({
    title: ['', [Validators.required]],
    topicId: [0, [Validators.required]],
    content: ['', [Validators.required]]
  });

  constructor(
    private postService: PostService,
    private topicService: TopicService,
    private router: Router,
    private fb: FormBuilder,
    private snackBar: MatSnackBar
  ) { }

  

  public submit(): void {
    const postRequest = this.form.value as PostRequest;
    this.postService.create(postRequest).subscribe({
      next: (response) => {
        console.log('blopi');
        console.log(response);
        //this.snackBar.open(response.message, 'Fermer', { duration: 3000 }); // Notification
        this.router.navigate(['/posts']);
      },
      error: (error) => {
        console.log('bloup');
        console.log(error);
        this.errorMessage = 'Une erreur est survenue lors du chargement des articles.';
      }
    });
  }

  


  public back() {
    window.history.back();
  }
}
