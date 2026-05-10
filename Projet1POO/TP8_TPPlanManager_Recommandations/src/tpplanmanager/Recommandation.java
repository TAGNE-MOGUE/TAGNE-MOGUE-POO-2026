/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpplanmanager;

/**
 *
 * @author GENERAL STORES
 */
public class Recommandation {
    private String titre;
    private String priorite;
    private String message;
    
    public Recommandation(String titre, String priorite, String message) {
        setTitre(titre);
        setPriorite(priorite);
        setMessage(message);
    }
    
    public String getTitre() {
        return titre;
    }
    
    public void setTitre(String titre) {
        if (titre == null || titre.isEmpty()) {
            this.titre = "Recommandation sans titre";
        } else {
            this.titre = titre;
        }
    }
    
    public String getPriorite() {
        return priorite;
    }
    
    public void setPriorite(String priorite) {
        if (priorite == null || priorite.isEmpty()) {
            this.priorite = "NORMAL";
        } else {
            this.priorite = priorite;
        }
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        if (message == null || message.isEmpty()) {
            this.message = "Aucun detail fourni.";
        } else {
            this.message = message;
        }
    }
    
    public void afficher() {
        System.out.println("[" + priorite + "] " + titre + " : " + message);
    }
} 
