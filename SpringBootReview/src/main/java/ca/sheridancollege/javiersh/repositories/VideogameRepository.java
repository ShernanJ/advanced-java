package ca.sheridancollege.javiersh.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.javiersh.beans.Videogame;

@Repository                              // <@Entity Object, @ID object>
public interface VideogameRepository extends JpaRepository<Videogame, Integer> {
	
	public List<Videogame> findByGenre(String genre);
	
	public List<Videogame> findbyPriceLessThan(double price);
	
	public List<Videogame> findByPriceLessThanAndGenre(double price, String genre);
	
	public List<Videogame> findByOrderByTitleAsc();
	public List<Videogame> findByOrderByTitleDesc();
	
	@Query("SELECT g FROM Videogame g WHERE LOWER(g.genre) = LOWER(:genre)")
	public List<Videogame> retrieveGenreIgnoreCase(@Param("genre") String genre);
	
	// Pagination
	public List<Videogame> findBy(Pageable page);
}

