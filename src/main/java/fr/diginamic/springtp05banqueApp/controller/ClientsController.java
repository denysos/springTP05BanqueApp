package fr.diginamic.springtp05banqueApp.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.diginamic.springtp05banqueApp.exception.ClientNotFoundException;
import fr.diginamic.springtp05banqueApp.model.Banque;
import fr.diginamic.springtp05banqueApp.model.Client;
import fr.diginamic.springtp05banqueApp.repository.iCrudBanque;
import fr.diginamic.springtp05banqueApp.repository.iCrudClient;

@Controller
@RequestMapping("banque")
public class ClientsController {

	@Autowired
	iCrudClient cr;
	
	@Autowired
	iCrudBanque cb;

	@GetMapping("/listeclients")
	public String listeClients(Model model) {
//		String dtitre = "Liste de tous les clients : ";
//		model.addAttribute("titre", dtitre);
		model.addAttribute("clients", (Collection<Client>) cr.findAll());
		return "clients/listeclients";
	}

	@GetMapping("/client/detail/{id}")
	public String detailClient(@PathVariable("id") Integer pid, Model model) {
//		String dtitre = "Détail client : ";
//		model.addAttribute("titre", dtitre);
		model.addAttribute("client", (Client) cr.findById(pid).get());
		return "/clients/detailclient";
	}
	
	@PostMapping("/client/update")
	public String updateClient(@Valid @ModelAttribute("client") Client client, BindingResult result, Model model) throws ClientNotFoundException{
		if (result.hasErrors()) {
			throw new ClientNotFoundException(result.toString());
		}
		cr.save(client);
		return "redirect:/banque/listeclients";
	}
	
	@GetMapping("/client/delete/{id}")
	public String deleteClient(@PathVariable("id") Integer pid) throws ClientNotFoundException{
		Optional<Client> c = cr.findById(pid);
		if (c.isEmpty()) {
			throw new ClientNotFoundException("Client id : " + pid + " non trouvé ! ");
		}
		cr.deleteById(pid);
		return "redirect:/banque/listeclients";
	}
	
	@GetMapping("/client/add")
	public String getAddClient(Model model) {
		model.addAttribute("clientForm", new Client());
		model.addAttribute("titre", "Ajout nouveau client");
		model.addAttribute("banques", (Collection<Banque>) cb.findAll());
		return "clients/addclient";
	}
	
	@PostMapping("/client/add")
	public String addClient(Model model, @ModelAttribute("clientForm") Client clientForm) {
		cr.save(clientForm);
		return "redirect:/banque/listeclients";
	}

}
