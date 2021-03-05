import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
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
    return this.http.get<Listing[]>(this.baseUrl + `/job/${title}`, this.headers);
  }

  getListingsByIndustry(industry: string): Observable<Listing[]> {
    return this.http.get<Listing[]>(this.baseUrl + `/industry/${industry}`, this.headers);
  }

  getListingsByCompany(company: string): Observable<Listing[]> {
    return this.http.get<Listing[]>(this.baseUrl + `/company/${company}`, this.headers);
  }

  getlistingsByEmploymentType(type: string): Observable<Listing[]> {
    return this.http.get<Listing[]>(this.baseUrl + `/employment/${type}`, this.headers);
  }

  getListingsBySalaryRange(low: number, high: number): Observable<Listing[]> {
    return this.http.get<Listing[]>(this.baseUrl + `/salary/${low}/${high}`, this.headers);
  }

  getListingsByLocation(city: string, state: string): Observable<Listing[]> {
    return this.http.get<Listing[]>(this.baseUrl + `/${city}/${state}`, this.headers);
  }

  getListingsByDaysAgo(daysAgo: number): Observable<Listing[]> {
    return this.http.get<Listing[]>(this.baseUrl + `/datePosted/${daysAgo}`, this.headers);
  }

  addListing(listing: Listing): Observable<Listing> {
    return this.http.post<Listing>(this.baseUrl, listing, this.headers);
  }

  updateListing(listing: Listing): Observable<Listing> {
    return this.http.put<Listing>(this.baseUrl, listing, this.headers);
  }

  deleteListing(id: number): Observable<HttpResponse<object>> {
    return this.http.delete<HttpResponse<object>>(this.baseUrl + `/${id}`, this.headers);
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
