/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// ==========================
// PACKAGE : gsm.model
// FICHIER : Smartphone.java
// ==========================

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
