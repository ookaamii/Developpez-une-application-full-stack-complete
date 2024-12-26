import { Component, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthService } from 'src/app/services/auth.service';
import { Router, NavigationEnd, RouterModule } from '@angular/router';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterModule, MatButtonModule, MatIconModule, MatListModule, MatSidenavModule, MatToolbarModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent {
  showNavCo: boolean = false;
  showColorTopic: boolean = false;
  imageUrl: string = "/assets/account.jpg"; 
  currentUrl: string = '';
  private authSubscription!: Subscription;

  @Output() public sidenavToggle = new EventEmitter();

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    // Écouter les changements d'état d'authentification
    this.authSubscription = this.authService.isAuthenticated$.subscribe((isAuthenticated) => {
      this.showNavCo = isAuthenticated;
    });

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

  public onToggleSidenav = () => {
    this.sidenavToggle.emit();
  }

  ngOnDestroy(): void {
    // Désabonnement pour éviter les fuites de mémoire
    if (this.authSubscription) {
      this.authSubscription.unsubscribe();
    }
  }
}
