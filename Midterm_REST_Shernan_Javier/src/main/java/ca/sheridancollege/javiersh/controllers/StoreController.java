package ca.sheridancollege.javiersh.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.javiersh.beans.Store;
import ca.sheridancollege.javiersh.repositories.StoreRepository;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class StoreController {
	
	private StoreRepository storeRepo;
	
	@GetMapping("/stores")
	public List<Store> getAllStores() {
		return storeRepo.findAll();
	}
	
	@GetMapping("/stores/{id}")
	public Store getStore(@PathVariable Long id) {
		Optional<Store> store = storeRepo.findById(id);
		
		if(store.isPresent()) {
			return store.get();
		} else {
			return null;
		}
	}
}
