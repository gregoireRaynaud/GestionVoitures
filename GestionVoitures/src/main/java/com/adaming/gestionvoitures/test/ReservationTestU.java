package com.adaming.gestionvoitures.test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adaming.gestionvoitures.entities.Reservation;
import com.adaming.gestionvoitures.entities.Voiture;
import com.adaming.gestionvoitures.exception.VoitureDisponibleException;
import com.adaming.gestionvoitures.service.facture.IFactureService;
import com.adaming.gestionvoitures.service.reservation.IReservationService;

public class ReservationTestU {
	
	private static ClassPathXmlApplicationContext context;
	private static IReservationService service;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		service = (IReservationService) context.getBean("reservationService");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}
/*
	@Test
	public void testAddReservation() throws ParseException, VoitureDisponibleException {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateRetour = sf.parse("2016-07-15-16/30/00/00");
		Date dateSortie = sf.parse("2016-07-13-16/30/00/00");
		Date dateReservation = sf.parse("2016-07-07");
		Reservation r = new Reservation(100, dateReservation, dateRetour, dateSortie, 2, "etatReservation");
		service.addReservation(r, 2L, 1L);
		assertNotNull(r.getIdreservation());
	}

	@Test
	public void testGetReservationById() {
		Reservation r = service.getReservationById(1L);
		assertNotNull(r.getIdreservation());
	}

	@Test
	public void testGetReservations() {
		List<Reservation> tabReservation = service.getReservations();
		assertTrue(tabReservation.size()>0);
	}

	@Test
	public void testUpdateReservation() throws ParseException, VoitureDisponibleException {
		Reservation r1 = service.getReservationById(2L);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateReservation = sf.parse("2016-07-08");
		Date dateRetour = sf.parse("2016-07-18-16/30/00/00");
		Date dateSortie = sf.parse("2016-07-16-16/30/00/00");
		r1.setDateReservation(dateReservation);
		r1.setDateRetour(dateRetour);
		r1.setDateSortie(dateSortie);
		service.updateReservation(r1);
		Reservation r2 = service.getReservationById(2L);
		assertSame(r1.getDateReservation(), r2.getDateReservation());
	}

	@Test
	public void testDeleteReservation() {
		service.deleteReservation(2L);
		assertNull(service.getReservationById(2L));
	}

	@Test
	public void testHistoriqueReservation() {
		List<Reservation> tabHistorique = service.historiqueReservation();
		assertTrue(tabHistorique.size()>0);
	}
	
	@Test
	public void testDisponibiliteVoiture() throws ParseException, VoitureDisponibleException {
		SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy-HH/mm/ss/SS");
		Date dateRetour = sf.parse("2016/07/30-16/30/00/00");
		Date dateSortie = sf.parse("2016/07/29-16/30/00/00");
		List<Voiture> tab = service.disponibiliteVoiture(dateSortie, dateRetour);
		assertTrue(tab.size()>0);
	}
*/
}
