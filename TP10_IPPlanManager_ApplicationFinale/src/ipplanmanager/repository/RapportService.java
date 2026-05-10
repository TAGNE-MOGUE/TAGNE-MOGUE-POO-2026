/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipplanmanager.repository;

import ipplanmanager.model.BesoinReseau;
import ipplanmanager.model.Recommandation;
import ipplanmanager.model.ResultatVLSM;
import ipplanmanager.model.VLAN;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RapportService {
    
    public void genererRapportComplet(ArrayList<BesoinReseau> besoins,
                                       ArrayList<ResultatVLSM> resultats,
                                       ArrayList<VLAN> vlans,
                                       ArrayList<Recommandation> recommandations,
                                       String cheminFichier) throws IOException {
        FileWriter writer = new FileWriter(cheminFichier);
        
        writer.write("============================================================\n");
        writer.write("         RAPPORT TECHNIQUE IPPLAN-MANAGER\n");
        writer.write("============================================================\n\n");
        
        writer.write("1. BESOINS EXPRIMES PAR L'UTILISATEUR\n");
        writer.write("------------------------------------\n");
        for (BesoinReseau besoin : besoins) {
            writer.write("- " + besoin.getNom() + " : " + besoin.getNombreHotes() + " hotes\n");
        }
        
        writer.write("\n2. PLAN D'ADRESSAGE PROPOSE (VLSM)\n");
        writer.write("------------------------------------\n");
        for (ResultatVLSM resultat : resultats) {
            writer.write("- " + resultat.getNomBesoin() + " : " + 
                        resultat.getAdresseReseau() + "/" + resultat.getCidr() + 
                        " (Demande: " + resultat.getHotesDemandes() + 
                        ", Capacite: " + resultat.getCapacite() + 
                        ", Marge: " + resultat.getMarge() + ")\n");
        }
        
        writer.write("\n3. VLANS PROPOSES\n");
        writer.write("------------------\n");
        for (VLAN vlan : vlans) {
            writer.write("- VLAN " + vlan.getId() + " : " + vlan.getNom());
            if (vlan.getReseauAssocie() != null) {
                writer.write(" -> " + vlan.getReseauAssocie().getAdresseReseau() + 
                            "/" + vlan.getReseauAssocie().getCidr());
            }
            writer.write("\n");
        }
        
        writer.write("\n4. RECOMMANDATIONS TECHNIQUES\n");
        writer.write("-----------------------------\n");
        if (recommandations.isEmpty()) {
            writer.write("Aucune recommandation particuliere.\n");
        } else {
            for (Recommandation rec : recommandations) {
                writer.write("- [" + rec.getPriorite() + "] " + rec.getTitre() + "\n");
                writer.write("  " + rec.getMessage() + "\n");
            }
        }
        
        writer.write("\n5. RESUME DU PROJET\n");
        writer.write("--------------------\n");
        writer.write("- Nombre de besoins : " + besoins.size() + "\n");
        writer.write("- Nombre de VLANs crees : " + vlans.size() + "\n");
        writer.write("- Nombre de recommandations : " + recommandations.size() + "\n");
        
        writer.write("\n============================================================\n");
        writer.write("              FIN DU RAPPORT\n");
        writer.write("============================================================\n");
        
        writer.close();
    }
}
