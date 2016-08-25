package com.adaming.gestionvoitures.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;




import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


import javax.faces.event.ActionEvent;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.adaming.gestionvoitures.entities.Agence;
import com.adaming.gestionvoitures.service.agence.IAgenceService;


@Component("AgenceBean")
@Scope("session")
public class AgenceBean {
	
	@Autowired
	private IAgenceService agenceService;
	
	private Long idAgence;
	@NotEmpty(message="Veuillez entrer une adresse postale")
	private String addrespostal;
	@NotEmpty(message="Veuillez entrer un numéro de téléphone")
	private String numerotel;
	@NotEmpty(message="Veuillez entrer une dénomination")
	private String denomination;
	@NotEmpty(message="Veuillez entrer une forme juridique")
	private String formJuridique;
	@NotEmpty(message="Veuillez entrer une activité")
	private String activite;
	@NotEmpty(message="Veuillez préciser l'adresse du siège")
	private String adressSiege;
	@NotNull(message="Veuillez préciser une date de commencement")
	private Date dateCommencement;
	@NotEmpty(message="Veuillez entrer une ville")
	private String ville;
	@NotEmpty(message="Veuillez entrer le numéro de téléphone secondaire")
	private String deuxiemTel;
	@NotNull(message="Veuillez préciser le taux de TVA")
	private Double tva;
	@NotEmpty(message="Veuillez préciser la monnaie locale")
	private String choixMonnais;
	private List<String> listMonnaie = new ArrayList<String>();
	
	private Agence agence;
	private Agence age;
	
	private List<Agence> tabAgences;
	private List<Agence> tabAgencesFiltered;
	
	/*METHODES*/
	@PostConstruct
	public void remplirListMonnaie(){
		listMonnaie.add("Dollar");
		listMonnaie.add("Euro");
		listMonnaie.add("Livre-Sterling");
		listMonnaie.add("Dinar");
		listMonnaie.add("Yen");
		tabAgences = agenceService.getAgences();
	}
	
	public String addAgence(){
		Agence a = new Agence(addrespostal, numerotel, denomination, formJuridique, activite, adressSiege, dateCommencement, ville, deuxiemTel, tva, choixMonnais);
		agenceService.addAgence(a);
		addMessage("Agence enregistrée!");
		
		return "successSaveAgence.xhtml";
	}
	
	public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	public void getAgenceByIdBean(){
		agence = agenceService.getAgenceById(idAgence);
		
		idAgence = agence.getIdAgence();
		addrespostal = agence.getAddrespostal();
		numerotel = agence.getNumerotel();
		denomination = agence.getDenomination();
		formJuridique = agence.getFormJuridique();
		activite = agence.getActivite();
		adressSiege = agence.getAdressSiege();
		dateCommencement = agence.getDateCommencement();
		ville = agence.getVille();
		deuxiemTel = agence.getDeuxiemTel();
		tva = agence.getTva();
		choixMonnais = agence.getChoixMonnais();			
	}
	public void attrListener(ActionEvent event){
		/*Sélectionne une ligne du tableau*/
		agence = (Agence) event.getComponent().getAttributes().get("age");
	}
	public String redirectUpdateAgence(){
		age = agenceService.getAgenceById(agence.getIdAgence());
		return "updateAgence.xhtml";
	}
	public String updateAgence(){
		agence.setAddrespostal(age.getAddrespostal());
		agence.setNumerotel(age.getNumerotel());
		agence.setDenomination(age.getDenomination());
		agence.setFormJuridique(age.getFormJuridique());
		agence.setActivite(age.getActivite());
		agence.setAdressSiege(age.getAdressSiege());
		agence.setDateCommencement(age.getDateCommencement());
		agence.setVille(age.getVille());
		agence.setDeuxiemTel(age.getDeuxiemTel());
		agence.setTva(age.getTva());
		agence.setChoixMonnais(age.getChoixMonnais());
		
		agenceService.updateAgence(agence);
		addMessage("Agence modifiée!");
		return "successUpdateAgence.xhtml";
	}
	@PostConstruct
	public void getAgences(){
		tabAgences = agenceService.getAgences();
	}
	
	@PostConstruct
	public String redirectPage(){		
		return "facture";
	}
	
	/*GETTERS & SETTERS*/
	
	public Long getIdAgence() {
		return idAgence;
	}
	
	public void setIdAgence(Long idAgence) {
		this.idAgence = idAgence;
	}

	public String getAddrespostal() {
		return addrespostal;
	}

	public void setAddrespostal(String addrespostal) {
		this.addrespostal = addrespostal;
	}

	public String getNumerotel() {
		return numerotel;
	}

	public void setNumerotel(String numerotel) {
		this.numerotel = numerotel;
	}

	public String getDenomination() {
		return denomination;
	}

	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}

	public String getFormJuridique() {
		return formJuridique;
	}

	public void setFormJuridique(String formJuridique) {
		this.formJuridique = formJuridique;
	}

	public String getActivite() {
		return activite;
	}

	public void setActivite(String activite) {
		this.activite = activite;
	}

	public String getAdressSiege() {
		return adressSiege;
	}

	public void setAdressSiege(String adressSiege) {
		this.adressSiege = adressSiege;
	}

	public Date getDateCommencement() {
		return dateCommencement;
	}

	public void setDateCommencement(Date dateCommencement) {
		this.dateCommencement = dateCommencement;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getDeuxiemTel() {
		return deuxiemTel;
	}

	public void setDeuxiemTel(String deuxiemTel) {
		this.deuxiemTel = deuxiemTel;
	}

	public Double getTva() {
		return tva;
	}

	public void setTva(Double tva) {
		this.tva = tva;
	}

	public String getChoixMonnais() {
		return choixMonnais;
	}

	public void setChoixMonnais(String choixMonnais) {
		this.choixMonnais = choixMonnais;
	}

	public List<Agence> getTabAgences() {
		return tabAgences;
	}

	public void setTabAgences(List<Agence> tabAgences) {
		this.tabAgences = tabAgences;
	}
	public Agence getAgence() {
		return agence;
	}
	public void setAgence(Agence agence) {
		this.agence = agence;
	}
	public List<Agence> getTabAgencesFiltered() {
		return tabAgencesFiltered;
	}
	public void setTabAgencesFiltered(List<Agence> tabAgencesFiltered) {
		this.tabAgencesFiltered = tabAgencesFiltered;
	}

	public List<String> getListMonnaie() {
		return listMonnaie;
	}

	public void setListMonnaie(List<String> listMonnaie) {
		this.listMonnaie = listMonnaie;
	}

	public Agence getAge() {
		return age;
	}

	public void setAge(Agence age) {
		this.age = age;
	}

	
	
	
}
