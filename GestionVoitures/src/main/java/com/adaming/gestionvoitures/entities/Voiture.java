package com.adaming.gestionvoitures.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.context.annotation.Lazy;

/*
 * Classe : Voiture
 * Type : entity
 * Groupe : Adaming
 * Author : Gwladys Ledocq
 * Date : 08/08/2016
 * Version : 1.0.0
 */

@Entity
public class Voiture {

	/*Attributs*/
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idvoiture;
    private String modelVoiture;
    private String immatricule;
    private Double kilometrage;
    private Double prixVoiture;
    private String typeVoiture;
    private String typeCarburant;
    private String etatVoiture;
    
    /*Associations*/
    
    @OneToMany(mappedBy="voiture")
    @JoinTable(name="Voiture_Reservation")
    private List<Reservation> tabReservations;

    /*Constructeurs*/
    
	public Voiture() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Voiture(String modelVoiture, String immatricule, Double kilometrage,
			Double prixVoiture, String typeVoiture, String typeCarburant,
			String etatVoiture) {
		super();
		this.modelVoiture = modelVoiture;
		this.immatricule = immatricule;
		this.kilometrage = kilometrage;
		this.prixVoiture = prixVoiture;
		this.typeVoiture = typeVoiture;
		this.typeCarburant = typeCarburant;
		this.etatVoiture = etatVoiture;
	}
	
	/*Getters and setters*/

	public Long getIdvoiture() {
		return idvoiture;
	}

	public void setIdvoiture(Long idvoiture) {
		this.idvoiture = idvoiture;
	}

	public String getModelVoiture() {
		return modelVoiture;
	}

	public void setModelVoiture(String modelVoiture) {
		this.modelVoiture = modelVoiture;
	}

	public String getImmatricule() {
		return immatricule;
	}

	public void setImmatricule(String immatricule) {
		this.immatricule = immatricule;
	}

	public Double getKilometrage() {
		return kilometrage;
	}

	public void setKilometrage(Double kilometrage) {
		this.kilometrage = kilometrage;
	}

	public Double getPrixVoiture() {
		return prixVoiture;
	}

	public void setPrixVoiture(Double prixVoiture) {
		this.prixVoiture = prixVoiture;
	}

	public String getTypeVoiture() {
		return typeVoiture;
	}

	public void setTypeVoiture(String typeVoiture) {
		this.typeVoiture = typeVoiture;
	}

	public String getTypeCarburant() {
		return typeCarburant;
	}

	public void setTypeCarburant(String typeCarburant) {
		this.typeCarburant = typeCarburant;
	}

	public String getEtatVoiture() {
		return etatVoiture;
	}

	public void setEtatVoiture(String etatVoiture) {
		this.etatVoiture = etatVoiture;
	}

	public List<Reservation> getTabReservations() {
		return tabReservations;
	}

	public void setTabReservations(List<Reservation> tabReservations) {
		this.tabReservations = tabReservations;
	}
    
	
}
