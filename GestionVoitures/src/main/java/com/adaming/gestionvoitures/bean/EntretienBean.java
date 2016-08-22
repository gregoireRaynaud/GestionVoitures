package com.adaming.gestionvoitures.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adaming.gestionvoitures.entities.ChaineDistribution;
import com.adaming.gestionvoitures.entities.Entretien;
import com.adaming.gestionvoitures.entities.FiltreHuile;
import com.adaming.gestionvoitures.entities.Vidange;
import com.adaming.gestionvoitures.entities.Voiture;
import com.adaming.gestionvoitures.service.entretien.IEntretienService;
import com.adaming.gestionvoitures.service.voiture.IVoitureService;

/**
 * Nom :EntretienBean
 * package com.adaming.gestionvoitures.bean;
 * @author Grégoire RAYNAUD
 * Date de dernière modification ; 11/08/2016
 */
@Component("EntretienBean")
@RequestScoped
public class EntretienBean {

	//Service
	@Autowired
	private IEntretienService entretienService;
	@Autowired
	private IVoitureService voitureService;
		
	//Attributs	
	private Long idEntretien;
	@NotNull(message="Veuillez entrer un type d'entretien")
	private String typeEntretien;
	@NotNull(message="Veuillez choisir une date d'entretien")
	private Date dateEntretien;
	private Double Kilometrage;//kilometrage de la voiture au moment de l'entretien
	@NotNull(message="Veuillez entrer un prix d'entretien")
	private Integer prixEntretien;	
	
	private  Voiture voiture;
	@NotNull(message="Veuillez choisir un entretien")
	private String type;
	private List<Entretien> entretiens;
	private List<ChaineDistribution> chaineDistributions;
	private List<FiltreHuile> filtreHuiles;
	private List<Vidange> vidanges;
	private List<Voiture> voitures;
	@NotNull(message="Veuillez choisir une voiture")
	private Long idVoiture;
	private Entretien entretien;
	private Entretien ent;
	
	private List<Entretien> filteredCars;
	
	//Constructeur par défaut
	public EntretienBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	//Getters et setters
	public IEntretienService getEntretienService() {
		return entretienService;
	}

	public void setEntretienService(IEntretienService entretienService) {
		this.entretienService = entretienService;
	}

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
	

	

	public Entretien getEnt() {
		return ent;
	}

	public void setEnt(Entretien ent) {
		this.ent = ent;
	}

	public Integer getPrixEntretien() {
		return prixEntretien;
	}

	public void setPrixEntretien(Integer prixEntretien) {
		this.prixEntretien = prixEntretien;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Voiture getVoiture() {
		return voiture;
	}

	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}

	public List<Entretien> getEntretiens() {
		return entretiens;
	}

	public void setEntretiens(List<Entretien> entretiens) {
		this.entretiens = entretiens;
	}
	
	public List<ChaineDistribution> getChaineDistributions() {
		return chaineDistributions;
	}

	public void setChaineDistributions(List<ChaineDistribution> chaineDistributions) {
		this.chaineDistributions = chaineDistributions;
	}

	public List<FiltreHuile> getFiltreHuiles() {
		return filtreHuiles;
	}

	public void setFiltreHuiles(List<FiltreHuile> filtreHuiles) {
		this.filtreHuiles = filtreHuiles;
	}

	public List<Vidange> getVidanges() {
		return vidanges;
	}
	

	public List<Entretien> getFilteredCars() {
		return filteredCars;
	}

	public void setFilteredCars(List<Entretien> filteredCars) {
		this.filteredCars = filteredCars;
	}

	public void setVidanges(List<Vidange> vidanges) {
		this.vidanges = vidanges;
	}

	public IVoitureService getVoitureService() {
		return voitureService;
	}

	public void setVoitureService(IVoitureService voitureService) {
		this.voitureService = voitureService;
	}

	public List<Voiture> getVoitures() {
		return voitures;
	}

