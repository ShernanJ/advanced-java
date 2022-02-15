package ca.sheridancollege.javiersh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.javiersh.beans.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
