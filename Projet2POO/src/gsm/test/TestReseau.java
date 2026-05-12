/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// ==========================
// PACKAGE : gsm.test
// FICHIER : TestReseau.java
// ==========================

package gsm.test;

import gsm.exception.*;
import gsm.model.*;

public class TestReseau {

    public static void main(String[] args)
            throws CelluleSatureeException {

        System.out.println("╔═══════════════════════════════════╗");
        System.out.println("║          PROJET GSM - RESEAU MOBILE                   ║");
        System.out.println("║          ETUDIANT : TAGNE MOGUE LAURA                 ║");
        System.out.println("╚═══════════════════════════════════╝");

        Reseau reseau = new Reseau(
                "Orange GSM",
                890,
                935,
                "TDMA/FDMA",
                14.4,
                14.4,
                100
        );

        BTS bts1 = new BTS(
                1,
                "Bafoussam",
                45,
                "rural",
                2500,
                40,
                100
        );

        BTS bts2 = new BTS(
                2,
                "Douala",
                40,
                "urbain",
                5000,
                35,
                80
        );

        BTS bts3 = new BTS(
                3,
                "Yaounde",
                50,
                "urbain",
                3000,
                45,
                120
        );

        reseau.ajouterBTS(bts1);
        reseau.ajouterBTS(bts2);
        reseau.ajouterBTS(bts3);

        Smartphone phone1 = new Smartphone(
                "TAGNE",
                "MOGUE",
                "1234",
                "691234567",
                "IMSI001",
                "Android",
                14,
                128
        );

        Smartphone phone2 = new Smartphone(
                "DJOMO",
                "CHRIST",
                "5678",
                "692345678",
                "IMSI002",
                "iOS",
                17,
                256
        );

        Tablet tablet1 = new Tablet(
                "STEVINE",
                "LAURA",
                "9999",
                "693456789",
                "IMSI003",
                10.5,
                true,
                12
        );

        Tablet tablet2 = new Tablet(
                "FOZING",
                "ANNE",
                "8888",
                "694567890",
                "IMSI004",
                12.9,
                true,
                15
        );

        bts1.ajouterMS(phone1);
        bts1.ajouterMS(phone2);

        bts2.ajouterMS(tablet1);

        bts3.ajouterMS(tablet2);

        System.out.println("\n=== TEST DES APPELS ===");

        phone1.appeler(phone2, bts1, bts1);

        phone2.appeler(tablet1, bts1, bts2);

        reseau.afficherPerformances();

        System.out.println("\n=== LISTE BTS ===");

        bts1.afficher();
        bts2.afficher();
        bts3.afficher();

        System.out.println("\n=== LISTE UTILISATEURS ===");

        phone1.afficher();
        phone2.afficher();
        tablet1.afficher();
        tablet2.afficher();

        System.out.println("\n=== APPELS RECUS ===");

        phone2.afficherAppelsRecus();
        tablet1.afficherAppelsRecus();

        System.out.println("\n=== STATISTIQUES ===");

        System.out.println("BTS urbaines : "
                + reseau.compterBTSParType("urbain"));

        System.out.println("BTS rurales : "
                + reseau.compterBTSParType("rural"));

        System.out.println("Nombre abonnes : "
                + reseau.getNombreAbonnes());

        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║               TEST TERMINE AVEC SUCCES              ║");
        System.out.println("║               TAGNE MOGUE LAURA                     ║");
        System.out.println("╚══════════════════════════════════╝");
    }
}