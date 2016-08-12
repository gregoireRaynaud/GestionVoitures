package com.adaming.gestionvoitures.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adaming.gestionvoitures.entities.Voiture;
import com.adaming.gestionvoitures.exception.VoitureDisponibleException;
import com.adaming.gestionvoitures.service.voiture.IVoitureService;

@Component("VoitureRetourBean")
@ViewScoped
public class VoitureRetourBean {

	@Autowired
	IVoitureService voitureService;
	
	private List<Voiture> tabVoiture;
	
	@PostConstruct
	public String init(){
		getVoitures();
		return "redirect:getVoitures.xhtml";
	}

	@PostConstruct
	public void getVoitures(){
			tabVoiture = voitureService.rentreVoiture();
	}
	
	
	public List<Voiture> getTabVoiture() {
		return tabVoiture;
	}

	public void setTabVoiture(List<Voiture> tabVoiture) {
		this.tabVoiture = tabVoiture;
	}

	public VoitureRetourBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	
	
}
