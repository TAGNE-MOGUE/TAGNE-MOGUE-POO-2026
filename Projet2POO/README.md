# PROJET2POO - SIMULATION DE LA PARTIE RADIO D’UN RESEAU GSM_

## Étudiant
**Nom : TAGNE MOGUE STEVINE LAURA**  


## Description du projet

Ce projet consiste à simuler le fonctionnement simplifié d’un réseau GSM en Java orienté objet.

L’application permet de :

- créer plusieurs BTS
- créer des utilisateurs mobiles
- gérer des smartphones et tablettes
- effectuer des appels entre utilisateurs
- afficher les statistiques du reseau
- gérer les exceptions personnalisées

Le projet met en pratique plusieurs notions importantes de la programmation orientée objet parmi lesquelles :

- encapsulation
- héritage
- polymorphisme
- collections Java
- exceptions personnalisées
- organisation en packages


## Structure du projet

j'ai organisé le travail en regroupant les differentes classes dans les packages
 -gsm.model: contient les classes MS, Smartphone, Tablet, BTS et Reseau
 -gsm.exception: contient la classe CelluleSatureeException
 -gsm.test: contient la classe  TestReseau


## Explication des principales classes

1.Classe MS: c'est la classe mère représentant un utilisateur du réseau GSM.

CODE

package gsm.model;
import java.util.ArrayList;

public class MS implements PeutAppeler {
    protected String nom;
    protected String prenom;
    protected String motDePasse;
    protected String msisdn;
    protected String imsi;
    protected ArrayList<String> appelsRecus;

    public MS(String nom, String prenom,
              String motDePasse,
              String msisdn,
              String imsi) {
        this.nom = nom;
        this.prenom = prenom;
        this.motDePasse = motDePasse;
        this.msisdn = msisdn;
        this.imsi = imsi;
        appelsRecus = new ArrayList<>();
    }

    public boolean peutSeConnecter() {
        return msisdn != null && !msisdn.isEmpty();
    }

    @Override
    public void appeler(MS destinataire,
                        BTS source,
                        BTS destination) {

        System.out.println("\n════════════════════════════════════");
        System.out.println("         DEMARRAGE APPEL GSM");
        System.out.println("════════════════════════════════════");

        System.out.println("Appelant : " + nom + " " + prenom);
        System.out.println("Destinataire : "
                + destinataire.nom + " "
                + destinataire.prenom);

        System.out.println("BTS Source : "
                + source.getEmplacement());

        System.out.println("BTS Destination : "
                + destination.getEmplacement());

        if (peutSeConnecter()) {

            System.out.println("Connexion reseau : ETABLIE");
            System.out.println("Canal GSM reserve...");
            System.out.println("Communication en cours...");
            System.out.println("Appel termine avec succes");

            destinataire.recevoirAppel(this);

        } else {

            System.out.println("ECHEC : utilisateur non connecte");
        }

        System.out.println("════════════════════════════════════\n");
    }
    public void recevoirAppel(MS expediteur) {
        String message = expediteur.nom
                + " vous a appele.";
        appelsRecus.add(message);
        System.out.println(nom
                + " recoit un appel de "
                + expediteur.nom);
    }
    public void afficherAppelsRecus() {
        System.out.println("\n=== APPELS RECUS ===");
        if (appelsRecus.isEmpty()) {
            System.out.println("Aucun appel recu.");
        } else {
            for (String appel : appelsRecus) {
                System.out.println(appel);
            }
        }
    }

    public void afficher() {

        System.out.println("Nom : " + nom);
        System.out.println("Prenom : " + prenom);
        System.out.println("MSISDN : " + msisdn);
        System.out.println("IMSI : " + imsi);
    }
}

2. Classe Smartphone: c'est la classe héritant de Abonne représentant un smartphone.
CODE
package gsm.model;

public class Smartphone extends MS {

    private String systeme;
    private int versionAndroid;
    private int stockage;

    public Smartphone(String nom,
                      String prenom,
                      String motDePasse,
                      String msisdn,
                      String imsi,
                      String systeme,
                      int versionAndroid,
                      int stockage) {

        super(nom, prenom, motDePasse, msisdn, imsi);
        this.systeme = systeme;
        this.versionAndroid = versionAndroid;
        this.stockage = stockage;
    }

