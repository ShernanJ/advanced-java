package ca.sheridancollege.javiersh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.javiersh.beans.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
