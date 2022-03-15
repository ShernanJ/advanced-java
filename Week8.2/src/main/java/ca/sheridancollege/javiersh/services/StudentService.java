package ca.sheridancollege.javiersh.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ca.sheridancollege.javiersh.beans.Student;

@Service
public interface StudentService {
	
	public List<Student> findAll();
	public Student findById(Long id);
	public Student findByName(String name);
	public Student save(Student student);
}
