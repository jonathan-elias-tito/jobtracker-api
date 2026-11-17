import { Routes } from '@angular/router';
import { JobListComponent } from './features/job-list/job-list';
import { ApplicationsComponent } from './features/applications/applications';
import { CurriculumsComponent } from './features/curriculums/curriculums';

export const routes: Routes = [
  { path: 'candidaturas', component: JobListComponent },
  { path: 'nova', component: ApplicationsComponent },
  { path: 'curriculums', component: CurriculumsComponent },
  { path: '', redirectTo: 'candidaturas', pathMatch: 'full' }
];
