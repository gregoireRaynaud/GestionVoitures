package com.adaming.gestionvoitures.dao.voiture;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.adaming.gestionvoitures.entities.ChaineDistribution;
import com.adaming.gestionvoitures.entities.FiltreHuile;
import com.adaming.gestionvoitures.entities.Reservation;
import com.adaming.gestionvoitures.entities.Vidange;
import com.adaming.gestionvoitures.entities.Voiture;
import com.adaming.gestionvoitures.exception.VoitureDisponibleException;
import com.adaming.gestionvoitures.service.entretien.IEntretienService;

@Repository(value="daoVoiture")
public class ImplementVoitureDao implements IVoitureDao {
	
	@PersistenceContext
	private EntityManager em;
	
	Logger log = Logger.getLogger("ImplementVoitureDao");
	
	

	@Override
	public Voiture addVoiture(Voiture v) {
		em.persist(v);
		log.info("La voiture " + v.getImmatricule() + " a bien été enregistrée");
		return v;
	}

	@Override
	public Voiture getVoitureById(Long idVoiture) {
		Voiture v = em.find(Voiture.class, idVoiture);
		log.info("La voiture " + v.getImmatricule() + " a bien été récupérée");
		return v;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Voiture> getVoitures() {
		Query query = em.createQuery("from Voiture");
		log.info(query.getResultList().size() + " voiture(s) ont été récupérés");
		return query.getResultList();
	}

	@Override
	public Voiture updateVoiture(Voiture v) {
		em.merge(v);
		log.info("La voiture " + v.getImmatricule() + " a bien été modifiée");
		return v;
	}

	@Override
	public Voiture deleteVoiture(Long idVoiture) {
		Voiture v = em.find(Voiture.class, idVoiture);
		em.remove(v);
		log.info("La voiture " + v.getImmatricule() + " a bien été supprimée");
		return v;
	}

	@Override
	public List<Voiture> disponibiliteVoiture() throws VoitureDisponibleException {
		List<Voiture> toutesVoitures = getVoitures();
		List<Voiture> voituresDisponibles = getVoitures();
		for(Voiture v : toutesVoitures){
			if(v.getTabReservations() != null){
				for(Reservation r : v.getTabReservations()){
					if(r.getDateSortie().getTime() < new Date().getTime() 
							&& r.getDateRetour().getTime() > new Date().getTime()){
						voituresDisponibles.remove(v);
						if(voituresDisponibles.size() == 0){
							throw new VoitureDisponibleException("Il n'y a aucune voiture disponible aujourd'hui");
						}
					}
				}
			}
		}
		log.info(voituresDisponibles.size() + " voiture(s) sont disponible(s) aujourd'hui");
		return voituresDisponibles;
	}

	@Override
	public List<Voiture> disponibiliteVoiture(Date dDebut, Date dFin) throws VoitureDisponibleException {
		List<Voiture> toutesVoitures = getVoitures();
		List<Voiture> voituresDisponibles = getVoitures();
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

	@Override
	public List<Voiture> rentreVoiture() {
		List<Voiture> toutesVoitures = getVoitures();
		List<Voiture> rentreVoiture = new ArrayList<Voiture>();
		Calendar calDay = Calendar.getInstance();
		calDay.setTime(new Date());
		int dayDay = calDay.get(Calendar.DAY_OF_MONTH);
		int monthDay = calDay.get(Calendar.MONTH);
		int yearDay = calDay.get(Calendar.YEAR);
		for(Voiture v : toutesVoitures){
			if(v.getTabReservations() != null){
				for(Reservation r : v.getTabReservations()){
					Calendar calR = Calendar.getInstance();
					calR.setTime(r.getDateRetour());
					int dayR = calDay.get(Calendar.DAY_OF_MONTH);
					int monthR = calDay.get(Calendar.MONTH);
					int yearR = calDay.get(Calendar.YEAR);
					if(dayDay == dayR && monthDay == monthR && yearDay == yearR){
						rentreVoiture.add(v);
					}
				}
			}
		}
		log.info(rentreVoiture.size() + " voiture(s) rentre(nt) aujourd'hui");
		return rentreVoiture;
	}

}
