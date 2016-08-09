package com.adaming.gestionvoitures.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adaming.gestionvoitures.entities.Client;
import com.adaming.gestionvoitures.entities.Reservation;
import com.adaming.gestionvoitures.entities.Voiture;
import com.adaming.gestionvoitures.exception.VoitureDisponibleException;
import com.adaming.gestionvoitures.service.client.IClientService;
import com.adaming.gestionvoitures.service.reservation.IReservationService;
import com.adaming.gestionvoitures.service.voiture.IVoitureService;

public class DriverMesTests {
	
	private static IVoitureService serviceVoiture;
	private static IReservationService serviceReservation;
	private static IClientService serviceClient;
	private static ClassPathXmlApplicationContext context;
	
	public static void main(String[] args) throws ParseException {

		context = new ClassPathXmlApplicationContext("app.xml");
		serviceVoiture = (IVoitureService) context.getBean("voitureService");
		serviceReservation = (IReservationService) context.getBean("reservationService");
		serviceClient = (IClientService) context.getBean("clientService");
		
		
		
		Voiture v1 = new Voiture("Punto", "JK5HJK", 2300d, 15000d, "Fiat", "Essence", "bon état");
		Voiture v2 = new Voiture("Audi", "5465322", 0d, 20000d, "???", "Essence", "neuf");
		Voiture v3 = new Voiture("Mercedes", "EDRTFG", 500d, 17000d, "???", "Essence", "bon état");
		Voiture v4 = new Voiture("Twingo", "UGYHJ", 2000d, 12000d, "???", "Essence", "bon");
		Voiture v5 = new Voiture("Senic", "GY6T7YU", 900d, 14000d, "??", "Essence", "bon état");
		
//		serviceVoiture.addVoiture(v1);
//		serviceVoiture.addVoiture(v2);
//		serviceVoiture.addVoiture(v3);
//		serviceVoiture.addVoiture(v4);
//		serviceVoiture.addVoiture(v5);
		
//		Voiture v6 = serviceVoiture.getVoitureById(1L);
//		System.out.println(v6.getImmatricule());
		
//		List<Voiture> tabVoiture = serviceVoiture.getVoitures();
//		System.out.println(tabVoiture.size());
		
//		Voiture v7 = serviceVoiture.getVoitureById(1L);
//		v7.setImmatricule("HBGYT6YUH");
//		Voiture v8 = serviceVoiture.updateVoiture(v7);
//		System.out.println(v8.getImmatricule());
		
//		serviceVoiture.deleteVoiture(1L);
		
//		Client c1 = new Client("AAAA", "aaaa", new Date(), "0125478965", "num1",
//				"ici", "truc@machin.fr", "France", new Date(), "Ville1",
//				"Ville1");
//		
//		serviceClient.addClient(c1);
		
//		SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy-HH/mm/ss/SS");
//		Date d1 = sf.parse("21/05/2016-16/30/00/00");
//		Date d2 = sf.parse("30/07/2016-14/30/00/00");
//		
//		Date d3 = sf.parse("14/07/2016-08/00/00/00");
//		Date d4 = sf.parse("12/08/2016-12/00/00/00");
//		
//		try {
//			Reservation r1 = serviceReservation.addReservation(new Reservation(150d, new Date(), d1, d2, 60, "bon état"), 5L, 1L);
//		} catch (VoitureDisponibleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		serviceVoiture.deleteVoiture(5L);
		
		context.close();

	}

}
