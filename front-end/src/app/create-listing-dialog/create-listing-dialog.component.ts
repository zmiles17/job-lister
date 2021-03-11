import { ChangeDetectorRef, Component, OnInit, OnChanges } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { AppConstants } from '../constants';

@Component({
  selector: 'app-create-listing-dialog',
  templateUrl: './create-listing-dialog.component.html',
  styleUrls: ['./create-listing-dialog.component.css']
})
export class CreateListingDialogComponent implements OnChanges {

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
    questions: new FormArray([new FormControl('', Validators.required)])
  });

  get questions() {
    return this.form.get('questions') as FormArray;
  }

  constructor(private cd: ChangeDetectorRef) { }

  ngOnChanges(): void {
    this.cd.detectChanges();
  }

  addQuestion(): void {
    this.questions.push(new FormControl('', Validators.required));
  }

  deleteQuestion(index: number): void {
    this.questions.removeAt(index);
  }
}
