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
public class Main {
     public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("     TP4 - CALCULS RESEAU AUTOMATIQUES");
        System.out.println("==================================================");
        
        InfrastructureReseau infrastructure = new InfrastructureReseau("Infrastructure YFY");
        
        // ===== CRÉATION DE PLUSIEURS RÉSEAUX AVEC DIFFÉRENTS CIDR =====
        
        // Réseau 1: Administration (Classe C - /24)
        ReseauIP reseauAdmin = new ReseauIP("192.168.1.0", 24, "Reseau Administration");
        
        // Réseau 2: Technique (Classe B - /16)
        ReseauIP reseauTech = new ReseauIP("172.16.0.0", 16, "Reseau Technique");
        
        // Réseau 3: WiFi (Classe A - /8)
        ReseauIP reseauWiFi = new ReseauIP("10.0.0.0", 8, "Reseau WiFi");
        
        // Réseau 4: Serveurs (Classe C - /25) - TRAVAIL DEMANDÉ
        ReseauIP reseauServeurs = new ReseauIP("192.168.2.0", 25, "Reseau Serveurs");
        
        // Réseau 5: DMZ (Classe C - /26) - TRAVAIL DEMANDÉ
        ReseauIP reseauDMZ = new ReseauIP("192.168.3.0", 26, "Reseau DMZ");
        
        // Réseau 6: VoIP (Classe C - /27) - TRAVAIL DEMANDÉ
        ReseauIP reseauVoIP = new ReseauIP("192.168.4.0", 27, "Reseau VoIP");
        
        // Réseau 7: Public (Classe A - /24) - TRAVAIL DEMANDÉ
        ReseauIP reseauPublic = new ReseauIP("8.8.8.0", 24, "Reseau Public (DNS Google)");
        
        // Réseau 8: IoT (Classe C - /28) - TRAVAIL DEMANDÉ
        ReseauIP reseauIoT = new ReseauIP("192.168.5.0", 28, "Reseau IoT (Objets Connectes)");
        
        // ===== CRÉATION DES SOUS-RÉSEAUX =====
        SousReseau admin = new SousReseau("ADMIN", reseauAdmin);
        SousReseau tech = new SousReseau("TECH", reseauTech);
        SousReseau wifi = new SousReseau("WIFI", reseauWiFi);
        SousReseau serveurs = new SousReseau("SERVEURS", reseauServeurs);
        SousReseau dmz = new SousReseau("DMZ", reseauDMZ);
        SousReseau voip = new SousReseau("VOIP", reseauVoIP);
        SousReseau publicNet = new SousReseau("PUBLIC", reseauPublic);
        SousReseau iot = new SousReseau("IOT", reseauIoT);
        
        // Ajout des sous-réseaux à l'infrastructure
        infrastructure.ajouterSousReseau(admin);
        infrastructure.ajouterSousReseau(tech);
        infrastructure.ajouterSousReseau(wifi);
        infrastructure.ajouterSousReseau(serveurs);
        infrastructure.ajouterSousReseau(dmz);
        infrastructure.ajouterSousReseau(voip);
        infrastructure.ajouterSousReseau(publicNet);
        infrastructure.ajouterSousReseau(iot);
        
        // ===== CRÉATION DES ÉQUIPEMENTS =====
        
        // Routeur principal
        Equipement routeur = new Equipement("RouteurPrincipal", "Routeur");
        routeur.ajouterInterface(new InterfaceReseau("Gig0/0", new AdresseIP("192.168.1.1"), "255.255.255.0"));
        routeur.ajouterInterface(new InterfaceReseau("Gig0/1", new AdresseIP("172.16.0.1"), "255.255.0.0"));
        routeur.ajouterInterface(new InterfaceReseau("Gig0/2", new AdresseIP("10.0.0.1"), "255.0.0.0"));
        infrastructure.ajouterEquipement(routeur);
        
        // Switch Core
        Equipement switchCore = new Equipement("SwitchCore", "Switch");
        switchCore.ajouterInterface(new InterfaceReseau("Eth0/1", new AdresseIP("192.168.1.10"), "255.255.255.0"));
        switchCore.ajouterInterface(new InterfaceReseau("Eth0/2", new AdresseIP("192.168.2.10"), "255.255.255.128"));
        infrastructure.ajouterEquipement(switchCore);
        
        // Serveur Web
        Equipement serveurWeb = new Equipement("ServeurWeb", "Serveur");
        serveurWeb.ajouterInterface(new InterfaceReseau("eth0", new AdresseIP("192.168.2.100"), "255.255.255.128"));
        infrastructure.ajouterEquipement(serveurWeb);
        
        // Serveur BDD
        Equipement serveurBDD = new Equipement("ServeurBDD", "Serveur");
        serveurBDD.ajouterInterface(new InterfaceReseau("eth0", new AdresseIP("192.168.2.101"), "255.255.255.128"));
        infrastructure.ajouterEquipement(serveurBDD);
        
        // Poste Admin
        Equipement posteAdmin = new Equipement("PosteAdmin", "PC");
        posteAdmin.ajouterInterface(new InterfaceReseau("eth0", new AdresseIP("192.168.1.20"), "255.255.255.0"));
        infrastructure.ajouterEquipement(posteAdmin);
        
        // Poste Technique
        Equipement posteTech = new Equipement("PosteTech", "PC");
        posteTech.ajouterInterface(new InterfaceReseau("eth0", new AdresseIP("172.16.1.10"), "255.255.0.0"));
        infrastructure.ajouterEquipement(posteTech);
        
        // ===== AFFICHAGE DES RÉSEAUX AVEC CALCULS AUTOMATIQUES =====
        System.out.println("\n\n");
        System.out.println("****************** AFFICHAGE DES RESEAUX ******************");
        System.out.println();
        
        reseauAdmin.afficher();
        reseauTech.afficher();
        reseauWiFi.afficher();
        reseauServeurs.afficher();
        reseauDMZ.afficher();
        reseauVoIP.afficher();
        reseauPublic.afficher();
        reseauIoT.afficher();
        
        // ===== AFFICHAGE DE L'INFRASTRUCTURE COMPLÈTE =====
        System.out.println("\n\n");
        System.out.println("****************** INFRASTRUCTURE COMPLETE ******************");
        infrastructure.afficher();
        
        // ===== TEST DE LA MÉTHODE ESTRESEAUPRIVE =====
        System.out.println("\n\n");
        System.out.println("****************** TEST ADRESSES PRIVEES ******************");
        System.out.println();
        
        String[] adressesTest = {
            "192.168.1.0", "172.16.0.0", "10.0.0.0", "8.8.8.8", 
            "192.168.2.0", "172.32.0.0", "11.0.0.0", "127.0.0.1"
        };
        
        for (String adresse : adressesTest) {
            boolean estPrive = CalculateurReseau.estReseauPrive(adresse);
            System.out.println("Adresse " + adresse + " → " + (estPrive ? "PRIVEE" : "PUBLIQUE"));
        }
        
        // ===== TEST DE RECHERCHE D'EQUIPEMENT =====
        System.out.println("\n\n");
        System.out.println("****************** TEST RECHERCHE EQUIPEMENTS ******************");
        System.out.println();
        
        infrastructure.rechercherEquipement("ServeurWeb");
        infrastructure.rechercherEquipement("RouteurPrincipal");
        infrastructure.rechercherEquipement("EquipementInexistant");
        
        System.out.println("\n==================================================");
        System.out.println("     TP4 TERMINE AVEC SUCCES !");
        System.out.println("==================================================");
    }
}   