    @Override
    public void afficher() {
        System.out.println("\n===== SMARTPHONE =====");
        super.afficher();
        System.out.println("Systeme : " + systeme);
        System.out.println("Version : " + versionAndroid);
        System.out.println("Stockage : " + stockage + " GB");
    }
}

3. Classe Tablet: c'est la classe héritant de Abonne représentant une tablette.
CODE
package gsm.model;

public class Tablet extends MS {

    private double tailleEcran;
    private boolean stylet;
    private int autonomie;

    public Tablet(String nom,
                  String prenom,
                  String motDePasse,
                  String msisdn,
                  String imsi,
                  double tailleEcran,
                  boolean stylet,
                  int autonomie) {

        super(nom, prenom, motDePasse, msisdn, imsi);

        this.tailleEcran = tailleEcran;
        this.stylet = stylet;
        this.autonomie = autonomie;
    }

    @Override
    public void afficher() {

        System.out.println("\n===== TABLETTE =====");

        super.afficher();

        System.out.println("Taille ecran : "
                + tailleEcran + " pouces");

        System.out.println("Stylet : "
                + (stylet ? "Oui" : "Non"));

        System.out.println("Autonomie : "
                + autonomie + " heures");
    }
}

4. Classe BTS: c'est la classe représentant une station de base GSM.
CODE
package gsm.model;

import gsm.exception.CelluleSatureeException;
import java.util.ArrayList;

public class BTS {

    private int numero;
    private String emplacement;
    private double hauteur;
    private String typeZone;
    private double portee;
    private double frequence;
    private int capaciteMax;

    private ArrayList<MS> utilisateurs;

    public BTS(int numero,
               String emplacement,
               double hauteur,
               String typeZone,
               double portee,
               double frequence,
               int capaciteMax) {

        this.numero = numero;
        this.emplacement = emplacement;
        this.hauteur = hauteur;
        this.typeZone = typeZone;
        this.portee = portee;
        this.frequence = frequence;
        this.capaciteMax = capaciteMax;

        utilisateurs = new ArrayList<>();
    }

    public void ajouterMS(MS utilisateur)
            throws CelluleSatureeException {

        if (utilisateurs.size() >= capaciteMax) {

            throw new CelluleSatureeException(
                    "ERREUR : BTS "
                    + numero
                    + " saturee !");
        }

        utilisateurs.add(utilisateur);
    }

    public String getEmplacement() {
        return emplacement;
    }

    public int getNombreUtilisateurs() {
        return utilisateurs.size();
    }

    public String getTypeZone() {
        return typeZone;
    }

    public void afficher() {

        System.out.println("\n================================");
        System.out.println("BTS Numero : " + numero);
        System.out.println("Zone : " + emplacement);
        System.out.println("Type : " + typeZone);
        System.out.println("Hauteur : " + hauteur + " m");
        System.out.println("Portee : " + portee + " m");
        System.out.println("Frequence : " + frequence + " MHz");
        System.out.println("Capacite max : " + capaciteMax);
        System.out.println("Utilisateurs : "
                + utilisateurs.size());
        System.out.println("================================");
    }
}

5. Classe Reseau: c'est la classe représentant l’ensemble du réseau GSM.
CODE
package gsm.model;

import java.util.ArrayList;

public class Reseau {

    private String nom;
    private double frequenceMontante;
    private double frequenceDescendante;
    private String technologie;
    private double debitMontant;
    private double debitDescendant;
    private int capacite;

    private ArrayList<BTS> listeBTS;

    public Reseau(String nom,
                  double frequenceMontante,
                  double frequenceDescendante,
                  String technologie,
                  double debitMontant,
                  double debitDescendant,
                  int capacite) {

        this.nom = nom;
        this.frequenceMontante = frequenceMontante;
        this.frequenceDescendante = frequenceDescendante;
        this.technologie = technologie;
        this.debitMontant = debitMontant;
        this.debitDescendant = debitDescendant;
        this.capacite = capacite;

        listeBTS = new ArrayList<>();
    }

    public void ajouterBTS(BTS bts) {
        listeBTS.add(bts);
    }

