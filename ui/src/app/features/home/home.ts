import { HttpClient } from '@angular/common/http';
import { Component, signal } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.html',
})
export class Home {
  protected readonly health = signal('Carregando...');

  constructor(http: HttpClient) {
    http.get('/health', { responseType: 'text' }).subscribe({
      next: (result) => this.health.set(result),
      error: () => this.health.set('Não foi possível consultar a API'),
    });
  }
}
