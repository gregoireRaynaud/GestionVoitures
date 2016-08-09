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
	
	@Autowired
	IEntretienService serviceEntretien;

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

	@Override
	public String alerteEntretien(Long idVoiture) {
		Voiture v = getVoitureById(idVoiture);
		String alerte = "Entretiens à faire :\n";
		List<Vidange> tousVidange = serviceEntretien.getVidanges();
		List<Vidange> voitureVidange = new ArrayList<Vidange>();
		List<FiltreHuile> tousFiltreHuile = serviceEntretien.getFiltreHuiles();
		List<FiltreHuile> voitureFiltreHuile = new ArrayList<FiltreHuile>();
		List<ChaineDistribution> tousChaineDistribution = serviceEntretien.getChaineDistributions();
		List<ChaineDistribution> voitureChaineDistribution = new ArrayList<ChaineDistribution>();
		if(tousVidange != null){
			for(Vidange vi : tousVidange){
				if(vi.getVoiture().getIdvoiture() == idVoiture){
					voitureVidange.add(vi);
				}
			}
			Vidange viRecente = new Vidange();
			for(Vidange vi : voitureVidange){
				if(vi.getDateEntretien().getTime() > viRecente.getDateEntretien().getTime()){
					viRecente = vi;
				}
			}
			if(viRecente.getKilometrage() + v.getKilometrage() > 10000){
				Double km = viRecente.getKilometrage() + v.getKilometrage() - 10000;
				alerte = alerte + "- Vidange à faire rapidement (km dépassés : " +  km + ")\n";
			}else{
				Double km = 10000 - (viRecente.getKilometrage() + v.getKilometrage());
				alerte = alerte + "- Vidange à faire dans : " +  km + "\n";
			}
		}else{
			if(v.getKilometrage() > 10000){
				Double km = v.getKilometrage() - 10000;
				alerte = alerte + "- Vidange à faire rapidement (km dépassés : " +  km + ")\n";
			}else{
				Double km = 10000 - (v.getKilometrage());
				alerte = alerte + "- Vidange à faire dans : " +  km + "\n";
			}
		}
		if(tousChaineDistribution != null){
			for(ChaineDistribution c : tousChaineDistribution){
				if(c.getVoiture().getIdvoiture() == idVoiture){
					voitureChaineDistribution.add(c);
				}
			}
			ChaineDistribution cRecente = new ChaineDistribution();
			for(ChaineDistribution c : voitureChaineDistribution){
				if(c.getDateEntretien().getTime() > cRecente.getDateEntretien().getTime()){
					cRecente = c;
				}
			}
			if(cRecente.getKilometrage() + v.getKilometrage() > 10000){
				Double km = cRecente.getKilometrage() + v.getKilometrage() - 10000;
				alerte = alerte + "- Chaine de distribution à faire rapidement (km dépassés : " +  km + ")\n";
			}else{
				Double km = 10000 - (cRecente.getKilometrage() + v.getKilometrage());
				alerte = alerte + "- Chaine de distribution à faire dans : " +  km + "\n";
			}
		}else{
			if(v.getKilometrage() > 10000){
				Double km = v.getKilometrage() - 10000;
				alerte = alerte + "- Chaine de distribution à faire rapidement (km dépassés : " +  km + ")\n";
			}else{
				Double km = 10000 - (v.getKilometrage());
				alerte = alerte + "- Chaine de distribution à faire dans : " +  km + "\n";
			}
		}
		if(tousFiltreHuile != null){
			for(FiltreHuile f : tousFiltreHuile){
				if(f.getVoiture().getIdvoiture() == idVoiture){
					voitureFiltreHuile.add(f);
				}
			}
			FiltreHuile fRecente = new FiltreHuile();
			for(FiltreHuile f : voitureFiltreHuile){
				if(f.getDateEntretien().getTime() > fRecente.getDateEntretien().getTime()){
					fRecente = f;
				}
			}
			if(fRecente.getKilometrage() + v.getKilometrage() > 10000){
				Double km = fRecente.getKilometrage() + v.getKilometrage() - 10000;
				alerte = alerte + "- Filtre à huile à faire rapidement (km dépassés : " +  km + ")\n";
			}else{
				Double km = 10000 - (fRecente.getKilometrage() + v.getKilometrage());
				alerte = alerte + "- Filtre à huile à faire dans : " +  km + "\n";
			}
		}else{
			if(v.getKilometrage() > 10000){
				Double km = v.getKilometrage() - 10000;
				alerte = alerte + "- Filtre à huile à faire rapidement (km dépassés : " +  km + ")\n";
			}else{
				Double km = 10000 - (v.getKilometrage());
				alerte = alerte + "- Filtre à huile à faire dans : " +  km + "\n";
			}
		}
		return alerte;
	}

}
