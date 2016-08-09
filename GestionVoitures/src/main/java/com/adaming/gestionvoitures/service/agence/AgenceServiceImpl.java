package com.adaming.gestionvoitures.service.agence;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adaming.gestionvoitures.dao.agence.IAgenceDao;
import com.adaming.gestionvoitures.entities.Agence;

/**
 * Nom : AgenceServiceImpl
 * package com.adaming.gestionvoitures.service.agence;
 * @author Grégoire RAYNAUD
 * Date de dernière modification : 09/08/2016
 */
@Service
@Transactional
public class AgenceServiceImpl implements IAgenceService{

	private IAgenceDao dao;
	
	Logger log = Logger.getLogger("AgenceServiceImpl");
	
	
	public void setDao(IAgenceDao dao) {
		this.dao = dao;
		log.info("<------------ daoAgence injected ------------>");
	}

	@Override
	public Agence addAgence(Agence a) {
		// TODO Auto-generated method stub
		return dao.addAgence(a);
	}

	@Override
	public Agence updateAgence(Agence a) {
		// TODO Auto-generated method stub
		return dao.updateAgence(a);
	}

	@Override
	public Agence getAgenceById(Long idAgence) {
		// TODO Auto-generated method stub
		return dao.getAgenceById(idAgence);
	}

	@Override
	public List<Agence> getAgences() {
		// TODO Auto-generated method stub
		return dao.getAgences();
	}

	@Override
	public Agence addFactureToAgence(Long idAgence, Long idFacture) {
		// TODO Auto-generated method stub
		return dao.addFactureToAgence(idAgence, idFacture);
	}

}
