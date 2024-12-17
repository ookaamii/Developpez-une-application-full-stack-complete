import { Component, OnInit } from '@angular/core';
import { TopicService } from '../../services/topic.service';
import { Topic } from '../../interfaces/topic.interface';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-topic',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './topic.component.html',
  styleUrl: './topic.component.scss'
})
export class TopicComponent implements OnInit {
  topics: Topic[] = []; // Liste des topics
  isLoading: boolean = false; // Indicateur de chargement
  errorMessage: string | null = null; // Gestion des erreurs

  constructor(private topicService: TopicService) {}

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
        this.errorMessage = 'Une erreur est survenue lors du chargement des topics.';
        console.error(error); // Affiche l'erreur dans la console pour le débogage
        this.isLoading = false;
      }
    });
  }
}