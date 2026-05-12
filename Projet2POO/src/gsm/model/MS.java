/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// ==========================
// PACKAGE : gsm.model
// FICHIER : MS.java
// ==========================

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
