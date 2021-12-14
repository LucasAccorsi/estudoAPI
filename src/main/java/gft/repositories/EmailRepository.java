package gft.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gft.entities.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
	
	Page<Email> findAll(Pageable pageable);

}