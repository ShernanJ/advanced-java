package ca.sheridancollege.javiersh.controllers;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.javiersh.beans.Student;
import ca.sheridancollege.javiersh.repositories.StudentRepository;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class StudentController {
	
	private StudentRepository studentRepo;
	
	@GetMapping("/students")
	public List<Student> getCollection() {
		return studentRepo.findAll();
	}
	
	@GetMapping("/students/{id}")
	public Student getElement(@PathVariable Long id) {
		Optional<Student> student = studentRepo.findById(id);
		if(student.isPresent()) {
			return student.get();
		} else {
			return null;
		}
	}
	
	@PostMapping(value="/students", headers= {"Content-type=application/json"})
	public String postCollection(@RequestBody Student student) {
		Student s = studentRepo.save(student);
		//s has the id of the student
		return s.getName() + " has been added at index " + s.getId();
	}
	
	@PutMapping(value="/students", headers= {"Content-type=application/json"})
	public String putCollection(@RequestBody List<Student> students) {
		// Delete the collection that currently exists
		Long oldCount = studentRepo.count();
		studentRepo.deleteAll();
		//Add the new list of students to the collection
		studentRepo.saveAll(students);
		//Return a confirmation
		//studentRepo.count() // Number of elements in the database
		return oldCount + " records were deleted and there are now " + studentRepo.count() + " records.";
	}
	
	@PutMapping(value="/students/{id}", headers= {"Content-type=application/json"})
	public String putElement(@RequestBody Student student, @PathVariable Long id) {
		String oldStudent = studentRepo.getById(id).getName();
		student.setId(id);
		Student s = studentRepo.save(student);
		return "Student named " + oldStudent + " at index " + s.getId() + " was replaced with " + s.getName();
	}
	
	@DeleteMapping(value="/students", headers= {"Content-type=application/json"})
	public String deleteCollection() {
		studentRepo.deleteAll();
		return "All records were deleted";
	}
	
	@DeleteMapping(value="/students/{id}", headers= {"Content-type=application/json"})
	public String deleteElement(@PathVariable Long id) {
		try {
			String oldStudent = studentRepo.getById(id).getName();
			studentRepo.deleteById(id);
			return "Student " + oldStudent + " in index " + id.toString() + " was deleted.";
		} catch (EntityNotFoundException e) {
			return "No student at index " + id.toString();
		}
	}
}

/**


HTTP Methods for a collection
	
	localhost:8080/<collection plural>

	GET - Retrieve the entire collection
	PUT - Replace the entire collection
	POST - Create a new entry in a collection then return its id
	DELETE - Remote the entire collection
	
HTTP methods for an element
	
	localhost:8080/<collection plural>/{id}

	GET - Retrieve an individual entry from the collection
	PUT - Replace an individual entry in the collection
	POST - Not used
	DELETE - Remove an individual entry from the collection

*/