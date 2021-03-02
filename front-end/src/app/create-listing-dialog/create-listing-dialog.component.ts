import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-create-listing-dialog',
  templateUrl: './create-listing-dialog.component.html',
  styleUrls: ['./create-listing-dialog.component.css']
})
export class CreateListingDialogComponent implements OnInit {

  listingName: string = "";
  company: string = "";
  industry: string = "";
  employmentTypes: string[] = ["Part-Time", "Full-Time", "Contract", "Temporary", "Remote"]
  employmentType: string = "";
  salary: number = NaN;
  city: string = "";
  state: string = "";

  constructor() { }

  ngOnInit(): void {
  }

}
