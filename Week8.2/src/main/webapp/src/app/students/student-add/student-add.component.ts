import { StudentService } from './../student.service';
import { Student } from './../student.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-student-add',
  templateUrl: './student-add.component.html',
  styleUrls: ['./student-add.component.css']
})
export class StudentAddComponent implements OnInit {

  addStudentValue='';

  constructor(private studentSerive : StudentService) { }

  ngOnInit(): void {
  }

  onStudentAdd = (event: any) => {
    //Get the value in the input and store it in student variable
    let student: Student = new Student((<HTMLInputElement>event.target).value);

    // Call my addStudent function in my StudentService
    this.studentSerive.addStudents(student).subscribe(
      (newStudent : any) => {
        this.addStudentValue = '';
        this.studentSerive.onStudentAdded.emit(newStudent);
      }
    )
  }
}
