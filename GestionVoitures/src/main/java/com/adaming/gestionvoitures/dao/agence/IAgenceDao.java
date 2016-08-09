package com.adaming.gestionvoitures.dao.agence;

import java.util.List;

import com.adaming.gestionvoitures.entities.Agence;

/**
 * Nom : IAgenceDao
 * package com.adaming.gestionvoitures.dao.agence;
 * @author Grégoire RAYNAUD
 * Date de dernière modification : 09/08/2016
 */
public interface IAgenceDao {

	public Agence addAgence(Agence a);
	public Agence updateAgence(Agence a);
	public Agence getAgenceById(Long idAgence);
	public List<Agence> getAgences();
	//public Agence addFactureToAgence(Long idAgence, Long idFacture);
}
