package fr.diginamic.springtp05banqueApp.controllerrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import fr.diginamic.springtp05banqueApp.exception.CompteNotFoundException;
import fr.diginamic.springtp05banqueApp.model.Compte;
import fr.diginamic.springtp05banqueApp.repository.iCrudCompte;

public abstract class ControllerCptAbstract<T extends Compte> {
	
	@Autowired
	protected iCrudCompte ccpt;
	
	@GetMapping("all")
	public Iterable<T> getCompteAll() throws CompteNotFoundException {

		return (Iterable<T>) ccpt.findAll();
	}
	
}
