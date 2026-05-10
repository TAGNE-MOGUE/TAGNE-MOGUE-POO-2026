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

public class VLAN {
    private int id;
    private String nom;
    private ResultatVLSM reseauAssocie;
    private String description;
    
    public VLAN(int id, String nom, ResultatVLSM reseauAssocie, String description) {
        setId(id);
        setNom(nom);
        this.reseauAssocie = reseauAssocie;
        setDescription(description);
    }
    
    public int getId() { return id; }
    
    public void setId(int id) {
        this.id = (id < 1 || id > 4094) ? 1 : id;
    }
    
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = (nom == null || nom.isEmpty()) ? "VLAN_INCONNU" : nom; }
    
    public ResultatVLSM getReseauAssocie() { return reseauAssocie; }
    public void setReseauAssocie(ResultatVLSM reseauAssocie) { this.reseauAssocie = reseauAssocie; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = (description == null || description.isEmpty()) ? "Aucune description" : description; }
    
    public int getCapacite() { return (reseauAssocie != null) ? reseauAssocie.getCapacite() : 0; }
    
    public void afficher() {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║ VLAN ID : " + id + " - " + nom);
        System.out.println("║ Description : " + description);
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        if (reseauAssocie != null) {
            System.out.println("║ Reseau : " + reseauAssocie.getAdresseReseau() + "/" + reseauAssocie.getCidr());
            System.out.println("║ Capacite : " + reseauAssocie.getCapacite() + " hotes");
        }
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }
}
