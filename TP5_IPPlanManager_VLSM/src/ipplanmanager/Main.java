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
        System.out.println("==================================================");
        System.out.println("     TP5 - MOTEUR VLSM");
        System.out.println("==================================================");
        System.out.println("IPPlan-Manager : Conception du moteur VLSM");
        
        MoteurVLSM moteur = new MoteurVLSM();
        
        // ===== SCÉNARIO 1 : EXEMPLE DU COURS =====
        ArrayList<BesoinReseau> besoins1 = new ArrayList<>();
        besoins1.add(new BesoinReseau("TECHNIQUE", 120));
        besoins1.add(new BesoinReseau("WIFI", 80));
        besoins1.add(new BesoinReseau("ADMINISTRATION", 50));
        besoins1.add(new BesoinReseau("SERVEURS", 20));
        besoins1.add(new BesoinReseau("DIRECTION", 10));
        
        moteur.genererEtAfficherPlan("Entreprise (Exemple cours)", "192.168.1.0", besoins1);
        
        // ===== SCÉNARIO 2 : PETITE ENTREPRISE (TRAVAIL DEMANDÉ) =====
        System.out.println("\n");
        System.out.println("########################################");
        System.out.println("# Scenario: Petite Entreprise");
        System.out.println("########################################");
        
        ArrayList<BesoinReseau> besoins2 = new ArrayList<>();
        besoins2.add(new BesoinReseau("ADMIN", 25));
        besoins2.add(new BesoinReseau("COMPTABILITE", 12));
        besoins2.add(new BesoinReseau("WIFI_INVITES", 40));
        besoins2.add(new BesoinReseau("SERVEURS", 8));
        
        moteur.genererEtAfficherPlan("Petite Entreprise", "10.0.0.0", besoins2);
        
        // ===== SCÉNARIO 3 : CAMPUS (TRAVAIL DEMANDÉ) =====
        System.out.println("\n");
        System.out.println("########################################");
        System.out.println("# Scenario: Campus Universitaire");
        System.out.println("########################################");
        
        ArrayList<BesoinReseau> besoins3 = new ArrayList<>();
        besoins3.add(new BesoinReseau("ETUDIANTS", 500));
        besoins3.add(new BesoinReseau("PERSONNEL", 120));
        besoins3.add(new BesoinReseau("LABORATOIRE", 60));
        besoins3.add(new BesoinReseau("ADMINISTRATION", 40));
        besoins3.add(new BesoinReseau("WIFI_PUBLIC", 200));
        
        moteur.genererEtAfficherPlan("Campus Universitaire", "172.16.0.0", besoins3);
        
        // ===== ANALYSE COMPARATIVE =====
        System.out.println("\n");
        System.out.println("########################################");
        System.out.println("# ANALYSE COMPARATIVE DES SCENARIOS");
        System.out.println("########################################");
        System.out.println();
        
        System.out.println("1. Scenario 1 (Entreprise) :");
        System.out.println("   - Utilise 192.168.1.0/24 comme réseau de depart");
        System.out.println("   - 5 besoins couvrant 120 à 10 hotes");
        System.out.println("   - Utilise des masques de /25 a /28");
        System.out.println();
        
        System.out.println("2. Scenario 2 (Petite Entreprise) :");
        System.out.println("   - Utilise 10.0.0.0/8 comme réseau de depart (beaucoup d'espace)");
        System.out.println("   - 4 besoins de 40 à 8 hotes");
        System.out.println("   - Masques de /26 a /28");
        System.out.println();
        
        System.out.println("3. Scenario 3 (Campus) :");
        System.out.println("   - Utilise 172.16.0.0/16 comme réseau de depart");
        System.out.println("   - 5 besoins avec 500 hotes (le plus grand)");
        System.out.println("   - Le besoin de 500 hotes nécessite un masque /23 (car /24 = 254 hotes insuffisant)");
        System.out.println("   - Masques de /23 a /26");
        System.out.println();
        
        System.out.println("=== OBSERVATIONS ===");
        System.out.println("- Les besoins sont systematiquement tries par ordre decroissant");
        System.out.println("- Le CIDR est calcule automatiquement en fonction du besoin");
        System.out.println("- Les adresses utilisables sont calculees automatiquement");
        System.out.println("- Pas de chevauchement entre les sous-reseaux");
        System.out.println("- L'espace adressage est utilise de maniere optimale");
        
        System.out.println("\n==================================================");
        System.out.println("     TP5 TERMINE AVEC SUCCES !");
        System.out.println("==================================================");
    }
}