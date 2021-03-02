import { Component, Input, OnInit, AfterViewInit, ViewChild } from '@angular/core';
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
export class JobsTableComponent implements OnInit, AfterViewInit {

  dataSource!: MatTableDataSource<Listing>;
  displayedColumns: string[] = ["name", "company", "industry", "location", "type",
    "salary", "date posted"]
  errorMessage: string = "";

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
  @ViewChild(MatSort)
  sort!: MatSort;

  constructor(private service: ListingsService) {
      service.getListings().subscribe(
        listings => this.dataSource = new MatTableDataSource(listings)

      )
   }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  ngOnInit(): void {
  }


}
