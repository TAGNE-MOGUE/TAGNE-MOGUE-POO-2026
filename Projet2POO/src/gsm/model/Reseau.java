/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// ==========================
// PACKAGE : gsm.model
// FICHIER : Reseau.java
// ==========================

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
