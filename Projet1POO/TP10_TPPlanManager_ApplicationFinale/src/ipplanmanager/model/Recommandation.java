/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipplanmanager.model;

public class Recommandation {
    private String titre;
    private String priorite;
    private String message;
    
    public Recommandation(String titre, String priorite, String message) {
        setTitre(titre);
        setPriorite(priorite);
        setMessage(message);
    }
    
    public String getTitre() { return titre; }
    public String getPriorite() { return priorite; }
    public String getMessage() { return message; }
    
    public void setTitre(String titre) {
        this.titre = (titre == null || titre.isEmpty()) ? "Recommandation sans titre" : titre;
    }
    
    public void setPriorite(String priorite) {
        this.priorite = (priorite == null || priorite.isEmpty()) ? "NORMAL" : priorite;
    }
    
    public void setMessage(String message) {
        this.message = (message == null || message.isEmpty()) ? "Aucun detail fourni." : message;
    }
    
    public void afficher() {
        System.out.println("[" + priorite + "] " + titre + " : " + message);
    }
}
