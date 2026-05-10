/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipplanmanager.model;

/**
 *
 * @author GENERAL STORES
 */

public class BesoinReseau {
    private String nom;
    private int nombreHotes;
    
    public BesoinReseau(String nom, int nombreHotes) {
        setNom(nom);
        setNombreHotes(nombreHotes);
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = (nom == null || nom.isEmpty()) ? "Besoin_inconnu" : nom;
    }
    
    public int getNombreHotes() {
        return nombreHotes;
    }
    
    public void setNombreHotes(int nombreHotes) {
        this.nombreHotes = (nombreHotes <= 0) ? 1 : nombreHotes;
    }
    
    public void afficher() {
        System.out.println("Besoin : " + nom + " | Hotes : " + nombreHotes);
    }
}
