/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipplanmanager.service;

import ipplanmanager.model.BesoinReseau;
import ipplanmanager.model.ResultatVLSM;
import java.util.ArrayList;

public class MoteurVLSM {
    
    // UNE SEULE méthode genererPlan
    public ArrayList<ResultatVLSM> genererPlan(String adresseDepart, ArrayList<BesoinReseau> besoins) {
        ArrayList<ResultatVLSM> resultats = new ArrayList<>();
        
        // Trier par ordre décroissant
        ArrayList<BesoinReseau> besoinsTries = new ArrayList<>(besoins);
        for (int i = 0; i < besoinsTries.size() - 1; i++) {
            for (int j = i + 1; j < besoinsTries.size(); j++) {
                if (besoinsTries.get(i).getNombreHotes() < besoinsTries.get(j).getNombreHotes()) {
                    BesoinReseau temp = besoinsTries.get(i);
                    besoinsTries.set(i, besoinsTries.get(j));
                    besoinsTries.set(j, temp);
                }
            }
        }
        
        int adresseCourante = CalculateurReseau.convertirIpEnEntier(adresseDepart);
        
        for (BesoinReseau besoin : besoinsTries) {
            int cidr = CalculateurReseau.calculerCidrPourHotes(besoin.getNombreHotes());
            int capacite = CalculateurReseau.calculerNombreHotes(cidr);
            String masque = CalculateurReseau.obtenirMasqueDecimal(cidr);
            int tailleBloc = CalculateurReseau.calculerTailleBloc(cidr);
            
            String adresseReseau = CalculateurReseau.convertirEntierEnIp(adresseCourante);
            
            resultats.add(new ResultatVLSM(besoin.getNom(), adresseReseau, cidr, masque, capacite, besoin.getNombreHotes()));
            adresseCourante += tailleBloc;
        }
        
        return resultats;
    }
}
