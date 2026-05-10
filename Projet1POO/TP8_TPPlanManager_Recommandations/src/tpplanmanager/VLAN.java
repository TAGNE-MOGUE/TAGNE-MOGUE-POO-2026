/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpplanmanager;

/**
 *
 * @author GENERAL STORES
 */
public class VLAN {
    private int id;
    private String nom;
    private ResultatVLSM reseauAssocie;
    private String description;
    
    // UN SEUL CONSTRUCTEUR
    public VLAN(int id, String nom, ResultatVLSM reseauAssocie, String description) {
        setId(id);
        setNom(nom);
        this.reseauAssocie = reseauAssocie;
        setDescription(description);
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        if (id < 1 || id > 4094) {
            this.id = 1;
        } else {
            this.id = id;
        }
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        if (nom == null || nom.isEmpty()) {
            this.nom = "VLAN_INCONNU";
        } else {
            this.nom = nom;
        }
    }
    
    public ResultatVLSM getReseauAssocie() {
        return reseauAssocie;
    }
    
    public void setReseauAssocie(ResultatVLSM reseauAssocie) {
        this.reseauAssocie = reseauAssocie;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            this.description = "Aucune description";
        } else {
            this.description = description;
        }
    }
    
    public int getCapacite() {
        if (reseauAssocie != null) {
            return reseauAssocie.getCapacite();
        }
        return 0;
    }
    
    public void afficher() {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║ VLAN ID : " + id);
        System.out.println("║ Nom : " + nom);
        System.out.println("║ Description : " + description);
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        if (reseauAssocie != null) {
            System.out.println("║ Reseau : " + reseauAssocie.getAdresseReseau() + "/" + reseauAssocie.getCidr());
            System.out.println("║ Plage : " + reseauAssocie.getPremiereAdresseUtilisable() + " - " + reseauAssocie.getDerniereAdresseUtilisable());
            System.out.println("║ Capacite : " + reseauAssocie.getCapacite() + " hotes");
        } else {
            System.out.println("║ Aucun reseau associe");
        }
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println();
    }
    
    public void afficherResume() {
        System.out.println("VLAN " + id + " : " + nom + " -> " + getAdresseReseau() + "/" + getCidr() + " (" + getCapacite() + " hotes)");
    }
    
    public String getAdresseReseau() {
        return (reseauAssocie != null) ? reseauAssocie.getAdresseReseau() : "0.0.0.0";
    }
    
    public int getCidr() {
        return (reseauAssocie != null) ? reseauAssocie.getCidr() : 0;
    }
    
    public boolean estCritique() {
        return getCapacite() > 100;
    }
}