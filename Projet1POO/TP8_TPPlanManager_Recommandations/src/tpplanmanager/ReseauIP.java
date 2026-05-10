/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpplanmanager;

/**
 *
 * @author GENERAL STORES
 */
public class ReseauIP {
    private String adresseReseau;
    private int cidr;
    private String description;
    
    public ReseauIP(String adresseReseau, int cidr, String description) {
        setAdresseReseau(adresseReseau);
        setCidr(cidr);
        setDescription(description);
    }
    
    public String getAdresseReseau() { return adresseReseau; }
    
    public void setAdresseReseau(String adresseReseau) {
        this.adresseReseau = (adresseReseau == null || adresseReseau.isEmpty()) ? "0.0.0.0" : adresseReseau;
    }
    
    public int getCidr() { return cidr; }
    
    public void setCidr(int cidr) {
        this.cidr = (cidr < 0 || cidr > 32) ? 24 : cidr;
    }
    
    public String getDescription() { return description; }
    
    public void setDescription(String description) {
        this.description = (description == null || description.isEmpty()) ? "Reseau_inconnu" : description;
    }
    
    public void afficher() {
        System.out.println("Reseau : " + adresseReseau + "/" + cidr);
        System.out.println("Description : " + description);
        System.out.println("Classe : " + CalculateurReseau.obtenirClasseReseau(adresseReseau));
        System.out.println("Masque : " + CalculateurReseau.obtenirMasqueDecimal(cidr));
        System.out.println("Capacite : " + CalculateurReseau.calculerNombreHotes(cidr) + " hotes");
    }
} 
