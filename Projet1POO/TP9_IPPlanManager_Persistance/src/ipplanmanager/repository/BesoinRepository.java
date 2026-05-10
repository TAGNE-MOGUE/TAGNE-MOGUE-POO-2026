/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipplanmanager.repository;

import ipplanmanager.model.BesoinReseau;
import java.io.*;
import java.util.ArrayList;

public class BesoinRepository {
    
    public ArrayList<BesoinReseau> chargerBesoins(String cheminFichier) throws IOException {
        ArrayList<BesoinReseau> besoins = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(cheminFichier));
        
        String ligne = reader.readLine(); // Ignorer l'en-tête
        while ((ligne = reader.readLine()) != null) {
            String[] colonnes = ligne.split(";");
            if (colonnes.length == 2) {
                String nom = colonnes[0];
                int hotes = Integer.parseInt(colonnes[1]);
                besoins.add(new BesoinReseau(nom, hotes));
            }
        }
        reader.close();
        return besoins;
    }
    
    // Travail supplémentaire
    public void sauvegarderBesoins(ArrayList<BesoinReseau> besoins, String cheminFichier) throws IOException {
        FileWriter writer = new FileWriter(cheminFichier);
        writer.write("Nom;Hotes\n");
        for (BesoinReseau besoin : besoins) {
            writer.write(besoin.getNom() + ";" + besoin.getNombreHotes() + "\n");
        }
        writer.close();
    }
}
