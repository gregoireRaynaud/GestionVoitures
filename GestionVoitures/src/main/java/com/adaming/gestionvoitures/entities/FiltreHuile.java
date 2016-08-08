package com.adaming.gestionvoitures.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("FILTRE_HUILE")
public class FiltreHuile extends Entretien{

	public FiltreHuile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FiltreHuile(String typeEntretien, Date dateEntretien, Double kilometrage, Integer prixEntretien) {
		super(typeEntretien, dateEntretien, kilometrage, prixEntretien);
		// TODO Auto-generated constructor stub
	}

}
