import { HttpClientModule, HttpClientXsrfModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { CatalogItemComponent } from './Components/catalog-item/catalog-item.component';
import { CatalogComponent } from './Components/catalog/catalog.component';
import { CartComponent } from './Components/cart/cart.component';
import { TokenIntercepterService } from './token-intercepter.service';
import { UserpageComponent } from './Components/userpage/userpage.component';
import { UserProfileComponent } from './Components/user-profile/user-profile.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent,
    CatalogComponent,
    CatalogItemComponent,
    CartComponent,
    UserpageComponent,
    UserProfileComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    HttpClientXsrfModule,
    BrowserAnimationsModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenIntercepterService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
