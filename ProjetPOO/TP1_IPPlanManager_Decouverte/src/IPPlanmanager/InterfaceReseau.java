/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IPPlanmanager;

/**
 *
 * @author GENERAL STORES
 */
public class InterfaceReseau {
    String nom;
    AdresseIP adresseIP;
    boolean active;

    public InterfaceReseau(String nom, AdresseIP adresseIP) {
        this.nom = nom;
        this.adresseIP = adresseIP;
        this.active = false;
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
        } else {
            System.out.println("Adresse IP : non configurée");
        }

        if (active) {
            System.out.println("État : active");
        } else {
            System.out.println("État : inactive");
        }
    }

}
