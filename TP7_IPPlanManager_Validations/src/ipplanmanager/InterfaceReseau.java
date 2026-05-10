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

public class InterfaceReseau {
    private String nom;
    private AdresseIP adresseIP;
    private String masque;
    
    public InterfaceReseau(String nom, AdresseIP adresseIP, String masque) {
        setNom(nom);
        this.adresseIP = adresseIP;
        setMasque(masque);
    }
    
    public String getNom() { return nom; }
    
    public void setNom(String nom) {
        this.nom = (nom == null || nom.isEmpty()) ? "interface_inconnue" : nom;
    }
    
    public AdresseIP getAdresseIP() { return adresseIP; }
    public void setAdresseIP(AdresseIP adresseIP) { this.adresseIP = adresseIP; }
    
    public String getMasque() { return masque; }
    
    public void setMasque(String masque) {
        this.masque = (masque == null || masque.isEmpty()) ? "255.255.255.0" : masque;
    }
    
    public void afficher() {
        System.out.print("Interface : " + nom + " | ");
        if (adresseIP != null) adresseIP.afficher();
        System.out.println("Masque : " + masque);
    }
}  
