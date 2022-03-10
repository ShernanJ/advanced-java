package ca.sheridancollege.javiersh.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Toy {

	private Long id;
	private String name;
	private Double price;
	private Integer quantity;
	private String storeName;
}
