import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomepageComponent } from './homepage/homepage.component';
import { SearchFormComponent } from './search-form/search-form.component';
import { ListingFormComponent } from './listing-form/listing-form.component';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSliderModule } from '@angular/material/slider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { NavbarComponent } from './navbar/navbar.component';
import { ListingComponent } from './listing/listing.component';
import { ListingViewComponent } from './listing-view/listing-view.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SalarySliderComponent } from './salary-slider/salary-slider.component';

@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    SearchFormComponent,
    ListingFormComponent,
    NavbarComponent,
    ListingComponent,
    ListingViewComponent,
    SalarySliderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatSliderModule,
    MatToolbarModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
