package ca.sheridancollege.javiersh.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.javiersh.beans.Player;
import ca.sheridancollege.javiersh.repositories.PlayerRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {
	
	private PlayerRepository playerRepo;
	
	@GetMapping("/")
	public String home() {
		return "index.html";
	}
	
	// Add player page
	@GetMapping("/add")
	public String serveAddPlayerPage(Model model) {
		model.addAttribute("player", new Player());
		return "addPlayer.html";
	}
	
	@PostMapping("/add")
	public String handleAddPlayer(@ModelAttribute Player player, Model model) {
		playerRepo.save(player);
		model.addAttribute("player", new Player());
		return "redirect:/add";
	}
	
	@GetMapping("/view")
	public String viewPlayers(Model model) {
		model.addAttribute("players", playerRepo.findAll());
		return "viewPlayers.html";
	}
}
