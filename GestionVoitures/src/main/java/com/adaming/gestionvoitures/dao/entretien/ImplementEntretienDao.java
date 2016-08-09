package com.adaming.gestionvoitures.dao.entretien;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.adaming.gestionvoitures.entities.Entretien;
import com.adaming.gestionvoitures.entities.Voiture;

/**
 * Nom : ImplementEntretienDao
 * ackage com.adaming.gestionvoitures.dao.entretien;
 * @author Grégoire RAYNAUD
 * Date de dernière modification : 09/08/2016
 */
@Repository(value="dao")
public class ImplementEntretienDao implements IEntretienDao {
	
	@PersistenceContext
	private EntityManager em;
	
	Logger log = Logger.getLogger("ImplementEntretienDao");

	//Create
	@Override
	public Entretien addEntretient(Entretien e, Long idVoiture) {
		Voiture v = em.find(Voiture.class, idVoiture);
		e.setVoiture(v);
		em.persist(e);
		log.info("L'entretien "+e.getIdEntretien()+" a bien été ajouté");
		return e;
	}

	//Update
	@Override
	public Entretien updateEntretien(Entretien e) {
		em.merge(e);
		log.info("L'entretien "+e.getIdEntretien()+" a bien été modifié");
		return e;
	}

	//Read
	@Override
	public Entretien getEntretienById(Long idEntretien) {
		Entretien e = em.find(Entretien.class, idEntretien);
		log.info("L'entretien "+e.getIdEntretien()+" a bien été trouvé");
		return e;
	}

	//Read
	@SuppressWarnings("unchecked")
	@Override
	public List<Entretien> getEntretiens() {
		Query query = em.createQuery("from Entretien");
		log.info("Il existe "+query.getResultList().size()+" entretien");
		return query.getResultList();
	}

	//Read
	@SuppressWarnings("unchecked")
	@Override
	public List<Entretien> getChaineDistributions() {
		Query query = em.createQuery("from Entretien e where e.DTYPE = 'CHAINE_DISTRIBUTION'");
		log.info("Il existe "+query.getResultList().size()+" entretien du type 'ChaineDistribution'");
		return query.getResultList();
	}

	//Read
	@SuppressWarnings("unchecked")
	@Override
	public List<Entretien> getFiltreHuiles() {
		Query query = em.createQuery("from Entretien e where e.DTYPE = 'FILTRE_HUILE'");
		log.info("Il existe "+query.getResultList().size()+" entretien du type 'FiltreHuile'");
		return query.getResultList();
	}

	//Read
	@SuppressWarnings("unchecked")
	@Override
	public List<Entretien> getVidanges() {
		Query query = em.createQuery("from Entretien e where e.DTYPE = 'VIDANGE'");
		log.info("Il existe "+query.getResultList().size()+" entretien du type 'Vidange'");
		return query.getResultList();
	}

}
