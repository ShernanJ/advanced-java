package ca.sheridancollege.javiersh.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.javiersh.beans.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	//Customers linked to an accounts only
	public List<Customer> findByAccount_IdIsNotNull();
}
