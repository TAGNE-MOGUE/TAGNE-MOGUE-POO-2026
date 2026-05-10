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
    
    public void ajouterEquipement(Equipement equipement) {
        equipements.add(equipement);
    }
    
    public void ajouterSousReseau(SousReseau sousReseau) {
        sousReseaux.add(sousReseau);
    }
    
    public void afficherEquipements() {
        if (equipements.isEmpty()) {
            System.out.println("Aucun equipement dans l'infrastructure");
        } else {
            for (Equipement equipement : equipements) {
                equipement.afficher();
            }
        }
    }
    
    public void afficherSousReseaux() {
        if (sousReseaux.isEmpty()) {
            System.out.println("Aucun sous-reseau configure");
        } else {
            for (SousReseau sousReseau : sousReseaux) {
                sousReseau.afficher();
                System.out.println();
            }
        }
    }
    
    // Travail supplémentaire : méthode de recherche d'équipement
    public void rechercherEquipement(String nom) {
        boolean trouve = false;
        for (Equipement equipement : equipements) {
            if (equipement.getNom().equalsIgnoreCase(nom)) {
                System.out.println("=== Equipement trouve ===");
                equipement.afficher();
                trouve = true;
                break;
            }
        }
        if (!trouve) {
            System.out.println("Equipement \"" + nom + "\" introuvable dans l'infrastructure.");
        }
    }
    
    public void afficher() {
        System.out.println("=========================================");
        System.out.println("Infrastructure : " + nom);
        System.out.println("=========================================");
        
        System.out.println("\n=== SOUS-RESEAUX ===");
        afficherSousReseaux();
        
        System.out.println("\n=== EQUIPEMENTS ===");
        afficherEquipements();
        
        System.out.println("=========================================");
    }
}

