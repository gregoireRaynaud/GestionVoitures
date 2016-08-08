package com.adaming.gestionvoitures.dao.voiture;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository(value="dao")
public class ImplementVoitureDao implements IVoitureDao {
	
	@PersistenceContext
	private EntityManager em;

}
