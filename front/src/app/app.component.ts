import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, Event, NavigationStart } from '@angular/router';
import { NavbarComponent } from './shared/navbar/navbar.component';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [NavbarComponent, HttpClientModule, RouterModule, RouterOutlet, CommonModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  showNavbar: boolean = true;

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.router.events.subscribe((event: Event) => {
      if (event instanceof NavigationStart) {
        // Condition pour afficher la navbar sur certaines routes
        // Par exemple, cacher la navbar sur la page de login
        if (event.url === '/') {
          this.showNavbar = false;
        } else {
          this.showNavbar = true;
        }
      }
    });
  }
}
