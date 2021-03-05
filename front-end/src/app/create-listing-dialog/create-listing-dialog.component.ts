import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AppConstants } from '../constants';

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
  employmentTypes: string[] = AppConstants.employmentTypes;
  states: string[] = AppConstants.states;
  questionCount: number = 0;

  form: FormGroup = new FormGroup({
    listingName: new FormControl('', Validators.required),
    industry: new FormControl('', Validators.required),
    company: new FormControl('', Validators.required),
    salary: new FormControl('', [Validators.required, Validators.min(0), Validators.max(500000)]),
    state: new FormControl('', Validators.required),
    city: new FormControl('', Validators.required),
    employmentType: new FormControl('', Validators.required)
  });

  constructor() {  
  }

  ngOnInit(): void {
  }

  addQuestion() {
    this.questionCount++;
  }

}
