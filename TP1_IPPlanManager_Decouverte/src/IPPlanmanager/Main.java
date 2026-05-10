/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IPPlanmanager;

/**
 *
 * @author GENERAL STORES
 */
public class Main {
    public static void main(String[] args) {

        System.out.println("===== IPPlan-Manager : TP1 =====");
        System.out.println("Découverte des premières classes du projet");
        System.out.println();

        /*
         * Création des adresses IP
         */

        AdresseIP ipRouteur = new AdresseIP("192.168.1.1");
        AdresseIP ipServeur = new AdresseIP("192.168.1.10");
        AdresseIP ipClient1 = new AdresseIP("192.168.1.50");

        // Adresse IP du deuxième client
        AdresseIP ipClient2 = new AdresseIP("192.168.1.60");

        // Adresse IP du point d'accès WiFi
        AdresseIP ipWifi = new AdresseIP("192.168.2.1");

        // Adresse IP du switch
        AdresseIP ipSwitch = new AdresseIP("192.168.1.2");



        /*
         * Création des interfaces réseau
         */

        InterfaceReseau interfaceRouteur =
                new InterfaceReseau("eth0", ipRouteur);

        InterfaceReseau interfaceServeur =
                new InterfaceReseau("eth0", ipServeur);

        InterfaceReseau interfaceClient1 =
                new InterfaceReseau("wlan0", ipClient1);

        InterfaceReseau interfaceClient2 =
                new InterfaceReseau("wlan1", ipClient2);

        InterfaceReseau interfaceWifi =
                new InterfaceReseau("GigabitEthernet0/1", ipWifi);

        InterfaceReseau interfaceSwitch =
                new InterfaceReseau("FastEthernet0/1", ipSwitch);


        /*
         * Interface sans adresse IP
         */

        InterfaceReseau interfaceSansIP =
                new InterfaceReseau("eth1", null);



        /*
         * Activation de certaines interfaces
         */

        interfaceRouteur.activer();
        interfaceServeur.activer();
        interfaceWifi.activer();
        interfaceSwitch.activer();

        // Client 1 reste inactive
        // Client 2 reste inactive
        // interfaceSansIP reste inactive



        /*
         * Création des équipements
         */

        Equipement routeur =
                new Equipement("R1_EDGE",
                        "Routeur",
                        interfaceRouteur);

        Equipement serveur =
                new Equipement("SRV_DNS",
                        "Serveur",
                        interfaceServeur);

        Equipement client1 =
                new Equipement("PC_ADMIN",
                        "Poste client",
                        interfaceClient1);

        Equipement client2 =
                new Equipement("PC_SECRETARIAT",
                        "Poste client",
                        interfaceClient2);

        Equipement switchReseau =
                new Equipement("SW_CORE",
                        "Switch",
                        interfaceSwitch);

        Equipement pointWifi =
                new Equipement("AP_WIFI",
                        "Point d'accès WiFi",
                        interfaceWifi);

        Equipement machineSansIP =
                new Equipement("PC_TEST",
                        "Poste client",
                        interfaceSansIP);



        /*
         * Création des réseaux
         */

        ReseauIP reseauPrincipal =
                new ReseauIP(
                        "192.168.1.0",
                        24,
                        "Réseau principal du laboratoire"
                );

        // Deuxième réseau demandé par le professeur

        ReseauIP reseauWifi =
                new ReseauIP(
                        "192.168.2.0",
                        24,
                        "Réseau WiFi des étudiants"
                );



        /*
         * Affichage des réseaux
         */

        System.out.println("----- Réseaux créés -----");

        reseauPrincipal.afficher();

        System.out.println();

        reseauWifi.afficher();



        /*
         * Affichage des équipements
         */

        System.out.println();
        System.out.println("----- Équipements créés -----");

        System.out.println();
        routeur.afficher();

        System.out.println();
        serveur.afficher();

        System.out.println();
        client1.afficher();

        System.out.println();
        client2.afficher();

        System.out.println();
        switchReseau.afficher();

        System.out.println();
        pointWifi.afficher();

        System.out.println();
        machineSansIP.afficher();
    }
}
