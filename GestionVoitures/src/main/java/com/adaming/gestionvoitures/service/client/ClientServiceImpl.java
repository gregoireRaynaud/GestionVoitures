package com.adaming.gestionvoitures.service.client;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adaming.gestionvoitures.dao.client.IClientDao;
import com.adaming.gestionvoitures.entities.Client;

/**
 * Nom : ClientServiceImpl
 * package com.adaming.gestionvoitures.service.client;
 * @author Grégoire RAYNAUD
 * Date de dernière modification : 09/08/2016
 */
@Service
@Transactional
public class ClientServiceImpl implements IClientService{

	private IClientDao daoClient;
	
	Logger log = Logger.getLogger("ClientServiceImpl");
	
	
	public void setDao(IClientDao daoClient) {
		this.daoClient = daoClient;
		log.info("<------------ daoClient injected ------------>");
	}

	@Override
	public Client addClient(Client c) {
		// TODO Auto-generated method stub
		return daoClient.addClient(c);
	}

	@Override
	public Client updateClient(Client c) {
		// TODO Auto-generated method stub
		return daoClient.updateClient(c);
	}

	@Override
	public Client getClientById(Long idClient) {
		// TODO Auto-generated method stub
		return daoClient.getClientById(idClient);
	}

	@Override
	public List<Client> getClientByMc(String mc) {
		// TODO Auto-generated method stub
		return daoClient.getClientByMc(mc);
	}

	@Override
	public List<Client> getClients() {
		// TODO Auto-generated method stub
		return daoClient.getClients();
	}

}
