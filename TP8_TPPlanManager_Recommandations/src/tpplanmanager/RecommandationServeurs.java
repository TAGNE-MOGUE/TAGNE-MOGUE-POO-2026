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

public class RecommandationServeurs implements RegleRecommandation {
    
    @Override
    public Recommandation analyser(VLAN vlan) {
        if (vlan.getNom().toUpperCase().contains("SERVEUR")) {
            return new Recommandation(
                "Protection du VLAN Serveurs",
                "ELEVEE",
                "Le VLAN " + vlan.getNom() + " (Serveurs) doit etre protege par des ACL et surveille en priorite."
            );
        }
        return null;
    }
}
