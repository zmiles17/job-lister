import { Component, Inject, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Listing } from '../listing';
import { AppConstants } from '../constants';

@Component({
  selector: 'app-update-listing-dialog',
  templateUrl: './update-listing-dialog.component.html',
  styleUrls: ['./update-listing-dialog.component.css']
})
export class UpdateListingDialogComponent implements OnInit {

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
  })

  get questions() {
    return this.form.get('questions') as FormArray;
  }

  constructor(@Inject(MAT_DIALOG_DATA) public data: Listing) {
    this.form.get('listingName').setValue(data.listingName);
    this.form.get('industry').setValue(data.industry);
    this.form.get('company').setValue(data.company);
    this.form.get('salary').setValue(data.salary);
    this.form.get('city').setValue(data.city);
    this.form.get('state').setValue(data.state);
    this.form.get('employmentType').setValue(data.employmentType);
    data.questions.forEach(q => this.questions.push(new FormControl(q.question, Validators.required)))
   }

  ngOnInit(): void {
  }

  addQuestion() {
    this.questions.push(new FormControl('', Validators.required))
  }

  deleteQuestion(index: number) {
    this.questions.removeAt(index);
  }

}
