package ca.sheridancollege.javiersh.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ca.sheridancollege.javiersh.beans.Student;
import ca.sheridancollege.javiersh.repositories.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
	
	private StudentRepository stuRepo;
	
	@Override
	public List<Student> findAll() {
		return stuRepo.findAll();
	}

	@Override
	public Student findById(Long id) {
		return stuRepo.findById(id).get();
	}

	@Override
	public Student findByName(String name) {
		return stuRepo.findByName(name);
	}

	@Override
	public Student save(Student student) {
		return stuRepo.save(student);
	}
}
