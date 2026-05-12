/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// ==========================
// PACKAGE : gsm.model
// FICHIER : BTS.java
// ==========================

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
