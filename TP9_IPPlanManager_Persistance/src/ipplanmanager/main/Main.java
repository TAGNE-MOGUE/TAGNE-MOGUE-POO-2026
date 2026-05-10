/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipplanmanager.main;

import ipplanmanager.exception.ConflitVLANException;
import ipplanmanager.model.*;
import ipplanmanager.repository.*;
import ipplanmanager.service.*;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║         TP9 - PERSISTANCE ET ORGANISATION PROFESSIONNELLE      ║");
        System.out.println("║              IPPlan-Manager - Sauvegarde des donnees           ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        
        // Fichiers d'entree/sortie
        String fichierBesoins = "exports/besoins.csv";
        String fichierPlan = "exports/plan_adressage.csv";
        String fichierVLANs = "exports/vlans.csv";
        String fichierRecommandations = "exports/recommandations.txt";
        String fichierRapport = "exports/rapport_complet.txt";
        
        // Fichiers pour le travail demande (PME)
        String fichierBesoinsPME = "exports/besoins_pme.csv";
        String fichierRapportPME = "exports/rapport_pme.txt";
        
        try {
            // ===== SCENARIO 1 : UNIVERSITE =====
            System.out.println("\n================================================================");
            System.out.println("1. TRAITEMENT DU SCENARIO UNIVERSITE");
            System.out.println("================================================================");
            
            BesoinRepository besoinRepo = new BesoinRepository();
            FichierPlanRepository fichierRepo = new FichierPlanRepository();
            RapportService rapportService = new RapportService();
            MoteurVLSM moteurVLSM = new MoteurVLSM();
            GestionnaireVLAN gestionnaireVLAN = new GestionnaireVLAN();
            MoteurRecommandation moteurRecommandation = new MoteurRecommandation();
            
            // Lecture des besoins
            ArrayList<BesoinReseau> besoins = besoinRepo.chargerBesoins(fichierBesoins);
            System.out.println("\n📋 Besoins charges depuis " + fichierBesoins + " :");
            for (BesoinReseau b : besoins) {
                System.out.println("   - " + b.getNom() + " : " + b.getNombreHotes() + " hotes");
            }
            
            // Generation du plan VLSM
            ArrayList<ResultatVLSM> resultats = moteurVLSM.genererPlan("10.10.0.0", besoins);
            
            // Creation des VLANs
            int numeroVLAN = 10;
            for (ResultatVLSM resultat : resultats) {
                VLAN vlan = new VLAN(numeroVLAN, resultat.getNomBesoin(), resultat, "VLAN_" + resultat.getNomBesoin());
                gestionnaireVLAN.ajouterVLAN(vlan);
                numeroVLAN += 10;
            }
            
            // Ajout des regles de recommandation
            moteurRecommandation.ajouterRegle(new RecommandationWiFiInvite());
            moteurRecommandation.ajouterRegle(new RecommandationServeurs());
            moteurRecommandation.ajouterRegle(new RecommandationGrandVLAN());
            
            // Generation des recommandations
            ArrayList<Recommandation> recommandations = moteurRecommandation.analyserVLANs(gestionnaireVLAN.getVlans());
            
            // Sauvegarde des fichiers
            fichierRepo.sauvegarderPlanCSV(resultats, fichierPlan);
            fichierRepo.sauvegarderVLANsCSV(gestionnaireVLAN.getVlans(), fichierVLANs);
            fichierRepo.sauvegarderRecommandations(recommandations, fichierRecommandations);
            rapportService.genererRapportComplet(besoins, resultats, gestionnaireVLAN.getVlans(), recommandations, fichierRapport);
            
            System.out.println("\n✅ Fichiers generes avec succes :");
            System.out.println("   - " + fichierPlan);
            System.out.println("   - " + fichierVLANs);
            System.out.println("   - " + fichierRecommandations);
            System.out.println("   - " + fichierRapport);
            
            // ===== SCENARIO 2 : PME (Travail demande) =====
            System.out.println("\n================================================================");
            System.out.println("2. TRAITEMENT DU SCENARIO PME (Travail demande)");
            System.out.println("================================================================");
            
            // Creation du fichier besoins_pme.csv s'il n'existe pas
            creerFichierBesoinsPME(fichierBesoinsPME);
            
            ArrayList<BesoinReseau> besoinsPME = besoinRepo.chargerBesoins(fichierBesoinsPME);
            System.out.println("\n📋 Besoins charges depuis " + fichierBesoinsPME + " :");
            for (BesoinReseau b : besoinsPME) {
                System.out.println("   - " + b.getNom() + " : " + b.getNombreHotes() + " hotes");
            }
            
            ArrayList<ResultatVLSM> resultatsPME = moteurVLSM.genererPlan("192.168.1.0", besoinsPME);
            
            GestionnaireVLAN gestionnairePME = new GestionnaireVLAN();
            numeroVLAN = 100;
            for (ResultatVLSM resultat : resultatsPME) {
                VLAN vlan = new VLAN(numeroVLAN, resultat.getNomBesoin(), resultat, "VLAN_" + resultat.getNomBesoin());
                gestionnairePME.ajouterVLAN(vlan);
                numeroVLAN += 10;
            }
            
            ArrayList<Recommandation> recommandationsPME = moteurRecommandation.analyserVLANs(gestionnairePME.getVlans());
            rapportService.genererRapportComplet(besoinsPME, resultatsPME, gestionnairePME.getVlans(), recommandationsPME, fichierRapportPME);
            
            System.out.println("\n✅ Rapport genere : " + fichierRapportPME);
            
            // ===== AFFICHAGE DES RECOMMANDATIONS =====
            System.out.println("\n================================================================");
            System.out.println("3. RECOMMANDATIONS PRODUITES");
            System.out.println("================================================================");
            
            if (recommandations.isEmpty()) {
                System.out.println("Aucune recommandation particuliere.");
            } else {
                for (Recommandation rec : recommandations) {
                    rec.afficher();
                }
            }
            
            System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
            System.out.println("║                    TP9 TERMINE AVEC SUCCES !                   ║");
            System.out.println("║                                                               ║");
            System.out.println("║  Fichiers generes dans le dossier 'exports' :                 ║");
            System.out.println("║  - plan_adressage.csv      (Plan VLSM)                        ║");
            System.out.println("║  - vlans.csv               (Liste des VLANs)                  ║");
            System.out.println("║  - recommandations.txt     (Recommandations)                 ║");
            System.out.println("║  - rapport_complet.txt     (Rapport technique)               ║");
            System.out.println("║  - rapport_pme.txt         (Rapport PME)                      ║");
            System.out.println("╚════════════════════════════════════════════════════════════════╝");
            
        } catch (IOException e) {
            System.out.println("❌ Erreur fichier : " + e.getMessage());
        } catch (ConflitVLANException e) {
            System.out.println("❌ Erreur VLAN : " + e.getMessage());
        }
    }
    
    private static void creerFichierBesoinsPME(String chemin) {
        try {
            java.io.File file = new java.io.File(chemin);
            if (!file.exists()) {
                java.io.FileWriter writer = new java.io.FileWriter(file);
                writer.write("Nom;Hotes\n");
                writer.write("ADMINISTRATION;50\n");
                writer.write("COMPTABILITE;20\n");
                writer.write("WIFI_INVITES;80\n");
                writer.write("SERVEURS;15\n");
                writer.write("VOIP;40\n");
                writer.close();
                System.out.println("📝 Fichier besoins_pme.csv cree automatiquement.");
            }
        } catch (IOException e) {
            System.out.println("⚠️ Impossible de creer besoins_pme.csv : " + e.getMessage());
        }
    }
}