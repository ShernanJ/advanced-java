package ca.sheridancollege.javiersh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.javiersh.beans.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {

}
