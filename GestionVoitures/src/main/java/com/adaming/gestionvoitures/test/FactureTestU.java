package com.adaming.gestionvoitures.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adaming.gestionvoitures.entities.Facture;
import com.adaming.gestionvoitures.service.facture.IFactureService;

public class FactureTestU {
	
	private static ClassPathXmlApplicationContext context;
	private static IFactureService service;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		service = (IFactureService) context.getBean("factureService");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}
/*
	@Test
	public void testAddFacture() {
		Facture f = new Facture(new Date());
		service.addFacture(f, null, null);
		assertNotNull(f.getIdFacture());
	}
	
	@Test
	public void testGetFactureById() {
		Facture f = service.getFactureById(1L);
		assertNotNull(f.getIdFacture());
	}

	@Test
	public void testGetListFactures() {
		List<Facture> tabFacture = service.getListFactures();
		assertTrue(tabFacture.size()>0);
	}

	@Test
	public void testCalculerCoutFacture() {
		Double cout = service.calculerCoutFacture(1L);
		assertNotNull(cout);
	}

	@Test
	public void testCalculerCoutFacturesByClient() {
		Double cout = service.calculerCoutFacturesByClient(1L);
		assertNotNull(cout);
	}

	@Test
	public void testCalculerCoutFacturesByVoiture() {
		Double cout = service.calculerCoutFacturesByVoiture(1L);
		assertNotNull(cout);
	}
*/

}
