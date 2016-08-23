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

//	@Override
//	public String alerteEntretien(Long idVoiture) {
//		Voiture v = getVoitureById(idVoiture);
//		String alerte = "Entretiens à faire :\n";
//		List<Vidange> tousVidange = entretienService.getVidanges();
//		List<Vidange> voitureVidange = new ArrayList<Vidange>();
//		List<FiltreHuile> tousFiltreHuile = entretienService.getFiltreHuiles();
//		List<FiltreHuile> voitureFiltreHuile = new ArrayList<FiltreHuile>();
//		List<ChaineDistribution> tousChaineDistribution = entretienService.getChaineDistributions();
//		List<ChaineDistribution> voitureChaineDistribution = new ArrayList<ChaineDistribution>();
//		if(tousVidange != null){
//			for(Vidange vi : tousVidange){
//				if(vi.getVoiture() != null){
//					if(vi.getVoiture().getIdvoiture() == idVoiture){
//						voitureVidange.add(vi);
//					}
//				}
//			}
//			if(!voitureVidange.isEmpty()){
//				Vidange viRecente = new Vidange();
//				for(Vidange vi : voitureVidange){
//					if(viRecente.getDateEntretien() == null){
//						viRecente = vi;
//					}else if(vi.getDateEntretien().getTime() > viRecente.getDateEntretien().getTime()){
//						viRecente = vi;
//					}
//				}
//				if(v.getKilometrage() - viRecente.getKilometrage() > 10000){
//					Double km = (v.getKilometrage() - viRecente.getKilometrage() - 10000);
//					alerte = alerte + "- Vidange à faire rapidement (km dépassés : " +  km + ")\n";
//				}else{
//					Double km = 10000 - (v.getKilometrage() - viRecente.getKilometrage());
//					alerte = alerte + "- Vidange à faire dans : " +  km + "\n";
//				}
//			}else{
//				if(v.getKilometrage() > 10000){
//					Double km = v.getKilometrage() - 10000d;
//					alerte = alerte + "- Vidange à faire rapidement (km dépassés : " +  km + ")\n";
//				}else{
//					Double km = 10000d - v.getKilometrage();
//					alerte = alerte + "- Vidange à faire dans : " +  km + "\n";
//				}
//			}
//		}else{
//			if(v.getKilometrage() > 10000){
//				Double km = v.getKilometrage() - 10000;
//				alerte = alerte + "- Vidange à faire rapidement (km dépassés : " +  km + ")\n";
//			}else{
//				Double km = 10000 - v.getKilometrage();
//				alerte = alerte + "- Vidange à faire dans : " +  km + "\n";
//			}
//		}
//		if(tousChaineDistribution != null){
//			for(ChaineDistribution c : tousChaineDistribution){
//				if(c.getVoiture() != null){
//					if(c.getVoiture().getIdvoiture() == idVoiture){
//						voitureChaineDistribution.add(c);
//					}
//				}
//			}
//			if(!voitureChaineDistribution.isEmpty()){
//				ChaineDistribution cRecente = new ChaineDistribution();
//				for(ChaineDistribution c : voitureChaineDistribution){
//					if(cRecente.getDateEntretien() == null){
//						cRecente = c;
//					}else if(c.getDateEntretien().getTime() > cRecente.getDateEntretien().getTime()){
//						cRecente = c;
//					}
//				}
//				if(v.getKilometrage() - cRecente.getKilometrage() > 30000){
//					Double km = (v.getKilometrage() - cRecente.getKilometrage() - 30000);
//					alerte = alerte + "- Chaine de distribution à faire rapidement (km dépassés : " +  km + ")\n";
//				}else{
//					Double km = 30000 - (v.getKilometrage() - cRecente.getKilometrage());
//					alerte = alerte + "- Chaine de distribution à faire dans : " +  km + "\n";
//				}
//			}else{
//				if(v.getKilometrage() > 30000){
//					Double km = v.getKilometrage() - 30000d;
//					alerte = alerte + "- Chaine de distribution à faire rapidement (km dépassés : " +  km + ")\n";
//				}else{
//					Double km = 30000d - v.getKilometrage();
//					alerte = alerte + "- Chaine de distribution à faire dans : " +  km + "\n";
//				}
//			}
//		}else{
//			if(v.getKilometrage() > 30000){
//				Double km = v.getKilometrage() - 30000;
//				alerte = alerte + "- Chaine de distribution à faire rapidement (km dépassés : " +  km + ")\n";
//			}else{
//				Double km = 30000 - v.getKilometrage();
//				alerte = alerte + "- Chaine de distribution à faire dans : " +  km + "\n";
//			}
//		}
//		if(tousFiltreHuile != null){
//			for(FiltreHuile f : tousFiltreHuile){
//				if(f.getVoiture() != null){
//					if(f.getVoiture().getIdvoiture() == idVoiture){
//						voitureFiltreHuile.add(f);
//					}
//				}
//			}
//			if(!voitureFiltreHuile.isEmpty()){
//				FiltreHuile fRecente = new FiltreHuile();
//				for(FiltreHuile f : voitureFiltreHuile){
//					if(fRecente.getDateEntretien() == null){
//						fRecente = f;
//					}else if(f.getDateEntretien().getTime() > fRecente.getDateEntretien().getTime()){
//						fRecente = f;
//					}
//				}
//				if(v.getKilometrage() - fRecente.getKilometrage() > 10000){
//					Double km = (v.getKilometrage() - fRecente.getKilometrage() - 10000);
//					alerte = alerte + "- Filtre à huile à faire rapidement (km dépassés : " +  km + ")\n";
//				}else{
//					Double km = 10000 - (v.getKilometrage() - fRecente.getKilometrage());
//					alerte = alerte + "- Filtre à huile à faire dans : " +  km + "\n";
//				}
//			}else{
//				if(v.getKilometrage() > 10000){
//					Double km = v.getKilometrage() - 10000d;
//					alerte = alerte + "- Filtre à huile à faire rapidement (km dépassés : " +  km + ")\n";
//				}else{
//					Double km = 10000d - v.getKilometrage();
//					alerte = alerte + "- Filtre à huile à faire dans : " +  km + "\n";
//				}
//			}
//		}else{
//			if(v.getKilometrage() > 10000){
//				Double km = v.getKilometrage() - 10000;
//				alerte = alerte + "- Filtre à huile à faire rapidement (km dépassés : " +  km + ")\n";
//			}else{
//				Double km = 10000 - v.getKilometrage();
//				alerte = alerte + "- Filtre à huile à faire dans : " +  km + "\n";
//			}
//		}
//		return alerte;
//	}
	
	@Override
	public List<Double> alerteEntretien(Long idVoiture) {
		Voiture v = getVoitureById(idVoiture);
		List<Double> alerte = new ArrayList<Double>();
		List<Vidange> tousVidange = entretienService.getVidanges();
		List<Vidange> voitureVidange = new ArrayList<Vidange>();
		List<FiltreHuile> tousFiltreHuile = entretienService.getFiltreHuiles();
		List<FiltreHuile> voitureFiltreHuile = new ArrayList<FiltreHuile>();
		List<ChaineDistribution> tousChaineDistribution = entretienService.getChaineDistributions();
		List<ChaineDistribution> voitureChaineDistribution = new ArrayList<ChaineDistribution>();
		if(tousVidange != null){
			for(Vidange vi : tousVidange){
				if(vi.getVoiture() != null){
					if(vi.getVoiture().getIdvoiture() == idVoiture){
						voitureVidange.add(vi);
					}
				}
			}
			if(!voitureVidange.isEmpty()){
				Vidange viRecente = new Vidange();
				for(Vidange vi : voitureVidange){
					if(viRecente.getDateEntretien() == null){
						viRecente = vi;
					}else if(vi.getDateEntretien().getTime() > viRecente.getDateEntretien().getTime()){
						viRecente = vi;
					}
				}
				if(v.getKilometrage() - viRecente.getKilometrage() > 10000){
					Double km = (v.getKilometrage() - viRecente.getKilometrage() - 10000);
					alerte.add(- km);
				}else{
					Double km = 10000 - (v.getKilometrage() - viRecente.getKilometrage());
					alerte.add(km);
				}
			}else{
				if(v.getKilometrage() > 10000){
					Double km = v.getKilometrage() - 10000d;
					alerte.add(-  km);
				}else{
					Double km = 10000d - v.getKilometrage();
					alerte.add(km);
				}
			}
		}else{
			if(v.getKilometrage() > 10000){
				Double km = v.getKilometrage() - 10000;
				alerte.add(-  km);
			}else{
				Double km = 10000 - v.getKilometrage();
				alerte.add(km);
			}
		}
		if(tousChaineDistribution != null){
			for(ChaineDistribution c : tousChaineDistribution){
				if(c.getVoiture() != null){
					if(c.getVoiture().getIdvoiture() == idVoiture){
						voitureChaineDistribution.add(c);
					}
				}
			}
			if(!voitureChaineDistribution.isEmpty()){
				ChaineDistribution cRecente = new ChaineDistribution();
				for(ChaineDistribution c : voitureChaineDistribution){
					if(cRecente.getDateEntretien() == null){
						cRecente = c;
					}else if(c.getDateEntretien().getTime() > cRecente.getDateEntretien().getTime()){
						cRecente = c;
					}
				}
				if(v.getKilometrage() - cRecente.getKilometrage() > 30000){
					Double km = (v.getKilometrage() - cRecente.getKilometrage() - 30000);
					alerte.add(- km);
				}else{
					Double km = 30000 - (v.getKilometrage() - cRecente.getKilometrage());
					alerte.add(km);
				}
			}else{
				if(v.getKilometrage() > 30000){
					Double km = v.getKilometrage() - 30000d;
					alerte.add(- km);
				}else{
					Double km = 30000d - v.getKilometrage();
					alerte.add(km);
				}
			}
		}else{
			if(v.getKilometrage() > 30000){
				Double km = v.getKilometrage() - 30000;
				alerte.add(- km);
			}else{
				Double km = 30000 - v.getKilometrage();
				alerte.add(km);
			}
		}
		if(tousFiltreHuile != null){
			for(FiltreHuile f : tousFiltreHuile){
				if(f.getVoiture() != null){
					if(f.getVoiture().getIdvoiture() == idVoiture){
						voitureFiltreHuile.add(f);
					}
				}
			}
			if(!voitureFiltreHuile.isEmpty()){
				FiltreHuile fRecente = new FiltreHuile();
				for(FiltreHuile f : voitureFiltreHuile){
					if(fRecente.getDateEntretien() == null){
						fRecente = f;
					}else if(f.getDateEntretien().getTime() > fRecente.getDateEntretien().getTime()){
						fRecente = f;
					}
				}
				if(v.getKilometrage() - fRecente.getKilometrage() > 10000){
					Double km = (v.getKilometrage() - fRecente.getKilometrage() - 10000);
					alerte.add(- km);
				}else{
					Double km = 10000 - (v.getKilometrage() - fRecente.getKilometrage());
					alerte.add(km);
				}
			}else{
				if(v.getKilometrage() > 10000){
					Double km = v.getKilometrage() - 10000d;
					alerte.add(- km);
				}else{
					Double km = 10000d - v.getKilometrage();
					alerte.add(km);
				}
			}
		}else{
			if(v.getKilometrage() > 10000){
				Double km = v.getKilometrage() - 10000;
				alerte.add(- km);
			}else{
				Double km = 10000 - v.getKilometrage();
				alerte.add(km);
			}
		}
		return alerte;
	}

}
