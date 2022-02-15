package ca.sheridancollege.javiersh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.javiersh.beans.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	
}
