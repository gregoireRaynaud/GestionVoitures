package com.adaming.gestionvoitures.bean;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adaming.gestionvoitures.entities.Reservation;
import com.adaming.gestionvoitures.entities.Voiture;
import com.adaming.gestionvoitures.exception.VoitureDisponibleException;
import com.adaming.gestionvoitures.service.reservation.IReservationService;

@Component("ReservationGetBean")
@ViewScoped
public class ReservationGetBean {

	@Autowired
	IReservationService reservationService;
	
	private List<Reservation> tabR;
	private List<Reservation> filteredCars;
	private Reservation reservation;
	private Reservation r;
	private String exceptionVoiture;

	
	@PostConstruct
	public String getRe(){
		tabR = reservationService.getReservations();
		return "redirect:getReservations";
	}
	

	public void attrListener(ActionEvent event){
		reservation = (Reservation) event.getComponent().getAttributes().get("r");
	}
	
	public String RedirectUpdateReservation(){
		r = reservationService.getReservationById(reservation.getIdreservation());
		return "updateReservation.xhtml";
	}
	
	public String updateReservation(){
		try {
			if(r.getDateRetour().getTime() - r.getDateSortie().getTime() < 0){
				throw new VoitureDisponibleException("Veuillez entrer une date de retour post�rieur � la date de d�part");
			}
			r.setNombresDeJours(nbJours(r.getDateSortie(), r.getDateRetour()));
			reservationService.updateReservation(r);
			exceptionVoiture = "";
			getRe();
			return "successUpdateReservation.xhtml";
		} catch (VoitureDisponibleException e) {
			exceptionVoiture = e.getMessage();
			return "updateReservation.xhtml";
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
	
	public String deleteReservation(){
		reservationService.deleteReservation(reservation.getIdreservation());
		FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Succ�s",  "La r�servation a bien �t� supprim�e") );
        getRe();
		return "getReservations.xhtml";
	}
	
	
	
	public ReservationGetBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Reservation> getTabR() {
		return tabR;
	}

	public void setTabR(List<Reservation> tabR) {
		this.tabR = tabR;
	}



	public List<Reservation> getFilteredCars() {
		return filteredCars;
	}



	public void setFilteredCars(List<Reservation> filteredCars) {
		this.filteredCars = filteredCars;
	}


	public Reservation getReservation() {
		return reservation;
	}


	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}


	public Reservation getR() {
		return r;
	}


	public void setR(Reservation r) {
		this.r = r;
	}


	public String getExceptionVoiture() {
		return exceptionVoiture;
	}


	public void setExceptionVoiture(String exceptionVoiture) {
		this.exceptionVoiture = exceptionVoiture;
	}


	
}
