package ca.sheridancollege.javiersh.bootstrap;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ca.sheridancollege.javiersh.beans.Employee;
import ca.sheridancollege.javiersh.beans.Store;
import ca.sheridancollege.javiersh.repositories.EmployeeRepository;
import ca.sheridancollege.javiersh.repositories.StoreRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BootstrapData implements CommandLineRunner{
	
	private StoreRepository storeRepo;
	private EmployeeRepository employeeRepo;
	
	@Override
	public void run(String...args) throws Exception {
		
		Store store1 = Store.builder()
				.name("Yeezy")
				.employees(new ArrayList<Employee>())
				.build();
		Store store2 = Store.builder()
				.name("Sheridan")
				.employees(new ArrayList<Employee>())
				.build();
		
		Employee employee1 = Employee.builder().name("Kanye West").build();
		Employee employee2 = Employee.builder().name("Shernan Javier").build();
		Employee employee3 = Employee.builder().name("Omar Abu Samra").build();
		Employee employee4 = Employee.builder().name("Andres Munevar").build();
		Employee employee5 = Employee.builder().name("Jacob Brasil").build();
		Employee employee6 = Employee.builder().name("Keshi").build();
		
		storeRepo.save(store1);
		storeRepo.save(store2);
		
		employee1.setStore(store1);
		employee2.setStore(store1);
		employee6.setStore(store1);
		
		employee3.setStore(store2);
		employee4.setStore(store2);
		employee5.setStore(store2);
		
		employeeRepo.save(employee1);
		employeeRepo.save(employee2);
		employeeRepo.save(employee3);
		employeeRepo.save(employee4);
		employeeRepo.save(employee5);
		employeeRepo.save(employee6);
		
	}
}
