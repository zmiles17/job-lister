import { Component, OnInit } from '@angular/core';
import { Listing } from '../listing';
import { ListingsService } from '../listings.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  listings: Listing[] = [];
  errorMessage: string = "";

  constructor(private service: ListingsService) { }

  ngOnInit(): void {
  }

}
