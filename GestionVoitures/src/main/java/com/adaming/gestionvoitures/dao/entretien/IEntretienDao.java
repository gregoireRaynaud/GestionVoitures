package com.adaming.gestionvoitures.dao.entretien;

import java.util.List;

import com.adaming.gestionvoitures.entities.Entretien;

/**
 * Nom : IEntretienDao
 * package com.adaming.gestionvoitures.dao.entretien;
 * @author Grégoire RAYNAUD
 * Date de dernière modification : 09/08/2016
 */
public interface IEntretienDao {

	public Entretien addEntretient(Entretien e, Long idVoiture);
	public Entretien updateEntretien(Entretien e);
	public Entretien getEntretienById(Long idEntretien);
	public List<Entretien> getEntretiens();
	public List<Entretien> getChaineDistributions();
	public List<Entretien> getFiltreHuiles();
	public List<Entretien> getVidanges();
}
