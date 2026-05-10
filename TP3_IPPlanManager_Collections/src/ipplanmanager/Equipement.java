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

public class Equipement {
    private String nom;
    private String type;
    private ArrayList<InterfaceReseau> interfaces;
    
    public Equipement(String nom, String type) {
        setNom(nom);
        setType(type);
        interfaces = new ArrayList<>();
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        if (nom == null || nom.isEmpty()) {
            this.nom = "equipement_inconnu";
        } else {
            this.nom = nom;
        }
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        if (type == null || type.isEmpty()) {
            this.type = "type_inconnu";
        } else {
            this.type = type;
        }
    }
    
    public void ajouterInterface(InterfaceReseau interfaceReseau) {
        interfaces.add(interfaceReseau);
    }
    
    public void afficherInterfaces() {
        if (interfaces.isEmpty()) {
            System.out.println("Aucune interface configuree");
        } else {
            for (InterfaceReseau interfaceReseau : interfaces) {
                interfaceReseau.afficher();
            }
        }
    }
    
    public void afficher() {
        System.out.println("Equipement : " + nom);
        System.out.println("Type : " + type);
        System.out.println("Nombre d'interfaces : " + interfaces.size());
        System.out.println("--- Interfaces ---");
        afficherInterfaces();
        System.out.println();
    }
}
