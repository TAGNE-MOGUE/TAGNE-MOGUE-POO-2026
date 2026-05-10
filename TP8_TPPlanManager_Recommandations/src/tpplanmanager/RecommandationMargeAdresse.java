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

public class RecommandationMargeAdresse implements RegleRecommandation {
    
    @Override
    public Recommandation analyser(VLAN vlan) {
        if (vlan.getReseauAssocie() != null) {
            int besoin = vlan.getReseauAssocie().getBesoinInitial();
            int capacite = vlan.getCapacite();
            int marge = capacite - besoin;
            
            if (marge >= 0 && marge < 10) {
                return new Recommandation(
                    "Marge d'adresses insuffisante",
                    "MOYENNE",
                    "Le VLAN " + vlan.getNom() + " a une marge de seulement " + marge + " adresses (besoin: " + besoin + ", capacite: " + capacite + "). Prevoir une evolution."
                );
            }
        }
        return null;
    }
}
