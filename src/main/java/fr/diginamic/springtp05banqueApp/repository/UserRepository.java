package fr.diginamic.springtp05banqueApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.diginamic.springtp05banqueApp.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("select u from User u where u.username = :username")
	Optional<User> findUserWithName(String username);
}
