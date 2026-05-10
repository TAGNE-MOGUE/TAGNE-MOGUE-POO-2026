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
  
        InfrastructureReseau infrastructure = new InfrastructureReseau("Infrastructure YFY");
    
        ReseauIP reseauAdmin = new ReseauIP("192.168.1.0", 24, "Reseau administration");
        SousReseau admin = new SousReseau("ADMIN", reseauAdmin);
        infrastructure.ajouterSousReseau(admin);
    
        ReseauIP reseauTech = new ReseauIP("192.168.2.0", 24, "Reseau technique");
        SousReseau tech = new SousReseau("TECH", reseauTech);
        infrastructure.ajouterSousReseau(tech);
        
        // Troisième sous-reseau : WIFI (travail demandé)
        ReseauIP reseauWiFi = new ReseauIP("192.168.3.0", 24, "Reseau WiFi");
        SousReseau wifi = new SousReseau("WIFI", reseauWiFi);
        infrastructure.ajouterSousReseau(wifi);
        
        // ===== EQUIPEMENTS AVEC MULTIPLES INTERFACES =====
        
        // 1. Routeur (avec plusieurs interfaces)
        Equipement routeur = new Equipement("RouteurPrincipal", "Routeur");
        routeur.ajouterInterface(new InterfaceReseau("GigabitEthernet0/0", new AdresseIP("192.168.1.1"), "255.255.255.0"));
        routeur.ajouterInterface(new InterfaceReseau("GigabitEthernet0/1", new AdresseIP("192.168.2.1"), "255.255.255.0"));
        routeur.ajouterInterface(new InterfaceReseau("GigabitEthernet0/2", new AdresseIP("192.168.3.1"), "255.255.255.0"));
        infrastructure.ajouterEquipement(routeur);
        
        // 2. Switch (travail demandé)
        Equipement switchPrincipal = new Equipement("SwitchCore", "Switch");
        switchPrincipal.ajouterInterface(new InterfaceReseau("FastEthernet0/1", new AdresseIP("192.168.1.10"), "255.255.255.0"));
        switchPrincipal.ajouterInterface(new InterfaceReseau("FastEthernet0/2", new AdresseIP("192.168.1.11"), "255.255.255.0"));
        switchPrincipal.ajouterInterface(new InterfaceReseau("FastEthernet0/24", new AdresseIP("192.168.1.12"), "255.255.255.0"));
        infrastructure.ajouterEquipement(switchPrincipal);
        
        // 3. Serveur (travail demandé)
        Equipement serveurWeb = new Equipement("ServeurWeb", "Serveur");
        serveurWeb.ajouterInterface(new InterfaceReseau("eth0", new AdresseIP("192.168.1.100"), "255.255.255.0"));
        serveurWeb.ajouterInterface(new InterfaceReseau("eth1", new AdresseIP("192.168.1.101"), "255.255.255.0"));
        infrastructure.ajouterEquipement(serveurWeb);
        
        // 4. Serveur BDD
        Equipement serveurBDD = new Equipement("ServeurBDD", "Serveur");
        serveurBDD.ajouterInterface(new InterfaceReseau("eth0", new AdresseIP("192.168.2.50"), "255.255.255.0"));
        serveurBDD.ajouterInterface(new InterfaceReseau("eth1", new AdresseIP("192.168.2.51"), "255.255.255.0"));
        infrastructure.ajouterEquipement(serveurBDD);
        
        // 5. Poste client 1
        Equipement client1 = new Equipement("PosteAdmin1", "PC");
        client1.ajouterInterface(new InterfaceReseau("eth0", new AdresseIP("192.168.1.20"), "255.255.255.0"));
        infrastructure.ajouterEquipement(client1);
        
        // 6. Poste client 2 (WiFi)
        Equipement clientWiFi = new Equipement("PortableWiFi", "Laptop");
        clientWiFi.ajouterInterface(new InterfaceReseau("wlan0", new AdresseIP("192.168.3.10"), "255.255.255.0"));
        clientWiFi.ajouterInterface(new InterfaceReseau("eth0", new AdresseIP("192.168.3.11"), "255.255.255.0"));
        infrastructure.ajouterEquipement(clientWiFi);
        
        // 7. Imprimante réseau
        Equipement imprimante = new Equipement("ImprimanteReseau", "Imprimante");
        imprimante.ajouterInterface(new InterfaceReseau("eth0", new AdresseIP("192.168.1.30"), "255.255.255.0"));
        infrastructure.ajouterEquipement(imprimante);
        
        // 8. Point d'accès WiFi
        Equipement pointAcces = new Equipement("AP_Principal", "PointAcces");
        pointAcces.ajouterInterface(new InterfaceReseau("eth0", new AdresseIP("192.168.3.2"), "255.255.255.0"));
        pointAcces.ajouterInterface(new InterfaceReseau("wlan0", new AdresseIP("192.168.3.3"), "255.255.255.0"));
        infrastructure.ajouterEquipement(pointAcces);
        
        infrastructure.afficher();
        
        System.out.println("\n*** TEST DE RECHERCHE D'EQUIPEMENTS ***");
        System.out.println("----------------------------------------");
        infrastructure.rechercherEquipement("ServeurWeb");
        infrastructure.rechercherEquipement("SwitchCore");
        infrastructure.rechercherEquipement("EquipementInexistant");
    }
}
