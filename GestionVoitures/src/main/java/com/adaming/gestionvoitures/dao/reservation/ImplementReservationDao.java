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

@Repository(value="daoReservation")
public class ImplementReservationDao implements IReservationDao {
	
	@PersistenceContext
	private EntityManager em;
	
	
	
	Logger log = Logger.getLogger("ImplementReservationDao");

	@Override
	public Reservation addReservation(Reservation r, Long idVoiture,
			Long idClient) throws VoitureDisponibleException {
		Voiture v = em.find(Voiture.class, idVoiture);
		Client c = em.find(Client.class, idClient);
		List<Voiture> voitureDispo = disponibiliteVoiture(r.getDateSortie(), r.getDateRetour());
		if(!voitureDispo.contains(v)){
			throw new VoitureDisponibleException("La voiture n'est pas disponible pour cette réservation");
		}
		r.setClient(c);
		r.setVoiture(v);
		v.getTabReservations().add(r);
		em.persist(r);
		em.merge(v);
		log.info("La réservation " + r.getIdreservation() + " a bien été enregistrée");
		return null;
	}

	@Override
	public Reservation getReservationById(Long idReservation) {
		Reservation r  = em.find(Reservation.class, idReservation);
		log.info("La réservation " + r.getIdreservation() + " a bien été retournée");
		return r;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reservation> getReservations() {
		Query query = em.createQuery("from Reservation");
		log.info(query.getResultList().size() + " réservations ont été retournées");
		return query.getResultList();
	}

	@Override
	public Reservation updateReservation(Reservation r) throws VoitureDisponibleException {
		List<Voiture> voitureDispo = disponibiliteVoiture(r.getDateSortie(), r.getDateRetour());
		if(!voitureDispo.contains(r.getVoiture())){
			throw new VoitureDisponibleException("La voiture n'est pas disponible pour cette réservation");
		}
		em.merge(r);
		log.info("La réservation " + r.getIdreservation() + " a bien été modifiée");
		return r;
	}

	@Override
	public Reservation deleteReservation(Long idReservation) {
		Reservation r  = em.find(Reservation.class, idReservation);
		r.getVoiture().getTabReservations().remove(r);
		em.merge(r.getVoiture());
		r.setVoiture(null);
		em.merge(r);
		em.remove(r);
		log.info("La réservation " + r.getIdreservation() + " a bien été supprimée");
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
		log.info(historique.size() + " réservations ont été retournées dans l'historique");
		return historique;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Voiture> disponibiliteVoiture(Date dDebut, Date dFin)
			throws VoitureDisponibleException {
		Query query = em.createQuery("from Voiture");
		List<Voiture> toutesVoitures = query.getResultList();
		List<Voiture> voituresDisponibles = query.getResultList();
		for(Voiture v : toutesVoitures){
			if(v.getTabReservations() != null){
				for(Reservation r : v.getTabReservations()){
					if(r.getDateSortie().before(dDebut) && r.getDateRetour().after(dDebut)
							|| r.getDateSortie().after(dDebut) && r.getDateSortie().before(dFin)){
						voituresDisponibles.remove(v);
						if(voituresDisponibles.size() == 0){
							throw new VoitureDisponibleException("Il n'y a aucune voiture disponible pour cette réservation");
						}
					}
				}
			}
		}
		log.info(voituresDisponibles.size() + " voiture(s) sont disponible(s)");
		return voituresDisponibles;
	}

}
