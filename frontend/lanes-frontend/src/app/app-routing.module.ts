import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LaneListComponent } from './components/lane-list/lane-list.component';
import { LaneFormComponent } from './components/lane-form/lane-form.component';

const routes: Routes = [
  { path: '', redirectTo: '/lanes', pathMatch: 'full' },
  { path: 'lanes', component: LaneListComponent },
  { path: 'lanes/new', component: LaneFormComponent },
  { path: 'lanes/:id/edit', component: LaneFormComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],   // ← Removed useHash: true
  exports: [RouterModule]
})
export class AppRoutingModule { }