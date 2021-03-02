import { Answer } from "./answer";

export interface Applicant {
    applicantId?: number;
    applicantName: string;
    phoneNumber: string;
    email: string;
    answers: Answer[];
}
