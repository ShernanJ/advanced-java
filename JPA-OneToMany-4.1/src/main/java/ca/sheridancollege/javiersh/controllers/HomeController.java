package ca.sheridancollege.javiersh.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ca.sheridancollege.javiersh.repositories.EmployeeRepository;
import ca.sheridancollege.javiersh.repositories.StoreRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {
	
	private StoreRepository storeRepo;
	private EmployeeRepository employeeRepo;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("storeList", storeRepo.findAll());
		model.addAttribute("employeeList", employeeRepo.findAll());
		return "index.html";
	}
}
