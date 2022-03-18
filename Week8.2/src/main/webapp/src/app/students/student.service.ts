import { Student } from './student.model';
import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http"
// We want Angular to do the Dependency Injection for us.

@Injectable()
export class StudentService {
  //Constructor which forces Depedency Injection of HTTPClient
  constructor(private http: HttpClient) { }

  // Get our list of students from our web serrvice
  getStudents = () => this.http.get<Student[]>('/api/students');
}