import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PostService } from '../../../services/post.service';
import { PostResponse } from '../../../interfaces/postResponse.interface';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { RouterModule, Router } from '@angular/router';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { catchError, switchMap, tap } from 'rxjs/operators';

@Component({
  selector: 'app-list-post',
  standalone: true,
  imports: [CommonModule, MatButtonModule, RouterModule, MatIconModule],
  templateUrl: './list-post.component.html',
  styleUrls: ['./list-post.component.scss']
})
export class ListPostComponent {
  private sortSubject = new BehaviorSubject<string>('desc');
  public posts$: Observable<PostResponse[]>;
  public isLoading = new BehaviorSubject<boolean>(true);
  public errorMessage = new BehaviorSubject<string | null>(null);
  public sortIcon = 'arrow_downward'; // Icône de tri

  constructor(private postService: PostService, private router: Router) {
    // Déclenchée à chaque changement du critère de tri
    this.posts$ = this.sortSubject.pipe(
      tap(() => {
        // Active le chargement
        this.isLoading.next(true); 
        // Réinitialise tout message d'erreur précédent
        this.errorMessage.next(null); 
      }),
      switchMap(sort => 
        // Appel au service pour récupérer les articles en fonction du critère de tri
        this.postService.findAllByPost(sort).pipe(
          tap(() => this.isLoading.next(false)), // Désactive le chargement après la réponse
          catchError(error => {
            this.isLoading.next(false); 
            this.errorMessage.next('Une erreur est survenue lors du chargement des articles.');
            // Retourne un tableau vide pour permettre à l'Observable de continuer son exécution sans erreur
            return of([]); 
          })
        )
      )
    );
  }
  
  // Méthode pour alterner le critère de tri entre ascendant et descendant
  changerSort(): void {
    // Change le critère de tri actuel (asc ou desc)
    const newSort = this.sortSubject.value === 'asc' ? 'desc' : 'asc';
    // Change l'icône
    this.sortIcon = newSort === 'asc' ? 'arrow_upward' : 'arrow_downward';
    // Met à jour le sujet avec le nouveau critère de tri
    this.sortSubject.next(newSort);
  }
  
  // Méthode pour naviguer vers la page de détail d'un article en fonction de son ID
  detail(id: number): void {
    this.router.navigate(['/post', id]);
  }
}
