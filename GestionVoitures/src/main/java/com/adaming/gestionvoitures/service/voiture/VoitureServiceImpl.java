package com.adaming.gestionvoitures.service.voiture;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adaming.gestionvoitures.dao.voiture.IVoitureDao;
import com.adaming.gestionvoitures.entities.Voiture;
import com.adaming.gestionvoitures.exception.VoitureDisponibleException;

@Service
@Transactional
public class VoitureServiceImpl implements IVoitureService{

	private IVoitureDao daoVoiture;
	
	Logger log = Logger.getLogger("VoitureServiceImpl");
	
	public void setDaoVoiture(IVoitureDao dao) {
		this.daoVoiture = dao;
		log.info("<------------- dao voiture injected ---------------->");
	}
	
	@Override
	public Voiture addVoiture(Voiture v) {
		return daoVoiture.addVoiture(v);
	}

	@Override
	public Voiture getVoitureById(Long idVoiture) {
		return daoVoiture.getVoitureById(idVoiture);
	}

	@Override
	public List<Voiture> getVoitures() {
		return daoVoiture.getVoitures();
	}

	@Override
	public Voiture updateVoiture(Voiture v) {
		return daoVoiture.updateVoiture(v);
	}

	@Override
	public Voiture deleteVoiture(Long idVoiture) {
		return daoVoiture.deleteVoiture(idVoiture);
	}

	@Override
	public List<Voiture> disponibiliteVoiture()
			throws VoitureDisponibleException {
		return daoVoiture.disponibiliteVoiture();
	}

	@Override
	public List<Voiture> disponibiliteVoiture(Date dDebut, Date dFin)
			throws VoitureDisponibleException {
		return daoVoiture.disponibiliteVoiture(dDebut, dFin);
	}

	@Override
	public List<Voiture> rentreVoiture() {
		return daoVoiture.rentreVoiture();
	}

	@Override
	public String alerteEntretien(Long idVoiture) {
		return daoVoiture.alerteEntretien(idVoiture);
	}

}
