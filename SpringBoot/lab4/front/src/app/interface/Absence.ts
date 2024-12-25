import { Student } from "./Student";

export interface Absence {
haveReason: boolean;
id: any;
  reason: string;
  date: string;  // Use string for the date instead of Date, since the backend sends it as a string
  student: Student | number;  // Can be a Student object or the student ID
}
