package ca.sheridancollege.javiersh.beans;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Videogame {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NonNull
	private String title;
	@NonNull
	private Double price;
	@NonNull
	private String genre;
	
	@Transient
	private String[] genres = {"Fighting", "FPS", "RPG", "Strategy", "Horror"};
}
