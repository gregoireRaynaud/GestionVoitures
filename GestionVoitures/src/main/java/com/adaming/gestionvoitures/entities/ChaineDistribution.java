package com.adaming.gestionvoitures.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CHAINE_DISTRIBUTION")
public class ChaineDistribution extends Entretien{

	public ChaineDistribution() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChaineDistribution(String typeEntretien, Date dateEntretien, Double kilometrage, Integer prixEntretien) {
		super(typeEntretien, dateEntretien, kilometrage, prixEntretien);
		// TODO Auto-generated constructor stub
	}

}
