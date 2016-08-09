package com.adaming.gestionvoitures.service.voiture;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adaming.gestionvoitures.dao.voiture.IVoitureDao;
import com.adaming.gestionvoitures.entities.ChaineDistribution;
import com.adaming.gestionvoitures.entities.FiltreHuile;
import com.adaming.gestionvoitures.entities.Vidange;
import com.adaming.gestionvoitures.entities.Voiture;
import com.adaming.gestionvoitures.exception.VoitureDisponibleException;
import com.adaming.gestionvoitures.service.entretien.IEntretienService;

@Service
@Transactional
public class VoitureServiceImpl implements IVoitureService{

	@Autowired
	IEntretienService entretienService;
	
	private IVoitureDao daoVoiture;
	
	Logger log = Logger.getLogger("VoitureServiceImpl");
	
	public void setDaoVoiture(IVoitureDao dao) {
		this.daoVoiture = dao;
		log.info("<------------- dao voiture injected ---------------->");
	}
	
	@Override
	public Voiture addVoiture(Voiture v) {
		return daoVoiture.addVoiture(v);
	}

	@Override
	public Voiture getVoitureById(Long idVoiture) {
		return daoVoiture.getVoitureById(idVoiture);
	}

	@Override
	public List<Voiture> getVoitures() {
		return daoVoiture.getVoitures();
	}

	@Override
	public Voiture updateVoiture(Voiture v) {
		return daoVoiture.updateVoiture(v);
	}

	@Override
	public Voiture deleteVoiture(Long idVoiture) {
		return daoVoiture.deleteVoiture(idVoiture);
	}

	@Override
	public List<Voiture> disponibiliteVoiture()
			throws VoitureDisponibleException {
		return daoVoiture.disponibiliteVoiture();
	}

	@Override
	public List<Voiture> disponibiliteVoiture(Date dDebut, Date dFin)
			throws VoitureDisponibleException {
		return daoVoiture.disponibiliteVoiture(dDebut, dFin);
	}

	@Override
	public List<Voiture> rentreVoiture() {
		return daoVoiture.rentreVoiture();
	}

	@Override
	public String alerteEntretien(Long idVoiture) {
		Voiture v = getVoitureById(idVoiture);
		String alerte = "Entretiens à faire :\n";
		List<Vidange> tousVidange = entretienService.getVidanges();
		List<Vidange> voitureVidange = new ArrayList<Vidange>();
		List<FiltreHuile> tousFiltreHuile = entretienService.getFiltreHuiles();
		List<FiltreHuile> voitureFiltreHuile = new ArrayList<FiltreHuile>();
		List<ChaineDistribution> tousChaineDistribution = entretienService.getChaineDistributions();
		List<ChaineDistribution> voitureChaineDistribution = new ArrayList<ChaineDistribution>();
		if(tousVidange != null){
			for(Vidange vi : tousVidange){
				if(vi.getVoiture().getIdvoiture() == idVoiture){
					voitureVidange.add(vi);
				}
			}
			Vidange viRecente = new Vidange();
			for(Vidange vi : voitureVidange){
				if(vi.getDateEntretien().getTime() > viRecente.getDateEntretien().getTime()){
					viRecente = vi;
				}
			}
			if(v.getKilometrage() - viRecente.getVoiture().getKilometrage() > viRecente.getKilometrage()){
				Double km = (v.getKilometrage() - viRecente.getVoiture().getKilometrage() - viRecente.getKilometrage());
				alerte = alerte + "- Vidange à faire rapidement (km dépassés : " +  km + ")\n";
			}else{
				Double km = 10000 - (viRecente.getKilometrage() + v.getKilometrage());
				alerte = alerte + "- Vidange à faire dans : " +  km + "\n";
			}
		}else{
			if(v.getKilometrage() > 10000){
				Double km = v.getKilometrage() - 10000;
				alerte = alerte + "- Vidange à faire rapidement (km dépassés : " +  km + ")\n";
			}else{
				Double km = 10000 - (v.getKilometrage());
				alerte = alerte + "- Vidange à faire dans : " +  km + "\n";
			}
		}
		if(tousChaineDistribution != null){
			for(ChaineDistribution c : tousChaineDistribution){
				if(c.getVoiture().getIdvoiture() == idVoiture){
					voitureChaineDistribution.add(c);
				}
			}
			ChaineDistribution cRecente = new ChaineDistribution();
			for(ChaineDistribution c : voitureChaineDistribution){
				if(c.getDateEntretien().getTime() > cRecente.getDateEntretien().getTime()){
					cRecente = c;
				}
			}
			if(v.getKilometrage() - cRecente.getVoiture().getKilometrage() > cRecente.getKilometrage()){
				Double km = (v.getKilometrage() - cRecente.getVoiture().getKilometrage() - cRecente.getKilometrage());
				alerte = alerte + "- Vidange à faire rapidement (km dépassés : " +  km + ")\n";
			}else{
				Double km = 30000 - (cRecente.getKilometrage() + v.getKilometrage());
				alerte = alerte + "- Vidange à faire dans : " +  km + "\n";
			}
		}else{
			if(v.getKilometrage() > 30000){
				Double km = v.getKilometrage() - 30000;
				alerte = alerte + "- Chaine de distribution à faire rapidement (km dépassés : " +  km + ")\n";
			}else{
				Double km = 30000 - (v.getKilometrage());
				alerte = alerte + "- Chaine de distribution à faire dans : " +  km + "\n";
			}
		}
		if(tousFiltreHuile != null){
			for(FiltreHuile f : tousFiltreHuile){
				if(f.getVoiture().getIdvoiture() == idVoiture){
					voitureFiltreHuile.add(f);
				}
			}
			FiltreHuile fRecente = new FiltreHuile();
			for(FiltreHuile f : voitureFiltreHuile){
				if(f.getDateEntretien().getTime() > fRecente.getDateEntretien().getTime()){
					fRecente = f;
				}
			}
			if(v.getKilometrage() - fRecente.getVoiture().getKilometrage() > fRecente.getKilometrage()){
				Double km = (v.getKilometrage() - fRecente.getVoiture().getKilometrage() - fRecente.getKilometrage());
				alerte = alerte + "- Vidange à faire rapidement (km dépassés : " +  km + ")\n";
			}else{
				Double km = 10000 - (fRecente.getKilometrage() + v.getKilometrage());
				alerte = alerte + "- Vidange à faire dans : " +  km + "\n";
			}
		}else{
			if(v.getKilometrage() > 10000){
				Double km = v.getKilometrage() - 10000;
				alerte = alerte + "- Filtre à huile à faire rapidement (km dépassés : " +  km + ")\n";
			}else{
				Double km = 10000 - (v.getKilometrage());
				alerte = alerte + "- Filtre à huile à faire dans : " +  km + "\n";
			}
		}
		return alerte;
	}

}
