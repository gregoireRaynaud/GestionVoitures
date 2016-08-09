package com.adaming.gestionvoitures.service.entretien;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adaming.gestionvoitures.dao.entretien.IEntretienDao;
import com.adaming.gestionvoitures.entities.Entretien;

/**
 * Nom : EntretienServiceImpl
 * package com.adaming.gestionvoitures.service.entretien;
 * @author Grégoire RAYNAUD
 * Date de dernière modification : 09/08/2016
 */
@Service
@Transactional
public class EntretienServiceImpl implements IEntretienService{

	private IEntretienDao dao;
	
	Logger log = Logger.getLogger("EntretienServiceImpl");
	
	
	public void setDao(IEntretienDao dao) {
		this.dao = dao;
		log.info("<------------- daoEntretien injected ------------>");
	}

	@Override
	public Entretien addEntretient(Entretien e, Long idVoiture) {
		// TODO Auto-generated method stub
		return dao.addEntretient(e, idVoiture);
	}

	@Override
	public Entretien updateEntretien(Entretien e) {
		// TODO Auto-generated method stub
		return dao.updateEntretien(e);
	}

	@Override
	public Entretien getEntretienById(Long idEntretien) {
		// TODO Auto-generated method stub
		return dao.getEntretienById(idEntretien);
	}

	@Override
	public List<Entretien> getEntretiens() {
		// TODO Auto-generated method stub
		return dao.getEntretiens();
	}

	@Override
	public List<Entretien> getChaineDistributions() {
		// TODO Auto-generated method stub
		return dao.getChaineDistributions();
	}

	@Override
	public List<Entretien> getFiltreHuiles() {
		// TODO Auto-generated method stub
		return dao.getFiltreHuiles();
	}

	@Override
	public List<Entretien> getVidanges() {
		// TODO Auto-generated method stub
		return dao.getVidanges();
	}

}
