package com.adaming.gestionvoitures.dao.agence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.adaming.gestionvoitures.entities.Agence;
//import com.adaming.gestionvoitures.entities.Facture;

/**
 * Nom : ImplementAgenceDao
 * package com.adaming.gestionvoitures.dao.agence;
 * @author Grégoire RAYNAUD
 * Date de dernière modification : 09/08/2016
 */
@Repository(value="daoAgence")
public class ImplementAgenceDao implements IAgenceDao {
	
	@PersistenceContext
	private EntityManager em;
	
	Logger log = Logger.getLogger("ImplementAgenceDao");

	//Create
	@Override
	public Agence addAgence(Agence a) {
		em.persist(a);
		log.info("L'agence "+a.getIdAgence()+" a bien été ajoutée");
		return a;
	}

	//Update
	@Override
	public Agence updateAgence(Agence a) {
		em.merge(a);
		log.info("L'agence "+a.getIdAgence()+" a bien été modifiée");
		return a;
	}

	//Read
	@Override
	public Agence getAgenceById(Long idAgence) {
		Agence a = em.find(Agence.class, idAgence);
		log.info("L'agence "+a.getIdAgence()+" a bien été trouvée");
		return a;
	}

	//Read
	@SuppressWarnings("unchecked")
	@Override
	public List<Agence> getAgences() {
		Query query = em.createQuery("from Agence");
		log.info("Il existe "+query.getResultList().size()+" agences");
		return query.getResultList();
	}

	/*//Méthode pour ajouter une facture à la liste des factures d'une agence
	@Override
	public Agence addFactureToAgence(Long idAgence, Long idFacture) {
		Agence a = em.find(Agence.class, idAgence);
		Facture f = em.find(Facture.class,idFacture);
		a.getFactures().add(f);
		em.merge(a);
		log.info("La facture "+f.getIdFacture()+" a bien été ajoutée à l'agence "+a.getIdAgence());
		return a;
	}*/

}
