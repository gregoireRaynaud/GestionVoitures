package com.adaming.gestionvoitures.dao.client;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository(value="dao")
public class ImplementClientDao implements IClientDao {
	
	@PersistenceContext
	private EntityManager em;

}
