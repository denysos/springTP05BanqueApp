package fr.diginamic.springtp05banqueApp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "client")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	@Column(length = 50)
	private String nom;
	
	@NotBlank
	@Column(length = 50)
	private String prenom;
	
//	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date dateNaissance;
	
//	@Valid
	@NotNull
	@Embedded
	private Adresse adresse;

	@ManyToOne
	@JoinColumn(name = "banque_id")
	private Banque banque;
	
	
	@ManyToMany
	@JoinTable(name = "cli_cpt", joinColumns = @JoinColumn(name = "id_cli", referencedColumnName = "id"), 
	inverseJoinColumns = @JoinColumn(name = "id_cpt", referencedColumnName = "id"))
	private Set<Compte> clientComptes;

	
	public Client() {
		super();
		this.clientComptes = new HashSet<Compte>();
	}
	
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Banque getBanque() {
		return banque;
	}

	public void setBanque(Banque banque) {
		this.banque = banque;
	}

	public Set<Compte> getClientComptes() {
		return clientComptes;
	}

	public void setClientComptes(Set<Compte> clientComptes) {
		this.clientComptes = clientComptes;
	}


	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

//	@DateTimeFormat(pattern="dd-MM-YYYY")
	public Date getDateNaissance() {
//		return dateNaissance.toString();
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

}
