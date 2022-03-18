import { Student } from './student.model';
import { Injectable, EventEmitter } from "@angular/core";
import { HttpClient } from "@angular/common/http"
// We want Angular to do the Dependency Injection for us.

@Injectable()
export class StudentService {

  onStudentAdded=new EventEmitter<Student>();

  //Constructor which forces Depedency Injection of HTTPClient
  constructor(private http: HttpClient) { }

  // Get our list of students from our web serrvice
  getStudents = () => this.http.get<Student[]>('/api/students');

  addStudents = (student: Student) => this.http.post("/api/students", student);
}