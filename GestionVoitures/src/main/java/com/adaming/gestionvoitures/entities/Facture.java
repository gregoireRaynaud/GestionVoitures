package com.adaming.gestionvoitures.entities;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Nom : Facture
 * package com.adaming.gestionvoitures.entities;
 * @author Grégoire RAYNAUD
 * Date de dernière modification : 08/08/2016
 */
@Entity
public class Facture {

	//Atributs
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idFacture;
	@Temporal(TemporalType.DATE)
	private Date DeFacturation;
	
	//Associations
	@OneToOne
	@JoinColumn(name="idReservation")
	private Reservation reservation;
	
	//Constructeur par défaut
	public Facture() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//Contructeur avec paramètres
	public Facture(Date deFacturation) {
		super();
		DeFacturation = deFacturation;
	}

	//Getters et setters
	public Long getIdFacture() {
		return idFacture;
	}

	public void setIdFacture(Long idFacture) {
		this.idFacture = idFacture;
	}

	public Date getDeFacturation() {
		return DeFacturation;
	}

	public void setDeFacturation(Date deFacturation) {
		DeFacturation = deFacturation;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	
}
