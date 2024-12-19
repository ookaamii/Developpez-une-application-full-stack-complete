import { Component, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthService } from 'src/app/services/auth.service';
import { Router, NavigationEnd, RouterModule } from '@angular/router';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';

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

  @Output() public sidenavToggle = new EventEmitter();

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

  public onToggleSidenav = () => {
    this.sidenavToggle.emit();
  }
}
