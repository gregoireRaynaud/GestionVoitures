package com.adaming.gestionvoitures.service.entretien;

import java.util.List;

import com.adaming.gestionvoitures.entities.ChaineDistribution;
import com.adaming.gestionvoitures.entities.Entretien;
import com.adaming.gestionvoitures.entities.FiltreHuile;
import com.adaming.gestionvoitures.entities.Vidange;

/**
 * Nom : IEntretienService
 * package com.adaming.gestionvoitures.service.entretien;
 * @author Grégoire RAYNAUD
 * Date de dernière modification : 09/08/2016
 */
public interface IEntretienService {

	public Entretien addEntretient(Entretien e, Long idVoiture);
	public Entretien updateEntretien(Entretien e);
	public Entretien getEntretienById(Long idEntretien);
	public List<Entretien> getEntretiens();
	public List<ChaineDistribution> getChaineDistributions();
	public List<FiltreHuile> getFiltreHuiles();
	public List<Vidange> getVidanges();
}
