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

public class Main {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║              TP6 - GESTION DES VLANS                           ║");
        System.out.println("║              IPPlan-Manager - Segmentation logique            ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        
        MoteurVLSM moteur = new MoteurVLSM();
        
        // ===== SCÉNARIO 1 : EXEMPLE DU COURS =====
        System.out.println("\n");
        System.out.println("############################################################");
        System.out.println("# SCENARIO 1 : ENTREPRISE (Exemple du cours)");
        System.out.println("############################################################");
        
        ArrayList<BesoinReseau> besoins1 = new ArrayList<>();
        besoins1.add(new BesoinReseau("TECHNIQUE", 120));
        besoins1.add(new BesoinReseau("WIFI", 80));
        besoins1.add(new BesoinReseau("ADMINISTRATION", 50));
        besoins1.add(new BesoinReseau("SERVEURS", 20));
        
        ArrayList<ResultatVLSM> resultats1 = moteur.genererPlan("192.168.1.0", besoins1);
        
        GestionnaireVLAN gestionnaire1 = new GestionnaireVLAN();
        int numeroVLAN = 10;
        for (ResultatVLSM resultat : resultats1) {
            VLAN vlan = new VLAN(
                numeroVLAN,
                resultat.getNomBesoin(),
                resultat,
                "VLAN du service " + resultat.getNomBesoin()
            );
            gestionnaire1.ajouterVLAN(vlan);
            numeroVLAN += 10;
        }
        
        System.out.println("\n----- VLANS GENERES -----");
        gestionnaire1.afficherTousLesVLANS();
        gestionnaire1.afficherStatistiques();
        
        // Test recherche VLAN
        System.out.println("----- TEST RECHERCHE VLAN ID 20 -----");
        VLAN vlanTrouve = gestionnaire1.rechercherVLAN(20);
        if (vlanTrouve != null) {
            System.out.println("VLAN trouve : " + vlanTrouve.getNom() + " (ID:" + vlanTrouve.getId() + ")");
        } else {
            System.out.println("VLAN introuvable.");
        }
        
        // Test VLANs critiques
        gestionnaire1.afficherVLANsCritiques();
        
        // ===== SCÉNARIO 2 : UNIVERSITÉ (TRAVAIL DEMANDÉ) =====
        System.out.println("\n");
        System.out.println("############################################################");
        System.out.println("# SCENARIO 2 : UNIVERSITE");
        System.out.println("############################################################");
        
        ArrayList<BesoinReseau> besoins2 = new ArrayList<>();
        besoins2.add(new BesoinReseau("ETUDIANTS", 500));
        besoins2.add(new BesoinReseau("ENSEIGNANTS", 120));
        besoins2.add(new BesoinReseau("LABORATOIRES", 60));
        besoins2.add(new BesoinReseau("WIFI_PUBLIC", 200));
        besoins2.add(new BesoinReseau("SERVEURS", 30));
        
        ArrayList<ResultatVLSM> resultats2 = moteur.genererPlan("172.16.0.0", besoins2);
        
        GestionnaireVLAN gestionnaire2 = new GestionnaireVLAN();
        numeroVLAN = 100;
        for (ResultatVLSM resultat : resultats2) {
            VLAN vlan = new VLAN(
                numeroVLAN,
                resultat.getNomBesoin(),
                resultat,
                "VLAN Universitaire - " + resultat.getNomBesoin()
            );
            gestionnaire2.ajouterVLAN(vlan);
            numeroVLAN += 10;
        }
        
        System.out.println("\n----- VLANS GENERES (UNIVERSITE) -----");
        gestionnaire2.afficherTousLesVLANS();
        gestionnaire2.afficherStatistiques();
        
        // Test VLANs critiques pour l'université
        gestionnaire2.afficherVLANsCritiques();
        
        // ===== SCÉNARIO 3 : PETITE ENTREPRISE SUPPLÉMENTAIRE =====
        System.out.println("\n");
        System.out.println("############################################################");
        System.out.println("# SCENARIO 3 : PETITE ENTREPRISE");
        System.out.println("############################################################");
        
        ArrayList<BesoinReseau> besoins3 = new ArrayList<>();
        besoins3.add(new BesoinReseau("COMMERCIAL", 30));
        besoins3.add(new BesoinReseau("COMPTABILITE", 15));
        besoins3.add(new BesoinReseau("RH", 10));
        besoins3.add(new BesoinReseau("DIRECTION", 8));
        besoins3.add(new BesoinReseau("ACCUEIL", 5));
        
        ArrayList<ResultatVLSM> resultats3 = moteur.genererPlan("10.0.0.0", besoins3);
        
        GestionnaireVLAN gestionnaire3 = new GestionnaireVLAN();
        numeroVLAN = 200;
        for (ResultatVLSM resultat : resultats3) {
            VLAN vlan = new VLAN(
                numeroVLAN,
                resultat.getNomBesoin(),
                resultat,
                "VLAN Petite Entreprise - " + resultat.getNomBesoin()
            );
            gestionnaire3.ajouterVLAN(vlan);
            numeroVLAN += 10;
        }
        
        System.out.println("\n----- VLANS GENERES (PETITE ENTREPRISE) -----");
        gestionnaire3.afficherTousLesVLANS();
        gestionnaire3.afficherStatistiques();
        
        // ===== RÉSUMÉ FINAL =====
        System.out.println("\n");
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                       RÉSUMÉ DES TESTS                         ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println("1. Scenario 1 (Entreprise) : " + gestionnaire1.obtenirNombreVLANS() + " VLANs crees");
        System.out.println("2. Scenario 2 (Universite) : " + gestionnaire2.obtenirNombreVLANS() + " VLANs crees");
        System.out.println("3. Scenario 3 (Petite Entreprise) : " + gestionnaire3.obtenirNombreVLANS() + " VLANs crees");
        
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                    TP6 TERMINE AVEC SUCCES !                   ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
    }
}