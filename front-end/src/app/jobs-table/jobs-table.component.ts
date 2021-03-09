import { Component, Input, OnChanges, OnInit, ViewChild } from '@angular/core';
import { Listing } from '../listing';
import { ListingsService } from '../listings.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { UpdateListingDialogComponent } from '../update-listing-dialog/update-listing-dialog.component';
import { ConfirmationDialogComponent } from '../confirmation-dialog/confirmation-dialog.component';
import { ApplicantDialogFormComponent } from '../applicant-dialog-form/applicant-dialog-form.component';
import { AnswerService } from '../answer.service';
import { ApplicantViewComponent } from '../applicant-view/applicant-view.component';


@Component({
  selector: 'app-jobs-table',
  templateUrl: './jobs-table.component.html',
  styleUrls: ['./jobs-table.component.css']
})
export class JobsTableComponent implements OnChanges {

  dataSource: MatTableDataSource<Listing>;
  displayedColumns: string[] = ["name", "company", "industry", "location", "type",
    "salary", "date posted", "star"]
  errorMessage: string;

  @Input() listings: Listing[];
  @Input() listingToAdd: Listing;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private listingService: ListingsService, private answerService: AnswerService, public dialog: MatDialog) {
    listingService.getListings().subscribe(
      listings => {
        this.dataSource = new MatTableDataSource(listings)
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
        this.dataSource.sortingDataAccessor = (item, property) => {
          switch (property) {
            case 'name': return item.listingName;
            case 'type': return item.employmentType;
            case 'location': return item.city;
            case 'date posted': return new Date(item.datePosted);
            default: return item[property];
          }
        };
      }
    )
  }


  ngOnChanges(): void {
    if (this.listingToAdd) {
      this.dataSource.data.push(this.listingToAdd);
      this.dataSource._updateChangeSubscription();
    }

    if (this.listings) {
      this.dataSource.data = this.listings;
      this.dataSource._updateChangeSubscription();
    }

  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  openApplication(listing: Listing) {
    const data = {
      listingId: listing.listingId,
      listingName: listing.listingName,
      company: listing.company,
      industry: listing.industry,
      employmentType: listing.employmentType,
      salary: listing.salary,
      city: listing.city,
      state: listing.state,
      questions: listing.questions
    }
    const dialogRef = this.dialog.open(ApplicantDialogFormComponent, { data });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        const answers = [];
        for (let answer of result.answers) {
          const i = result.answers.indexOf(answer);
          answers.push({
            applicant: {
              applicantName: result.applicantName,
              phoneNumber: result.phoneNumber,
              email: result.email
            },
            question: {
              questionId: data.questions[i].questionId,
              question: data.questions[i].question
            },
            answer
          });
        }
        this.answerService.saveAnswers(listing.listingId, answers).subscribe(answers => {
          const index = this.dataSource.data.indexOf(listing);
          this.listingService.getListingById(listing.listingId).subscribe(response => {
            this.dataSource.data[index] = response;
            this.dataSource._updateChangeSubscription();
          }, error => console.log(error));
        });
      } else {
        console.log("Application cancelled");
      }
    });
  }

  viewApplicants(listing: Listing) {
    this.dialog.open(ApplicantViewComponent, {
      data: {
        applicants: listing.applicants
      }
    })
  }

  deleteListing(listing: Listing) {
    const rowIndex = this.dataSource.data.indexOf(listing);
    const dialogRef = this.dialog.open(ConfirmationDialogComponent);
    dialogRef.afterClosed().subscribe(result => result ? this.listingService.deleteListing(listing.listingId).subscribe(
      () => {
        this.dataSource.data.splice(rowIndex, 1)
        this.dataSource._updateChangeSubscription()
      }
    ) : console.log("User wasn't sure"))
  }

  updateListing(listing: Listing) {
    const rowIndex = this.dataSource.data.indexOf(listing);
    const data = {
      listingName: listing.listingName,
      company: listing.company,
      industry: listing.industry,
      employmentType: listing.employmentType,
      salary: listing.salary,
      city: listing.city,
      state: listing.state
    };

    const dialogRef = this.dialog.open(UpdateListingDialogComponent, { data });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== '' && data !== result) {
        result['listingId'] = listing.listingId;
        this.listingService.updateListing(result).subscribe(
          updatedListing => {
            this.dataSource.data[rowIndex] = updatedListing;
            this.dataSource._updateChangeSubscription();
          },
          error => console.log(error)
        )
      } else {
        console.log('Update did not occur');
      }
    });
  }
}
