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

import ca.sheridancollege.javiersh.beans.Toy;
import ca.sheridancollege.javiersh.repositories.ToyRepository;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ToyController {
	
	private ToyRepository toyRepo;
	
	/**
	 * Get - For all toys
	 * @return
	 */
	@GetMapping("/toys")
	public List<Toy> getAllToys() {
		return toyRepo.findAll();
	}
	
	/**
	 * Get - For a single toy by id 
	 * @param id
	 * @return
	 */
	@GetMapping("/toys/{id}")
	public Toy getToy(@PathVariable Long id) {
		Optional<Toy> toy = toyRepo.findById(id);
		
		if(toy.isPresent()) {
			return toy.get();
		} else {
			return null;
		}
	}
	
	/**
	 * Post - A new toy
	 * @param toy
	 * @return
	 */
	@PostMapping(value="/toys", headers= {"Content-type=application/json"})
	public String AddToy(@RequestBody Toy toy) {
		Toy t = toyRepo.save(toy);
		
		return t.getName() + " has been added at index " + t.getId();
	}
	
	/**
	 * Put - An edited toy by id
	 * @param toy
	 * @param id
	 * @return
	 */
	@PutMapping(value="/toys/{id}", headers= {"Content-type=application/json"})
	public String editToy(@RequestBody Toy toy, @PathVariable Long id) {
		String oldToy = toyRepo.getById(id).getName();
		toy.setId(id);
		Toy t = toyRepo.save(toy);
		return "Toy previoused named" + oldToy + " at index " + t.getId() + " was edited.";
	}
	/**
	 * Delete - Delete a toy by id
	 * @param id
	 * @return
	 */
	@DeleteMapping(value="/toys/{id}", headers= {"Content-type=application/json"})
	public String deleteToy(@PathVariable Long id) {
		try {
			String oldToy = toyRepo.getById(id).getName();
			toyRepo.deleteById(id);
			return "Toy named " + oldToy + " in index " + id.toString() + " was deleted.";
		} catch (EntityNotFoundException e) {
			return "No Toy at index " + id.toString();
		}
	}
}
