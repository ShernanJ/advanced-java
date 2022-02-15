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

import ca.sheridancollege.javiersh.beans.Player;
import ca.sheridancollege.javiersh.repositories.PlayerRepository;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class PlayerController {
	
	private PlayerRepository playerRepo;
	
	@GetMapping("/players")
	public List<Player> getPlayers() {
		return playerRepo.findAll();
	}
	
	@GetMapping("/players/{id}")
	public Player getPlayer(@PathVariable Long id) {
		Optional<Player> player = playerRepo.findById(id);
		if(player.isPresent()) {
			return player.get();
		} else {
			return null;
		}
	}
	
	@PostMapping(value="/players", headers= {"Content-type=application/json"})
	public String createPlayer(@RequestBody Player player) {
		Player p = playerRepo.save(player);
		return p.getName() + " has been added at index " + p.get_id();
	}
	
	@PutMapping(value="/players", headers= {"Content-type=application/json"})
	public String replacePlayers(@RequestBody List<Player> players) {
		Long oldCount = playerRepo.count();
		playerRepo.deleteAll();
		playerRepo.saveAll(players);
		return oldCount + " players were deleted and there are now " + playerRepo.count();
	}
	
	@PutMapping(value="/players/{id}", headers= {"Content-type=application/json"})
	public String replacePlayer(@RequestBody Player player, @PathVariable Long id) {
		try {
			String oldPlayer = playerRepo.getById(id).getName();
			player.set_id(id);
			Player p = playerRepo.save(player);
			return "Player named " + oldPlayer + " at index " + p.get_id() + " was replaced with " + p.getName();
		}
		catch(EntityNotFoundException e) {
			return "Cannot find player at index " + id.toString();
		}
	}
	
	@DeleteMapping(value="/players", headers= {"Content-type=application/json"})
	public String deletePlayers() {
		playerRepo.deleteAll();
		return "All players were deleted";
	}
	
	@DeleteMapping(value="/players/{id}", headers= {"Content-type=application/json"})
	public String deletePlayer(@PathVariable Long id) {
		try {
			String oldPlayer = playerRepo.getById(id).getName();
			playerRepo.deleteById(id);
			return "Player " + oldPlayer + " in index " + id.toString() + " was deleted.";
		}
		 catch(EntityNotFoundException e) {
			 return "No player found at index " + id.toString();
		 }
	}
}
