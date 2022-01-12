package fr.diginamic.springtp05banqueApp.controllerrest;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.springtp05banqueApp.exception.CompteNotFoundException;
import fr.diginamic.springtp05banqueApp.model.Client;
import fr.diginamic.springtp05banqueApp.model.Compte;
import fr.diginamic.springtp05banqueApp.model.CompteCourant;
import fr.diginamic.springtp05banqueApp.repository.iCrudCompte;

@RestController
@CrossOrigin
@RequestMapping("api/banque/comptecourant")
public class ControllerCompte extends  ControllerCptAbstract<CompteCourant>{
	
//	@Autowired
//	iCrudCompte ccpt;

	@GetMapping("{id}")
	public CompteCourant getCompte(@PathVariable("id") Integer pid) throws CompteNotFoundException {
//		Optional<CompteCourant> result = ccpt.findById(pid);
		CompteCourant result = ccpt.getCompteCourantById(pid);
		if (result == null) {
			String s = "Compte non trouvé, id: [" + pid + "]";
			throw new CompteNotFoundException(s);
		}

		return  result;
	}
	
	@GetMapping("allcomptescourant")
	public Iterable<CompteCourant> getCompteAll() throws CompteNotFoundException {
		return ccpt.getAllCompteCourant();
		
	}
	
//	@GetMapping("/clientsbycompte/{id}")
//	public Iterable<Client> getClientsByCompte(@PathVariable("id" ) Integer pid) throws CompteNotFoundException {
////		CompteCourant compte = (CompteCourant) ccpt.findById(pid).get();
////		if (compte.isEmpty()) {
////			String s = "Compte non trouvé, id: [" + pid + "]";
////			throw new CompteNotFoundException(s);
////		}
//		
//		Iterable<Client> result = ccpt.findClientsByCompte(compte);
//		
//		return result;
//	}


	
	@PutMapping("{id}")
	public CompteCourant updateCompte(@PathVariable("id") Integer pid, @RequestBody CompteCourant compte) throws CompteNotFoundException {
		if (pid != compte.getId()) {
			String s = "Error pathvariable entre l'id : " + pid + " !!";
			throw new CompteNotFoundException(s);
		}
		
		if (ccpt.findById(pid).isEmpty()) {
			String s = "Compte non trouvé, id: " + pid +" !!";
			throw new CompteNotFoundException(s);
		}
		
		return (CompteCourant) ccpt.save(compte);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteCompte(@PathVariable("id") Integer pid) throws CompteNotFoundException {
		if (ccpt.findById(pid).isEmpty()) {
			String s = "Compte non trouvé, id: [" + pid + "]";
			throw new CompteNotFoundException(s);
		}
		ccpt.deleteById(pid);
		return ResponseEntity.status(HttpStatus.OK).body("Compte supprimé");
	}
	
	@PostMapping
	public CompteCourant addCompte(@RequestBody CompteCourant compte) {
		return ccpt.save(compte);
	}

}
