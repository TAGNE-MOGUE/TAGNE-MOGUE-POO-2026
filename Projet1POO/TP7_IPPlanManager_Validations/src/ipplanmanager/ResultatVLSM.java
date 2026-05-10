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

public class ResultatVLSM {
    private String nomBesoin;
    private String adresseReseau;
    private int cidr;
    private String masqueDecimal;
    private int capacite;
    private String premiereAdresseUtilisable;
    private String derniereAdresseUtilisable;
    
    public ResultatVLSM(String nomBesoin, String adresseReseau, int cidr, String masqueDecimal, int capacite) {
        this.nomBesoin = nomBesoin;
        this.adresseReseau = adresseReseau;
        this.cidr = cidr;
        this.masqueDecimal = masqueDecimal;
        this.capacite = capacite;
        this.premiereAdresseUtilisable = CalculateurReseau.calculerPremiereAdresseUtilisable(adresseReseau, cidr);
        this.derniereAdresseUtilisable = CalculateurReseau.calculerDerniereAdresseUtilisable(adresseReseau, cidr);
    }
    
    public String getNomBesoin() { return nomBesoin; }
    public String getAdresseReseau() { return adresseReseau; }
    public int getCidr() { return cidr; }
    public String getMasqueDecimal() { return masqueDecimal; }
    public int getCapacite() { return capacite; }
    public String getPremiereAdresseUtilisable() { return premiereAdresseUtilisable; }
    public String getDerniereAdresseUtilisable() { return derniereAdresseUtilisable; }
    
    public void afficher() {
        System.out.println(nomBesoin + " -> " + adresseReseau + "/" + cidr);
        System.out.println("   Masque : " + masqueDecimal);
        System.out.println("   Plage : " + premiereAdresseUtilisable + " - " + derniereAdresseUtilisable);
        System.out.println("   Capacite : " + capacite + " hotes");
    }
}    
