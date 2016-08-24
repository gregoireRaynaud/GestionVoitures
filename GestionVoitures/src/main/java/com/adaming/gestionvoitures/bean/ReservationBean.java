package com.adaming.gestionvoitures.bean;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adaming.gestionvoitures.entities.Client;
import com.adaming.gestionvoitures.entities.Reservation;
import com.adaming.gestionvoitures.entities.Voiture;
import com.adaming.gestionvoitures.exception.VoitureDisponibleException;
import com.adaming.gestionvoitures.service.client.IClientService;
import com.adaming.gestionvoitures.service.reservation.IReservationService;
import com.adaming.gestionvoitures.service.voiture.IVoitureService;

@Component("ReservationBean")
@RequestScoped
public class ReservationBean {

	@Autowired
	IVoitureService voitureService;
	
	@Autowired
	IClientService clientService;
	
	@Autowired
	IReservationService reservationService;
	
	private Long idreservation;
	@Min(0) 
	@Max(100000)
	@NotNull(message="Veuillez entrer un prix")
	private double prixReservation;
	private Date dateReservation;
	@NotNull(message="Veuillez entrer une date de retour")
	@Future(message="Veuillez entrer une date dans le futur")
	private Date dateRetour;//date + heure
	@NotNull(message="Veuillez entrer une date de d�part")
	@Future(message="Veuillez entrer une date dans le futur")
	private Date dateSortie;//date + heure
	@NotNull(message="Veuillez choisir une voiture")
	private Long idVoiture;
	private Voiture voiture;
	@NotNull(message="Veuillez choisir un client")
	private Long idClient;
	
	private Client client;
	private List<Voiture> tabVoiture;
	private List<Client> tabClient;
	private String etatReservation;
	private Integer nombresDeJours;
	private String excpetionDispo;
	private Reservation r;
	private List<Reservation> tabReservation;
	
	@PostConstruct
	public String getVC(){
		tabVoiture = voitureService.getVoitures();
		tabClient = clientService.getClients();
		excpetionDispo = "";
		return "redirect:saveReservation.xhtml";
	}
	
	@PostConstruct
	public void getReservation(){
		tabReservation = reservationService.getReservations();
	}
	
	public void choix(){
		voiture = voitureService.getVoitureById(idVoiture);
		client = clientService.getClientById(idClient);
	}
	
	public String saveReservation(){
		etatReservation = voiture.getEtatVoiture();
		nombresDeJours = nbJours(dateSortie, dateRetour);
		dateReservation = new Date();
		excpetionDispo = "";
		r = new Reservation(prixReservation, dateReservation, dateRetour, dateSortie, nombresDeJours, etatReservation);
		try {
			if(dateRetour.getTime() - dateSortie.getTime() < 0){
				throw new VoitureDisponibleException("Veuillez entrer une date de retour post�rieur � la date de d�part");
			}
			reservationService.addReservation(r, idVoiture, idClient);
			getReservation();
			return "successSaveReservation.xhtml-redirect=true";
		} catch (VoitureDisponibleException e) {
			excpetionDispo = e.getMessage();
			return "saveReservation.xhtml";
		}
	}
	
	
	public Integer nbJours(Date date1, Date date2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(date2);
		Integer nbJours = (int) ((c2.getTimeInMillis() - c1.getTimeInMillis())/ 86400000);
		return nbJours;
	}
 
	
	public ReservationBean() {
		super();
	}


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


	public Long getIdVoiture() {
		return idVoiture;
	}



	public void setIdVoiture(Long idVoiture) {
		this.idVoiture = idVoiture;
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


	public List<Voiture> getTabVoiture() {
		return tabVoiture;
	}


	public void setTabVoiture(List<Voiture> tabVoiture) {
		this.tabVoiture = tabVoiture;
	}


	public List<Client> getTabClient() {
		return tabClient;
	}


	public void setTabClient(List<Client> tabClient) {
		this.tabClient = tabClient;
	}

	public Long getIdClient() {
		return idClient;
	}

	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}

	public String getExcpetionDispo() {
		return excpetionDispo;
	}

	public void setExcpetionDispo(String excpetionDispo) {
		this.excpetionDispo = excpetionDispo;
	}

	public Reservation getR() {
		return r;
	}

	public void setR(Reservation r) {
		this.r = r;
	}

	public List<Reservation> getTabReservation() {
		return tabReservation;
	}

	public void setTabReservation(List<Reservation> tabReservation) {
		this.tabReservation = tabReservation;
	}

	
	
}
