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

public class Main {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║         TP8 - MOTEUR DE RECOMMANDATIONS INTELLIGENTES         ║");
        System.out.println("║              IPPlan-Manager - Assistant reseau                ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        
        // ===== SCENARIO 1 : EXEMPLE DU COURS =====
        System.out.println("\n================================================================");
        System.out.println("1. SCENARIO UNIVERSITE (Exemple du cours)");
        System.out.println("================================================================");
        
        ArrayList<BesoinReseau> besoins1 = new ArrayList<>();
        besoins1.add(new BesoinReseau("ETUDIANTS", 500));
        besoins1.add(new BesoinReseau("WIFI_INVITES", 200));
        besoins1.add(new BesoinReseau("ENSEIGNANTS", 120));
        besoins1.add(new BesoinReseau("LABORATOIRES", 60));
        besoins1.add(new BesoinReseau("SERVEURS", 30));
        
        MoteurVLSM moteurVLSM = new MoteurVLSM();
        ArrayList<ResultatVLSM> resultats1 = moteurVLSM.genererPlan("10.10.0.0", besoins1);
        
        GestionnaireVLAN gestionnaire1 = new GestionnaireVLAN();
        int numeroVLAN = 10;
        
        try {
            for (ResultatVLSM resultat : resultats1) {
                VLAN vlan = new VLAN(numeroVLAN, resultat.getNomBesoin(), resultat, "VLAN " + resultat.getNomBesoin());
                gestionnaire1.ajouterVLAN(vlan);
                numeroVLAN += 10;
            }
        } catch (ConflitVLANException e) {
            System.out.println("Erreur VLAN : " + e.getMessage());
        }
        
        System.out.println("\n📡 PLAN VLAN GENERE :");
        System.out.println("----------------------------------------");
        gestionnaire1.afficherTousLesVLANS();
        
        // Création du moteur de recommandations
        MoteurRecommandation moteurRecommandation = new MoteurRecommandation();
        moteurRecommandation.ajouterRegle(new RecommandationWiFiInvite());
        moteurRecommandation.ajouterRegle(new RecommandationServeurs());
        moteurRecommandation.ajouterRegle(new RecommandationGrandVLAN());
        moteurRecommandation.ajouterRegle(new RecommandationAdministration());
        moteurRecommandation.ajouterRegle(new RecommandationMargeAdresse());
        
        ArrayList<Recommandation> recommandations1 = moteurRecommandation.analyserVLANs(gestionnaire1.getVlans());
        moteurRecommandation.afficherRecommandations(recommandations1);
        
        // ===== SCENARIO 2 : TRAVAIL DEMANDE =====
        System.out.println("\n================================================================");
        System.out.println("2. SCENARIO ENTREPRISE (Travail demande)");
        System.out.println("================================================================");
        
        ArrayList<BesoinReseau> besoins2 = new ArrayList<>();
        besoins2.add(new BesoinReseau("ADMINISTRATION", 50));
        besoins2.add(new BesoinReseau("WIFI_INVITES", 120));
        besoins2.add(new BesoinReseau("SERVEURS", 20));
        besoins2.add(new BesoinReseau("CAMERAS", 80));
        besoins2.add(new BesoinReseau("VOIP", 60));
        
        ArrayList<ResultatVLSM> resultats2 = moteurVLSM.genererPlan("192.168.1.0", besoins2);
        
        GestionnaireVLAN gestionnaire2 = new GestionnaireVLAN();
        numeroVLAN = 100;
        
        try {
            for (ResultatVLSM resultat : resultats2) {
                VLAN vlan = new VLAN(numeroVLAN, resultat.getNomBesoin(), resultat, "VLAN " + resultat.getNomBesoin());
                gestionnaire2.ajouterVLAN(vlan);
                numeroVLAN += 10;
            }
        } catch (ConflitVLANException e) {
            System.out.println("Erreur VLAN : " + e.getMessage());
        }
        
        System.out.println("\n📡 PLAN VLAN GENERE :");
        System.out.println("----------------------------------------");
        gestionnaire2.afficherTousLesVLANS();
        
        ArrayList<Recommandation> recommandations2 = moteurRecommandation.analyserVLANs(gestionnaire2.getVlans());
        moteurRecommandation.afficherRecommandations(recommandations2);
        
        // ===== SCENARIO 3 : PETITE ENTREPRISE =====
        System.out.println("\n================================================================");
        System.out.println("3. SCENARIO PETITE ENTREPRISE");
        System.out.println("================================================================");
        
        ArrayList<BesoinReseau> besoins3 = new ArrayList<>();
        besoins3.add(new BesoinReseau("COMMERCIAL", 30));
        besoins3.add(new BesoinReseau("COMPTABILITE", 15));
        besoins3.add(new BesoinReseau("RH", 10));
        besoins3.add(new BesoinReseau("DIRECTION", 8));
        
        ArrayList<ResultatVLSM> resultats3 = moteurVLSM.genererPlan("10.0.0.0", besoins3);
        
        GestionnaireVLAN gestionnaire3 = new GestionnaireVLAN();
        numeroVLAN = 200;
        
        try {
            for (ResultatVLSM resultat : resultats3) {
                VLAN vlan = new VLAN(numeroVLAN, resultat.getNomBesoin(), resultat, "VLAN " + resultat.getNomBesoin());
                gestionnaire3.ajouterVLAN(vlan);
                numeroVLAN += 10;
            }
        } catch (ConflitVLANException e) {
            System.out.println("Erreur VLAN : " + e.getMessage());
        }
        
        System.out.println("\n📡 PLAN VLAN GENERE :");
        System.out.println("----------------------------------------");
        gestionnaire3.afficherTousLesVLANS();
        
        ArrayList<Recommandation> recommandations3 = moteurRecommandation.analyserVLANs(gestionnaire3.getVlans());
        moteurRecommandation.afficherRecommandations(recommandations3);
        
        // ===== RESUME =====
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                    TP8 TERMINE AVEC SUCCES !                   ║");
        System.out.println("║                                                               ║");
        System.out.println("║  Regles de recommandation implementees :                      ║");
        System.out.println("║  - RecommendationWiFiInvite (WIFI)                            ║");
        System.out.println("║  - RecommendationServeurs (SERVEURS)                          ║");
        System.out.println("║  - RecommendationGrandVLAN (>200 hotes)                       ║");
        System.out.println("║  - RecommendationAdministration (ADMIN)                       ║");
        System.out.println("║  - RecommendationMargeAdresse (marge < 10)                    ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
    }
}
