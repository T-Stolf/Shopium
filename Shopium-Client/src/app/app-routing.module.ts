import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { CatalogComponent } from './Components/catalog/catalog.component';
import { CatalogItemComponent } from './Components/catalog-item/catalog-item.component';
import { CartComponent } from './Components/cart/cart.component';
import { UserpageComponent } from './Components/userpage/userpage.component';
import { UserProfileComponent } from './Components/user-profile/user-profile.component';

const routes: Routes = [
  { path: '', redirectTo: '/catalog', pathMatch: 'full' },
  { path: 'catalog-item', component: CatalogItemComponent },
  { path: 'catalog', component: CatalogComponent },
  { path: 'cart', component: CartComponent },
  { path: 'userpage', component: UserpageComponent },
  { path: 'userprofile', component: UserProfileComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
