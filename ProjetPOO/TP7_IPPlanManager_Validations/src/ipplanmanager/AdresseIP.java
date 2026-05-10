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

public class AdresseIP {
    private String adresse;
    
    public AdresseIP(String adresse) {
        setAdresse(adresse);
    }
    
    public String getAdresse() {
        return adresse;
    }
    
    public void setAdresse(String adresse) {
        if (adresse == null || adresse.isEmpty() || !CalculateurReseau.estAdresseIPValide(adresse)) {
            this.adresse = "0.0.0.0";
        } else {
            this.adresse = adresse;
        }
    }
    
    public void afficher() {
        System.out.println("Adresse IP : " + adresse);
    }
}
