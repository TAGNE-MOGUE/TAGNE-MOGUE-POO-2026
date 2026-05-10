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
public class Equipement {
        private String nom;
    private String type;
    private InterfaceReseau interfacePrincipale;

    public Equipement(String nom, String type, InterfaceReseau interfacePrincipale) {
        setNom(nom);
        setType(type);
        this.interfacePrincipale = interfacePrincipale;
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
            this.type = "Type inconnu";
        } else {
            this.type = type;
        }
    }

    public InterfaceReseau getInterfacePrincipale() {
        return interfacePrincipale;
    }

    public void setInterfacePrincipale(InterfaceReseau interfacePrincipale) {
        this.interfacePrincipale = interfacePrincipale;
    }

    public void afficher() {
        System.out.println("Nom : " + nom);
        System.out.println("Type : " + type);

        if (interfacePrincipale != null) {
            interfacePrincipale.afficher();
            } else {
            System.out.println("Aucune interface configurée.");
        
        }
    }
}
