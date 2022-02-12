package ca.sheridancollege.javiersh.controllers;

//import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.javiersh.beans.Videogame;
import ca.sheridancollege.javiersh.repositories.VideogameRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {
	
	// Temporary for my database
//	static ArrayList<Videogame> games = new ArrayList<Videogame>();
	
	private VideogameRepository gameRepo;
	
	@GetMapping("/")
	public String goHomePage() {
		return "rootpage.html";
	}
	
	//Load the add game page
	@GetMapping("/add")
	public String loadAddPage(Model model) {
		//Model allows us to store attributes that can be access by an HTML Page
		model.addAttribute("videogame", new Videogame());
		return "addGame.html";
	}
	
	//Process the form action
	@PostMapping("/add")
	public String saveGame(@ModelAttribute Videogame videogame, Model model) {
		//games.add(videogame);
		gameRepo.save(videogame);
		model.addAttribute("videogame", new Videogame());
		return "redirect:/add";
	}
	
	@GetMapping("/view")
	public String viewGames(Model model) {
		model.addAttribute("mygames", gameRepo.findAll());
		return "viewGames.html";
	}
}
