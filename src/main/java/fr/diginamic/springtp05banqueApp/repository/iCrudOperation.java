package fr.diginamic.springtp05banqueApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.diginamic.springtp05banqueApp.model.Compte;
import fr.diginamic.springtp05banqueApp.model.Operation;

public interface iCrudOperation extends CrudRepository<Operation, Integer> {

	@Query("SELECT op FROM Operation op WHERE op.compte = :compte")
	public Iterable<Operation> findOperationsByCompte(Compte compte);
	
}
