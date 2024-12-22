import { Component } from '@angular/core';
import { SubscriptionService } from '../../../services/subscription.service';
import { Topic } from '../../../interfaces/topic.interface';
import { Response } from '../../../interfaces/response.interface';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-subscription',
  standalone: true,
  imports: [CommonModule, MatButtonModule],
  templateUrl: './subscription.component.html',
  styleUrl: './subscription.component.scss'
})
export class SubscriptionComponent {
  topics: Topic[] = []; // Liste des topics
  errorMessage: string | null = null; // Gestion des erreurs

  constructor(
    private subscriptionService: SubscriptionService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.loadTopics();
  }

  loadTopics(): void {
    this.subscriptionService.findAllByUser().subscribe({
      next: (response: Topic[]) => {
        this.topics = response; // Récupère les données
      },
      error: (error) => {
        this.errorMessage = 'Une erreur est survenue lors du chargement des thèmes.';
      }
    });
  }

  unSubscribe(id: number) {
    this.subscriptionService.unSubscribe(id).subscribe({
      next: (response: Response) => {
        this.snackBar.open(response.message, 'Fermer', { duration: 3000 }); // Notification
      },
      error: (error) => {
        this.errorMessage = 'Une erreur est survenue lors du désabonnement du thème.';
      }
    });
  }

}
