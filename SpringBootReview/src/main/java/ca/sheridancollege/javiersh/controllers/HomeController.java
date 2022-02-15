package ca.sheridancollege.javiersh.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

//import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	@GetMapping("/edit/{id}")
	public String editGame(@PathVariable int id, Model model) {
		Optional<Videogame> game = gameRepo.findById(id);
		//game.isPresent() Returns true if the element was found
		//game.get() Retrieves the specified element
		if(game.isPresent()) {
			Videogame selectedGame = game.get();
			model.addAttribute("videogame", selectedGame);
			return "editgame.html";
		} else {
			return "redirect:/view";
		}
	}
	@PostMapping("/save")
	public String editGame(@ModelAttribute Videogame videogame, Model model) {
		gameRepo.save(videogame);
		return "redirect:/view";
	}
	@GetMapping("/delete/{id}")
	public String deleteGame(@PathVariable int id, Model model) {
		gameRepo.deleteById(id);
		return "redirect:/view";
	}
	
	@GetMapping("/searchGenre/{genre}")
	public String viewGames(@PathVariable String genre, Model model) {
		List<Videogame> games = gameRepo.retrieveGenreIgnoreCase(genre);
		model.addAttribute("mygames", games);
		return "viewgames.html";
	}
	
	@GetMapping("searchPriceLessThan/{price}")
	public String searchPriceLessThan(@PathVariable double price, Model model) {
		List<Videogame> games = gameRepo.findbyPriceLessThan(price);
		model.addAttribute("mygames", games);
		return "viewgames.html";
	}
	
	@GetMapping("/page/{num}")
	public String page(Model model, @PathVariable int num) {
		//(The page number, # per page)
		Pageable page = PageRequest.of(num-1, 4);
		model.addAttribute("mygames", gameRepo.findBy(page));
		return "viewGames.html";
	}
}
