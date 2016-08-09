package com.adaming.gestionvoitures.dao.client;

import java.util.List;





import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.adaming.gestionvoitures.entities.Client;

/**
 * Nom : ImplementClientDao
 * package com.adaming.gestionvoitures.dao.client;
 * @author Grégoire RAYNAUD
 * Date de dernière modification : 09/08/2016
 */
//@Repository(value="daoClient")
public class ImplementClientDao implements IClientDao {
	
	@PersistenceContext
	private EntityManager em;
	
	Logger log = Logger.getLogger("ImplementClientDao");

	//Create
	@Override
	public Client addClient(Client c) {
		em.persist(c);
		log.info("Le client "+c.getIdClient()+" a bien été ajouté");
		return c;
	}

	//Update
	@Override
	public Client updateClient(Client c) {
		em.merge(c);
		log.info("Le client "+c.getIdClient()+" a bien été modifié");
		return c;
	}

	//Read
	@Override
	public Client getClientById(Long idClient) {
		Client c = em.find(Client.class, idClient);
		log.info("Le client "+c.getIdClient()+" a bien été trouvé");
		return c;
	}

	//Read
	@SuppressWarnings("unchecked")
	@Override
	public List<Client> getClientByMc(String mc) {
		Query query = em.createQuery("from Client c where c.nomClient like:x");
		query.setParameter("x", "%"+mc+"%");
		log.info("Il existe "+query.getResultList().size()+" clients dont le nom contient '"+mc+"'");
		return query.getResultList();
	}

	//Read
	@SuppressWarnings("unchecked")
	@Override
	public List<Client> getClients() {
		Query query = em.createQuery("from Client");
		log.info("Il existe "+query.getResultList().size()+" clients");
		return query.getResultList();
	}

}
