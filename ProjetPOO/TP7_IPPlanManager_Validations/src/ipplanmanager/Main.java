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
        System.out.println("║     TP7 - VALIDATIONS AVANCEES ET DETECTION DES CONFLITS      ║");
        System.out.println("║              IPPlan-Manager - Validation reseau               ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        
        System.out.println("\n================================================================");
        System.out.println("1. TEST AVEC PLAN VALIDE");
        System.out.println("================================================================");
        
        ArrayList<BesoinReseau> besoins = new ArrayList<>();
        besoins.add(new BesoinReseau("ADMINISTRATION", 50));
        besoins.add(new BesoinReseau("TECHNIQUE", 120));
        besoins.add(new BesoinReseau("WIFI", 80));
        besoins.add(new BesoinReseau("SERVEURS", 20));
        
        MoteurVLSM moteur = new MoteurVLSM();
        
        try {
            ArrayList<ResultatVLSM> resultats = moteur.genererPlan("192.168.1.0", besoins);
            
            if (resultats.isEmpty()) {
                System.out.println("⚠️ Aucun resultat genere (adresse invalide)");
            } else {
                System.out.println("\n📡 PLAN GENERE :");
                System.out.println("----------------------------------------");
                for (ResultatVLSM resultat : resultats) {
                    resultat.afficher();
                    System.out.println();
                }
                
                ValidateurPlanAdressage validateur = new ValidateurPlanAdressage();
                validateur.verifierAdresses(resultats);
                validateur.verifierChevauchements(resultats);
                validateur.afficherValidationReussie();
                
                // Test Gestionnaire VLAN
                System.out.println("\n================================================================");
                System.out.println("2. TEST DE CONFLIT VLAN");
                System.out.println("================================================================");
                
                GestionnaireVLAN gestionnaire = new GestionnaireVLAN();
                
                VLAN vlan10 = new VLAN(10, "ADMINISTRATION", resultats.get(0), "VLAN Administration");
                VLAN vlan20 = new VLAN(20, "TECHNIQUE", resultats.get(1), "VLAN Technique");
                VLAN vlan10Error = new VLAN(10, "WIFI", resultats.get(2), "VLAN WiFi (conflit volontaire)");
                
                gestionnaire.ajouterVLAN(vlan10);
                System.out.println("✅ VLAN 10 ajoute avec succes");
                
                gestionnaire.ajouterVLAN(vlan20);
                System.out.println("✅ VLAN 20 ajoute avec succes");
                
                gestionnaire.ajouterVLAN(vlan10Error);
                System.out.println("❌ Cette ligne ne devrait pas s'afficher (conflit detecte)");
            }
            
        } catch (AdresseIPInvalideException e) {
            System.out.println("❌ Erreur adresse IP : " + e.getMessage());
        } catch (ChevauchementReseauException e) {
            System.out.println("❌ Erreur chevauchement : " + e.getMessage());
        } catch (ConflitVLANException e) {
            System.out.println("❌ " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Erreur : " + e.getMessage());
        }
        
        // ===== SCENARIO 2 : ADRESSE DE DEPART INVALIDE =====
        System.out.println("\n================================================================");
        System.out.println("3. TEST AVEC ADRESSE DE DEPART INVALIDE (Travail demande)");
        System.out.println("================================================================");
        
        ArrayList<BesoinReseau> besoins2 = new ArrayList<>();
        besoins2.add(new BesoinReseau("TEST", 10));
        
        MoteurVLSM moteur2 = new MoteurVLSM();
        ArrayList<ResultatVLSM> resultats2 = moteur2.genererPlan("192.168.300.0", besoins2);
        
        if (resultats2.isEmpty()) {
            System.out.println("✅ Erreur detectee : adresse de depart invalide (192.168.300.0)");
        } else {
            System.out.println("⚠️ Aucune erreur detectee (anormal)");
        }
        
        // ===== SCENARIO 3 : CHEVAUCHEMENT ARTIFICIEL =====
        System.out.println("\n================================================================");
        System.out.println("4. TEST DE CHEVAUCHEMENT RESEAU (Travail demande)");
        System.out.println("================================================================");
        
        try {
            ResultatVLSM r1 = new ResultatVLSM("Reseau1", "192.168.1.0", 25, "255.255.255.128", 126);
            ResultatVLSM r2 = new ResultatVLSM("Reseau2", "192.168.1.64", 26, "255.255.255.192", 62);
            
            ArrayList<ResultatVLSM> resultatsChevauchement = new ArrayList<>();
            resultatsChevauchement.add(r1);
            resultatsChevauchement.add(r2);
            
            System.out.println("Reseau1: 192.168.1.0/25");
            System.out.println("Reseau2: 192.168.1.64/26");
            System.out.println("Ces deux reseaux DEVRAIENT se chevaucher !");
            
            ValidateurPlanAdressage validateur2 = new ValidateurPlanAdressage();
            validateur2.verifierChevauchements(resultatsChevauchement);
            System.out.println("⚠️ Aucun chevauchement detecte (anormal)");
            
        } catch (ChevauchementReseauException e) {
            System.out.println("✅ Chevauchement detecte : " + e.getMessage());
        } catch (Exception e) {
            System.out.println("✅ Exception captee : " + e.getMessage());
        }
        
        // ===== SCENARIO 4 : TROIS VLAN VALIDES PUIS CONFLIT =====
        System.out.println("\n================================================================");
        System.out.println("5. TEST TROIS VLANS VALIDES PUIS CONFLIT (Travail demande)");
        System.out.println("================================================================");
        
        try {
            GestionnaireVLAN gestionnaire2 = new GestionnaireVLAN();
            
            VLAN vlan1 = new VLAN(100, "COMMERCIAL", new ResultatVLSM("COMMERCIAL", "10.0.0.0", 27, "255.255.255.224", 30), "VLAN Commercial");
            VLAN vlan2 = new VLAN(200, "COMPTABILITE", new ResultatVLSM("COMPTABILITE", "10.0.0.32", 28, "255.255.255.240", 14), "VLAN Comptabilite");
            VLAN vlan3 = new VLAN(300, "DIRECTION", new ResultatVLSM("DIRECTION", "10.0.0.48", 28, "255.255.255.240", 14), "VLAN Direction");
            VLAN vlanConflit = new VLAN(100, "RH", new ResultatVLSM("RH", "10.0.0.64", 28, "255.255.255.240", 14), "VLAN RH (conflit)");
            
            gestionnaire2.ajouterVLAN(vlan1);
            System.out.println("✅ VLAN 100 ajoute avec succes");
            
            gestionnaire2.ajouterVLAN(vlan2);
            System.out.println("✅ VLAN 200 ajoute avec succes");
            
            gestionnaire2.ajouterVLAN(vlan3);
            System.out.println("✅ VLAN 300 ajoute avec succes");
            
            gestionnaire2.ajouterVLAN(vlanConflit);
            System.out.println("❌ Cette ligne ne devrait pas s'afficher");
            
        } catch (ConflitVLANException e) {
            System.out.println("✅ Conflit detecte : " + e.getMessage());
        }
        
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                    TP7 TERMINE AVEC SUCCES !                   ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
    }
}