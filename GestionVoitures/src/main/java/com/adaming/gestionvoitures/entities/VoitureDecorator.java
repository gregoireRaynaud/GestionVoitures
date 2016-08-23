package com.adaming.gestionvoitures.entities;

/**
 * Nom : VoitureDecorator
 * package com.adaming.gestionvoitures.entities;
 * @author GrÃ©goire RAYNAUD
 * Date : 23/08/2016
 */
public class VoitureDecorator {

	//Attributs
	private Voiture voiture;
	private Double chiffreDAffaires;
	
	//Constructeur par dÃ©faut
	public VoitureDecorator() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//Constructeur avec paramÃ¨tres
	public VoitureDecorator(Voiture voiture) {
		super();
		this.voiture = voiture;
	}
	
	//Getters et setters
	public Voiture getVoiture() {
		return voiture;
	}
	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}
	public Double getChiffreDAffaires() {
		return chiffreDAffaires;
	}
	public void setChiffreDAffaires(Double chiffreDAffaires) {
		this.chiffreDAffaires = chiffreDAffaires;
	}
	
	
}
