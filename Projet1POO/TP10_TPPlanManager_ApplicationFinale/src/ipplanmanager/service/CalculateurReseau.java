/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipplanmanager.service;

import ipplanmanager.exception.AdresseIPInvalideException;

public class CalculateurReseau {
    
    public static int calculerNombreHotes(int cidr) {
        if (cidr < 0 || cidr > 32) return 0;
        int bitsHotes = 32 - cidr;
        return (bitsHotes == 0) ? 1 : (int) Math.pow(2, bitsHotes) - 2;
    }
    
    public static int calculerCidrPourHotes(int nombreHotes) {
        for (int cidr = 32; cidr >= 0; cidr--) {
            if (calculerNombreHotes(cidr) >= nombreHotes) return cidr;
        }
        return 32;
    }
    
    public static int calculerTailleBloc(int cidr) {
        return (int) Math.pow(2, 32 - cidr);
    }
    
    public static int convertirIpEnEntier(String adresseIP) {
        try {
            String[] parties = adresseIP.split("\\.");
            int resultat = 0;
            for (int i = 0; i < 4; i++) {
                resultat = (resultat << 8) | Integer.parseInt(parties[i]);
            }
            return resultat;
        } catch (Exception e) {
            return 0;
        }
    }
    
    public static String convertirEntierEnIp(int ipEntier) {
        return ((ipEntier >> 24) & 0xFF) + "." +
               ((ipEntier >> 16) & 0xFF) + "." +
               ((ipEntier >> 8) & 0xFF) + "." +
               (ipEntier & 0xFF);
    }
    
    public static String obtenirMasqueDecimal(int cidr) {
        int mask = 0xFFFFFFFF << (32 - cidr);
        return ((mask >> 24) & 0xFF) + "." +
               ((mask >> 16) & 0xFF) + "." +
               ((mask >> 8) & 0xFF) + "." +
               (mask & 0xFF);
    }
    
    public static String calculerPremiereAdresseUtilisable(String adresseReseau, int cidr) {
        return convertirEntierEnIp(convertirIpEnEntier(adresseReseau) + 1);
    }
    
    public static String calculerDerniereAdresseUtilisable(String adresseReseau, int cidr) {
        return convertirEntierEnIp(convertirIpEnEntier(adresseReseau) + calculerTailleBloc(cidr) - 2);
    }
    
    public static boolean estAdresseIPValide(String ip) {
        if (ip == null || ip.isEmpty()) return false;
        String[] parties = ip.split("\\.");
        if (parties.length != 4) return false;
        for (String partie : parties) {
            try {
                int valeur = Integer.parseInt(partie);
                if (valeur < 0 || valeur > 255) return false;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean reseauxSeChevauchent(String adresse1, int cidr1, String adresse2, int cidr2) {
        int debut1 = convertirIpEnEntier(adresse1);
        int fin1 = debut1 + calculerTailleBloc(cidr1) - 1;
        int debut2 = convertirIpEnEntier(adresse2);
        int fin2 = debut2 + calculerTailleBloc(cidr2) - 1;
        return debut1 <= fin2 && debut2 <= fin1;
    }
    
    public static void verifierAdresseIP(String ip) throws AdresseIPInvalideException {
        if (!estAdresseIPValide(ip)) {
            throw new AdresseIPInvalideException("Adresse IP invalide : " + ip);
        }
    }
}
