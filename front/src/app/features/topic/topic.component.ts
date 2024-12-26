import { Component, OnInit } from '@angular/core';
import { TopicService } from '../../services/topic.service';
import { SubscriptionService } from '../../services/subscription.service';
import { Topic } from '../../interfaces/topic.interface';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBar } from '@angular/material/snack-bar';
import { BehaviorSubject, Observable, of, tap, catchError, finalize, map } from 'rxjs';

@Component({
  selector: 'app-topic',
  standalone: true,
  imports: [CommonModule, MatButtonModule],
  templateUrl: './topic.component.html',
  styleUrl: './topic.component.scss'
})
export class TopicComponent implements OnInit {
  topics$: Observable<Topic[]> = of([]); // Liste des topics
  userSubscriptions$ = new BehaviorSubject<number[]>([]); // Liste des IDs des abonnements utilisateur
  public isLoading$ = new BehaviorSubject<boolean>(false); // Indicateur de chargement
  public errorMessage$ = new BehaviorSubject<string | null>(null);

  constructor(
    private topicService: TopicService,
    private subscriptionService: SubscriptionService,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
    this.loadTopics();
    this.loadUserSubscriptions();
  }

  loadTopics(): void {
    this.isLoading$.next(true); // Indique le chargement
    this.topics$ = this.topicService.findAll().pipe(
      finalize(() => this.isLoading$.next(false)), // Cela garantit que isLoading$ est mis à false après le chargement
      catchError((error) => {
        this.errorMessage$.next('Une erreur est survenue lors du chargement des thèmes.');
        return of([]); // Retourne une liste vide en cas d'erreur
      })
    );
  }

  loadUserSubscriptions(): void {
    this.subscriptionService.findAllByUser().subscribe({
      next: (response) => {
        const subscriptions = response.map((subscription: any) => subscription.id); // Extraire les IDs des topics
        this.userSubscriptions$.next(subscriptions); // Met à jour la liste des abonnements
      },
      error: (err) => {
        console.error('Erreur lors de la récupération des abonnements utilisateur :', err);
        this.userSubscriptions$.next([]); // En cas d'erreur, vide les abonnements
      }
    });
  }
  
  // Vérifie si l'utilisateur est abonné au thème
  isSubscribed(topicId: number): boolean {
    return this.userSubscriptions$.getValue().includes(topicId);
  }

  subscribe(topicId: number): void {
    this.subscriptionService.subscribe(topicId).subscribe({
      next: (response) => {
        this.snackBar.open(response.message, 'Fermer', { duration: 3000 }); // Notification
        this.loadTopics(); // Recharge les topics
        this.loadUserSubscriptions(); // Recharge les abonnements utilisateur
      },
      error: (err) => {
        this.snackBar.open('Une erreur est survenue.', 'Fermer', { duration: 3000 });
      }
    });
  }
}