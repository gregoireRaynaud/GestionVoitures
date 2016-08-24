package com.adaming.gestionvoitures.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adaming.gestionvoitures.entities.Voiture;
import com.adaming.gestionvoitures.service.voiture.IVoitureService;

@Component("VoitureBean")
@RequestScoped
public class VoitureBean{
	
	@Autowired
	IVoitureService voitureService;

	@Size(min=2,max=20, message="Veuillez entrer un model entre 2 et 20 lettres")
	private String modelVoiture;
	@Size(min=7, max=7, message="Veuillez entrer une immatriculation de 7 caractères")
    private String immatricule;
	@Min(0) 
	@Max(1000000)
	@NotNull(message="Veuillez entrer le kilométrage")
    private Double kilometrage;
	@Min(0) 
	@Max(100000)
	@NotNull(message="Veuillez entrer un prix")
    private Double prixVoiture;
	@NotNull(message="Veuillez choisir un type de voiture")
    private String typeVoiture;
	@NotNull(message="Veuillez choisir un type de carburant")
    private String typeCarburant;
	@NotNull(message="Veuillez choisir un état")
    private String etatVoiture;
	private List<Voiture> tabVoiture;
	private List<Voiture> filteredCars;
	private Voiture voiture;
	
	@PostConstruct
	public String init(){
		return "redirect:saveVoiture.xhtml";
	}
	
	@PostConstruct
	public void getVoitures(){
		tabVoiture = voitureService.getVoitures();
	}
	
	public String updateVoiture(){
		//voitureService.updateVoiture(voiture);
		//return "updateVoiture.xhtml";
		return "successSaveVoiture.xhtml";
	}
	
    public String addVoiture(){
    	Voiture v = new Voiture(modelVoiture, immatricule, kilometrage, prixVoiture, typeVoiture, typeCarburant, etatVoiture);
    	voitureService.addVoiture(v);
    	getVoitures();
    	return "successSaveVoiture.xhtml";
    }
    
    
    public VoitureBean() {
		super();
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

	public List<Voiture> getTabVoiture() {
		return tabVoiture;
	}

	public void setTabVoiture(List<Voiture> tabVoiture) {
		this.tabVoiture = tabVoiture;
	}

	public List<Voiture> getFilteredCars() {
		return filteredCars;
	}

	public void setFilteredCars(List<Voiture> filteredCars) {
		this.filteredCars = filteredCars;
	}

	public Voiture getVoiture() {
		return voiture;
	}

	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}
    
    
	
}
