import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { Listing } from './listing';

@Injectable({
  providedIn: 'root'
})
export class ListingsService {

  private baseUrl: string = "http://localhost:8080/api/listings"
  private headers: object = {
    "Content-Type": "application/json"
  }

  constructor(private http: HttpClient) {
  }

  getListings(): Observable<Listing[]> {
    return this.http.get<Listing[]>(this.baseUrl, this.headers);
  }

  getListingsByJobTitle(title: string): Observable<Listing[]> {
    return this.http.get<Listing[]>(this.baseUrl + `/job/${title}`, this.headers)
      .pipe(
        tap(data => console.log(data)),
        catchError(this.handleError)
      );
  }

  addListing(listing: Listing): Observable<Listing> {
    return this.http.post<Listing>(this.baseUrl, listing, this.headers);
  }

  private handleError(err: any): Observable<never> {
    // in a real world app, we may send the server to some remote logging infrastructure
    // instead of just logging it to the console
    let errorMessage: string;
    if (err.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      errorMessage = `An error occurred: ${err.error.message}`;
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      errorMessage = `Backend returned code ${err.status}: ${err.body.error}`;
    }
    console.error(err);
    return throwError(errorMessage);
  }

}
