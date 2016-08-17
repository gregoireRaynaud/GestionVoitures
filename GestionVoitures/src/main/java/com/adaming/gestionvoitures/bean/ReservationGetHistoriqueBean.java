package com.adaming.gestionvoitures.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adaming.gestionvoitures.entities.Reservation;
import com.adaming.gestionvoitures.service.reservation.IReservationService;

@Component("ReservationGetHistoriqueBean")
@ViewScoped
public class ReservationGetHistoriqueBean {

	@Autowired
	IReservationService reservationService;
	
	private List<Reservation> tabR;
	private List<Reservation> filteredCars;
	
	@PostConstruct
	public String getRe(){
		tabR = reservationService.historiqueReservation();
		return "redirect:getHistoriqueReservation";
	}
	
	
	
	
	public ReservationGetHistoriqueBean() {
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
	
	
	
}
