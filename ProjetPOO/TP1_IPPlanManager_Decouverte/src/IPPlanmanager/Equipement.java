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
public class Equipement {
    String nom;
    String type;
    InterfaceReseau interfacePrincipale;

    public Equipement(String nom, String type,
            InterfaceReseau interfacePrincipale) {

        this.nom = nom;
        this.type = type;
        this.interfacePrincipale = interfacePrincipale;
    }

    public void afficher() {

        System.out.println("Nom de l'équipement : " + nom);
        System.out.println("Type d'équipement : " + type);

        if (interfacePrincipale != null) {
            interfacePrincipale.afficher();
        } else {
            System.out.println("Aucune interface réseau configurée.");
        }
    }

}
