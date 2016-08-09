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

import com.adaming.gestionvoitures.entities.Agence;
import com.adaming.gestionvoitures.service.agence.IAgenceService;

/**
 * Nom : AgenceTestU
 * package com.adaming.gestionvoitures.test;
 * @author Grégoire RAYNAUD
 * Date de dernière modification : 09/08/2016
 */
public class AgenceTestU {

	private static IAgenceService serviceAgence;
	private static ClassPathXmlApplicationContext context;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		serviceAgence = (IAgenceService) context.getBean("agenceService");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}

	//@Ignore
	@Test
	//Testé
	public void testAddAgence() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Date d1 = sdf.parse("12/05/2004");
		Agence a1 = new Agence("33 avenue du Maine"," 01 23 45 67 89","Agence montparnasse","SARL",
				"Location de voitures","30 esplanade de la Défense",d1,"Paris","02 13 45 67 89",0.2,"Euro");
		serviceAgence.addAgence(a1);
		
		Date d2 = sdf.parse("19/10/2006");
		Agence a2 = new Agence("10 avenue de Bretagne"," 03 12 45 67 89","Agence Rennes","SARL",
				"Location de voitures","30 esplanade de la Défense",d2,"Rennes","04 12 35 67 89",0.2,"Euro");
		serviceAgence.addAgence(a2);
		
		Date d3 = sdf.parse("04/03/2005");
		Agence a3 = new Agence("98 boulevard garibaldi"," 05 12 34 67 89","Agence Lyon","SARL",
				"Location de voitures","30 esplanade de la Défense",d3,"Lyon","06 12 34 57 89",0.2,"Euro");
		serviceAgence.addAgence(a3);
		assertNotNull(a1.getIdAgence());
	}

	@Ignore
	@Test
	//Testé
	public void testUpdateAgence() {
		Agence ainit = serviceAgence.getAgenceById(3L);
		ainit.setAddrespostal("AdresseModifiée");
		serviceAgence.updateAgence(ainit);
		Agence afin = serviceAgence.getAgenceById(3L);
		assertTrue(afin.getAddrespostal().equals("AdresseModifiée"));
	}

	@Ignore
	@Test
	//Testé
	public void testGetAgenceById() {
		Agence a = serviceAgence.getAgenceById(1L);
		assertNotNull(a.getIdAgence());
	}

	@Ignore
	@Test
	//Testé
	public void testGetAgences() {
		List<Agence> tabAgence = serviceAgence.getAgences();
		assertTrue(tabAgence.size()>0);
	}

	/*@Ignore
	@Test
	//Non testé (besoin de la facture id=1 dans la BD)
	public void testAddFactureToAgence() throws ParseException {
		serviceAgence.addFactureToAgence(1L, 1L);
		Agence a = serviceAgence.getAgenceById(1L);
		assertTrue(a.getFactures().size()>0);
	}*/

}
