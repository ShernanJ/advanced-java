package ca.sheridancollege.javiersh.repository;

import ca.sheridancollege.javiersh.domain.Phone;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Phone entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {}
