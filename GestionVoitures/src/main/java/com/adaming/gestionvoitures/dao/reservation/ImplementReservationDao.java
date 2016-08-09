package com.adaming.gestionvoitures.dao.reservation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.adaming.gestionvoitures.entities.Client;
import com.adaming.gestionvoitures.entities.Reservation;
import com.adaming.gestionvoitures.entities.Voiture;
import com.adaming.gestionvoitures.exception.VoitureDisponibleException;
import com.adaming.gestionvoitures.service.voiture.IVoitureService;

//@Repository(value="daoReservation")
public class ImplementReservationDao implements IReservationDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	IVoitureService serviceVoiture;
	
	Logger log = Logger.getLogger("ImplementReservationDao");

	@Override
	public Reservation addReservation(Reservation r, Long idVoiture,
			Long idClient) throws VoitureDisponibleException {
		Voiture v = em.find(Voiture.class, idVoiture);
		Client c = em.find(Client.class, idClient);
		List<Voiture> voitureDispo = serviceVoiture.disponibiliteVoiture(r.getDateSortie(), r.getDateRetour());
		if(!voitureDispo.contains(v)){
			throw new VoitureDisponibleException("La voiture n'est pas disponible pour cette r�servation");
		}
		r.setClient(c);
		r.setVoiture(v);
		v.getTabReservations().add(r);
		em.persist(r);
		em.merge(v);
		log.info("La r�servation " + r.getIdreservation() + " a bien �t� enregistr�e");
		return null;
	}

	@Override
	public Reservation getReservationById(Long idReservation) {
		Reservation r  = em.find(Reservation.class, idReservation);
		log.info("La r�servation " + r.getIdreservation() + " a bien �t� retourn�e");
		return r;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reservation> getReservations() {
		Query query = em.createQuery("from Reservation");
		log.info(query.getResultList().size() + " r�servations ont �t� retourn�es");
		return query.getResultList();
	}

	@Override
	public Reservation updateReservation(Reservation r) throws VoitureDisponibleException {
		List<Voiture> voitureDispo = serviceVoiture.disponibiliteVoiture(r.getDateSortie(), r.getDateRetour());
		if(!voitureDispo.contains(r.getVoiture())){
			throw new VoitureDisponibleException("La voiture n'est pas disponible pour cette r�servation");
		}
		em.merge(r);
		log.info("La r�servation " + r.getIdreservation() + " a bien �t� modifi�e");
		return r;
	}

	@Override
	public Reservation deleteReservation(Long idReservation) {
		Reservation r  = em.find(Reservation.class, idReservation);
		em.remove(r);
		log.info("La r�servation " + r.getIdreservation() + " a bien �t� supprim�e");
		return null;
	}

	@Override
	public List<Reservation> historiqueReservation() {
		List<Reservation> tousReservation = getReservations();
		List<Reservation> historique = new ArrayList<Reservation>();
		for(Reservation r : tousReservation){
			if(r.getDateRetour().getTime() < new Date().getTime()){
				historique.add(r);
			}
		}
		log.info(historique.size() + " r�servations ont �t� retourn�es dans l'historique");
		return historique;
	}

}
