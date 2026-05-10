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

        System.out.println("===== TP2 Encapsulation =====");

        AdresseIP ip1 = new AdresseIP("192.168.1.1");
        AdresseIP ip2 = new AdresseIP("");
        AdresseIP ip3 = new AdresseIP(null);

        System.out.println("IP1 locale ? " + ip1.estAdresseLocale());

        InterfaceReseau int1 = new InterfaceReseau("eth0", ip1);
        InterfaceReseau int2 = new InterfaceReseau("", ip2);

        int1.activer();

        Equipement r1 = new Equipement("Routeur1", "Routeur", int1);
        Equipement r2 = new Equipement("", "", int2);

        ReseauIP net1 = new ReseauIP("192.168.1.0", 24, "Réseau OK");
        ReseauIP net2 = new ReseauIP("", 50, "");

        System.out.println("\n--- Réseau 1 ---");
        net1.afficher();

        System.out.println("\n--- Réseau 2 ---");
        net2.afficher();

        System.out.println("\n--- Equipement 1 ---");
        r1.afficher();

        System.out.println("\n--- Equipement 2 ---");
        r2.afficher();
    }
}
