import { enableProdMode } from '@angular/core';
import { bootstrapApplication,provideProtractorTestingSupport } from '@angular/platform-browser';
import { provideRouter } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';
import { environment } from './environments/environment';
import { AppComponent } from './app/app.component'; 
import routeConfig from './app/app.routes';

if (environment.production) {
  enableProdMode();
}

bootstrapApplication(AppComponent,
  {
    providers: [
      provideHttpClient(),
      provideProtractorTestingSupport(),
      provideRouter(routeConfig)
    ]
  }
).catch(err => console.error(err));