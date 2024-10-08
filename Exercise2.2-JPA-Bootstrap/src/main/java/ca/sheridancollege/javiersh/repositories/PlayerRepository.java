package ca.sheridancollege.javiersh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.javiersh.beans.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
	
}
