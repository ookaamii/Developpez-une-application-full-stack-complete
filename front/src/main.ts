import { enableProdMode } from '@angular/core';
import { bootstrapApplication,provideProtractorTestingSupport } from '@angular/platform-browser';
import { provideRouter } from '@angular/router';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { environment } from './environments/environment';
import { AppComponent } from './app/app.component'; 
import { authInterceptor } from './app/interceptors/auth-interceptor';
import routeConfig from './app/app.routes';

if (environment.production) {
  enableProdMode();
}

bootstrapApplication(AppComponent,
  {
    providers: [
      provideHttpClient(withInterceptors([authInterceptor])),
      provideProtractorTestingSupport(),
      provideRouter(routeConfig)
    ]
  }
).catch(err => console.error(err));