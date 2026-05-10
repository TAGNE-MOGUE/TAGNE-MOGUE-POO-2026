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
public class ReseauIP {
String adresseReseau;
    int masqueCidr;
    String description;

    public ReseauIP(String adresseReseau, int masqueCidr, String description) {
        this.adresseReseau = adresseReseau;
        this.masqueCidr = masqueCidr;
        this.description = description;
    }

    public void afficher() {
        System.out.println("Réseau : " + adresseReseau + "/" + masqueCidr);
        System.out.println("Description : " + description);
    }


}
