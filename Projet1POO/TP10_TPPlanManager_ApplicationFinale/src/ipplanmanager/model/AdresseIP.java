/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipplanmanager.model;

public class AdresseIP {
    private String adresse;
    
    public AdresseIP(String adresse) {
        setAdresse(adresse);
    }
    
    public String getAdresse() {
        return adresse;
    }
    
    public void setAdresse(String adresse) {
        if (adresse == null || adresse.isEmpty() || !estAdresseValide(adresse)) {
            this.adresse = "0.0.0.0";
        } else {
            this.adresse = adresse;
        }
    }
    
    private boolean estAdresseValide(String ip) {
        String[] parties = ip.split("\\.");
        if (parties.length != 4) return false;
        for (String partie : parties) {
            try {
                int valeur = Integer.parseInt(partie);
                if (valeur < 0 || valeur > 255) return false;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }
    
    public void afficher() {
        System.out.println("Adresse IP : " + adresse);
    }
}
