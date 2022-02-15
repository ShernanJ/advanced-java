package ca.sheridancollege.javiersh.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ca.sheridancollege.javiersh.beans.Student;
import ca.sheridancollege.javiersh.repositories.StudentRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BootstrapData implements CommandLineRunner {
	
	private StudentRepository studentRepo;
	
	@Override
	public void run(String...args) throws Exception {
		
		String[] names = {"Kanye West", "Shernan Javier", "Ann", "Kim", "Sam", "Fred", "Bob", "Liz", "Sal", "Tim"};
		for (String s : names) {
			Student student = Student.builder().name(s).build();
			studentRepo.save(student);
		}
	}
}
