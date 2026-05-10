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
import java.util.ArrayList;

public class ValidateurPlanAdressage {
    
    public void verifierAdresses(ArrayList<ResultatVLSM> resultats) throws AdresseIPInvalideException {
        for (ResultatVLSM resultat : resultats) {
            CalculateurReseau.verifierAdresseIP(resultat.getAdresseReseau());
        }
    }
    
    public void verifierChevauchements(ArrayList<ResultatVLSM> resultats) throws ChevauchementReseauException {
        for (int i = 0; i < resultats.size(); i++) {
            ResultatVLSM r1 = resultats.get(i);
            for (int j = i + 1; j < resultats.size(); j++) {
                ResultatVLSM r2 = resultats.get(j);
                boolean conflit = CalculateurReseau.reseauxSeChevauchent(
                    r1.getAdresseReseau(), r1.getCidr(),
                    r2.getAdresseReseau(), r2.getCidr()
                );
                if (conflit) {
                    throw new ChevauchementReseauException(
                        "Chevauchement detecte entre " + r1.getNomBesoin() + 
                        " (" + r1.getAdresseReseau() + "/" + r1.getCidr() + ") et " +
                        r2.getNomBesoin() + " (" + r2.getAdresseReseau() + "/" + r2.getCidr() + ")"
                    );
                }
            }
        }
    }
    
    public void afficherValidationReussie() {
        System.out.println("✅ Validation terminee : aucun conflit critique detecte.");
    }
}