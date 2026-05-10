/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipplanmanager.service;

import ipplanmanager.model.Recommandation;
import ipplanmanager.model.VLAN;

public class RecommandationWiFiInvite implements RegleRecommandation {
    @Override
    public Recommandation analyser(VLAN vlan) {
        if (vlan.getNom().toUpperCase().contains("WIFI")) {
            return new Recommandation(
                "Isolation du WiFi invite",
                "ELEVEE",
                "Le VLAN " + vlan.getNom() + " doit etre isole des VLANs internes sensibles."
            );
        }
        return null;
    }
}