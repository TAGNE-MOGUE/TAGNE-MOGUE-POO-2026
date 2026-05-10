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

public class VLAN {
    private int id;
    private String nom;
    private ResultatVLSM reseauAssocie;
    private String description;
    
    // Constructeur
    public VLAN(int id, String nom, ResultatVLSM reseauAssocie, String description) {
        setId(id);
        setNom(nom);
        this.reseauAssocie = reseauAssocie;
        setDescription(description);
    }
    
    // Getter pour l'ID
    public int getId() {
        return id;
    }
    
    // Setter pour l'ID avec validation (1-4094, norme IEEE 802.1Q)
    public void setId(int id) {
        if (id < 1 || id > 4094) {
            this.id = 1;
        } else {
            this.id = id;
        }
    }
    
    // Getter pour le nom
    public String getNom() {
        return nom;
    }
    
    // Setter pour le nom
    public void setNom(String nom) {
        if (nom == null || nom.isEmpty()) {
            this.nom = "VLAN_INCONNU";
        } else {
            this.nom = nom;
        }
    }
    
    // Getter pour le réseau associé
    public ResultatVLSM getReseauAssocie() {
        return reseauAssocie;
    }
    
    // Setter pour le réseau associé
    public void setReseauAssocie(ResultatVLSM reseauAssocie) {
        this.reseauAssocie = reseauAssocie;
    }
    
    // Getter pour la description
    public String getDescription() {
        return description;
    }
    
    // Setter pour la description
    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            this.description = "Aucune description";
        } else {
            this.description = description;
        }
    }
    
    // Méthode pour obtenir la capacité du VLAN (nombre d'hôtes)
    public int getCapacite() {
        if (reseauAssocie != null) {
            return reseauAssocie.getCapacite();
        }
        return 0;
    }
    
    // Méthode pour obtenir l'adresse réseau
    public String getAdresseReseau() {
        if (reseauAssocie != null) {
            return reseauAssocie.getAdresseReseau();
        }
        return "0.0.0.0";
    }
    
    // Méthode pour obtenir le CIDR
    public int getCidr() {
        if (reseauAssocie != null) {
            return reseauAssocie.getCidr();
        }
        return 0;
    }
    
    // Méthode pour obtenir la première adresse utilisable
    public String getPremiereAdresseUtilisable() {
        if (reseauAssocie != null) {
            return reseauAssocie.getPremiereAdresseUtilisable();
        }
        return "0.0.0.0";
    }
    
    // Méthode pour obtenir la dernière adresse utilisable
    public String getDerniereAdresseUtilisable() {
        if (reseauAssocie != null) {
            return reseauAssocie.getDerniereAdresseUtilisable();
        }
        return "0.0.0.0";
    }
    
    // Méthode pour afficher toutes les informations du VLAN
    public void afficher() {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                         VLAN                                 ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║ ID          : " + id);
        System.out.println("║ Nom         : " + nom);
        System.out.println("║ Description : " + description);
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║                    INFORMATIONS RESEAU                       ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        
        if (reseauAssocie != null) {
            System.out.println("║ Service     : " + reseauAssocie.getNomBesoin());
            System.out.println("║ Adresse reseau : " + reseauAssocie.getAdresseReseau() + "/" + reseauAssocie.getCidr());
            System.out.println("║ Masque      : " + reseauAssocie.getMasqueDecimal());
            System.out.println("║ Plage       : " + reseauAssocie.getPremiereAdresseUtilisable() + " -> " + reseauAssocie.getDerniereAdresseUtilisable());
            System.out.println("║ Capacite    : " + reseauAssocie.getCapacite() + " hotes utilisables");
        } else {
            System.out.println("║ Aucun reseau associe                                         ║");
        }
        
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println();
    }
    
    // Méthode pour afficher un résumé (version courte)
    public void afficherResume() {
        System.out.println("VLAN " + id + " : " + nom + " -> " + getAdresseReseau() + "/" + getCidr() + " (" + getCapacite() + " hotes)");
    }
    
    // Méthode pour vérifier si le VLAN est critique (capacité > 100)
    public boolean estCritique() {
        return getCapacite() > 100;
    }
    
    // Méthode toString
    @Override
    public String toString() {
        return "VLAN{" +
               "id=" + id +
               ", nom='" + nom + '\'' +
               ", reseau=" + (reseauAssocie != null ? reseauAssocie.getAdresseReseau() + "/" + reseauAssocie.getCidr() : "null") +
               ", capacite=" + getCapacite() +
               '}';
    }
}
