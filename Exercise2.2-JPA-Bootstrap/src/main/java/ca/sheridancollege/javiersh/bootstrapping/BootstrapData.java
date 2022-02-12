package ca.sheridancollege.javiersh.bootstrapping;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ca.sheridancollege.javiersh.beans.Player;
import ca.sheridancollege.javiersh.repositories.PlayerRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BootstrapData implements CommandLineRunner {
	
	private PlayerRepository playerRepo;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Player p1 = new Player("Master Chief", 100000, 0, 999, 100000.0);
		Player p2 = new Player("Player 2", 10, 8, 3, 100.0);
		Player p3 = new Player("Player3", 2, 5, 5, 20.0);
	
		
		playerRepo.save(p1);
		playerRepo.save(p2);
		playerRepo.save(p3);
	}

}
