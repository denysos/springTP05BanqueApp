package fr.diginamic.springtp05banqueApp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Embeddable
public class Adresse {
	
	private Integer numero;
	
//	@Pattern(regexp=".*[0123456789]")	
	@NotBlank
	@Column(length=50)
	private String rue;
	
	@NotBlank
	@Column(length=5)
	private String codePostal;
	
	@NotBlank
	@Column(length=50)
	private String ville;

	@Override
	public String toString() {
		return numero + ", rue " + rue + " " + codePostal + " " + ville;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}


	
}
