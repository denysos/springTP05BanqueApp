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
import fr.diginamic.springtp05banqueApp.exception.OperationNotFoundException;
import fr.diginamic.springtp05banqueApp.model.Compte;
import fr.diginamic.springtp05banqueApp.model.CompteCourant;
import fr.diginamic.springtp05banqueApp.model.Operation;
import fr.diginamic.springtp05banqueApp.repository.iCrudCompte;
import fr.diginamic.springtp05banqueApp.repository.iCrudOperation;

@RestController
@CrossOrigin
@RequestMapping("api/banque/operation")
public class ControllerOperation {

	@Autowired
	iCrudOperation co;
	
	@Autowired
	iCrudCompte cc;
	
	
	@GetMapping("{id}")
	public Optional<Operation> getOperation(@PathVariable("id") Integer pid) throws OperationNotFoundException {
		Optional<Operation> result = co.findById(pid);
		if (result.isEmpty()) {
			String s = "Operation non trouvée, id: [" + pid + "]";
			throw new OperationNotFoundException(s);
		}
		return result;
	}
	
	@GetMapping("operationsbycompte/{id}")
	public Iterable<Operation> getOperationsByCompte(@PathVariable("id") Integer pid) throws CompteNotFoundException {
		Compte compte = cc.findById(pid).get();
//		if (compte.isEmpty()) {
//			String s = "Compte non trouvée, id: [" + pid + "]";
//			throw new CompteNotFoundException(s);
//		}
		Iterable<Operation> result =  co.findOperationsByCompte(compte); 
		return result;
	}
	
	@PutMapping("{id}")
	public Operation updateOperation(@PathVariable("id") Integer pid, @RequestBody Operation operation) throws OperationNotFoundException {
		if (pid != operation.getId()) {
			String s = "Error pathvariable entre l'id : " + pid + " !!";
			throw new OperationNotFoundException(s);
		}
		if (co.findById(pid).isEmpty()) {
			String s = "Operation non trouvé, id: " + pid +" !!";
			throw new OperationNotFoundException(s);
		}
		return co.save(operation);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteOperation(@PathVariable("id") Integer pid) throws OperationNotFoundException {
		if (co.findById(pid).isEmpty()) {
			String s = "Operation non trouvé, id: [" + pid + "]";
			throw new OperationNotFoundException(s);
		}
		co.deleteById(pid);
		return ResponseEntity.status(HttpStatus.OK).body("Operation supprimée");
				
	}
	
	@PostMapping
	public Operation addOperation(@RequestBody Operation operation) {
		return co.save(operation);
		
		
	}
	
	
}
