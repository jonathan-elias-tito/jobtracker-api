import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { RouterLink } from '@angular/router';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-curriculums',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './curriculums.html',
  styleUrl: './curriculums.css'
})
export class CurriculumsComponent implements OnInit {
  curriculos: any[] = [];
  private apiUrl = 'http://localhost:8080/curriculums';
  private applicationsUrl = 'http://localhost:8080/applications';
  private userId = 1;

  constructor(
    private http: HttpClient,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.carregarDadosAutomaticamente();
  }

  carregarDadosAutomaticamente(): void {
    forkJoin({
      curriculos: this.http.get<any[]>(this.apiUrl),
      applications: this.http.get<any[]>(this.applicationsUrl)
    }).subscribe({
      next: (res) => {
        const listaCurriculos = res.curriculos || [];
        const listaApplications = res.applications || [];

        this.curriculos = listaCurriculos.map(curr => {
          const qtdVagas = listaApplications.filter(app => {
            const idVinculado = app.curriculum?.id || app.curriculumId || app.curriculum_id;
            return Number(idVinculado) === Number(curr.id);
          }).length;

          // Limpa qualquer texto "Versão" ou "Versão · " que esteja vindo do banco de dados
          let nomeLimpo = curr.education || curr.experiences || `Currículo #${curr.id}`;
          nomeLimpo = nomeLimpo.replace('Versão · ', '').replace('Versão ', '');

          return {
            ...curr,
            displayName: nomeLimpo,
            usageCount: qtdVagas === 1 ? '1 vaga' : `${qtdVagas} vagas`
          };
        });

        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Erro ao carregar currículos automaticamente:', err);
      }
    });
  }

  onFileSelected(event: any): void {
    const file: File = event.target.files[0];
    if (!file) return;

    const novoCurriculo = {
      education: file.name,
      experiences: file.name,
      professionalSummary: 'Enviado via painel web'
    };

    this.http.post(`${this.apiUrl}/user/${this.userId}`, novoCurriculo).subscribe({
      next: () => {
        alert('Novo currículo salvo com sucesso no banco!');
        this.carregarDadosAutomaticamente();
      },
      error: (err) => {
        console.error('Erro ao salvar currículo:', err);
        alert('Erro ao salvar no banco.');
      }
    });
  }
}
