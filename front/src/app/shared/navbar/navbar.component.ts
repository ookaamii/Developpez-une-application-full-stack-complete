import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthService } from 'src/app/services/auth.service';
import { Router, NavigationEnd, RouterModule } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent {
  showNavCo: boolean = false;
  showColorTopic: boolean = false;
  imageUrl: string = "/assets/account.jpg"; 
  currentUrl: string = '';

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    if (this.authService.isAuthenticated()) {
      this.showNavCo = true;
    }

    // Écouter les changements d'URL
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.currentUrl = event.url; // Récupérer l'URL actuelle
      }
    });
  }

  isActive(route: string): boolean {
    return this.currentUrl.includes(route); // Vérifie si l'URL contient le chemin
  }
}
