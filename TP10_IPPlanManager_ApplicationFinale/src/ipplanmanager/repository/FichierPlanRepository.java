/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipplanmanager.repository;

import ipplanmanager.model.Recommandation;
import ipplanmanager.model.ResultatVLSM;
import ipplanmanager.model.VLAN;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FichierPlanRepository {
    
    public void sauvegarderPlanCSV(ArrayList<ResultatVLSM> resultats, String cheminFichier) throws IOException {
        FileWriter writer = new FileWriter(cheminFichier);
        writer.write("Nom;AdresseReseau;CIDR;Masque;HotesDemandes;Capacite;Marge\n");
        for (ResultatVLSM resultat : resultats) {
            writer.write(resultat.getNomBesoin() + ";" +
                        resultat.getAdresseReseau() + ";" +
                        resultat.getCidr() + ";" +
                        resultat.getMasqueDecimal() + ";" +
                        resultat.getHotesDemandes() + ";" +
                        resultat.getCapacite() + ";" +
                        resultat.getMarge() + "\n");
        }
        writer.close();
    }
    
    public void sauvegarderVLANsCSV(ArrayList<VLAN> vlans, String cheminFichier) throws IOException {
        FileWriter writer = new FileWriter(cheminFichier);
        writer.write("ID;Nom;AdresseReseau;CIDR;Capacite\n");
        for (VLAN vlan : vlans) {
            if (vlan.getReseauAssocie() != null) {
                writer.write(vlan.getId() + ";" +
                            vlan.getNom() + ";" +
                            vlan.getReseauAssocie().getAdresseReseau() + ";" +
                            vlan.getReseauAssocie().getCidr() + ";" +
                            vlan.getCapacite() + "\n");
            }
        }
        writer.close();
    }
    
    public void sauvegarderRecommandations(ArrayList<Recommandation> recommandations, String cheminFichier) throws IOException {
        FileWriter writer = new FileWriter(cheminFichier);
        writer.write("===== RECOMMANDATIONS IPPLAN-MANAGER =====\n");
        if (recommandations.isEmpty()) {
            writer.write("Aucune recommandation particuliere.\n");
        } else {
            for (Recommandation rec : recommandations) {
                writer.write("[" + rec.getPriorite() + "] " + rec.getTitre() + " : " + rec.getMessage() + "\n");
            }
        }
        writer.close();
    }
}
