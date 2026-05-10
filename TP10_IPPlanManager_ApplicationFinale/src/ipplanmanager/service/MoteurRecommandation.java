/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipplanmanager.service;

import ipplanmanager.model.Recommandation;
import ipplanmanager.model.VLAN;
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
                Recommandation rec = regle.analyser(vlan);
                if (rec != null) {
                    recommandations.add(rec);
                }
            }
        }
        return recommandations;
    }
    
    public void afficherRecommandations(ArrayList<Recommandation> recommandations) {
        System.out.println("\n--- RECOMMANDATIONS ---");
        if (recommandations.isEmpty()) {
            System.out.println("Aucune recommandation particuliere.");
        } else {
            for (Recommandation rec : recommandations) {
                rec.afficher();
            }
        }
    }
}
