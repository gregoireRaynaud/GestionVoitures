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

import com.adaming.gestionvoitures.entities.Client;
import com.adaming.gestionvoitures.service.client.IClientService;

/**
 * Nom : ClientTestU
 * package com.adaming.gestionvoitures.test;
 * @author Grégoire RAYNAUD
 * Date de dernière modification : 09/08/2016
 */
public class ClientTestU {

	private static IClientService serviceClient;
	private static ClassPathXmlApplicationContext context;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		serviceClient = (IClientService) context.getBean("clientService");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	//@Ignore
	@Test
	//Testé
	public void testAddClient() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Date d1 = sdf.parse("09/07/1992");
		Date d2 = sdf.parse("20/03/2016");
		Client c1 = new Client("Raynaud","Grégoire",d1,"01 23 45 67 89","032487846","42 rue Sibuet",
				"gregoire.raynaud2@gmail.com","France",d2,"Paris","Sous-préfécture de Redon");
		serviceClient.addClient(c1);
		
		Date d3 = sdf.parse("12/10/1992");
		Date d4 = sdf.parse("15/04/2014");
		Client c2 = new Client("Ledocq","Gwladys",d3,"02 13 45 67 89","978722681681","24 rue De ferrart",
				"gwladys.ledocq@gmail.com","France",d4,"Paris","Sous-préfecture de Fontainebleau");
		serviceClient.addClient(c2);
		
		Date d5 = sdf.parse("10/08/1990");
		Date d6 = sdf.parse("04/09/2013");
		Client c3 = new Client("Folgoas","Gautier",d5,"03 12 45 67 89","87712187","7bis rue Caroline",
				"gautier.folgoas@gmail.com","France",d6,"Paris","Préfecture de Paris");
		serviceClient.addClient(c3);
		
		assertNotNull(c1.getIdClient());
	}

	@Ignore
	@Test
	//Testé
	public void testUpdateClient() {
		Client cinit = serviceClient.getClientById(3L);
		cinit.setNomClient("NomModifié");
		serviceClient.updateClient(cinit);
		Client cfin = serviceClient.getClientById(3L);
		assertTrue(cfin.getNomClient().equals("NomModifié"));
	}

	@Ignore
	@Test
	//Testé
	public void testGetClientById() {
		Client c = serviceClient.getClientById(1L);
		assertNotNull(c.getIdClient());
	}

	@Ignore
	@Test
	//Testé
	public void testGetClientByMc() {
		List<Client> tabClient = serviceClient.getClientByMc("a");
		assertTrue(tabClient.size()>0);
	}

	@Ignore
	@Test
	//Testé
	public void testGetClients() {
		List<Client> tabClient = serviceClient.getClients();
		assertTrue(tabClient.size()>0);
	}

}
