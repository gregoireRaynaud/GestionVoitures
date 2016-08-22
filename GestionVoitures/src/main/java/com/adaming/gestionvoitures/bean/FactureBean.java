package com.adaming.gestionvoitures.bean;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.validation.constraints.NotNull;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adaming.gestionvoitures.entities.Agence;
import com.adaming.gestionvoitures.entities.Facture;
import com.adaming.gestionvoitures.entities.Reservation;
import com.adaming.gestionvoitures.exception.ReservationDejaFacturee;
import com.adaming.gestionvoitures.service.agence.IAgenceService;
import com.adaming.gestionvoitures.service.facture.IFactureService;
import com.adaming.gestionvoitures.service.reservation.IReservationService;

import javassist.tools.rmi.ObjectNotFoundException;

@Component("FactureBean")
@RequestScoped
public class FactureBean {
	
	@Autowired
	private IFactureService factureService;
	@Autowired
	private IReservationService reservationService;
	@Autowired
	private IAgenceService agenceService;
	
	private Long idFacture;
	private Long idFacture1;
	@NotNull(message="Veuillez entrer une date de facturation.")
	private Date deFacturation;
	private List<Facture> tabFactures;
	private List<Facture> tabFacturesFiltered;
	private Facture facture;
	
	private Double coutFacture;
	private Double coutFacturesByClient;
	private Double coutFacturesByVoiture;
	
	private String messageException;
	
	@NotNull(message="Veuillez préciser la réservation.")
	private Long idReservation;
	@NotNull(message="Veuillez préciser l'agence.")
	private Long idAgence;
	private Long idClient;
	private Long idVoiture;
	private Reservation reservation;
	private Agence agence;
	private List<Reservation> tabReservations;
	private List<Agence> tabAgences;
	
	/*METHODES*/
	public void addFacture() throws ObjectNotFoundException{
		Facture f = new Facture(deFacturation);
		try {
			factureService.addFacture(f, idReservation, idAgence);
			addMessage("Facture enregistrée!");
			
			deFacturation = null;
			idReservation = null;
			idAgence = null;
		} catch (ReservationDejaFacturee e) {
			// TODO Auto-generated catch block
			messageException = e.getMessage();
		}
	}
	public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	public void attrListener(ActionEvent event){
		/*Sélectionne une ligne du tableau*/
		facture = (Facture) event.getComponent().getAttributes().get("fac");
	}
	public void consulterFacture(){
		
		coutFacture = factureService.calculerCoutFacture(facture.getIdFacture());		
		/*Appel une autre page xhtml avec options de présentations dans un hashmap*/
		Map<String,Object> options = new HashMap<String, Object>();
		options.put("resizable", false);
		options.put("modal", true);
        options.put("width", 680);
        options.put("height", 570);
        RequestContext.getCurrentInstance().openDialog("FactureToPrint", options, null);
		
	}     
    
	@PostConstruct
	public void getListFactures(){
		tabFactures = factureService.getListFactures();
	}
	
	public void calculerCoutFacture(){
		coutFacture = factureService.calculerCoutFacture(idFacture);
	}
	
	public void calculerCoutFacturesByClient(){
		coutFacturesByClient = factureService.calculerCoutFacturesByClient(idClient);
	}
	
	public void calculerCoutFacturesByVoiture(){
		coutFacturesByVoiture = factureService.calculerCoutFacturesByVoiture(idVoiture);
	}
	@PostConstruct
	public void getListReservation(){
		tabReservations = reservationService.getReservations();
	}
	@PostConstruct
	public void getListAgences(){
		tabAgences = agenceService.getAgences();
	}
	@PostConstruct
	public String redirectPage(){
		idFacture = null;
		deFacturation = null;
		tabFactures = null;
		facture = null;
		
		coutFacture = null;
		coutFacturesByClient = null;
		coutFacturesByVoiture = null;
		
		idReservation = null;
		idAgence = null;
		idClient = null;
		idVoiture = null;
		reservation = null;
		agence = null;
		tabReservations = null;
		tabAgences = null;
		
		return "facture";
	}

	/*GETTERS & SETTERS*/
	public IFactureService getfactureService() {
		return factureService;
	}

	public void setfactureService(IFactureService factureService) {
		this.factureService = factureService;
	}

	public Long getIdFacture() {
		return idFacture;
	}

	public void setIdFacture(Long idFacture) {
		this.idFacture = idFacture;
	}

	public Date getDeFacturation() {
		return deFacturation;
	}

	public void setDeFacturation(Date deFacturation) {
		this.deFacturation = deFacturation;
	}

	public List<Facture> getTabFactures() {
		return tabFactures;
	}

	public void setTabFactures(List<Facture> tabFactures) {
		this.tabFactures = tabFactures;
	}

	public Long getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(Long idReservation) {
		this.idReservation = idReservation;
	}

	public Long getIdAgence() {
		return idAgence;
	}

	public void setIdAgence(Long idAgence) {
		this.idAgence = idAgence;
	}

	public Facture getFacture() {
		return facture;
	}

	public void setFacture(Facture facture) {
		this.facture = facture;
	}

	public Double getCoutFacture() {
		return coutFacture;
	}

	public void setCoutFacture(Double coutFacture) {
		this.coutFacture = coutFacture;
	}

	public Double getCoutFacturesByClient() {
		return coutFacturesByClient;
	}

	public void setCoutFacturesByClient(Double coutFacturesByClient) {
		this.coutFacturesByClient = coutFacturesByClient;
	}

	public Double getCoutFacturesByVoiture() {
		return coutFacturesByVoiture;
	}

	public void setCoutFacturesByVoiture(Double coutFacturesByVoiture) {
		this.coutFacturesByVoiture = coutFacturesByVoiture;
	}

	public Long getIdClient() {
		return idClient;
	}

	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}

	public Long getIdVoiture() {
		return idVoiture;
	}

	public void setIdVoiture(Long idVoiture) {
		this.idVoiture = idVoiture;
	}

	public List<Reservation> getTabReservations() {
		return tabReservations;
	}

	public void setTabReservations(List<Reservation> tabReservations) {
		this.tabReservations = tabReservations;
	}

	public List<Agence> getTabAgences() {
		return tabAgences;
	}

	public void setTabAgences(List<Agence> tabAgences) {
		this.tabAgences = tabAgences;
	}

	public IAgenceService getAgenceService() {
		return agenceService;
	}

	public void setAgenceService(IAgenceService agenceService) {
		this.agenceService = agenceService;
	}

	public Agence getAgence() {
		return agence;
	}

	public void setAgence(Agence agence) {
		this.agence = agence;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	public Long getIdFacture1() {
		return idFacture1;
	}
	public void setIdFacture1(Long idFacture1) {
		this.idFacture1 = idFacture1;
	}
	public List<Facture> getTabFacturesFiltered() {
		return tabFacturesFiltered;
	}
	public void setTabFacturesFiltered(List<Facture> tabFacturesFiltered) {
		this.tabFacturesFiltered = tabFacturesFiltered;
	}
	public String getMessageException() {
		return messageException;
	}
	public void setMessageException(String messageException) {
		this.messageException = messageException;
	}
	
	
	
}

