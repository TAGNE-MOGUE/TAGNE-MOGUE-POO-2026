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

import java.util.ArrayList;

public class MoteurRecommandation {
    private ArrayList<RegleRecommandation> regles;
    
    public MoteurRecommandation() {
        regles = new ArrayList<>();
    }
    
    public void ajouterRegle(RegleRecommandation regle) {
        regles.add(regle);
    }
    
    public ArrayList<Recommandation> analyserVLANs(ArrayList<VLAN> vlans) {
        ArrayList<Recommandation> recommandations = new ArrayList<>();
        
        for (VLAN vlan : vlans) {
            for (RegleRecommandation regle : regles) {
                Recommandation recommandation = regle.analyser(vlan);
                if (recommandation != null) {
                    recommandations.add(recommandation);
                }
            }
        }
        
        return recommandations;
    }
    
    public void afficherRecommandations(ArrayList<Recommandation> recommandations) {
        if (recommandations.isEmpty()) {
            System.out.println("Aucune recommandation particuliere.");
            return;
        }
        
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                    RECOMMANDATIONS PROPOSEES                    ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        
        for (Recommandation recommandation : recommandations) {
            recommandation.afficher();
        }
    }
}
