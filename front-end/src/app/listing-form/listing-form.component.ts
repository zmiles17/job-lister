import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { AppConstants } from '../constants';
import { Listing } from '../listing';
import { ListingsService } from '../listings.service';

@Component({
  selector: 'app-listing-form',
  templateUrl: './listing-form.component.html',
  styleUrls: ['./listing-form.component.css']
})
export class ListingFormComponent implements OnInit {

  jobTitle: string;
  company: string;
  industry: string;
  employmentType: string;
  states: string[] = AppConstants.states;
  employmentTypes: string[] = AppConstants.employmentTypes;
  selectedState: string;
  city: string;
  salary: number;
  dateSelected: Date;

  today: Date = new Date();

  searchOptions: string[] = ['None', 'Job Title', 'Industry', 'Company', 'Salary', 'Employment Type', 'Location', 'Date Posted'];

  searchBy: string;

  @Output() searchListingsEventEmitter: EventEmitter<Listing[]> = new EventEmitter<Listing[]>();

  constructor(private service: ListingsService) {
  }

  ngOnInit(): void {
  }

  formatLabel(value: number) {
    if (value >= 1000) {
      return Math.round(value / 1000) + 'k';
    }

    return value;
  }

  search() {
    switch (this.searchBy) {
      case 'Job Title':
        if (this.jobTitle) {
          this.service.getListingsByJobTitle(this.jobTitle).subscribe(
            response => this.searchListingsEventEmitter.emit(response),
            error => console.log(error)
          )
        }
        break;
      case 'Industry':
        if (this.industry) {
          this.service.getListingsByIndustry(this.industry).subscribe(
            res => this.searchListingsEventEmitter.emit(res),
            error => console.log(error)
          )
        }
        break;
      case 'Company':
        if (this.company) {
          this.service.getListingsByCompany(this.company).subscribe(
            res => this.searchListingsEventEmitter.emit(res),
            err => console.log(err)
          )
        }
        break;
      case 'Salary':
        if (this.salary >= 0) {
          this.service.getListingsBySalaryRange(0, this.salary).subscribe(
            res => this.searchListingsEventEmitter.emit(res),
            err => console.log(err)
          )
        }
        break;
      case 'Employment Type':
        if (this.employmentType) {
          this.service.getlistingsByEmploymentType(this.employmentType).subscribe(
            res => this.searchListingsEventEmitter.emit(res),
            err => console.log(err)
          )
        }
        break;
      case 'Location':
        if (this.city && this.selectedState) {
          this.service.getListingsByLocation(this.city, this.selectedState).subscribe(
            res => this.searchListingsEventEmitter.emit(res),
            err => console.log(err)
          )
        }
        break;
      case 'Date Posted':
        const milliseconds = this.today.getTime() - this.dateSelected.getTime();
        const daysAgo = Math.round(milliseconds / (1000 * 60 * 60 * 24));
        this.service.getListingsByDaysAgo(daysAgo).subscribe(
          res => this.searchListingsEventEmitter.emit(res),
          err => console.log(err)
        )
        break;
      default:
        this.service.getListings().subscribe(
          res => this.searchListingsEventEmitter.emit(res),
          err => console.log(err)
        )
        break;
    }
  }
}
