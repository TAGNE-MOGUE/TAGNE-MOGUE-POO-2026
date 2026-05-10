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

public class GestionnaireVLAN {
    private ArrayList<VLAN> vlans;
    
    public GestionnaireVLAN() {
        vlans = new ArrayList<>();
    }
    
    public void ajouterVLAN(VLAN vlan) {
        vlans.add(vlan);
    }
    
    public void afficherTousLesVLANS() {
        if (vlans.isEmpty()) {
            System.out.println("Aucun VLAN configure.");
            return;
        }
        for (VLAN vlan : vlans) {
            vlan.afficher();
        }
    }
    
    public VLAN rechercherVLAN(int id) {
        for (VLAN vlan : vlans) {
            if (vlan.getId() == id) {
                return vlan;
            }
        }
        return null;
    }
    
    public int obtenirNombreVLANS() {
        return vlans.size();
    }
    
    public void afficherVLANsCritiques() {
        boolean trouve = false;
        System.out.println("\n=== VLANS CRITIQUES (>100 hotes) ===");
        for (VLAN vlan : vlans) {
            if (vlan.getCapacite() > 100) {
                System.out.println("⚠️ VLAN CRITIQUE : " + vlan.getNom() + " (ID:" + vlan.getId() + ") - " + vlan.getCapacite() + " hotes");
                trouve = true;
            }
        }
        if (!trouve) {
            System.out.println("Aucun VLAN critique.");
        }
    }
    
    public VLAN trouverVLANPlusGrandeCapacite() {
        if (vlans.isEmpty()) return null;
        VLAN maxVLAN = null;
      
        for (VLAN vlan : vlans) {
            if (vlan.getCapacite() > maxVLAN.getCapacite()) {
                maxVLAN = vlan;
            }
        }
        return maxVLAN;
    }
    
    public ArrayList<VLAN> trouverVLANsAvecCapaciteSuperieure(int seuil) {
        ArrayList<VLAN> resultats = new ArrayList<>();
        for (VLAN vlan : vlans) {
            if (vlan.getCapacite() > seuil) {
                resultats.add(vlan);
            }
        }
        return resultats;
    }
    
    public void afficherStatistiques() {
        System.out.println("\n=== STATISTIQUES DES VLANS ===");
        System.out.println("Nombre total de VLANs : " + vlans.size());
        if (!vlans.isEmpty()) {
            VLAN maxVLAN = trouverVLANPlusGrandeCapacite();
            System.out.println("VLAN avec plus grande capacite : " + maxVLAN.getNom() + " (ID:" + maxVLAN.getId() + ") - " + maxVLAN.getCapacite() + " hotes");
        }
    }
}  
