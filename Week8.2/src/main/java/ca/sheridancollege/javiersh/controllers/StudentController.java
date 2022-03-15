package ca.sheridancollege.javiersh.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.javiersh.beans.Student;
import ca.sheridancollege.javiersh.services.StudentService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor

@RequestMapping("/api/students")
public class StudentController {
	
	private StudentService stuService;
	
	@GetMapping(value= {"/",""})
	public List<Student> getCollection() {
		return stuService.findAll();
	}
	
	@GetMapping(value= {"/{id}"})
	public Student getElementById(@PathVariable Long id) {
		return stuService.findById(id);
	}
	
	@GetMapping(value= {"/name/{name}"})
	public Student getElementByName(@PathVariable String name) {
		return stuService.findByName(name);
	}
	
	@PostMapping(value={"/", ""}, headers= {"Content-type=application/json"})
	public Student postCollection(@RequestBody Student student) {
		return stuService.save(student);
	}
}
