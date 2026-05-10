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
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        if (nom == null || nom.isEmpty()) {
            this.nom = "interface_inconnue";
        } else {
            this.nom = nom;
        }
    }
    
    public AdresseIP getAdresseIP() {
        return adresseIP;
    }
    
    public void setAdresseIP(AdresseIP adresseIP) {
        this.adresseIP = adresseIP;
    }
    
    public String getMasque() {
        return masque;
    }
    
    public void setMasque(String masque) {
        if (masque == null || masque.isEmpty()) {
            this.masque = "255.255.255.0";
        } else {
            this.masque = masque;
        }
    }
    
    public void afficher() {
        System.out.print("Interface : " + nom + " | ");
        if (adresseIP != null) {
            adresseIP.afficher();
        }
        System.out.println("Masque : " + masque);
    }
}