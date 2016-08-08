package com.adaming.gestionvoitures.dao.entretien;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository(value="dao")
public class ImplementEntretienDao implements IEntretienDao {
	
	@PersistenceContext
	private EntityManager em;

}
