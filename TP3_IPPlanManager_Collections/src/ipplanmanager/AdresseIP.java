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
    private String valeur;

    public AdresseIP(String valeur) {
        setValeur(valeur);
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        if (valeur == null || valeur.isEmpty()) {
            System.out.println("Erreur : adresse IP invalide.");
            this.valeur = "0.0.0.0";
        } else {
            this.valeur = valeur;
        }
    }

    public void afficher() {
        System.out.println("Adresse IP : " + valeur);
    }

    //  Travail supplémentaire du TP
    public boolean estAdresseLocale() {
        return valeur != null && valeur.startsWith("192.");
    }
}
