/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipplanmanager.service;

import ipplanmanager.exception.*;
import ipplanmanager.model.*;
import ipplanmanager.repository.*;
import ipplanmanager.console.ConsoleService;
import java.io.IOException;
import java.util.ArrayList;

public class ApplicationIPPlanManager {
    
    private ConsoleService console;
    private MoteurVLSM moteurVLSM;
    private GestionnaireVLAN gestionnaireVLAN;
    private ValidateurPlanAdressage validateur;
    private MoteurRecommandation moteurRecommandation;
    private FichierPlanRepository fichierRepository;
    private RapportService rapportService;
    private BesoinRepository besoinRepository;
    
    private String nomProjet;
    private String adresseDepart;
    private ArrayList<BesoinReseau> besoins;
    private ArrayList<ResultatVLSM> resultats;
    private ArrayList<VLAN> vlans;
    private ArrayList<Recommandation> recommandations;
    
    public ApplicationIPPlanManager() {
        console = new ConsoleService();
        moteurVLSM = new MoteurVLSM();
        gestionnaireVLAN = new GestionnaireVLAN();
        validateur = new ValidateurPlanAdressage();
        moteurRecommandation = new MoteurRecommandation();
        fichierRepository = new FichierPlanRepository();
        rapportService = new RapportService();
        besoinRepository = new BesoinRepository();
        
        // Ajout des regles de recommandation
        moteurRecommandation.ajouterRegle(new RecommandationWiFiInvite());
        moteurRecommandation.ajouterRegle(new RecommandationServeurs());
        moteurRecommandation.ajouterRegle(new RecommandationGrandVLAN());
    }
    
