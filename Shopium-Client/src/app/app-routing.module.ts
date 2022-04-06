import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { CatalogComponent } from './catalog/catalog.component';
import { CatalogItemComponent } from './catalog-item/catalog-item.component';

const routes: Routes = [
  { path: '', redirectTo: '/catalog', pathMatch: 'full' },
  { path: 'catalog-item', component: CatalogItemComponent },
  { path: 'catalog', component: CatalogComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
