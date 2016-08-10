package com.adaming.gestionvoitures.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adaming.gestionvoitures.entities.Voiture;
import com.adaming.gestionvoitures.exception.VoitureDisponibleException;
import com.adaming.gestionvoitures.service.voiture.IVoitureService;

public class VoitureTestU {

	private static IVoitureService serviceVoiture;
	private static ClassPathXmlApplicationContext context;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		serviceVoiture = (IVoitureService) context.getBean("voitureService");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}

	@Test
	public void testAddVoiture() {
		Voiture v1 = new Voiture("Punto", "JK5HJK", 2300d, 15000d, "Fiat", "Essence", "bon état");
		serviceVoiture.addVoiture(v1);
		assertNotNull(v1.getIdvoiture());
	}

	@Test
	public void testGetVoitureById() {
		Voiture v2 = serviceVoiture.getVoitureById(2L);
		assertNotNull(v2.getIdvoiture());
	}

	@Test
	public void testGetVoitures() {
		List<Voiture> tabVoiture = serviceVoiture.getVoitures();
		assertTrue(tabVoiture.size() != 0);
	}

	@Test
	public void testUpdateVoiture() {
		Voiture v3 = serviceVoiture.getVoitureById(3L);
		v3.setEtatVoiture("test");
		Voiture v4 = serviceVoiture.updateVoiture(v3);
		assertSame("test", v4.getEtatVoiture());
	}

	@Test
	public void testDeleteVoiture() {
		List<Voiture> tabVoiture = serviceVoiture.getVoitures();
		serviceVoiture.deleteVoiture(5L);
		List<Voiture> tabVoiture2 = serviceVoiture.getVoitures();
		assertEquals(tabVoiture.size(), tabVoiture2.size() + 1);
	}

	@Test
	public void testDisponibiliteVoiture() throws VoitureDisponibleException {
		List<Voiture> dispo = serviceVoiture.disponibiliteVoiture();
		assertTrue(dispo.size() != 0);
	}

	@Test
	public void testRentreVoiture() {
		List<Voiture> dispo = serviceVoiture.rentreVoiture();
		assertTrue(dispo.size() == 0);
	}

	@Test
	public void testAlerteEntretien() {
		String alerte = serviceVoiture.alerteEntretien(2L);
		assertTrue(alerte.length() != 0);
	}

}
