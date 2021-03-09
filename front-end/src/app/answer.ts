import { Applicant } from "./applicant";
import { Question } from "./question";

export class Answer {
    applicant: Applicant;
    question: Question;
    answer: string;
}
