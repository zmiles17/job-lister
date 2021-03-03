import { Applicant } from "./applicant";
import { Question } from "./question";

export class Listing {
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

    constructor(listingName: string, company: string, salary: number, industry: string, employmentType: string,
        city: string, state: string) {
            this.listingName = listingName;
            this.company = company;
            this.salary = salary;
            this.industry = industry;
            this.employmentType = employmentType;
            this.city = city;
            this.state = state;
        }
}
