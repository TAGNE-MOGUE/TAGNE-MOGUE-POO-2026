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

public class CalculateurReseau {
    
    // ===== CALCULS DE BASE =====
    public static int calculerNombreHotes(int cidr) {
        if (cidr < 0 || cidr > 32) return 0;
        int bitsHotes = 32 - cidr;
        return (bitsHotes == 0) ? 1 : (int) Math.pow(2, bitsHotes) - 2;
    }
    
    public static int calculerCidrPourHotes(int nombreHotes) {
        if (nombreHotes <= 0) return 32;
        for (int cidr = 32; cidr >= 0; cidr--) {
            if (calculerNombreHotes(cidr) >= nombreHotes) return cidr;
        }
        return 32;
    }
    
    public static int calculerTailleBloc(int cidr) {
        if (cidr < 0 || cidr > 32) return 0;
        return (int) Math.pow(2, 32 - cidr);
    }
    
    // ===== CONVERSION IP ↔ ENTIER =====
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
    
    // ===== VALIDATION ADRESSE IP =====
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
    
    public static void verifierAdresseIP(String ip) throws AdresseIPInvalideException {
        if (!estAdresseIPValide(ip)) {
            throw new AdresseIPInvalideException("Adresse IP invalide : " + ip);
        }
    }
    
    // ===== CALCUL DES BORNES RÉSEAU =====
    public static int calculerAdresseFin(String adresseReseau, int cidr) {
        int debut = convertirIpEnEntier(adresseReseau);
        int tailleBloc = calculerTailleBloc(cidr);
        return debut + tailleBloc - 1;
    }
    
    // ===== DÉTECTION CHEVAUCHEMENT =====
    public static boolean reseauxSeChevauchent(String adresse1, int cidr1, String adresse2, int cidr2) {
        int debut1 = convertirIpEnEntier(adresse1);
        int fin1 = calculerAdresseFin(adresse1, cidr1);
        int debut2 = convertirIpEnEntier(adresse2);
        int fin2 = calculerAdresseFin(adresse2, cidr2);
        return debut1 <= fin2 && debut2 <= fin1;
    }
    
    // ===== CLASSES RÉSEAU =====
    public static String obtenirClasseReseau(String adresseIP) {
        try {
            int premierOctet = Integer.parseInt(adresseIP.split("\\.")[0]);
            if (premierOctet >= 1 && premierOctet <= 126) return "Classe A";
            if (premierOctet >= 128 && premierOctet <= 191) return "Classe B";
            if (premierOctet >= 192 && premierOctet <= 223) return "Classe C";
            if (premierOctet >= 224 && premierOctet <= 239) return "Classe D (Multicast)";
            if (premierOctet >= 240 && premierOctet <= 255) return "Classe E (Reservee)";
            return "Classe inconnue";
        } catch (Exception e) {
            return "Adresse invalide";
        }
    }
    
    // ===== MASQUE DÉCIMAL =====
    public static String obtenirMasqueDecimal(int cidr) {
        if (cidr < 0 || cidr > 32) return "Masque invalide";
        int mask = 0xFFFFFFFF << (32 - cidr);
        return ((mask >> 24) & 0xFF) + "." +
               ((mask >> 16) & 0xFF) + "." +
               ((mask >> 8) & 0xFF) + "." +
               (mask & 0xFF);
    }
    
    // ===== ADRESSES PRIVÉES =====
    public static boolean estReseauPrive(String adresseIP) {
        try {
            int p1 = Integer.parseInt(adresseIP.split("\\.")[0]);
            int p2 = Integer.parseInt(adresseIP.split("\\.")[1]);
            if (p1 == 10) return true;
            if (p1 == 172 && p2 >= 16 && p2 <= 31) return true;
            if (p1 == 192 && p2 == 168) return true;
            return false;
        } catch (Exception e) {
            return false;
        }
    }
    
    // ===== ADRESSES UTILISABLES =====
    public static String calculerPremiereAdresseUtilisable(String adresseReseau, int cidr) {
        return convertirEntierEnIp(convertirIpEnEntier(adresseReseau) + 1);
    }
    
    public static String calculerDerniereAdresseUtilisable(String adresseReseau, int cidr) {
        return convertirEntierEnIp(convertirIpEnEntier(adresseReseau) + calculerTailleBloc(cidr) - 2);
    }
    
    public static String calculerBroadcast(String adresseReseau, int cidr) {
        return convertirEntierEnIp(convertirIpEnEntier(adresseReseau) + calculerTailleBloc(cidr) - 1);
    }
}