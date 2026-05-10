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
public class RecommandationGrandVLAN implements RegleRecommandation {
    
    @Override
    public Recommandation analyser(VLAN vlan) {
        if (vlan.getReseauAssocie() != null && vlan.getCapacite() > 200) {
            return new Recommandation(
                "VLAN de grande taille",
                "MOYENNE",
                "Le VLAN " + vlan.getNom() + " possede une grande capacite (" + vlan.getCapacite() + " hotes). Il faut surveiller les broadcasts."
            );
        }
        return null;
    }
}
