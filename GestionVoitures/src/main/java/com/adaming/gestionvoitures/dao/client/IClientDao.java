package com.adaming.gestionvoitures.dao.client;

import java.util.List;

import com.adaming.gestionvoitures.entities.Client;

/**
 * Nom : IClientDao
 * package com.adaming.gestionvoitures.dao.client;
 * @author Grégoire RAYNAUD
 * Date de dernière modification : 09/08/2016
 */
public interface IClientDao {

	public Client addClient(Client c);
	public Client updateClient(Client c);
	public Client getClientById(Long idClient);
	public List<Client> getClientByMc(String mc);
	public List<Client> getClients();
}
