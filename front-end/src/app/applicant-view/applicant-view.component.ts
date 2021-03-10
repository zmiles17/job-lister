import { trigger, state, style, transition, animate } from '@angular/animations';
import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Applicant } from '../applicant';
import { Question } from '../question';

@Component({
  selector: 'app-applicant-view',
  templateUrl: './applicant-view.component.html',
  styleUrls: ['./applicant-view.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class ApplicantViewComponent implements OnInit {

  columnsToDisplay = ['Name', 'Phone', 'Email'];
  expandedElement: Applicant | null;
  dataSource: MatTableDataSource<Applicant>;
  questions: Question[];
  propertyColMap = {
    'Name': 'applicantName',
    'Phone': 'phoneNumber',
    'Email': 'email'
  }

  constructor(@Inject(MAT_DIALOG_DATA) data) {
    this.dataSource = new MatTableDataSource(data.applicants);
    this.questions = data.questions;
  }

  ngOnInit(): void {
  }

  getProperty(column: string) {
    return this.propertyColMap[column];
  }

}
