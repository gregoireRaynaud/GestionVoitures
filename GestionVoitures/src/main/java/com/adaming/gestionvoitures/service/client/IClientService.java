package com.adaming.gestionvoitures.service.client;

import java.util.List;

import com.adaming.gestionvoitures.entities.Client;

/**
 * Nom : IClientService
 * package com.adaming.gestionvoitures.service.client;
 * @author Grégoire RAYNAUD
 * Date de dernière modification : 09/08/2016
 */
public interface IClientService {

	public Client addClient(Client c);
	public Client updateClient(Client c);
	public Client getClientById(Long idClient);
	public List<Client> getClientByMc(String mc);
	public List<Client> getClients();
}
