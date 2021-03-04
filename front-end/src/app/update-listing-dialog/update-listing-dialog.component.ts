import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Listing } from '../listing';

@Component({
  selector: 'app-update-listing-dialog',
  templateUrl: './update-listing-dialog.component.html',
  styleUrls: ['./update-listing-dialog.component.css']
})
export class UpdateListingDialogComponent implements OnInit {

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
  form = new FormGroup({
    listingName: new FormControl('listingName', Validators.required),
    industry: new FormControl('industry', Validators.required),
    company: new FormControl('industry', Validators.required),
    salary: new FormControl('salary', [Validators.required, Validators.min(0), Validators.max(200000)]),
    selectedState: new FormControl('selectedState', Validators.required),
    city: new FormControl('city', Validators.required),
    employmentType: new FormControl('employmentType', Validators.required)
  })

  constructor(@Inject(MAT_DIALOG_DATA) public data: Listing) {
   }

  ngOnInit(): void {
  }

}
