package ca.sheridancollege.javiersh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.javiersh.beans.Toy;

public interface ToyRepository extends JpaRepository<Toy, Long> {

}