    public void demarrer() {
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║         IPPLAN-MANAGER - Application Finale                    ║");
        System.out.println("║         Planification d'adressage IP professionnelle          ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        
        boolean continuer = true;
        
        while (continuer) {
            console.afficherMenu();
            int choix = console.saisirEntier("Votre choix : ");
            
            switch (choix) {
                case 1:
                    executerSaisieEtGeneration();
                    break;
                case 2:
                    executerChargementFichier();
                    break;
                case 3:
                    System.out.println("Au revoir !");
                    continuer = false;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez reessayer.");
            }
        }
    }
    
    private void executerSaisieEtGeneration() {
        try {
            System.out.println("\n================================================================");
            System.out.println("NOUVEAU PROJET");
            System.out.println("================================================================");
            
            // Saisie des informations
            nomProjet = console.saisirTexte("Nom du projet reseau : ");
            adresseDepart = console.saisirTexte("Adresse reseau de depart (ex: 10.10.0.0) : ");
            
            // Validation de l'adresse
            CalculateurReseau.verifierAdresseIP(adresseDepart);
            
            // Saisie des besoins
            besoins = console.saisirBesoins();
            
            // Generation du plan VLSM
            System.out.println("\n📡 Generation du plan VLSM...");
            resultats = moteurVLSM.genererPlan(adresseDepart, besoins);
            
            // Affichage du plan
            System.out.println("\n--- PLAN D'ADRESSAGE PROPOSE ---");
            for (ResultatVLSM resultat : resultats) {
                resultat.afficher();
                System.out.println();
            }
            
            // Creation des VLANs
            genererVLANs(resultats);
            
            // Validation du plan
            System.out.println("\n🔍 Validation du plan...");
            validateur.verifierAdresses(resultats);
            validateur.verifierChevauchements(resultats);
            validateur.afficherValidationReussie();
            
            // Generation des recommandations
            System.out.println("\n💡 Analyse des recommandations...");
            recommandations = moteurRecommandation.analyserVLANs(vlans);
            moteurRecommandation.afficherRecommandations(recommandations);
            
            // Sauvegarde des resultats
            sauvegarderResultats();
            
            // Affichage final
            afficherResultats();
            
        } catch (AdresseIPInvalideException e) {
            System.out.println("❌ Erreur d'adresse IP : " + e.getMessage());
        } catch (ChevauchementReseauException e) {
            System.out.println("❌ Erreur de chevauchement : " + e.getMessage());
        } catch (ConflitVLANException e) {
            System.out.println("❌ Erreur VLAN : " + e.getMessage());
        } catch (IOException e) {
            System.out.println("❌ Erreur de fichier : " + e.getMessage());
        }
    }
    
    private void executerChargementFichier() {
        try {
            System.out.println("\n================================================================");
            System.out.println("CHARGEMENT DEPUIS FICHIER CSV");
            System.out.println("================================================================");
            
            String cheminFichier = console.saisirTexte("Chemin du fichier CSV (ex: exports/besoins.csv) : ");
            
            besoins = besoinRepository.chargerBesoins(cheminFichier);
            
            System.out.println("\n📋 Besoins charges :");
            for (BesoinReseau b : besoins) {
                System.out.println("   - " + b.getNom() + " : " + b.getNombreHotes() + " hotes");
            }
            
            nomProjet = console.saisirTexte("Nom du projet pour sauvegarde : ");
            adresseDepart = console.saisirTexte("Adresse reseau de depart : ");
            
            CalculateurReseau.verifierAdresseIP(adresseDepart);
            
            System.out.println("\n📡 Generation du plan VLSM...");
            resultats = moteurVLSM.genererPlan(adresseDepart, besoins);
            
            genererVLANs(resultats);
            
            validateur.verifierAdresses(resultats);
            validateur.verifierChevauchements(resultats);
            
            recommandations = moteurRecommandation.analyserVLANs(vlans);
            
            sauvegarderResultats();
            
            System.out.println("\n✅ Generation terminee avec succes !");
            
        } catch (IOException e) {
            System.out.println("❌ Erreur de lecture du fichier : " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Erreur : " + e.getMessage());
        }
    }
    
    private void genererVLANs(ArrayList<ResultatVLSM> resultats) throws ConflitVLANException {
        vlans = new ArrayList<>();
        int numeroVLAN = 10;
        
        for (ResultatVLSM resultat : resultats) {
            VLAN vlan = new VLAN(numeroVLAN, resultat.getNomBesoin(), resultat, "VLAN_" + resultat.getNomBesoin());
            gestionnaireVLAN.ajouterVLAN(vlan);
            vlans.add(vlan);
            numeroVLAN += 10;
        }
    }
    
    private void sauvegarderResultats() throws IOException {
        String basePath = "exports/";
        String nomFichierBase = nomProjet.replace(" ", "_");
        
        fichierRepository.sauvegarderPlanCSV(resultats, basePath + nomFichierBase + "_plan.csv");
        fichierRepository.sauvegarderVLANsCSV(vlans, basePath + nomFichierBase + "_vlans.csv");
        fichierRepository.sauvegarderRecommandations(recommandations, basePath + nomFichierBase + "_recommandations.txt");
        rapportService.genererRapportComplet(besoins, resultats, vlans, recommandations, basePath + nomFichierBase + "_rapport.txt");
        
        System.out.println("\n💾 Fichiers sauvegardes dans le dossier 'exports' :");
        System.out.println("   - " + nomFichierBase + "_plan.csv");
        System.out.println("   - " + nomFichierBase + "_vlans.csv");
        System.out.println("   - " + nomFichierBase + "_recommandations.txt");
        System.out.println("   - " + nomFichierBase + "_rapport.txt");
    }
    
    private void afficherResultats() {
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                    RESUME DU PROJET                            ║");
        System.out.println("╠════════════════════════════════════════════════════════════════╣");
        System.out.println("║ Nom du projet : " + nomProjet);
        System.out.println("║ Adresse de depart : " + adresseDepart);
        System.out.println("║ Nombre de besoins : " + besoins.size());
        System.out.println("║ Nombre de VLANs crees : " + vlans.size());
        System.out.println("║ Nombre de recommandations : " + recommandations.size());
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
    }
}
