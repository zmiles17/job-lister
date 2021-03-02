import { Applicant } from "./applicant";
import { Question } from "./question";

export interface Listing {
    [index: string]: any;
    listingId?: number;
    listingName: string;
    company: string;
    salary: number;
    industry: string;
    employmentType: string;
    city: string;
    state: string;
    datePosted?: Date;
    questions?: Question[];
    applicants?: Applicant[];
}
