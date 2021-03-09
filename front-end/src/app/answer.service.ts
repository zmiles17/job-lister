import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Answer } from './answer';

@Injectable({
  providedIn: 'root'
})
export class AnswerService {

  private baseUrl: string = "http://localhost:8080/api/answers";
  private headers: object = {
    "Content-Type": "application/json"
  }

  constructor(private http: HttpClient) { }

  saveAnswers(listingId: number, answers: Answer[]) {
    return this.http.post<Answer[]>(this.baseUrl + `/listing/${listingId}`, answers, this.headers);
  }
}
