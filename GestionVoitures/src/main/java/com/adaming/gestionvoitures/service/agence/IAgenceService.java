package com.adaming.gestionvoitures.service.agence;

import java.util.List;

import com.adaming.gestionvoitures.entities.Agence;

/**
 * Nom : IAgenceService
 * package com.adaming.gestionvoitures.service.agence;
 * @author Grégoire RAYNAUD
 * Date de dernière modification : 09/08/2016
 */
public interface IAgenceService {

	public Agence addAgence(Agence a);
	public Agence updateAgence(Agence a);
	public Agence getAgenceById(Long idAgence);
	public List<Agence> getAgences();
}
