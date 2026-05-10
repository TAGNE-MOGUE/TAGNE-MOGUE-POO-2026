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
    
    public void ajouterVLAN(VLAN vlan) throws ConflitVLANException {
        if (vlan == null) return;
        
        for (VLAN v : vlans) {
            if (v.getId() == vlan.getId()) {
                throw new ConflitVLANException("Conflit VLAN : l'identifiant " + vlan.getId() + " est déjà utilisé par le VLAN " + v.getNom());
            }
        }
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
            if (vlan.getId() == id) return vlan;
        }
        return null;
    }
    
    public int obtenirNombreVLANS() {
        return vlans.size();
    }
}