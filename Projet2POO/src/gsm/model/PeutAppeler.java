/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// ==========================
// PACKAGE : gsm.model
// FICHIER : PeutAppeler.java
// ==========================

package gsm.model;

public interface PeutAppeler {
    void appeler(MS destinataire, BTS source, BTS destination);
}
