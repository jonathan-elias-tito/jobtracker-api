import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-applications',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './applications.html',
  styleUrl: './applications.css'
})
export class ApplicationsComponent implements OnInit {
  curriculos: any[] = [];

    application = {
      role: '',
      company: '',
      jobLink: '',
      applicationDate: new Date().toLocaleDateString('en-CA'), // Já vem como "2026-11-27" certinho
      status: 'ANÁLISE',
      observations: '',
      curriculumId: null
    };

  private apiUrl = 'http://localhost:8080/applications';

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.carregarCurriculos();
  }

  carregarCurriculos(): void {
    this.http.get<any[]>('http://localhost:8080/curriculums').subscribe({
      next: (data) => {
        this.curriculos = data;
      },
      error: (err) => console.error('Erro ao carregar currículos:', err)
    });
  }

  salvarCandidatura(): void {
    // Validações estritas para impedir salvamento vazio ou mocado
    if (!this.application.role || this.application.role.trim() === '') {
      alert('Erro: O campo Cargo não pode ficar vazio!');
      return;
    }
    if (!this.application.company || this.application.company.trim() === '') {
      alert('Erro: O campo Empresa não pode ficar vazio!');
      return;
    }
    if (!this.application.applicationDate) {
      alert('Erro: Informe a Data da candidatura!');
      return;
    }
    if (!this.application.curriculumId) {
      alert('Erro: Selecione um Currículo antes de salvar!');
      return;
    }
const payload = {
      role: this.application.role.trim(),
      company: this.application.company.trim(),
      applicationDate: this.application.applicationDate, // String "YYYY-MM-DD" limpa
      status: this.application.status,
      observations: this.application.observations
    };
    const url = `${this.apiUrl}/curriculum/${this.application.curriculumId}`;
    this.http.post(url, payload).subscribe({
      next: () => {
        alert('Candidatura salva com sucesso no banco!');
        this.limparFormulario();
      },
      error: (err) => {
        console.error('Erro ao salvar candidatura:', err);
        alert('Erro ao salvar no banco de dados. Verifique o Spring Boot.');
      }
    });
  }

  limparFormulario(): void {
    this.application = {
      role: '',
      company: '',
      jobLink: '',
      applicationDate: new Date().toISOString().split('T')[0],
      status: 'Analise',
      observations: '',
      curriculumId: null
    };
  }

obterDataLocalFormatada(): string {
      return new Date().toLocaleDateString('en-CA'); // Retorna exatamente "YYYY-MM-DD" no fuso local
    }
}
