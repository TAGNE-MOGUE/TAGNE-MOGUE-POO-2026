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
import java.util.Comparator;

public class MoteurVLSM {
    
    public ArrayList<ResultatVLSM> genererPlan(String adresseDepart, ArrayList<BesoinReseau> besoins) {
        ArrayList<ResultatVLSM> resultats = new ArrayList<>();
        
        // Trier les besoins par ordre décroissant (du plus grand au plus petit)
        System.out.println("\n--- Tri des besoins (ordre décroissant) ---");
        Collections.sort(besoins, new Comparator<BesoinReseau>() {
            @Override
            public int compare(BesoinReseau b1, BesoinReseau b2) {
                return b2.getNombreHotes() - b1.getNombreHotes();
            }
        });
        
        // Afficher l'ordre trié
        for (BesoinReseau besoin : besoins) {
            System.out.println(" - " + besoin.getNom() + " : " + besoin.getNombreHotes() + " hotes");
        }
        
        // Convertir l'adresse de départ en entier
        int adresseCourante = CalculateurReseau.convertirIpEnEntier(adresseDepart);
        
        System.out.println("\n--- Generation du plan VLSM ---");
        
        for (BesoinReseau besoin : besoins) {
            // Calculer le CIDR adapté au besoin
            int cidr = CalculateurReseau.calculerCidrPourHotes(besoin.getNombreHotes());
            int capacite = CalculateurReseau.calculerNombreHotes(cidr);
            String masque = CalculateurReseau.obtenirMasqueDecimal(cidr);
            int tailleBloc = CalculateurReseau.calculerTailleBloc(cidr);
            
            // Convertir l'adresse courante en IP
            String adresseReseau = CalculateurReseau.convertirEntierEnIp(adresseCourante);
            
            // Vérifier que l'adresse réseau est valide (dans la plage)
            String broadcast = CalculateurReseau.calculerBroadcast(adresseReseau, cidr);
            
            // Créer le résultat
            ResultatVLSM resultat = new ResultatVLSM(besoin.getNom(), adresseReseau, cidr, masque, capacite);
            resultats.add(resultat);
            
            System.out.println("  " + besoin.getNom() + " (" + besoin.getNombreHotes() + " hotes) -> /" + cidr + " [Bloc: " + tailleBloc + " adresses]");
            
            // Avancer l'adresse courante
            adresseCourante = adresseCourante + tailleBloc;
        }
        
        return resultats;
    }
    
    // Méthode pour générer un plan en affichant les détails
    public void genererEtAfficherPlan(String nomScenario, String adresseDepart, ArrayList<BesoinReseau> besoins) {
        System.out.println("\n");
        System.out.println("########################################");
        System.out.println("# Scenario: " + nomScenario);
        System.out.println("########################################");
        System.out.println("Adresse de depart: " + adresseDepart);
        
        ArrayList<ResultatVLSM> resultats = genererPlan(adresseDepart, besoins);
        
        System.out.println("\n--- RESULTAT FINAL ---");
        for (ResultatVLSM resultat : resultats) {
            resultat.afficher();
        }
        
        // Afficher un résumé
        System.out.println("\n--- RESUME DES PLAGES ALLOUÉES ---");
        for (ResultatVLSM resultat : resultats) {
            System.out.println(resultat.getNomBesoin() + ": " + resultat.getAdresseReseau() + "/" + resultat.getCidr() + 
                             " [" + resultat.getPremiereAdresseUtilisable() + " -> " + resultat.getDerniereAdresseUtilisable() + "]");
        }
    }
}