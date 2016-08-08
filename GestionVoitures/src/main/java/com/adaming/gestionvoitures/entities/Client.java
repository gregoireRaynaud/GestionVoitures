package com.adaming.gestionvoitures.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Gautier
 * Nom Classe: Client
 * Date: 08/08/2016
 * Version: 1.0
 *
 */

@Entity
public class Client {
	
	  @Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	  private Long idClient;
	  private String nomClient;
	  private String prenomClient;
	  @Temporal(TemporalType.DATE)
	  private Date  dateDeNaissance; 
	  private String numeroTel; 
	  private String numeroPermis; 
	  private String adressePostale; 
	  private String mail; 
	  private String pays; 
	  private Date   dateDeDelivrance;
	  private String lieuDeNaissance;
	  private String delivrerPar;
	
	  /*CONSTRUCTEURS*/
	  
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Client(String nomClient, String prenomClient, Date dateDeNaissance, String numeroTel, String numeroPermis,
			String adressePostale, String mail, String pays, Date dateDeDelivrance, String lieuDeNaissance,
			String delivrerPar) {
		super();
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.dateDeNaissance = dateDeNaissance;
		this.numeroTel = numeroTel;
		this.numeroPermis = numeroPermis;
		this.adressePostale = adressePostale;
		this.mail = mail;
		this.pays = pays;
		this.dateDeDelivrance = dateDeDelivrance;
		this.lieuDeNaissance = lieuDeNaissance;
		this.delivrerPar = delivrerPar;
	}
	
	 /*GETTERS & SETTERS*/
	
	public Long getIdClient() {
		return idClient;
	}
	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}
	public String getNomClient() {
		return nomClient;
	}
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
	public String getPrenomClient() {
		return prenomClient;
	}
	public void setPrenomClient(String prenomClient) {
		this.prenomClient = prenomClient;
	}
	public Date getDateDeNaissance() {
		return dateDeNaissance;
	}
	public void setDateDeNaissance(Date dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}
	public String getNumeroTel() {
		return numeroTel;
	}
	public void setNumeroTel(String numeroTel) {
		this.numeroTel = numeroTel;
	}
	public String getNumeroPermis() {
		return numeroPermis;
	}
	public void setNumeroPermis(String numeroPermis) {
		this.numeroPermis = numeroPermis;
	}
	public String getAdressePostale() {
		return adressePostale;
	}
	public void setAdressePostale(String adressePostale) {
		this.adressePostale = adressePostale;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public Date getDateDeDelivrance() {
		return dateDeDelivrance;
	}
	public void setDateDeDelivrance(Date dateDeDelivrance) {
		this.dateDeDelivrance = dateDeDelivrance;
	}
	public String getLieuDeNaissance() {
		return lieuDeNaissance;
	}
	public void setLieuDeNaissance(String lieuDeNaissance) {
		this.lieuDeNaissance = lieuDeNaissance;
	}
	public String getDelivrerPar() {
		return delivrerPar;
	}
	public void setDelivrerPar(String delivrerPar) {
		this.delivrerPar = delivrerPar;
	}  
	  

}
