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
    
    public static int calculerNombreHotes(int cidr) {
        if (cidr < 0 || cidr > 32) {
            return 0;
        }
        int bitsHotes = 32 - cidr;
        if (bitsHotes == 0) {
            return 1;
        }
        return (int) Math.pow(2, bitsHotes) - 2;
    }
    
    public static int calculerCidrPourHotes(int nombreHotes) {
        if (nombreHotes <= 0) {
            return 32;
        }
        for (int cidr = 32; cidr >= 0; cidr--) {
            int capacite = calculerNombreHotes(cidr);
            if (capacite >= nombreHotes) {
                return cidr;
            }
        }
        return 32;
    }
    
    public static int calculerTailleBloc(int cidr) {
        if (cidr < 0 || cidr > 32) {
            return 0;
        }
        int bitsHotes = 32 - cidr;
        return (int) Math.pow(2, bitsHotes);
    }
    
    public static int convertirIpEnEntier(String adresseIP) {
        try {
            String[] parties = adresseIP.split("\\.");
            int resultat = 0;
            for (int i = 0; i < 4; i++) {
                int octet = Integer.parseInt(parties[i]);
                resultat = (resultat << 8) | octet;
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
    
    public static String obtenirClasseReseau(String adresseIP) {
        try {
            String[] parties = adresseIP.split("\\.");
            int premierOctet = Integer.parseInt(parties[0]);
            
            if (premierOctet >= 1 && premierOctet <= 126) {
                return "Classe A";
            } else if (premierOctet >= 128 && premierOctet <= 191) {
                return "Classe B";
            } else if (premierOctet >= 192 && premierOctet <= 223) {
                return "Classe C";
            } else if (premierOctet >= 224 && premierOctet <= 239) {
                return "Classe D (Multicast)";
            } else if (premierOctet >= 240 && premierOctet <= 255) {
                return "Classe E (Reservee)";
            } else {
                return "Classe inconnue";
            }
        } catch (Exception e) {
            return "Adresse invalide";
        }
    }
    
    public static String obtenirMasqueDecimal(int cidr) {
        if (cidr == 0) return "0.0.0.0";
        if (cidr == 1) return "128.0.0.0";
        if (cidr == 2) return "192.0.0.0";
        if (cidr == 3) return "224.0.0.0";
        if (cidr == 4) return "240.0.0.0";
        if (cidr == 5) return "248.0.0.0";
        if (cidr == 6) return "252.0.0.0";
        if (cidr == 7) return "254.0.0.0";
        if (cidr == 8) return "255.0.0.0";
        if (cidr == 9) return "255.128.0.0";
        if (cidr == 10) return "255.192.0.0";
        if (cidr == 11) return "255.224.0.0";
        if (cidr == 12) return "255.240.0.0";
        if (cidr == 13) return "255.248.0.0";
        if (cidr == 14) return "255.252.0.0";
        if (cidr == 15) return "255.254.0.0";
        if (cidr == 16) return "255.255.0.0";
        if (cidr == 17) return "255.255.128.0";
        if (cidr == 18) return "255.255.192.0";
        if (cidr == 19) return "255.255.224.0";
        if (cidr == 20) return "255.255.240.0";
        if (cidr == 21) return "255.255.248.0";
        if (cidr == 22) return "255.255.252.0";
        if (cidr == 23) return "255.255.254.0";
        if (cidr == 24) return "255.255.255.0";
        if (cidr == 25) return "255.255.255.128";
        if (cidr == 26) return "255.255.255.192";
        if (cidr == 27) return "255.255.255.224";
        if (cidr == 28) return "255.255.255.240";
        if (cidr == 29) return "255.255.255.248";
        if (cidr == 30) return "255.255.255.252";
        if (cidr == 31) return "255.255.255.254";
        if (cidr == 32) return "255.255.255.255";
        return "Masque non disponible";
    }
    
    public static boolean estReseauPrive(String adresseIP) {
        try {
            String[] parties = adresseIP.split("\\.");
            int premierOctet = Integer.parseInt(parties[0]);
            int deuxiemeOctet = Integer.parseInt(parties[1]);
            
            if (premierOctet == 10) return true;
            if (premierOctet == 172 && deuxiemeOctet >= 16 && deuxiemeOctet <= 31) return true;
            if (premierOctet == 192 && deuxiemeOctet == 168) return true;
            if (premierOctet == 127) return true;
            return false;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static String calculerPremiereAdresseUtilisable(String adresseReseau, int cidr) {
        try {
            int ipEntier = convertirIpEnEntier(adresseReseau);
            int premiereAdresse = ipEntier + 1;
            return convertirEntierEnIp(premiereAdresse);
        } catch (Exception e) {
            return "Invalide";
        }
    }
    
    public static String calculerDerniereAdresseUtilisable(String adresseReseau, int cidr) {
        try {
            int ipEntier = convertirIpEnEntier(adresseReseau);
            int tailleBloc = calculerTailleBloc(cidr);
            int derniereAdresse = ipEntier + tailleBloc - 2;
            return convertirEntierEnIp(derniereAdresse);
        } catch (Exception e) {
            return "Invalide";
        }
    }
    
    public static String calculerBroadcast(String adresseReseau, int cidr) {
        try {
            int ipEntier = convertirIpEnEntier(adresseReseau);
            int tailleBloc = calculerTailleBloc(cidr);
            int broadcast = ipEntier + tailleBloc - 1;
            return convertirEntierEnIp(broadcast);
        } catch (Exception e) {
            return "Invalide";
        }
    }
}