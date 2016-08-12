package com.adaming.gestionvoitures.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adaming.gestionvoitures.entities.Voiture;
import com.adaming.gestionvoitures.exception.VoitureDisponibleException;
import com.adaming.gestionvoitures.service.voiture.IVoitureService;

@Component("VoitureGetDispoBean")
@ViewScoped
public class VoitureGetDispoBean {

	@Autowired
	IVoitureService voitureService;
	
	private List<Voiture> tabVoiture;
	private String exceptionDispo;
	
	@PostConstruct
	public String init(){
		getVoitures();
		return "redirect:getVoitures.xhtml";
	}

	@PostConstruct
	public void getVoitures(){
		try {
			tabVoiture = voitureService.disponibiliteVoiture();
		} catch (VoitureDisponibleException e) {
			exceptionDispo = e.getMessage();
		}
	}
	
	
	public List<Voiture> getTabVoiture() {
		return tabVoiture;
	}

	public void setTabVoiture(List<Voiture> tabVoiture) {
		this.tabVoiture = tabVoiture;
	}

	public VoitureGetDispoBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getExceptionDispo() {
		return exceptionDispo;
	}

	public void setExceptionDispo(String exceptionDispo) {
		this.exceptionDispo = exceptionDispo;
	}

	
	
	
	
	
	
}
