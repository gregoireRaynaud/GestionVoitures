package com.adaming.gestionvoitures.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/*
 * Classe : Reservation
 * Type : entity
 * Groupe : Adaming
 * Author : Gwladys Ledocq
 * Date : 08/08/2016
 * Version : 1.0.0
 */

@Entity
public class Reservation {

	/*Attributs*/
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idreservation;
	private double prixReservation;
	@Temporal(TemporalType.TIME)
	private Date dateReservation;//juste date
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateRetour;//date + heure
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateSortie;//date + heure
	private Integer nombresDeJours;
	private String etatReservation;
	
	/*Association*/
	
	@ManyToOne
	@JoinColumn(name="idVoiture")
	private Voiture voiture;
	@ManyToOne
	@JoinColumn(name="idClient")
	private Client client;

	/*Constructeurs*/
	
	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reservation(double prixReservation, Date dateReservation,
			Date dateRetour, Date dateSortie, Integer nombresDeJours,
			String etatReservation) {
		super();
		this.prixReservation = prixReservation;
		this.dateReservation = dateReservation;
		this.dateRetour = dateRetour;
		this.dateSortie = dateSortie;
		this.nombresDeJours = nombresDeJours;
		this.etatReservation = etatReservation;
	}
	
	/*Getters and setters*/

	public Long getIdreservation() {
		return idreservation;
	}

	public void setIdreservation(Long idreservation) {
		this.idreservation = idreservation;
	}

	public double getPrixReservation() {
		return prixReservation;
	}

	public void setPrixReservation(double prixReservation) {
		this.prixReservation = prixReservation;
	}

	public Date getDateReservation() {
		return dateReservation;
	}

	public void setDateReservation(Date dateReservation) {
		this.dateReservation = dateReservation;
	}

	public Date getDateRetour() {
		return dateRetour;
	}

	public void setDateRetour(Date dateRetour) {
		this.dateRetour = dateRetour;
	}

	public Date getDateSortie() {
		return dateSortie;
	}

	public void setDateSortie(Date dateSortie) {
		this.dateSortie = dateSortie;
	}

	public Integer getNombresDeJours() {
		return nombresDeJours;
	}

	public void setNombresDeJours(Integer nombresDeJours) {
		this.nombresDeJours = nombresDeJours;
	}

	public String getEtatReservation() {
		return etatReservation;
	}

	public void setEtatReservation(String etatReservation) {
		this.etatReservation = etatReservation;
	}

	public Voiture getVoiture() {
		return voiture;
	}

	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	
	
}
