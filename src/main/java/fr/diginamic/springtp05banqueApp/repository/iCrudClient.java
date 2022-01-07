package fr.diginamic.springtp05banqueApp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.diginamic.springtp05banqueApp.model.Client;
import fr.diginamic.springtp05banqueApp.model.Compte;

public interface iCrudClient extends CrudRepository<Client, Integer> {
	

}
