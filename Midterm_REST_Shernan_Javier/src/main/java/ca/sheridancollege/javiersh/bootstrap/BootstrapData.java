package ca.sheridancollege.javiersh.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ca.sheridancollege.javiersh.beans.Store;
import ca.sheridancollege.javiersh.beans.Toy;
import ca.sheridancollege.javiersh.repositories.StoreRepository;
import ca.sheridancollege.javiersh.repositories.ToyRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BootstrapData implements CommandLineRunner {
	
	private StoreRepository storeRepo;
	private ToyRepository toyRepo;
	
	@Override
	public void run(String...args) throws Exception {
		
		String[] storeNames = {"ToonTown", "ForKids", "CoolerToys"};
		for (String n : storeNames) {
			Store store = Store.builder()
					.name(n)
					.build();
			storeRepo.save(store);
		}
		
		String[] toyNames = {"Spider-Man", "Iron Man", "Batman", "Eren Yeager", "Barbie", "Kanye West", "Car", "Sword", "Pickaxe", "Helicopter", "Boat"};
		for(String n : toyNames) {
			Toy toy = Toy.builder()
					.name(n)
					.storeName(storeNames[(int)(Math.random()*(2))])
					.price((Math.round(((double) (Math.random()*(200.00 - 10.00))) + 10.00)*100.00)/100.00)
					.quantity((int)(Math.random()*(100 - 1)) + 1)
					.build();
			toyRepo.save(toy);
		}
	}
	
}
