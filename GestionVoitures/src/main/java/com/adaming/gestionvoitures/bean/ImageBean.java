package com.adaming.gestionvoitures.bean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
 

/**
 * Nom : ImageBean
 * package com.adaming.gestionvoitures.bean;
 * Classe pour gérer la gallerie d'images de la page d'accueil
 * @author Grégoire RAYNAUD
 * Date : 23/08/2016
 */

@ManagedBean
public class ImageBean {
	private List<String> images;
    
    @PostConstruct
    public void init() {
        images = new ArrayList<String>();
        for (int i = 1; i <= 5; i++) {
            images.add("voiture" + i + ".jpg");
        }
    }
 
    public List<String> getImages() {
        return images;
    }
}
