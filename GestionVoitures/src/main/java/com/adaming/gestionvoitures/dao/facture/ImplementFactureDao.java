package com.adaming.gestionvoitures.dao.facture;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.adaming.gestionvoitures.entities.Agence;
import com.adaming.gestionvoitures.entities.Facture;
import com.adaming.gestionvoitures.entities.Reservation;
import com.adaming.gestionvoitures.entities.Voiture;
import com.adaming.gestionvoitures.exception.ReservationDejaFacturee;

@Repository(value="daoFacture")
public class ImplementFactureDao implements IFactureDao {
	
	@PersistenceContext
	private EntityManager em;
	
	Logger log = Logger.getLogger("ImplementFactureDao");

	@Override
	public Facture addFacture(Facture f, Long idReservation, Long idAgence) throws ReservationDejaFacturee {
		Reservation r = em.find(Reservation.class, idReservation);
		
		/*Check si la réservation est déja associée à une facture*/
		Query query = em.createQuery("from Facture as f");
		List<Facture> tabFacture = query.getResultList();
		List<Long> tabIdReservation = new ArrayList<Long>();
		for(Facture f2: tabFacture){
			tabIdReservation.add(f2.getReservation().getIdreservation());
		}
		if(tabIdReservation.contains(r.getIdreservation())){
			throw new ReservationDejaFacturee("Cette réservation a déjà été facturée.");
		}
		/*-------------------------------------------------------*/
		
		f.setReservation(r);
		em.persist(f);
		Agence a = em.find(Agence.class, idAgence);
		a.getFactures().add(f);
		em.merge(a);
		log.info("La facture "+f.getIdFacture()+" a été ajoutée.");
		return f;
	}

	@Override
	public Facture getFactureById(Long idFacture) {
		Facture f = em.find(Facture.class, idFacture);
		log.info("La facture "+f.getIdFacture()+" a été trouvée.");
		return f;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Facture> getListFactures() {
		Query query = em.createQuery("from Facture as f");
		log.info("Il existe "+query.getResultList().size()+" factures dans la BDD.");
		return query.getResultList();
	}

	@Override
	public Double calculerCoutFacture(Long idFacture) {
		Facture f = em.find(Facture.class, idFacture);
		Double coutFacture = f.getReservation().getPrixReservation();
		log.info("Le coût de la facture "+f.getIdFacture()+" est de "+coutFacture);
		return coutFacture;
	}

	@Override
	public Double calculerCoutFacturesByClient(Long idClient) {
		Query query = em.createQuery("from Reservation as r where r.client.idClient = :x");
		query.setParameter("x", idClient);
		@SuppressWarnings("unchecked")
		List<Reservation> tabReservationByClient = query.getResultList();
		Double coutFacturesByClient = 0D;
		for(Reservation r: tabReservationByClient){
			coutFacturesByClient += r.getPrixReservation();
		}
		log.info("Le coût total des factures pour le client "+idClient+" est de "+coutFacturesByClient);
		return coutFacturesByClient;
	}

	@Override
	public Double calculerCoutFacturesByVoiture(Long idVoiture) {
		Voiture v = em.find(Voiture.class, idVoiture);
		List<Reservation> tabReservation = v.getTabReservations();
		Double coutFacturesByVoiture = 0D;
		for(Reservation r: tabReservation){
			coutFacturesByVoiture += r.getPrixReservation();
		}
		log.info("Le coût total des factures pour la voiture "+idVoiture+" est de "+coutFacturesByVoiture);
		return coutFacturesByVoiture;
		
		/*
		 * Methode qui renvoit la liste de factures par voiture
		 * 
		List<Facture> tabFactureByVoiture = new ArrayList<Facture>();
		Query query = em.createQuery("from Facture as f");
		@SuppressWarnings("unchecked")
		List<Facture> tabFacture = query.getResultList();
		for (Facture f: tabFacture){
			for(Reservation r: tabReservation){
				if(r.getIdreservation() == f.getReservation().getIdreservation()){
					tabFactureByVoiture.add(f);
				}
			}
		}*/		
	}

}
