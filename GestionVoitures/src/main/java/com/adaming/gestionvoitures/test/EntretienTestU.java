package com.adaming.gestionvoitures.test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adaming.gestionvoitures.entities.ChaineDistribution;
import com.adaming.gestionvoitures.entities.Entretien;
import com.adaming.gestionvoitures.entities.FiltreHuile;
import com.adaming.gestionvoitures.entities.Vidange;
import com.adaming.gestionvoitures.service.entretien.IEntretienService;
/**
 * Nom : EntretienTestU
 * package com.adaming.gestionvoitures.test;
 * @author Grégoire RAYNAUD
 * Date de dernière modification : 09/08/2016
 */

public class EntretienTestU {

	private static IEntretienService serviceEntretien;
	private static ClassPathXmlApplicationContext context;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		serviceEntretien = (IEntretienService) context.getBean("entretienService");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	//@Ignore
	@Test
	//Testé
	public void testAddEntretient() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Date d1 = sdf.parse("26/08/2015");
		Entretien e1 = new ChaineDistribution("Remplacement de la chaine de distribution",d1,15000.0,250);
		serviceEntretien.addEntretient(e1, 1L);
		
		Date d2 = sdf.parse("09/04/2015");
		Entretien e2 = new FiltreHuile("Remplacement de filtre à huile",d2,20000.0,350);
		serviceEntretien.addEntretient(e2, 2L);
		
		Date d3 = sdf.parse("22/02/2016");
		Entretien e3 = new Vidange("Vidange",d3,12000.0,150);
		serviceEntretien.addEntretient(e3, 3L);
		
		assertNotNull(e1.getIdEntretien());
	}

	@Ignore
	@Test
	//Testé
	public void testUpdateEntretien() {
		Entretien einit = serviceEntretien.getEntretienById(3L);
		einit.setTypeEntretien("TypeEntretienModifié");
		serviceEntretien.updateEntretien(einit);
		Entretien efin = serviceEntretien.getEntretienById(3L);
		assertTrue(efin.getTypeEntretien().equals("TypeEntretienModifié"));
	}

	@Ignore
	@Test
	//Testé
	public void testGetEntretienById() {
		Entretien e = serviceEntretien.getEntretienById(1L);
		assertNotNull(e.getIdEntretien());
	}

	@Ignore
	@Test
	//Testé
	public void testGetEntretiens() {
		List<Entretien> tabEntretien = serviceEntretien.getEntretiens();
		assertTrue(tabEntretien.size()>0);
	}

	@Ignore
	@Test
	//Testé
	public void testGetChaineDistributions() {
		List<ChaineDistribution> tabEntretien = serviceEntretien.getChaineDistributions();
		assertTrue(tabEntretien.size()>0);
	}

	@Ignore
	@Test
	//Testé
	public void testGetFiltreHuiles() {
		List<FiltreHuile> tabEntretien = serviceEntretien.getFiltreHuiles();
		assertTrue(tabEntretien.size()>0);
	}

	@Ignore
	@Test
	//Testé
	public void testGetVidanges() {
		List<Vidange> tabEntretien = serviceEntretien.getVidanges();
		assertTrue(tabEntretien.size()>0);
	}

}
