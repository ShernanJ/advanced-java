package ca.sheridancollege.javiersh.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@OneToOne
//	@JoinColumn Links 'Account_ID' column in Customer Table to 'id' in Account Table
	@JoinTable(name="CUSTOMER_ACCOUNT",
			joinColumns = @JoinColumn(name="CUSTOMER_ID"),
			inverseJoinColumns=@JoinColumn(name="ACCOUNT_ID"))
	private Account account;
}
