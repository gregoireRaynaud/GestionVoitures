package com.adaming.gestionvoitures.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.adaming.gestionvoitures.entities.Voiture;
import com.adaming.gestionvoitures.entities.VoitureDecorator;
import com.adaming.gestionvoitures.service.facture.IFactureService;
import com.adaming.gestionvoitures.service.voiture.IVoitureService;

@SuppressWarnings("serial")
@Component("VoitureGetBean")
@Scope("session")
public class VoitureGetBean implements Serializable{

	@Autowired
	IVoitureService voitureService;
	@Autowired
	private IFactureService factureService;
	
	private Voiture v;
	private List<Voiture> tabVoiture;
	private Voiture voiture;
	private VoitureDecorator voitureD;
	private List<VoitureDecorator> tabVoitureD = new ArrayList<VoitureDecorator>();
	
	@Size(min=2,max=20, message="Veuillez entrer un model entre 2 et 20 lettres")
	private String modelVoiture;
	@Size(min=7, max=7, message="Veuillez entrer une immatriculation de 7 caractï¿½res")
    private String immatricule;
	@Min(0) 
	@Max(1000000)
	@NotNull(message="Veuillez entrer le kilomï¿½trage")
    private Double kilometrage;
	@Min(0) 
	@Max(100000)
	@NotNull(message="Veuillez entrer un prix")
    private Double prixVoiture;
	@NotNull(message="Veuillez choisir un type de voiture")
    private String typeVoiture;
	@NotNull(message="Veuillez choisir un type de carburant")
    private String typeCarburant;
	@NotNull(message="Veuillez choisir un ï¿½tat")
    private String etatVoiture;
	private Long idVoiture;
	private int test = 0;
	
	
	public String updateVoiture(){
		//Voiture v = new Voiture(voiture.getModelVoiture(), immatricule, kilometrage, prixVoiture, typeVoiture, typeCarburant, etatVoiture);
		//v.setIdvoiture(voiture.getIdvoiture());
		voitureService.updateVoiture(v);
		return "successUpdateVoiture.xhtml";
	}
	
	public String deleteVoiture(){
		voitureService.deleteVoiture(voitureD.getVoiture().getIdvoiture());
		FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Succès",  "La voiture a bien été supprimée") );
		return "getVoitures.xhtml";
	}
	
	 public void attrListener(ActionEvent event){
		voitureD = (VoitureDecorator) event.getComponent().getAttributes().get("v");
	 }
	
	@PostConstruct
	public String getVoitures(){
		/*tabVoiture = voitureService.getVoitures();
		return "redirect:getVoitures.xhtml";*/
		List<Voiture> tabV = voitureService.getVoitures();
		for(Voiture v:tabV){
			VoitureDecorator vd = new VoitureDecorator(v);
			vd.setChiffreDAffaires(factureService.calculerCoutFacturesByVoiture(v.getIdvoiture()));
			tabVoitureD.add(vd);
		}
		return "redirect:getVoitures.xhtml";
	}
	
	public String RedirectUpdateVoiture(){
		//voitureService.updateVoiture(voiture);
		//return "updateVoiture.xhtml";
		v = voitureD.getVoiture();
		//System.out.println(v.getModelVoiture());
		return "updateVoiture.xhtml";
	}
	
//	public String deleteVoiture(){
//		
//	}
	
	
	public VoitureGetBean() {
		super();
	}

	
	
	
	
	
	public Voiture getV() {
		return v;
	}

	public void setV(Voiture v) {
		this.v = v;
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

	public String getModelVoiture() {
		return modelVoiture;
	}

	public void setModelVoiture(String modelVoiture) {
		this.modelVoiture = modelVoiture;
	}

	public String getImmatricule() {
		return immatricule;
	}

	public void setImmatricule(String immatricule) {
		this.immatricule = immatricule;
	}

	public Double getKilometrage() {
		return kilometrage;
	}

	public void setKilometrage(Double kilometrage) {
		this.kilometrage = kilometrage;
	}

	public Double getPrixVoiture() {
		return prixVoiture;
	}

	public void setPrixVoiture(Double prixVoiture) {
		this.prixVoiture = prixVoiture;
	}

	public String getTypeVoiture() {
		return typeVoiture;
	}

	public void setTypeVoiture(String typeVoiture) {
		this.typeVoiture = typeVoiture;
	}

	public String getTypeCarburant() {
		return typeCarburant;
	}

	public void setTypeCarburant(String typeCarburant) {
		this.typeCarburant = typeCarburant;
	}

	public String getEtatVoiture() {
		return etatVoiture;
	}

	public void setEtatVoiture(String etatVoiture) {
		this.etatVoiture = etatVoiture;
	}

	public Long getIdVoiture() {
		return idVoiture;
	}

	public void setIdVoiture(Long idVoiture) {
		this.idVoiture = idVoiture;
	}

	public int getTest() {
		return test;
	}

	public void setTest(int test) {
		this.test = test;
	}

	public List<VoitureDecorator> getVoitureD() {
		return tabVoitureD;
	}

	public void setVoitureD(List<VoitureDecorator> tabVoitureD) {
		this.tabVoitureD = tabVoitureD;
	}

	public List<VoitureDecorator> getTabVoitureD() {
		return tabVoitureD;
	}

	public void setTabVoitureD(List<VoitureDecorator> tabVoitureD) {
		this.tabVoitureD = tabVoitureD;
	}

	public void setVoitureD(VoitureDecorator voitureD) {
		this.voitureD = voitureD;
	}
	
	
}
