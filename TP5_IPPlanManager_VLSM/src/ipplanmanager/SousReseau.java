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
public class SousReseau {

    private String nom;
    private ReseauIP reseau;
    
    public SousReseau(String nom, ReseauIP reseau) {
        setNom(nom);
        this.reseau = reseau;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        if (nom == null || nom.isEmpty()) {
            this.nom = "Sous-reseau_inconnu";
        } else {
            this.nom = nom;
        }
    }
    
    public ReseauIP getReseau() {
        return reseau;
    }
    
    public void setReseau(ReseauIP reseau) {
        this.reseau = reseau;
    }
    
    public void afficher() {
        System.out.println("Sous-reseau : " + nom);
        if (reseau != null) {
            reseau.afficher();
        }
    }
}   
