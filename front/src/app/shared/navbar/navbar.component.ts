import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent {
  token: string | null = null;
  //showNavCo: boolean = false;
  showNavCo: boolean = true;
  imageUrl: string = "/assets/account.jpg"; 

  ngOnInit(): void {
    this.token = localStorage.getItem('token');

    if (this.token !== null) {
      this.showNavCo = true;
    }
  }
}
