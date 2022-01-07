package fr.diginamic.springtp05banqueApp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@DiscriminatorValue("V")
public class Virement extends Operation implements Serializable{
	
	@Column(length=50)
	private String beneficiaire;

	public String getBeneficiaire() {
		return beneficiaire;
	}

	public void setBeneficiaire(String beneficiaire) {
		this.beneficiaire = beneficiaire;
	}
	
	
}
