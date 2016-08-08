package com.adaming.gestionvoitures.dao.facture;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository(value="dao")
public class ImplementFactureDao implements IFactureDao {
	
	@PersistenceContext
	private EntityManager em;

}
