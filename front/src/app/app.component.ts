import { Component, OnInit, HostListener } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { NavbarComponent } from './shared/navbar/navbar.component';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, RouterLink, RouterOutlet, Event } from '@angular/router';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [NavbarComponent, HttpClientModule, RouterModule, RouterOutlet, CommonModule, MatSidenavModule, MatIconModule, MatListModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  showNavCo: boolean = false;
  isDesktop: boolean = window.innerWidth > 768;
  imageUrl: string = "/assets/account.jpg"; 

  constructor(private router: Router, private authService: AuthService) {}

  ngOnInit(): void {
    if (this.authService.isAuthenticated()) {
      this.showNavCo = true;
    }
  }

    // Écoute le redimensionnement de la fenêtre pour changer le mode
  @HostListener('window:resize', ['$event'])
  onResize(event: Event): void {
    this.isDesktop = window.innerWidth > 768;
  }
  
}
