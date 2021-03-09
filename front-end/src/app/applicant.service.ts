import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Applicant } from './applicant';

@Injectable({
  providedIn: 'root'
})
export class ApplicantService {

  private baseUrl: string = "http://localhost:8080/api";
  private headers: object = {
    "Content-Type": "application/json"
  }

  constructor(private http: HttpClient) { }

  saveApplicant(applicant: Applicant): Observable<Applicant> {
    return this.http.post<Applicant>(this.baseUrl + "/applicant", applicant, this.headers);
  }
}
