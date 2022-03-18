import { StudentService } from './../student.service';
import { Component, OnInit } from '@angular/core';
import { Student } from "../student.model";

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css']
})
export class StudentListComponent implements OnInit {

  // Define the Students array that will be displayed in the list.
  students: Student[] = [];

  // Connect to the StudentService class
  constructor(private studentService: StudentService) { }

  // We make a call to our getStudents() method from our student service
  // If there are changes to be made, set the students array and log any
  // errors to the console. 
  ngOnInit() {
    this.studentService.getStudents()
      .subscribe(
        (students:any[]) => {
          this.students = students
        },
        (err) => console.log(err)
      );
      this.studentService.onStudentAdded.subscribe(
        (student: Student) => this.students.push(student)
      );
  }

}
