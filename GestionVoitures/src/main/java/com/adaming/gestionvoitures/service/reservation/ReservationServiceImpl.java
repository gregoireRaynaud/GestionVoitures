package com.adaming.gestionvoitures.service.reservation;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adaming.gestionvoitures.dao.reservation.IReservationDao;
import com.adaming.gestionvoitures.dao.voiture.IVoitureDao;
import com.adaming.gestionvoitures.entities.Reservation;
import com.adaming.gestionvoitures.exception.VoitureDisponibleException;

@Service
@Transactional
public class ReservationServiceImpl implements IReservationService{

	@Autowired
	private IReservationDao daoReservation;
	
	Logger log = Logger.getLogger("VoitureServiceImpl");
	
	public void setDaoReservation(IReservationDao daoReservation) {
		this.daoReservation = daoReservation;
		log.info("<------------- dao reservation injected ---------------->");
	}
	
	@Override
	public Reservation addReservation(Reservation r, Long idVoiture,
			Long idClient) throws VoitureDisponibleException {
		return daoReservation.addReservation(r, idVoiture, idClient);
	}

	@Override
	public Reservation getReservationById(Long idReservation) {
		return daoReservation.getReservationById(idReservation);
	}

	@Override
	public List<Reservation> getReservations() {
		return daoReservation.getReservations();
	}

	@Override
	public Reservation updateReservation(Reservation r)
			throws VoitureDisponibleException {
		return daoReservation.updateReservation(r);
	}

	@Override
	public Reservation deleteReservation(Long idReservation) {
		return daoReservation.deleteReservation(idReservation);
	}

	@Override
	public List<Reservation> historiqueReservation() {
		return daoReservation.historiqueReservation();
	}

}
