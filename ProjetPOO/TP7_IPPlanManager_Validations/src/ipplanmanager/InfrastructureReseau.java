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
import java.util.ArrayList;

public class InfrastructureReseau {
    private String nom;
    private ArrayList<Equipement> equipements;
    private ArrayList<SousReseau> sousReseaux;
    
    public InfrastructureReseau(String nom) {
        this.nom = nom;
        equipements = new ArrayList<>();
        sousReseaux = new ArrayList<>();
    }
    
    public void ajouterEquipement(Equipement equipement) { equipements.add(equipement); }
    public void ajouterSousReseau(SousReseau sousReseau) { sousReseaux.add(sousReseau); }
    
    public void afficherEquipements() {
        for (Equipement equipement : equipements) equipement.afficher();
    }
    
    public void afficherSousReseaux() {
        for (SousReseau sousReseau : sousReseaux) {
            sousReseau.afficher();
            System.out.println();
        }
    }
    
    public void rechercherEquipement(String nom) {
        for (Equipement equipement : equipements) {
            if (equipement.getNom().equalsIgnoreCase(nom)) {
                equipement.afficher();
                return;
            }
        }
        System.out.println("Equipement \"" + nom + "\" introuvable.");
    }
    
    public void afficher() {
        System.out.println("=========================================");
        System.out.println("Infrastructure : " + nom);
        System.out.println("=========================================");
        System.out.println("\n=== SOUS-RESEAUX ===");
        afficherSousReseaux();
        System.out.println("\n=== EQUIPEMENTS ===");
        afficherEquipements();
    }
}    
