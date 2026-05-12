/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// ==========================
// PACKAGE : gsm.model
// FICHIER : Tablet.java
// ==========================

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
