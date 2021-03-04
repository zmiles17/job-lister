import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CreateListingDialogComponent } from '../create-listing-dialog/create-listing-dialog.component';
import { Listing } from '../listing';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  @Output() listingAddedEventEmitter: EventEmitter<Listing> = new EventEmitter<Listing>();

  constructor(public dialog: MatDialog) { }

  ngOnInit(): void {
  }

  openDialog() {
    const dialogRef = this.dialog.open(CreateListingDialogComponent);
    const subscribeDialog = dialogRef.componentInstance.listingAddedEvent.subscribe((data) => {
      this.listingAddedEventEmitter.emit(data);
    });

    dialogRef.afterClosed().subscribe(result => {
      subscribeDialog.unsubscribe();
    });
  }
}

