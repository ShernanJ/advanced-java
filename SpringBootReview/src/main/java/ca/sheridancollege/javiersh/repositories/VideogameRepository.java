package ca.sheridancollege.javiersh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.javiersh.beans.Videogame;

@Repository                              // <@Entity Object, @ID object>
public interface VideogameRepository extends JpaRepository<Videogame, Integer> {
	
}

