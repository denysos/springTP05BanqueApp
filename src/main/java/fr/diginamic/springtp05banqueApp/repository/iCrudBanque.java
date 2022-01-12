package fr.diginamic.springtp05banqueApp.repository;



import org.springframework.data.repository.CrudRepository;

import fr.diginamic.springtp05banqueApp.model.Banque;

public interface iCrudBanque extends CrudRepository<Banque, Integer> {

}
