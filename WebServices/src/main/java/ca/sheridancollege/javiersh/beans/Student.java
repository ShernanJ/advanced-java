package ca.sheridancollege.javiersh.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	private Double grade;
	private String letterGrade;
	
	public void calculate() {
		if (this.grade >= 80) {
			this.letterGrade = "A";
		}
		else if (this.grade >= 70) {
			this.letterGrade = "B";
		}
		else if (this.grade >= 60) {
			this.letterGrade = "C";
		}
		else if (this.grade >= 50) {
			this.letterGrade = "D";
		} else {
			this.letterGrade = "F";
		}
	}
}
