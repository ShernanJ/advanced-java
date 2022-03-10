package ca.sheridancollege.javiersh.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.client.RestTemplate;

import ca.sheridancollege.javiersh.beans.Store;
import ca.sheridancollege.javiersh.beans.Toy;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class HomeController {
	
	final private String TOYS_REST_URL = "http://localhost:8085/toys/";
	final private String STORES_REST_URL = "http://localhost:8085/stores/";

	@GetMapping("/")
	public String rootPage(Model model, RestTemplate restTemplate) {
		
		ResponseEntity<Toy[]> responseEntity
		= restTemplate.getForEntity(TOYS_REST_URL, Toy[].class);
		
		model.addAttribute("toys", responseEntity.getBody());
		
		return "index.html";
	}
	
	@GetMapping("/add")
	public String addToysPage(Model model, RestTemplate restTemplate) {
		model.addAttribute("toy", new Toy());
		
		ResponseEntity<Store[]> responseEntity 
		= restTemplate.getForEntity(STORES_REST_URL, Store[].class);
		
		model.addAttribute("stores", responseEntity.getBody());
		
		return "addToys.html";
	}
	
	@PostMapping("/add")
	public String addToy(@ModelAttribute Toy toy, RestTemplate restTemplate) {
		restTemplate.postForLocation(TOYS_REST_URL, toy);
		
		return "redirect:/add";
	}
	
	@PutMapping("/purchase/{id}")
	public String purchaseToy(@PathVariable Long id, RestTemplate restTemplate, Model model) {
		ResponseEntity<Toy> responseEntity
		= restTemplate.getForEntity(TOYS_REST_URL, Toy.class);
		
		model.addAttribute("toy", responseEntity.getBody());
		
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public String editLink(@PathVariable Long id, RestTemplate restTemplate, Model model) {
		ResponseEntity<Toy> responseEntity = restTemplate.getForEntity(TOYS_REST_URL + id, Toy.class);
		model.addAttribute("toy", responseEntity.getBody());
		
		ResponseEntity<Store[]> storeResponseEntity 
		= restTemplate.getForEntity(STORES_REST_URL, Store[].class);
		
		model.addAttribute("stores", storeResponseEntity.getBody());
		
		return "editToys.html";
	}
	
	@PostMapping("/edit")
	public String editStudent(@ModelAttribute Toy toy, RestTemplate restTemplate) {
		restTemplate.put(TOYS_REST_URL + toy.getId(), toy);
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id, RestTemplate restTemplate) {
		restTemplate.delete(TOYS_REST_URL + id);
		return "redirect:/";
	}
	
}
