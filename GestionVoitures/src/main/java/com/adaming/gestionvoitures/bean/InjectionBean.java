package com.adaming.gestionvoitures.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.adaming.gestionvoitures.dao.agence.IAgenceDao;
import com.adaming.gestionvoitures.dao.agence.ImplementAgenceDao;
import com.adaming.gestionvoitures.dao.client.IClientDao;
import com.adaming.gestionvoitures.dao.client.ImplementClientDao;
import com.adaming.gestionvoitures.dao.entretien.IEntretienDao;
import com.adaming.gestionvoitures.dao.entretien.ImplementEntretienDao;
import com.adaming.gestionvoitures.dao.facture.IFactureDao;
import com.adaming.gestionvoitures.dao.facture.ImplementFactureDao;
import com.adaming.gestionvoitures.dao.reservation.IReservationDao;
import com.adaming.gestionvoitures.dao.reservation.ImplementReservationDao;
import com.adaming.gestionvoitures.dao.voiture.IVoitureDao;
import com.adaming.gestionvoitures.dao.voiture.ImplementVoitureDao;

@Configuration
@ComponentScan(value={"com.adaming.gestionvoitures"})
public class InjectionBean {

	@Bean
	public IAgenceDao getDaoAgence(){
		return new ImplementAgenceDao();
	}
	
	@Bean
	public IClientDao getDaoClient(){
		return new ImplementClientDao();
	}
	
	@Bean
	public IVoitureDao getDaoVoiture(){
		return new ImplementVoitureDao();
	}
	
	@Bean
	public IReservationDao getDaoReservation(){
		return new ImplementReservationDao();
	}
	
	@Bean
	public IEntretienDao getDaoEntretien(){
		return new ImplementEntretienDao();
	}
	
	@Bean
	public IFactureDao getDaoFacture(){
		return new ImplementFactureDao();
	}
	
}
