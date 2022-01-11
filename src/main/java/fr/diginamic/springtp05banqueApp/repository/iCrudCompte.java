package fr.diginamic.springtp05banqueApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.diginamic.springtp05banqueApp.model.Client;
import fr.diginamic.springtp05banqueApp.model.Compte;
import fr.diginamic.springtp05banqueApp.model.CompteCourant;

public interface iCrudCompte extends CrudRepository<Compte, Integer> {
	
	@Query("SELECT c FROM Client c WHERE :compte MEMBER OF c.clientComptes")
	public Iterable<Client> findClientsByCompte(Compte compte);
	
	
	@Query("select c from CompteCourant c where c.id = :id")
	public CompteCourant getCompteCourantById(int id);
	
	@Query("select c from CompteCourant c")
	public Iterable<CompteCourant> getAllCompteCourant();

}
