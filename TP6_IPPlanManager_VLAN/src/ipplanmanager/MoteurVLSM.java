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
import java.util.Collections;

public class MoteurVLSM {
    
    public ArrayList<ResultatVLSM> genererPlan(String adresseDepart, ArrayList<BesoinReseau> besoins) {
        ArrayList<ResultatVLSM> resultats = new ArrayList<>();
        
        // Copier la liste pour ne pas modifier l'originale
        ArrayList<BesoinReseau> besoinsTries = new ArrayList<>(besoins);
        
        // Trier manuellement par ordre décroissant (sans Comparator)
        for (int i = 0; i < besoinsTries.size() - 1; i++) {
            for (int j = i + 1; j < besoinsTries.size(); j++) {
                if (besoinsTries.get(i).getNombreHotes() < besoinsTries.get(j).getNombreHotes()) {
                    // Échanger les éléments
                    BesoinReseau temp = besoinsTries.get(i);
                    besoinsTries.set(i, besoinsTries.get(j));
                    besoinsTries.set(j, temp);
                }
            }
        }
        
        // Convertir l'adresse de départ en entier
        int adresseCourante = CalculateurReseau.convertirIpEnEntier(adresseDepart);
        
        for (BesoinReseau besoin : besoinsTries) {
            // Calculer le CIDR adapté au besoin
            int cidr = CalculateurReseau.calculerCidrPourHotes(besoin.getNombreHotes());
            int capacite = CalculateurReseau.calculerNombreHotes(cidr);
            String masque = CalculateurReseau.obtenirMasqueDecimal(cidr);
            int tailleBloc = CalculateurReseau.calculerTailleBloc(cidr);
            
            // Convertir l'adresse courante en IP
            String adresseReseau = CalculateurReseau.convertirEntierEnIp(adresseCourante);
            
            // Créer le résultat
            ResultatVLSM resultat = new ResultatVLSM(
                besoin.getNom(), 
                adresseReseau, 
                cidr, 
                masque, 
                capacite
            );
            resultats.add(resultat);
            
            // Avancer l'adresse courante
            adresseCourante = adresseCourante + tailleBloc;
        }
        
        return resultats;
    }
}