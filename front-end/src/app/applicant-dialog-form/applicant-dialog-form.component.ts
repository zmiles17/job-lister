import { Component, Inject, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Listing } from '../listing';

@Component({
  selector: 'app-applicant-dialog-form',
  templateUrl: './applicant-dialog-form.component.html',
  styleUrls: ['./applicant-dialog-form.component.css']
})
export class ApplicantDialogFormComponent implements OnInit {

  applicationForm = new FormGroup({
    applicantName: new FormControl('', [Validators.required]),
    phoneNumber: new FormControl('', [Validators.required, Validators.minLength(10)]),
    email: new FormControl('', [Validators.required, Validators.email]),
    answers: new FormArray([])
  })

  get answers() {
    return this.applicationForm.get('answers') as FormArray;
  }

  constructor(@Inject(MAT_DIALOG_DATA) public data: Listing) { 
    data.questions.forEach(q => this.answers.push(new FormControl('', Validators.required)))
  }

  ngOnInit(): void {
  
  }

}
