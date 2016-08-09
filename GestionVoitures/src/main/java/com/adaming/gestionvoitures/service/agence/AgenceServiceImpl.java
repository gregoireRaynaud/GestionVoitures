package com.adaming.gestionvoitures.service.agence;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
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
@Component
public class AgenceServiceImpl implements IAgenceService{

	private IAgenceDao daoAgence;
	
	Logger log = Logger.getLogger("AgenceServiceImpl");
	
	
	public void setDaoAgence(IAgenceDao daoAgence) {
		this.daoAgence = daoAgence;
		log.info("<------------ daoAgence injected ------------>");
	}

	@Override
	public Agence addAgence(Agence a) {
		// TODO Auto-generated method stub
		return daoAgence.addAgence(a);
	}

	@Override
	public Agence updateAgence(Agence a) {
		// TODO Auto-generated method stub
		return daoAgence.updateAgence(a);
	}

	@Override
	public Agence getAgenceById(Long idAgence) {
		// TODO Auto-generated method stub
		return daoAgence.getAgenceById(idAgence);
	}

	@Override
	public List<Agence> getAgences() {
		// TODO Auto-generated method stub
		return daoAgence.getAgences();
	}

	@Override
	public Agence addFactureToAgence(Long idAgence, Long idFacture) {
		// TODO Auto-generated method stub
		return daoAgence.addFactureToAgence(idAgence, idFacture);
	}

}
