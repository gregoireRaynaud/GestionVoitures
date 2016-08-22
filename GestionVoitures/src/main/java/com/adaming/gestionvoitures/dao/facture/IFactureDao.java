package com.adaming.gestionvoitures.dao.facture;

import java.util.List;

import com.adaming.gestionvoitures.entities.Facture;
import com.adaming.gestionvoitures.exception.ReservationDejaFacturee;

public interface IFactureDao {
	
	public Facture addFacture(Facture f, Long idReservation, Long idAgence) throws ReservationDejaFacturee;
	public Facture getFactureById(Long idFacture);
	public List<Facture> getListFactures();
	public Double calculerCoutFacture(Long idFacture);
	public Double calculerCoutFacturesByClient(Long idClient);
	public Double calculerCoutFacturesByVoiture(Long idVoiture);

}
