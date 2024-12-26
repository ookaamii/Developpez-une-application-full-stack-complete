import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { CommentComponent } from './comment/comment.component';
import { PostService } from '../../services/post.service';
import { PostResponse } from '../../interfaces/postResponse.interface';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { catchError, switchMap, tap } from 'rxjs/operators';

@Component({
  selector: 'app-post',
  standalone: true,
  imports: [CommonModule, MatIconModule, MatButtonModule, CommentComponent],
  templateUrl: './post.component.html',
  styleUrl: './post.component.scss'
})
export class PostComponent {
  public post$ = new Observable<PostResponse | null>; // Info du post
  public errorMessage = new BehaviorSubject<string | null>(null);
  postId: number;

  constructor(
    private postService: PostService,
    private route: ActivatedRoute
  ) { this.postId = Number(this.route.snapshot.paramMap.get('id')); }

  ngOnInit(): void {
    this.loadPost();
  }

  loadPost(): void {
    this.post$ = this.postService.getPost(this.postId).pipe(
      catchError(error => {
        this.errorMessage.next('Article non trouvé.');
        return of(null);
      })
    );
  }

  public back() {
    window.history.back();
  }

}
