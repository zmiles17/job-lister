import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
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
import { TextInputComponent } from './text-input/text-input.component';
import { ListingsService } from './listings.service';
import { FormsModule } from '@angular/forms';
import { MatSelectModule } from '@angular/material/select';
import { JobsTableComponent } from './jobs-table/jobs-table.component';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';

@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    SearchFormComponent,
    ListingFormComponent,
    NavbarComponent,
    ListingComponent,
    ListingViewComponent,
    SalarySliderComponent,
    TextInputComponent,
    JobsTableComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MatButtonModule,
    FormsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatPaginatorModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatSelectModule,
    MatSliderModule,
    MatTableModule,
    MatToolbarModule,
    BrowserAnimationsModule
  ],
  providers: [ListingsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
