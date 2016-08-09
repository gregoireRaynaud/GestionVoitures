package com.adaming.gestionvoitures.service.facture;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adaming.gestionvoitures.dao.facture.IFactureDao;
import com.adaming.gestionvoitures.entities.Facture;

@Service
@Transactional
@Component
public class FactureServiceImpl implements IFactureService{
	
	@Autowired
	private IFactureDao daoFacture;
	
	Logger log = Logger.getLogger("FactureServiceImpl");
	
	
	public void setDaoFacture(IFactureDao daoFacture) {
		this.daoFacture = daoFacture;
	}

	@Override
	public Facture addFacture(Facture f, Long idReservation, Long idAgence) {
		// TODO Auto-generated method stub
		return daoFacture.addFacture(f, idReservation, idAgence);
	}

	@Override
	public Facture getFactureById(Long idFacture) {
		// TODO Auto-generated method stub
		return daoFacture.getFactureById(idFacture);
	}

	@Override
	public List<Facture> getListFactures() {
		// TODO Auto-generated method stub
		return daoFacture.getListFactures();
	}

	@Override
	public Double calculerCoutFacture(Long idFacture) {
		// TODO Auto-generated method stub
		return daoFacture.calculerCoutFacture(idFacture);
	}

	@Override
	public Double calculerCoutFacturesByClient(Long idClient) {
		// TODO Auto-generated method stub
		return daoFacture.calculerCoutFacturesByClient(idClient);
	}

	@Override
	public Double calculerCoutFacturesByVoiture(Long idVoiture) {
		// TODO Auto-generated method stub
		return daoFacture.calculerCoutFacturesByVoiture(idVoiture);
	}

}
