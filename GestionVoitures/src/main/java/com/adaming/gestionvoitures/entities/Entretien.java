package com.adaming.gestionvoitures.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class Entretien {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long idEntretien;
	protected String typeEntretien;
	@Temporal(TemporalType.DATE)
	protected Date dateEntretien;
	protected  Double Kilometrage;//tous les cb de km il faut faire l'entretien
	protected Integer prixEntretien;
	
	@OneToOne
	@JoinColumn(name="ID_VOITURE")
	protected Voiture voiture;

	/*CONSTRUCTEURS*/
	
	public Entretien() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Entretien(String typeEntretien, Date dateEntretien, Double kilometrage, Integer prixEntretien) {
		super();
		this.typeEntretien = typeEntretien;
		this.dateEntretien = dateEntretien;
		Kilometrage = kilometrage;
		this.prixEntretien = prixEntretien;
	}
	
	 /*GETTERS & SETTERS*/
	
	public Long getIdEntretien() {
		return idEntretien;
	}

	public void setIdEntretien(Long idEntretien) {
		this.idEntretien = idEntretien;
	}

	public String getTypeEntretien() {
		return typeEntretien;
	}

	public void setTypeEntretien(String typeEntretien) {
		this.typeEntretien = typeEntretien;
	}

	public Date getDateEntretien() {
		return dateEntretien;
	}

	public void setDateEntretien(Date dateEntretien) {
		this.dateEntretien = dateEntretien;
	}

	public Double getKilometrage() {
		return Kilometrage;
	}

	public void setKilometrage(Double kilometrage) {
		Kilometrage = kilometrage;
	}

	public Integer getPrixEntretien() {
		return prixEntretien;
	}

	public void setPrixEntretien(Integer prixEntretien) {
		this.prixEntretien = prixEntretien;
	}

	public Voiture getVoiture() {
		return voiture;
	}

	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}

}
