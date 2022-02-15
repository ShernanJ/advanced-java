package ca.sheridancollege.javiersh.bootstrap;

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
	public void run(String...args) throws Exception {
		String[] names = {"Kanye West", "Shernan Javier", "Andres Munevar", "Jacob Brasil", "Omar Abu Samra"};
		String[] classes = {"Fighter", "Assassin", "Mage", "Tank", "Ranged", "Support"};
		for(String s : names) {
			int gamesWon = (int)(Math.random()*(100 - 1)) + 1;
			int gamesLost = (int)(Math.random()*(100 - 1)) + 1;
			int classRng = (int)(Math.random()*(5));
			Player player = Player.builder()
					.name(s)
					.className(classes[classRng])
					.gamesWon(gamesWon)
					.gamesLost(gamesLost)
					.gamesPlayed(gamesWon + gamesLost)
					.points((int)(Math.random()*(1000)))
					.build();
			playerRepo.save(player);
		}
	}
}
