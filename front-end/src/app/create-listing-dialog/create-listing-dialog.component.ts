import { Component, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { AppConstants } from '../constants';
import { Question } from '../question';

@Component({
  selector: 'app-create-listing-dialog',
  templateUrl: './create-listing-dialog.component.html',
  styleUrls: ['./create-listing-dialog.component.css']
})
export class CreateListingDialogComponent implements OnInit {

  employmentTypes: string[] = AppConstants.employmentTypes;
  states: string[] = AppConstants.states;

  form: FormGroup = new FormGroup({
    listingName: new FormControl('', Validators.required),
    industry: new FormControl('', Validators.required),
    company: new FormControl('', Validators.required),
    salary: new FormControl('', [Validators.required, Validators.min(0), Validators.max(500000)]),
    state: new FormControl('', Validators.required),
    city: new FormControl('', Validators.required),
    employmentType: new FormControl('', Validators.required),
    questions: new FormArray([])
  });

  get questions() {
    return this.form.get('questions') as FormArray;
  }

  constructor() { 
     
  }

  ngOnInit(): void {
  }

  addQuestion() {
    this.questions.push(new FormControl('', Validators.nullValidator));
  }

}
