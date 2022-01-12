package fr.diginamic.springtp05banqueApp.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.diginamic.springtp05banqueApp.model.Banque;
import fr.diginamic.springtp05banqueApp.repository.iCrudBanque;

@Controller
@RequestMapping("banque")
public class BanquesController {

	@Autowired
	iCrudBanque cb;
	
	@GetMapping("/listebanques")
	public String listeBanques(Model model) {
		model.addAttribute("banques", (Collection<Banque>) cb.findAll());
		return "";
	}
	
	
}
