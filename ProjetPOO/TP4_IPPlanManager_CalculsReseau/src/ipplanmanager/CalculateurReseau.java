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
        return (int) Math.pow(2, bitsHotes) - 2;
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
        switch (cidr) {
            case 0: return "0.0.0.0";
            case 1: return "128.0.0.0";
            case 2: return "192.0.0.0";
            case 3: return "224.0.0.0";
            case 4: return "240.0.0.0";
            case 5: return "248.0.0.0";
            case 6: return "252.0.0.0";
            case 7: return "254.0.0.0";
            case 8: return "255.0.0.0";
            case 9: return "255.128.0.0";
            case 10: return "255.192.0.0";
            case 11: return "255.224.0.0";
            case 12: return "255.240.0.0";
            case 13: return "255.248.0.0";
            case 14: return "255.252.0.0";
            case 15: return "255.254.0.0";
            case 16: return "255.255.0.0";
            case 17: return "255.255.128.0";
            case 18: return "255.255.192.0";
            case 19: return "255.255.224.0";
            case 20: return "255.255.240.0";
            case 21: return "255.255.248.0";
            case 22: return "255.255.252.0";
            case 23: return "255.255.254.0";
            case 24: return "255.255.255.0";
            case 25: return "255.255.255.128";
            case 26: return "255.255.255.192";
            case 27: return "255.255.255.224";
            case 28: return "255.255.255.240";
            case 29: return "255.255.255.248";
            case 30: return "255.255.255.252";
            case 31: return "255.255.255.254";
            case 32: return "255.255.255.255";
            default: return "Masque non disponible pour /" + cidr;
        }
    }
    
    // TRAVAIL SUPPLÉMENTAIRE : Méthode pour vérifier si une adresse est privée
    public static boolean estReseauPrive(String adresseIP) {
        try {
            String[] parties = adresseIP.split("\\.");
            int premierOctet = Integer.parseInt(parties[0]);
            int deuxiemeOctet = Integer.parseInt(parties[1]);
            
            // Plage 10.0.0.0 à 10.255.255.255
            if (premierOctet == 10) {
                return true;
            }
            
            // Plage 172.16.0.0 à 172.31.255.255
            if (premierOctet == 172 && deuxiemeOctet >= 16 && deuxiemeOctet <= 31) {
                return true;
            }
            
            // Plage 192.168.0.0 à 192.168.255.255
            if (premierOctet == 192 && deuxiemeOctet == 168) {
                return true;
            }
            
            // Adresse de loopback (considérée comme spéciale)
            if (premierOctet == 127) {
                return true;
            }
            
            return false;
            
        } catch (Exception e) {
            return false;
        }
    }
    
    // Méthode supplémentaire : calculer l'adresse de broadcast
    public static String calculerBroadcast(String adresseReseau, int cidr) {
        try {
            String[] parties = adresseReseau.split("\\.");
            int bitsHotes = 32 - cidr;
            int nombreHotes = (int) Math.pow(2, bitsHotes);
            
            int[] ip = new int[4];
            for (int i = 0; i < 4; i++) {
                ip[i] = Integer.parseInt(parties[i]);
            }
            
            // Calcul simple pour les /24
            if (cidr == 24) {
                return ip[0] + "." + ip[1] + "." + ip[2] + "." + (nombreHotes - 1);
            } else if (cidr == 16) {
                return ip[0] + "." + ip[1] + "." + "255.255";
            } else if (cidr == 8) {
                return ip[0] + ".255.255.255";
            } else {
                return "Calcul non disponible pour ce CIDR";
            }
        } catch (Exception e) {
            return "Erreur de calcul";
        }
    }
}
