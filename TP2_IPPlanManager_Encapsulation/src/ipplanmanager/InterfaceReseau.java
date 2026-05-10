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
    private boolean active;

    public InterfaceReseau(String nom, AdresseIP adresseIP) {
        setNom(nom);
        this.adresseIP = adresseIP;
        this.active = false;
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

    public boolean isActive() {
        return active;
    }

    public void activer() {
        active = true;
    }

    public void desactiver() {
        active = false;
    }

    public void afficher() {
        System.out.println("Interface : " + nom);
        if (adresseIP != null) {
            adresseIP.afficher();
        }
        System.out.println("État : " + (active ? "active" : "inactive"));
    }
}

