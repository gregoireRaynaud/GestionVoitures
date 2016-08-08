package com.adaming.gestionvoitures.dao.agence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository(value="dao")
public class ImplementAgenceDao implements IAgenceDao {
	
	@PersistenceContext
	private EntityManager em;

}
