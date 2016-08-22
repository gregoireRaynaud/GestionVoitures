package com.adaming.gestionvoitures.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.adaming.gestionvoitures.entities.Client;
import com.adaming.gestionvoitures.entities.ClientDecorator;
import com.adaming.gestionvoitures.entities.Voiture;
import com.adaming.gestionvoitures.service.client.IClientService;
import com.adaming.gestionvoitures.service.facture.IFactureService;

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
	@Autowired
	private IFactureService factureService;
	
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
	
	//private List<ClientDecorator> filteredCustomer;
	private List<ClientDecorator> clientsD = new ArrayList<ClientDecorator>();
	private Client client;
	private ClientDecorator clientD;
	
	
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
	
	public ClientDecorator getClientD() {
		return clientD;
	}

	public void setClientD(ClientDecorator clientD) {
		this.clientD = clientD;
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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<ClientDecorator> getClientsD() {
		return clientsD;
	}

	public void setClientsD(List<ClientDecorator> clientsD) {
		this.clientsD = clientsD;
	}

	/*public List<ClientDecorator> getFilteredCustomer() {
		return filteredCustomer;
	}

	public void setFilteredCustomer(List<ClientDecorator> filteredCustomer) {
		this.filteredCustomer = filteredCustomer;
	}*/

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
		//clients = clientService.getClients();
		List<Client> tabC = clientService.getClients();
		for(Client c:tabC){
			ClientDecorator cd = new ClientDecorator(c);
			cd.setDepenses(factureService.calculerCoutFacturesByClient(c.getIdClient()));
			clientsD.add(cd);
		}
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
	
	//Get Client By Id
	public void getClientById(){
		client = clientService.getClientById(idClient);
	}
	
	//Méthode pour capturer un client depuis le tableau des clients
	public void attrListener(ActionEvent event){
		clientD = (ClientDecorator) event.getComponent().getAttributes().get("c");
	}
	
	//Redirection vers la page updateClient
	public String RedirectUpdateClient(){
		client = clientD.getClient();
		return "updateClient.xhtml";
//		return "getClients.xhtml";
	}
}
