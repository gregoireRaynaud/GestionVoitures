package com.adaming.gestionvoitures.entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Nom : Agence
 * package com.adaming.gestionvoitures.entities;
 * @author Grégoire RAYNAUD
 * Date de dernière modification : 08/08/2016
 */
@Entity
public class Agence {

	//Attributs
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idAgence;
	private String nom;
	private String prenom;
	private Date datedenaissance;
	private String addrespostal;
	private String numerotel;
	private String adressMail;
	private String numeroDimatricule;
	private String denomination;
	private String formJuridique;
	private String activite;
	private String adressSiege;
	private Date dateCommencement;
	private String ville;
	private String douxiemTel;
	private Double tva;
	private String choixMonnais;	
	
	//Associations
	@OneToMany
	private List<Facture> factures;
	
	//Constructeur par défaut
	public Agence() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//Constrcteur avec paramètre
	public Agence(String nom, String prenom, Date datedenaissance,
			String addrespostal, String numerotel, String adressMail,
			String numeroDimatricule, String denomination,
			String formJuridique, String activite, String adressSiege,
			Date dateCommencement, String ville, String douxiemTel, Double tva,
			String choixMonnais) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.datedenaissance = datedenaissance;
		this.addrespostal = addrespostal;
		this.numerotel = numerotel;
		this.adressMail = adressMail;
		this.numeroDimatricule = numeroDimatricule;
		this.denomination = denomination;
		this.formJuridique = formJuridique;
		this.activite = activite;
		this.adressSiege = adressSiege;
		this.dateCommencement = dateCommencement;
		this.ville = ville;
		this.douxiemTel = douxiemTel;
		this.tva = tva;
		this.choixMonnais = choixMonnais;
	}
	
	//Getters et setters
	public Long getIdAgence() {
		return idAgence;
	}

	public void setIdAgence(Long idAgence) {
		this.idAgence = idAgence;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDatedenaissance() {
		return datedenaissance;
	}

	public void setDatedenaissance(Date datedenaissance) {
		this.datedenaissance = datedenaissance;
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

	public String getAdressMail() {
		return adressMail;
	}

	public void setAdressMail(String adressMail) {
		this.adressMail = adressMail;
	}

	public String getNumeroDimatricule() {
		return numeroDimatricule;
	}

	public void setNumeroDimatricule(String numeroDimatricule) {
		this.numeroDimatricule = numeroDimatricule;
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

	public String getDouxiemTel() {
		return douxiemTel;
	}

	public void setDouxiemTel(String douxiemTel) {
		this.douxiemTel = douxiemTel;
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
