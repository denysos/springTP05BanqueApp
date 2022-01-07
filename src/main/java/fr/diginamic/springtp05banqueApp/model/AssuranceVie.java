package fr.diginamic.springtp05banqueApp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "assurancevie")
public class AssuranceVie extends Compte implements Serializable {
	


	@Temporal(TemporalType.DATE)
	private Date dateFin;
//	private LocalDate dateFin;
	
	private Double taux;

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Double getTaux() {
		return taux;
	}

	public void setTaux(Double taux) {
		this.taux = taux;
	}
	
	
}
