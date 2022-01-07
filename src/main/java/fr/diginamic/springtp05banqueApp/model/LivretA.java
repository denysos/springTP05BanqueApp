package fr.diginamic.springtp05banqueApp.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "livretA")
public class LivretA extends Compte implements Serializable{
	
	private Double taux;

	public Double getTaux() {
		return taux;
	}

	public void setTaux(Double taux) {
		this.taux = taux;
	}
	
	
	
}
