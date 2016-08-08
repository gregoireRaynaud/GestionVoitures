package com.adaming.gestionvoitures.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("VIDANGE")
public class Vidange extends Entretien{

	public Vidange() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Vidange(String typeEntretien, Date dateEntretien, Double kilometrage, Integer prixEntretien) {
		super(typeEntretien, dateEntretien, kilometrage, prixEntretien);
		// TODO Auto-generated constructor stub
	}

}
