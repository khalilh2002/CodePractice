import { Absence } from "./Absence";

export interface Student {
  id: number | undefined | null;
  firstName: string;
  lastName: string;
  absences: Absence[] | null;  // Changed to Absence[] for better consistency
}
