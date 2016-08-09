package com.adaming.gestionvoitures.dao.voiture;

import java.util.Date;
import java.util.List;

import com.adaming.gestionvoitures.entities.Voiture;
import com.adaming.gestionvoitures.exception.VoitureDisponibleException;

public interface IVoitureDao {

	public Voiture addVoiture(Voiture v);
	public Voiture getVoitureById(Long idVoiture);
	public List<Voiture> getVoitures();
	public Voiture updateVoiture(Voiture v);
	public Voiture deleteVoiture(Long idVoiture);
	public List<Voiture> disponibiliteVoiture() throws VoitureDisponibleException;
	public List<Voiture> disponibiliteVoiture(Date dDebut, Date dFin) throws VoitureDisponibleException;
	public List<Voiture> rentreVoiture();
	public String alerteEntretien(Long idVoiture);
	
}
