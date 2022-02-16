package ca.sheridancollege.javiersh.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import ca.sheridancollege.javiersh.beans.Student;

@Controller
public class StudentController {
	
	final private String REST_URL = "http://localhost:8081/students/";
	
	@GetMapping("/")
	public String homePage() {
		return "index.html";
	}
	
	@GetMapping("/view")
	public String viewStudentsPage(Model model, RestTemplate restTemplate) {
		
		ResponseEntity<Student[]> responseEntity 
			= restTemplate.getForEntity(REST_URL, Student[].class);
		
		model.addAttribute("students", responseEntity.getBody());
		
		return "viewStudents.html";
	}
	
	@GetMapping("/add")
	public String addStudentsPage(Model model) {
		model.addAttribute("student", new Student());
		return "addStudents.html";
	}
	
	@PostMapping("/add")
	public String goAdd(@ModelAttribute Student student, RestTemplate restTemplate) {
		restTemplate.postForLocation(REST_URL, student);
		
		return "redirect:/add";
	}
	@GetMapping("/edit/{id}")
	public String editLink(@PathVariable Long id, RestTemplate restTemplate, Model model) {
		ResponseEntity<Student> responseEntity = restTemplate.getForEntity(REST_URL + id, Student.class);
		model.addAttribute("student", responseEntity.getBody());
		return "editStudent.html";
	}
	@PostMapping("/edit")
	public String editStudent(@ModelAttribute Student student, RestTemplate restTemplate) {
		restTemplate.put(REST_URL + student.getId(), student);
		return "redirect:/view";
	}
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id, RestTemplate restTemplate) {
		restTemplate.delete(REST_URL + id);
		return "redirect:/view";
	}
}
