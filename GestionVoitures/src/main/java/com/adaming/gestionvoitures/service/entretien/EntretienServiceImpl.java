package com.adaming.gestionvoitures.service.entretien;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adaming.gestionvoitures.dao.entretien.IEntretienDao;
import com.adaming.gestionvoitures.entities.ChaineDistribution;
import com.adaming.gestionvoitures.entities.Entretien;
import com.adaming.gestionvoitures.entities.FiltreHuile;
import com.adaming.gestionvoitures.entities.Vidange;

/**
 * Nom : EntretienServiceImpl
 * package com.adaming.gestionvoitures.service.entretien;
 * @author Grégoire RAYNAUD
 * Date de dernière modification : 09/08/2016
 */
@Service
@Transactional
public class EntretienServiceImpl implements IEntretienService{

	private IEntretienDao daoEntretien;
	
	Logger log = Logger.getLogger("EntretienServiceImpl");
	
	
	public void setDao(IEntretienDao daoEntretien) {
		this.daoEntretien = daoEntretien;
		log.info("<------------- daoEntretien injected ------------>");
	}

	@Override
	public Entretien addEntretient(Entretien e, Long idVoiture) {
		// TODO Auto-generated method stub
		return daoEntretien.addEntretient(e, idVoiture);
	}

	@Override
	public Entretien updateEntretien(Entretien e) {
		// TODO Auto-generated method stub
		return daoEntretien.updateEntretien(e);
	}

	@Override
	public Entretien getEntretienById(Long idEntretien) {
		// TODO Auto-generated method stub
		return daoEntretien.getEntretienById(idEntretien);
	}

	@Override
	public List<Entretien> getEntretiens() {
		// TODO Auto-generated method stub
		return daoEntretien.getEntretiens();
	}

	@Override
	public List<ChaineDistribution> getChaineDistributions() {
		// TODO Auto-generated method stub
		return daoEntretien.getChaineDistributions();
	}

	@Override
	public List<FiltreHuile> getFiltreHuiles() {
		// TODO Auto-generated method stub
		return daoEntretien.getFiltreHuiles();
	}

	@Override
	public List<Vidange> getVidanges() {
		// TODO Auto-generated method stub
		return daoEntretien.getVidanges();
	}

}
