package ca.sheridancollege.javiersh.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
public class Player {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NonNull
	private String name;
	
	@NonNull
	private Integer kills;
	
	@NonNull
	private Integer deaths;
	
	@NonNull
	private Integer matches;
	
	@NonNull
	private Double points;
}
