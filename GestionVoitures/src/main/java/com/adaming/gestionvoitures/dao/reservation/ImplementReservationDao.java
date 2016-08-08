package com.adaming.gestionvoitures.dao.reservation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository(value="dao")
public class ImplementReservationDao implements IReservationDao {
	
	@PersistenceContext
	private EntityManager em;

}
