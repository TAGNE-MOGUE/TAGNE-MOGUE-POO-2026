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
public class RecommandationAdministration implements RegleRecommandation {
    
    @Override
    public Recommandation analyser(VLAN vlan) {
        String nom = vlan.getNom().toUpperCase();
        if (nom.contains("ADMIN") || nom.contains("ADMINISTRATION")) {
            return new Recommandation(
                "Restriction acces Administration",
                "HAUTE",
                "Le VLAN " + vlan.getNom() + " (Administration) doit etre accessible uniquement aux administrateurs reseau."
            );
        }
        return null;
    }
}
