/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipplanmanager.model;

import ipplanmanager.service.CalculateurReseau;

public class ResultatVLSM {
    private String nomBesoin;
    private String adresseReseau;
    private int cidr;
    private String masqueDecimal;
    private int capacite;
    private int besoinInitial;
    private String premiereAdresseUtilisable;
    private String derniereAdresseUtilisable;
    
    public ResultatVLSM(String nomBesoin, String adresseReseau, int cidr, 
                        String masqueDecimal, int capacite, int besoinInitial) {
        this.nomBesoin = nomBesoin;
        this.adresseReseau = adresseReseau;
        this.cidr = cidr;
        this.masqueDecimal = masqueDecimal;
        this.capacite = capacite;
        this.besoinInitial = besoinInitial;
        this.premiereAdresseUtilisable = CalculateurReseau.calculerPremiereAdresseUtilisable(adresseReseau, cidr);
        this.derniereAdresseUtilisable = CalculateurReseau.calculerDerniereAdresseUtilisable(adresseReseau, cidr);
    }
    
    public String getNomBesoin() { return nomBesoin; }
    public String getAdresseReseau() { return adresseReseau; }
    public int getCidr() { return cidr; }
    public String getMasqueDecimal() { return masqueDecimal; }
    public int getCapacite() { return capacite; }
    public int getBesoinInitial() { return besoinInitial; }
    public String getPremiereAdresseUtilisable() { return premiereAdresseUtilisable; }
    public String getDerniereAdresseUtilisable() { return derniereAdresseUtilisable; }
    
    public int getMarge() {
        return capacite - besoinInitial;
    }
    
    public void afficher() {
        System.out.println(nomBesoin + " -> " + adresseReseau + "/" + cidr);
        System.out.println("   Masque : " + masqueDecimal);
        System.out.println("   Demandes : " + besoinInitial + " hotes");
        System.out.println("   Capacite : " + capacite + " hotes");
        System.out.println("   Marge : " + getMarge() + " hotes");
        System.out.println("   Plage : " + premiereAdresseUtilisable + " - " + derniereAdresseUtilisable);
    }
}