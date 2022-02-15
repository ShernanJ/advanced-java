package ca.sheridancollege.javiersh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.javiersh.beans.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
