package com.adaming.gestionvoitures.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

/**
 * Nom : Agence
 * package com.adaming.gestionvoitures.entities;
 * @author Grégoire RAYNAUD
 * Date de dernière modification : 09/08/2016
 */
@Entity
public class Agence {

	//Attributs
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idAgence;
	private String addrespostal;
	private String numerotel;
	private String denomination;
	private String formJuridique;
	private String activite;
	private String adressSiege;
	private Date dateCommencement;
	private String ville;
	private String deuxiemTel;
	private Double tva;
	private String choixMonnais;	
	
	//Associations
	@OneToMany
	@JoinTable(name="Agence_Facture")
	private List<Facture> factures = new ArrayList<Facture>();
	
	//Constructeur par défaut
	public Agence() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	//Constrcteur avec paramètre
	public Agence(String addrespostal, String numerotel, String denomination,
			String formJuridique, String activite, String adressSiege,
			Date dateCommencement, String ville, String deuxiemTel, Double tva,
			String choixMonnais, List<Facture> factures) {
		super();
		this.addrespostal = addrespostal;
		this.numerotel = numerotel;
		this.denomination = denomination;
		this.formJuridique = formJuridique;
		this.activite = activite;
		this.adressSiege = adressSiege;
		this.dateCommencement = dateCommencement;
		this.ville = ville;
		this.deuxiemTel = deuxiemTel;
		this.tva = tva;
		this.choixMonnais = choixMonnais;
		this.factures = factures;
	}
	
	//Getters et setters
	public Long getIdAgence() {
		return idAgence;
	}
	public void setIdAgence(Long idAgence) {
		this.idAgence = idAgence;
	}
	public String getAddrespostal() {
		return addrespostal;
	}
	public void setAddrespostal(String addrespostal) {
		this.addrespostal = addrespostal;
	}
	public String getNumerotel() {
		return numerotel;
	}
	public void setNumerotel(String numerotel) {
		this.numerotel = numerotel;
	}
	public String getDenomination() {
		return denomination;
	}
	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}
	public String getFormJuridique() {
		return formJuridique;
	}
	public void setFormJuridique(String formJuridique) {
		this.formJuridique = formJuridique;
	}
	public String getActivite() {
		return activite;
	}
	public void setActivite(String activite) {
		this.activite = activite;
	}
	public String getAdressSiege() {
		return adressSiege;
	}
	public void setAdressSiege(String adressSiege) {
		this.adressSiege = adressSiege;
	}
	public Date getDateCommencement() {
		return dateCommencement;
	}
	public void setDateCommencement(Date dateCommencement) {
		this.dateCommencement = dateCommencement;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getDeuxiemTel() {
		return deuxiemTel;
	}
	public void setDeuxiemTel(String deuxiemTel) {
		this.deuxiemTel = deuxiemTel;
	}
	public Double getTva() {
		return tva;
	}
	public void setTva(Double tva) {
		this.tva = tva;
	}
	public String getChoixMonnais() {
		return choixMonnais;
	}
	public void setChoixMonnais(String choixMonnais) {
		this.choixMonnais = choixMonnais;
	}
	public List<Facture> getFactures() {
		return factures;
	}
	public void setFactures(List<Facture> factures) {
		this.factures = factures;
	}
	
}