	public void setVoitures(List<Voiture> voitures) {
		this.voitures = voitures;
	}
	
	public Long getIdVoiture() {
		return idVoiture;
	}

	public void setIdVoiture(Long idVoiture) {
		this.idVoiture = idVoiture;
	}

	public Entretien getEntretien() {
		return entretien;
	}

	public void setEntretien(Entretien entretien) {
		this.entretien = entretien;
	}

	//Autres méthodes
	//Ajouter un entretien	
	public String addEntretien(){
		voitures = voitureService.getVoitures();
		voiture = voitureService.getVoitureById(idVoiture);
		System.out.println("<---------------------- id : " + idVoiture);
////		if(voiture==null){
////			return "getClientsByMc";
////		}
////		if(type==null){
////			return "index.xhtml";
////		}
		if(type.equals("CHAINE_DISTRIBUTION")){
			Entretien e = new ChaineDistribution(typeEntretien,dateEntretien,
					voiture.getKilometrage(),prixEntretien);
			entretienService.addEntretient(e, voiture.getIdvoiture());
		}
		if(type.equals("FILTRE_HUILE")){
			Entretien e = new FiltreHuile(typeEntretien,dateEntretien,
					voiture.getKilometrage(),prixEntretien);
			entretienService.addEntretient(e, voiture.getIdvoiture());
		}
		if(type.equals("VIDANGE")){
			Entretien e = new Vidange(typeEntretien,dateEntretien,
					voiture.getKilometrage(),prixEntretien);
			entretienService.addEntretient(e, voiture.getIdvoiture());
		}

		getE();
		return "successSaveEntretien.xhtml";
	}

	//Get Entretiens
	@PostConstruct
	public void getE(){
		entretiens = entretienService.getEntretiens();
	}
	
	//Get Entretiens de type ChaineDistribution
	@PostConstruct
	public void getCd(){
		chaineDistributions = entretienService.getChaineDistributions();
	}
	
	//Get Entretiens de type FiltreHuile
	@PostConstruct
	public void getFh(){
		filtreHuiles = entretienService.getFiltreHuiles();
	}
	
	//Get Entretiens de type Vidange
	@PostConstruct
	public void getV(){
		vidanges = entretienService.getVidanges();
	}
	
	
	@PostConstruct
	public String redirect(){
		if(type==null){
			return "index.xhtml";
		}
		if(type.equals("CHAINE_DISTRIBUTION")){
			return "getChaineDistribution.xhtml";
		}
		if(type.equals("FILTRE_HUILE")){
			return "getFiltreHuile.xhtml";
		}
		if(type.equals("VIDANGE")){
			return "getVidange.xhtml";
		}
		return"redirect:getEntretiens.xhtml";
	}
	
	//Update Entretien
	public String updateEntretien(){
		entretienService.updateEntretien(entretien);
		getE();
		return "successUpdateEntretien.xhtml";
	}
	
	//Get Id de tous les entretiens
	@PostConstruct
	public List<Long> getEntretiensId(){
		entretiens = entretienService.getEntretiens();
		List<Long> tabId = new ArrayList<Long>();
		for(Entretien e:getEntretiens()){
			tabId.add(e.getIdEntretien());
		}
		return tabId;
	}
	
	//Get Id de toutes les voitures
	@PostConstruct
	public List<Long> getVoituresId(){
		voitures = voitureService.getVoitures();
		List<Long> tabId = new ArrayList<Long>();
		for(Voiture v:getVoitures()){
			tabId.add(v.getIdvoiture());
		}
		return tabId;
	}
	
	public void getEntretienById(){
		entretien = entretienService.getEntretienById(idEntretien);
	}
	
	public void attrListener(ActionEvent event){
		entretien = (Entretien) event.getComponent().getAttributes().get("entretien");
 }
	
	public String RedirectUpdateEntretien(){
		ent = entretienService.getEntretienById(entretien.getIdEntretien());
		return "updateEntretien.xhtml";
	}
	
}
