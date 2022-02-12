package ca.sheridancollege.javiersh.bootstrapping;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ca.sheridancollege.javiersh.beans.Videogame;
import ca.sheridancollege.javiersh.repositories.VideogameRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BootstrapData implements CommandLineRunner {
	
	private VideogameRepository gameRepo;
	
	@Override
	public void run(String... args) throws Exception {
		Videogame g1 = new Videogame("Test Game 1", 12.99, "Horror");
		Videogame g2 = new Videogame("Test Game 2", 22.99, "RPG");
		Videogame g3 = new Videogame("Test Game 3", 32.99, "Strategy");
		Videogame g4 = new Videogame("Test Game 4", 42.99, "Fighting");
		
		gameRepo.save(g1);
		gameRepo.save(g2);
		gameRepo.save(g3);
		gameRepo.save(g4);
	}

}
