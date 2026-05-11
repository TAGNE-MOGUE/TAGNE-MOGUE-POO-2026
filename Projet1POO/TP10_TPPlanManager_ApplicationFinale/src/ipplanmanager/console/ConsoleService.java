/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipplanmanager.console;

import ipplanmanager.model.BesoinReseau;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleService {
    private Scanner scanner;
    
    public ConsoleService() {
        scanner = new Scanner(System.in);
    }
    
    public String saisirTexte(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
    
    public int saisirEntier(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Valeur invalide. Entrez un nombre entier.");
            }
        }
    }
    
    public ArrayList<BesoinReseau> saisirBesoins() {
        ArrayList<BesoinReseau> besoins = new ArrayList<>();
        int nombreBesoins = saisirEntier("Nombre de besoins reseau a saisir : ");
        
        for (int i = 1; i <= nombreBesoins; i++) {
            System.out.println("\n--- Saisie du besoin " + i + " ---");
            String nom = saisirTexte("Nom du service ou departement : ");
            int hotes = saisirEntier("Nombre d'hotes demandes : ");
            besoins.add(new BesoinReseau(nom, hotes));
        }
        return besoins;
    }
    
    public void afficherMenu() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║           MENU IPPLAN-MANAGER            ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║ 1. Saisir les besoins et generer un plan ║");
        System.out.println("║ 2. Charger les besoins depuis CSV        ║");
        System.out.println("║ 3. Quitter                               ║");
        System.out.println("╚══════════════════════════════════════════╝");
    }
}
