/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipplanmanager.service;

import ipplanmanager.exception.ConflitVLANException;
import ipplanmanager.model.VLAN;
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
                throw new ConflitVLANException("Conflit VLAN : l'identifiant " + vlan.getId() + " est deja utilise");
            }
        }
        vlans.add(vlan);
    }
    
    public void afficherTousLesVLANS() {
        System.out.println("\n--- LISTE DES VLANS ---");
        for (VLAN vlan : vlans) {
            vlan.afficher();
        }
    }
    
    public ArrayList<VLAN> getVlans() {
        return vlans;
    }
}
