package com.adaming.gestionvoitures.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
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

	
	@PostConstruct
	public void getRe(){
		tabR = reservationService.getReservations();
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
			reservationService.updateReservation(r);
		} catch (VoitureDisponibleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:getReservations.xhtml";
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


	
}
