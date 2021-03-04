import { Component, OnInit } from '@angular/core';
import { Listing } from '../listing';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  listingToAdd: Listing;

  constructor() {
  }

  ngOnInit(): void {
  }

  listingAdded(listing: Listing) {
    this.listingToAdd = listing;
  }

}
