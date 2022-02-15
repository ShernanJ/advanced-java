package ca.sheridancollege.javiersh.bootstrap;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import ca.sheridancollege.javiersh.beans.Account;
import ca.sheridancollege.javiersh.beans.AccountStatus;
import ca.sheridancollege.javiersh.beans.Customer;
import ca.sheridancollege.javiersh.repositories.AccountRepository;
import ca.sheridancollege.javiersh.repositories.CustomerRepository;

@Component
@AllArgsConstructor
public class BootstrapData implements CommandLineRunner {
	
	private CustomerRepository custRepo;
	private AccountRepository accRepo;
	
	@Override
	public void run(String...args) throws Exception {
		Account account = Account.builder()
				.accountNumber(1111)
				.balance(new BigDecimal(123.45))
				.status(AccountStatus.APPROVED)
				.build();
		
		Customer customer = Customer.builder()
				.name("Jon")
				.account(account)
				.build();
		
		accRepo.save(account);
		custRepo.save(customer);
	}
}
