import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CreateListingDialogComponent } from '../create-listing-dialog/create-listing-dialog.component';
import { Listing } from '../listing';
import { ListingsService } from '../listings.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  @Output() listingAddedEventEmitter: EventEmitter<Listing> = new EventEmitter<Listing>();

  constructor(public dialog: MatDialog, private service: ListingsService) { }

  ngOnInit(): void {
  }

  openDialog() {
    const dialogRef = this.dialog.open(CreateListingDialogComponent);

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.service.addListing(result).subscribe(
          listing => this.listingAddedEventEmitter.emit(listing),
          error => console.log(error)
        )
      }
    });
  }

  homePage() {
    //redirect to homepage url
  }
}

