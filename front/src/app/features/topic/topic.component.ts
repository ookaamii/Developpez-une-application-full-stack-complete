import { Component, OnInit } from '@angular/core';
import { TopicService } from '../../services/topic.service';
import { SubscriptionService } from '../../services/subscription.service';
import { Topic } from '../../interfaces/topic.interface';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-topic',
  standalone: true,
  imports: [CommonModule, MatButtonModule],
  templateUrl: './topic.component.html',
  styleUrl: './topic.component.scss'
})
export class TopicComponent implements OnInit {
  topics: Topic[] = []; // Liste des topics
  isLoading: boolean = false; // Indicateur de chargement
  errorMessage: string | null = null; // Gestion des erreurs

  constructor(
    private topicService: TopicService,
    private subscriptionService: SubscriptionService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.loadTopics();
  }

  loadTopics(): void {
    this.isLoading = true; // Active le spinner ou indique le chargement
    this.topicService.findAllNotSub().subscribe({
      next: (response: Topic[]) => {
        this.topics = response; // Récupère les données
        this.isLoading = false; // Désactive le chargement
      },
      error: (error) => {
        this.errorMessage = 'Une erreur est survenue lors du chargement des thèmes.';
        this.isLoading = false;
      }
    });
  }

    subscribe(topicId: number): void {
      this.subscriptionService.subscribe(topicId).subscribe({
        next: (response) => {
          this.snackBar.open(response.message, 'Fermer', { duration: 3000 }); // Notification
          this.loadTopics(); // Recharge les topics pour enlever celui auquel on vient de s'abonner
        },
        error: (err) => {
          console.error('Erreur lors de l\'abonnement :', err);
          this.snackBar.open('Une erreur est survenue.', 'Fermer', { duration: 3000 });
        }
      });
  }
}
