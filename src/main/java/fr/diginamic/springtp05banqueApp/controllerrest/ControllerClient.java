package fr.diginamic.springtp05banqueApp.controllerrest;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.springtp05banqueApp.exception.ClientNotFoundException;
import fr.diginamic.springtp05banqueApp.model.Client;
import fr.diginamic.springtp05banqueApp.repository.iCrudClient;

@RestController
@RequestMapping("api/banque/client")
public class ControllerClient {

	@Autowired
	iCrudClient cc;

	@GetMapping("{id}")
	public Optional<Client> getClient(@PathVariable("id") Integer pid) throws ClientNotFoundException {
		Optional<Client> result = cc.findById(pid);
		if (result.isEmpty()) {
			String s = "Client non trouvé, id: [" + pid + "]";
			throw new ClientNotFoundException(s);
		}

		return result;
	}

	@GetMapping("all")
	public Iterable<Client> getClientAll() {

		return cc.findAll();
	}
	
	@PutMapping("{id}")
	public Client updateClient(@PathVariable("id") Integer pid, @RequestBody Client client) throws ClientNotFoundException {
		if (pid != client.getId()) {
			String s = "Error pathvariable entre l'id : " + pid + " !!";
			throw new ClientNotFoundException(s);
		}
		
		if (cc.findById(pid).isEmpty()) {
			String s = "Client non trouvé, id: " + pid +" !!";
			throw new ClientNotFoundException(s);
		}
		
		return cc.save(client);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteClient(@PathVariable("id") Integer pid) throws ClientNotFoundException {
		if (cc.findById(pid).isEmpty()) {
			String s = "Client non trouvé, id: [" + pid + "]";
			throw new ClientNotFoundException(s);
		}
		cc.deleteById(pid);
		return ResponseEntity.status(HttpStatus.OK).body("Client supprimé");
	}
	
	@PostMapping
	public Client addClient(@RequestBody Client client) {
		return cc.save(client);
	}
}
