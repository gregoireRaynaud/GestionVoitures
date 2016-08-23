package com.adaming.gestionvoitures.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adaming.gestionvoitures.entities.Voiture;
import com.adaming.gestionvoitures.exception.VoitureDisponibleException;
import com.adaming.gestionvoitures.service.voiture.IVoitureService;

@Component("VoitureUpdateBean")
@RequestScoped
public class VoitureUpdateBean {

	@Autowired
	IVoitureService voitureService;

	@NotNull(message="Veuillez choisir une voiture")
	private Long idVoiture;
	private List<Voiture> tabVoiture;
	private Voiture voiture;
	private Voiture saveV;
	@NotNull
	private Double kilometrage;
	private String alerte;
	private List<Double> tabAlerte;
	private String exception = "";
	
	@PostConstruct
	public String init(){
		getVoitures();
		return "redirect:updateKmVoiture.xhtml";
	}
	
	@PostConstruct
	public void getVoitures(){
		tabVoiture = voitureService.rentreVoiture();
	}
	
	public void getVoitureById(){
		voiture = voitureService.getVoitureById(idVoiture);
	}
	
	public String updateVoiture(){
		try {
			saveV = voitureService.getVoitureById(idVoiture);
			System.out.println("<---------------- save : " + saveV.getKilometrage());
			exception = "";
			System.out.println("<---------------- voiture : " + voiture.getKilometrage());
			Voiture v = voiture;
			v.setKilometrage(voiture.getKilometrage());
			System.out.println("<---------------- v : " + v.getKilometrage());
			System.out.println("<---------------- test : " + (saveV.getKilometrage() > v.getKilometrage()));
			if(saveV.getKilometrage() > v.getKilometrage()){
				throw new VoitureDisponibleException("Vous ne pouvez pas entrer un kilométrage inférieur au précendent kilométrage enregistré");
			}
			voitureService.updateVoiture(v);
			tabAlerte = voitureService.alerteEntretien(idVoiture);
			return "succesRetour.xhtml-redirect=true";
		} catch (VoitureDisponibleException e) {
			exception = e.getMessage();
			return "updateKmVoiture.xhtml";
		}
	}
	
	public VoitureUpdateBean() {
		super();
	}


	

	public Long getIdVoiture() {
		return idVoiture;
	}

	public void setIdVoiture(Long idVoiture) {
		this.idVoiture = idVoiture;
	}



	public List<Voiture> getTabVoiture() {
		return tabVoiture;
	}



	public void setTabVoiture(List<Voiture> tabVoiture) {
		this.tabVoiture = tabVoiture;
	}

	public Voiture getVoiture() {
		return voiture;
	}

	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}

	public Double getKilometrage() {
		return kilometrage;
	}

	public void setKilometrage(Double kilometrage) {
		this.kilometrage = kilometrage;
	}

	public String getAlerte() {
		return alerte;
	}

	public void setAlerte(String alerte) {
		this.alerte = alerte;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public Voiture getSaveV() {
		return saveV;
	}

	public void setSaveV(Voiture saveV) {
		this.saveV = saveV;
	}

	public List<Double> getTabAlerte() {
		return tabAlerte;
	}

	public void setTabAlerte(List<Double> tabAlerte) {
		this.tabAlerte = tabAlerte;
	}

	
	
	
}
