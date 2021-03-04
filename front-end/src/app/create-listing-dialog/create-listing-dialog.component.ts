import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Listing } from '../listing';
import { ListingsService } from '../listings.service';

@Component({
  selector: 'app-create-listing-dialog',
  templateUrl: './create-listing-dialog.component.html',
  styleUrls: ['./create-listing-dialog.component.css']
})
export class CreateListingDialogComponent implements OnInit {

  listingName: string;
  company: string;
  industry: string;
  employmentType: string;
  salary: number;
  city: string;
  selectedState: string;
  employmentTypes: string[] = ["Part-Time", "Full-Time", "Contract", "Temporary", "Remote"]
  states: string[] = [
    'Alabama', 'Alaska', 'Arizona', 'Arkansas', 'California', 'Colorado', 'Connecticut', 'Delaware',
    'Florida', 'Georgia', 'Hawaii', 'Idaho', 'Illinois', 'Indiana', 'Iowa', 'Kansas', 'Kentucky',
    'Louisiana', 'Maine', 'Maryland', 'Massachusetts', 'Michigan', 'Minnesota', 'Mississippi',
    'Missouri', 'Montana', 'Nebraska', 'Nevada', 'New Hampshire', 'New Jersey', 'New Mexico',
    'New York', 'North Carolina', 'North Dakota', 'Ohio', 'Oklahoma', 'Oregon', 'Pennsylvania',
    'Rhode Island', 'South Carolina', 'South Dakota', 'Tennessee', 'Texas', 'Utah', 'Vermont',
    'Virginia', 'Washington', 'West Virginia', 'Wisconsin', 'Wyoming'
  ];
  questionCount: number = 0;

  @Output() listingAddedEvent: EventEmitter<Listing> = new EventEmitter<Listing>();

  numberFormControl = new FormControl(0, [
    Validators.min(0),
    Validators.required
  ]);

  textFormControl = new FormControl('', [
    Validators.required,
  ]);

  constructor(private service: ListingsService) {
      
  }

  ngOnInit(): void {
    
  }

  

  createListingRequest() {
    this.service.addListing(new Listing(
      this.listingName, this.company, this.salary, this.industry, this.employmentType, this.city, this.selectedState)
    ).subscribe(
      listing => this.listingAddedEvent.emit(listing)
    )
  }

  addQuestion() {
    this.questionCount++;
  }

}
