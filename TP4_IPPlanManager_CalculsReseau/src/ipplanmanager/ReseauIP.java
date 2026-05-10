/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipplanmanager;

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
    
    public String getAdresseReseau() {
        return adresseReseau;
    }
    
    public void setAdresseReseau(String adresseReseau) {
        if (adresseReseau == null || adresseReseau.isEmpty()) {
            this.adresseReseau = "0.0.0.0";
        } else {
            this.adresseReseau = adresseReseau;
        }
    }
    
    public int getCidr() {
        return cidr;
    }
    
    public void setCidr(int cidr) {
        if (cidr < 0 || cidr > 32) {
            this.cidr = 24;
        } else {
            this.cidr = cidr;
        }
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            this.description = "Reseau_inconnu";
        } else {
            this.description = description;
        }
    }
    
    // NOUVELLE MÉTHODE AFFICHER AVEC CALCULS AUTOMATIQUES
    public void afficher() {
        System.out.println("----------------------------------------");
        System.out.println("Reseau : " + adresseReseau + "/" + cidr);
        System.out.println("Description : " + description);
        System.out.println("Classe réseau : " + CalculateurReseau.obtenirClasseReseau(adresseReseau));
        System.out.println("Masque decimal : " + CalculateurReseau.obtenirMasqueDecimal(cidr));
        System.out.println("Capacite maximale : " + CalculateurReseau.calculerNombreHotes(cidr) + " hotes");
        System.out.println("Reseau prive : " + (CalculateurReseau.estReseauPrive(adresseReseau) ? "Oui" : "Non"));
        System.out.println("----------------------------------------");
    }
}