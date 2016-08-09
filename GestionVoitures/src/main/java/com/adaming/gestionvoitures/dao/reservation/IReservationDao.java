package com.adaming.gestionvoitures.dao.reservation;

import java.util.List;

import com.adaming.gestionvoitures.entities.Reservation;
import com.adaming.gestionvoitures.exception.VoitureDisponibleException;

public interface IReservationDao {

	public Reservation addReservation(Reservation r, Long idVoiture, Long idClient) throws VoitureDisponibleException;
	public Reservation getReservationById(Long idReservation);
	public List<Reservation> getReservations();
	public Reservation updateReservation(Reservation r) throws VoitureDisponibleException;
	public Reservation deleteReservation(Long idReservation);
	public List<Reservation> historiqueReservation();
	
}
