package com.adaming.gestionvoitures.entities;

/**
 * Nom : ClientDecorator
 * package com.adaming.gestionvoitures.entities;
 * @author Grégoire RAYNAUD
 * Date : 22/08/2016
 * Classe pour ajouter l'attribut dépenses au client
 */
public class ClientDecorator {

	//Attributs
	private Client client;
	private Double depenses;
	
	//Constructeur sans paramètre
	public ClientDecorator() {
		super();
		// TODO Auto-generated constructor stub
	}

	//Constructeur avec paramètres
	public ClientDecorator(Client client) {
		super();
		this.client = client;
	}
	
	//Getter et setters
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Double getDepenses() {
		return depenses;
	}
	public void setDepenses(Double depenses) {
		this.depenses = depenses;
	}
	
	
}