    public void supprimerBTS(int numero) {

        boolean trouve = false;

        for (BTS bts : listeBTS) {

            if (bts.equals(numero)) {

                listeBTS.remove(bts);

                trouve = true;

                System.out.println("BTS supprimee.");

                break;
            }
        }

        if (!trouve) {
            System.out.println("BTS introuvable.");
        }
    }

    public int getNombreAbonnes() {

        int total = 0;

        for (BTS bts : listeBTS) {
            total += bts.getNombreUtilisateurs();
        }

        return total;
    }

    public int compterBTSParType(String type) {

        int compteur = 0;

        for (BTS bts : listeBTS) {

            if (bts.getTypeZone()
                    .equalsIgnoreCase(type)) {

                compteur++;
            }
        }

        return compteur;
    }

    public String rechercherLocalisation(String numero) {

        for (BTS bts : listeBTS) {

            return "Utilisateur localise dans : "
                    + bts.getEmplacement();
        }

        return "Utilisateur introuvable";
    }

    public void afficherPerformances() {

        System.out.println("\n========== RESEAU GSM ==========");

        System.out.println("Nom : " + nom);
        System.out.println("Technologie : " + technologie);

        System.out.println("Frequence montante : "
                + frequenceMontante + " MHz");

        System.out.println("Frequence descendante : "
                + frequenceDescendante + " MHz");

        System.out.println("Debit montant : "
                + debitMontant + " Mbps");

        System.out.println("Debit descendant : "
                + debitDescendant + " Mbps");

        System.out.println("Capacite : "
                + capacite);

        System.out.println("Nombre BTS : "
                + listeBTS.size());

        System.out.println("Nombre abonnes : "
                + getNombreAbonnes());

        System.out.println("================================");
    }
}

6. Classe CelluleSatureeException: c'est l'exception levée lorsqu’une BTS atteint sa capacité maximale.
package gsm.exception;

public class CelluleSatureeException extends Exception {

    public CelluleSatureeException(String message) {
        super(message);
    }
}

7. Classe TestReseau: elee permet de tester toutes les autres classes
CODE
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

## Fonctionnalités réalisées

les differentes fonctionnalités que j'ai implementé sont:

1. Gestion des BTS
Le programme permet de : créer plusieurs BTS, ajouter des utilisateurs dans les BTS, afficher les informations des BTS
et supprimer une BTS

2. Gestion des abonnés
Deux types d’utilisateurs sont gérés :
-Smartphone ( Android / iOS, version système, stockage )
-Tablet ( taille écran, support stylet)

3. Gestion des appels
Les utilisateurs peuvent : appeler un autre utilisateur, recevoir des appels et consulter l’historique des appels

4. Gestion des exceptions
Le projet gère la saturation d’une cellule BTS grâce à une exception personnalisée qui est :
CelluleSatureeException

5. Recherche d’utilisateur
Le réseau peut rechercher la localisation d’un utilisateur à partir de son numéro MSISDN.

## Exemple d’exécution

Appelant : TAGNE MOGUE
Destinataire : DJOMO CHRIST
BTS Source : Bafoussam
BTS Destination : Bafoussam
Connexion reseau : ETABLIE
Canal GSM reserve...
Communication en cours...
Appel termine avec succes
DJOMO recoit un appel de TAGNE

## Difficultés rencontrées

les pricipales difficultées que j'ai rencontré ont concerné :

1. La gestion des packages: l’organisation correcte des packages était importante pour éviter les erreurs d’importation.

2. La gestion des collections: L’utilisation des ArrayList nécessitait une bonne maîtrise des boucles et des ajouts dynamiques.

3. Gestion des exceptions: La gestion de la saturation d’une cellule BTS demandait la création d’une exception personnalisée.

4. Héritage et polymorphisme: Il fallait bien différencier les comportements des Smartphones et des Tablettes tout en conservant 
une structure commune.

## Conclusion

Ce projet m’a permis de comprendre concrètement les principes fondamentaux de la programmation orientée objet appliqués aux réseaux GSM.
J’ai appris à : structurer une application Java, utiliser l’héritage, gérer les exceptions, manipuler des collections, organiser un projet professionnel avec GitHub
Ce projet représente donc une simulation réaliste simplifiée d’un réseau mobile GSM
