import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-job-list',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './job-list.html',
  styleUrl: './job-list.css'
})
export class JobListComponent implements OnInit {
  candidaturas: any[] = [];
  candidaturasFiltradas: any[] = [];
  filtroStatus: string = 'TODAS';
  termoBusca: string = '';

  constructor(
    private http: HttpClient,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.carregarCandidaturasAutomaticamente();
  }

  // Garante o carregamento automático imediato e forçado na tela
  carregarCandidaturasAutomaticamente(): void {
    this.http.get<any[]>('http://localhost:8080/applications').subscribe({
      next: (data) => {
        this.candidaturas = data || [];
        this.aplicarFiltros();
        this.cdr.detectChanges(); // Força a renderização instantânea na tela
      },
      error: (err) => {
        console.error('Erro ao carregar candidaturas automaticamente:', err);
      }
    });
  }

  mudarFiltro(status: string): void {
    this.filtroStatus = status;
    this.aplicarFiltros();
  }

  normalizarStatus(status: string): string {
    if (!status) return 'ANÁLISE';
    const s = status.toUpperCase().replace(/_/g, ' ').trim();
    if (s.includes('ANDAMENTO') || s.includes('ANALISE') || s.includes('ANÁLISE')) {
      return 'ANÁLISE';
    }
    if (s.includes('CONTRATADO')) return 'CONTRATADO';
    if (s.includes('REPROVADO') || s.includes('RECUSADA')) return 'REPROVADO';
    return s;
  }
aplicarFiltros(): void {
    this.candidaturasFiltradas = this.candidaturas.filter(app => {
      const statusAppNorm = this.normalizarStatus(app.status);
      const filtroNorm = this.normalizarStatus(this.filtroStatus);

      const matchStatus = this.filtroStatus === 'TODAS' || statusAppNorm === filtroNorm;

      const busca = this.termoBusca.toLowerCase().trim();

      // Permite buscar por ID ("1", "#1"), cargo, empresa ou nome do currículo
      const idStr = String(app.id);
      const matchTexto = !busca ||
        idStr.includes(busca.replace('#', '')) ||
        (app.role && app.role.toLowerCase().includes(busca)) ||
        (app.company && app.company.toLowerCase().includes(busca)) ||
        (app.curriculum?.education && app.curriculum.education.toLowerCase().includes(busca));

      return matchStatus && matchTexto;
    });
  }

  contarPorStatus(statusFiltro: string): number {
    const filtroNorm = this.normalizarStatus(statusFiltro);
    return this.candidaturas.filter(app => {
      return this.normalizarStatus(app.status) === filtroNorm;
    }).length;
  }

  getBadgeClass(status: string): string {
    const s = this.normalizarStatus(status);
    if (s === 'CONTRATADO') return 'badge-verde';
    if (s === 'REPROVADO') return 'badge-cinza';
    return 'badge-amarelo';
  }
}
