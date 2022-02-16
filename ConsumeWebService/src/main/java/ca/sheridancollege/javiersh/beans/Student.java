package ca.sheridancollege.javiersh.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {
	
	private Long id;
	private String name;
	private Double grade;
	private String letterGrade;
}
