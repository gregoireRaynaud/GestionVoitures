package com.adaming.gestionvoitures.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adaming.gestionvoitures.entities.Client;
import com.adaming.gestionvoitures.service.client.IClientService;

/**
 * Nom :ClientBean
 * package com.adaming.gestionvoitures.bean;
 * @author Grégoire RAYNAUD
 * Date de dernière modification ; 11/08/2016
 */
@Component("ClientBean")
@RequestScoped
public class ClientBean {

	//Service
	@Autowired
	private IClientService clientService;
	
	//Attributs	
	private Long idClient;
	private String nomClient;
	private String prenomClient;
	private Date  dateDeNaissance; 
	private String numeroTel; 
	private String numeroPermis; 
	private String adressePostale; 
	private String mail; 
	private String pays; 
	private Date   dateDeDelivrance;
	private String lieuDeNaissance;
	private String delivrerPar;
	
	private List<Client> clients;
	private List<Client> clientsByMc;
	private Client client;
	private String mc;
	
	
	//Constructeur par défaut
	public ClientBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//Getters et setters
	public IClientService getClientService() {
		return clientService;
	}
	public void setClientService(IClientService clientService) {
		this.clientService = clientService;
	}
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
	
	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public List<Client> getClientsByMc() {
		return clientsByMc;
	}

	public void setClientsByMc(List<Client> clientsByMc) {
		this.clientsByMc = clientsByMc;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	//Autres méthodes
	//Ajouter un client
	public String addClient(){
		Client c = new Client(nomClient,prenomClient,dateDeNaissance,numeroTel,numeroPermis,adressePostale,
				mail,pays,dateDeDelivrance,lieuDeNaissance,delivrerPar);
		clientService.addClient(c);
		getC();
		return "getClients.xhtml";
	}
	
	//Redirection vers la page saveClient
	@PostConstruct
	public String redirect(){
		return "redirect:saveClient.xhtml";
	}
	
	//Get Clients
	@PostConstruct
	public void getC(){
		clients = clientService.getClients();
	}
	
	//Update Client
	public String updateClient(){
		Client c = client;
		c.setNomClient(nomClient);
		c.setPrenomClient(prenomClient);
		c.setDateDeNaissance(dateDeNaissance);
		c.setNumeroTel(numeroTel);
		c.setNumeroPermis(numeroPermis);
		c.setAdressePostale(adressePostale);
		c.setMail(mail);
		c.setPays(pays);
		c.setDateDeDelivrance(dateDeDelivrance);
		c.setLieuDeNaissance(lieuDeNaissance);
		c.setDelivrerPar(delivrerPar);
		clientService.updateClient(c);
		getC();
		return "getClients.xhtml";		
	}
	
	//Get Clients par mot-clef
	@PostConstruct
	public String getCByMc(){
		clientsByMc = clientService.getClientByMc(mc);
		return "getClientsByMc.xhtml";
	}
	
	//Get Client By Id
	public void getClientById(){
		client = clientService.getClientById(idClient);
	}
	
}
