package ca.sheridancollege.javiersh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.javiersh.beans.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	public Student findByName(String name);

}
