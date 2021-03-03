import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { Listing } from '../listing';
import { ListingsService } from '../listings.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-jobs-table',
  templateUrl: './jobs-table.component.html',
  styleUrls: ['./jobs-table.component.css']
})
export class JobsTableComponent implements OnInit {

  dataSource: MatTableDataSource<Listing>;
  displayedColumns: string[] = ["name", "company", "industry", "location", "type",
    "salary", "date posted", "star"]
  errorMessage: string;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private service: ListingsService) {
    service.getListings().subscribe(
      listings => {
        this.dataSource = new MatTableDataSource(listings)
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      }
    )
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  deleteListing(listing: Listing) {
    const rowIndex = this.dataSource.data.indexOf(listing);
    this.service.deleteListing(listing.listingId).subscribe(
      () => {
        this.dataSource.data.splice(rowIndex, 1)
        this.dataSource._updateChangeSubscription()
      }
    );
  }

  ngOnInit(): void {
  }


}
