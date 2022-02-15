package ca.sheridancollege.javiersh.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ca.sheridancollege.javiersh.repositories.CustomerRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {
	
	private CustomerRepository custRepo;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("customerList", custRepo.findByAccount_IdIsNotNull());
		return "index.html";
	}
}
