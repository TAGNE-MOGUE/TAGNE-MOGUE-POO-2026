# TP1 - IPPlan-Manager

## Objectif du TP

Ce TP permet de découvrir les premières classes Java du projet IPPlan-Manager.
Il pose les bases de la programmation orientée objet dans le contexte de l'ingénierie des réseaux.

## Classes créées

- AdresseIP : représente une adresse IPv4 sous forme de texte
- ReseauIP : représente un réseau IP avec adresse réseau, masque CIDR et description
- InterfaceReseau : interface réseau avec nom, adresse IP et état actif/inactif
- Equipement : équipement réseau avec nom, type et interface principale
- Main : classe de test contenant la méthode main

## Travail réalisé

Objets créés dans la classe Main :


 -R1_EDGE (Routeur), Interface eth0, Adresse 192.168.1.1, Etat active 
 -SRV_DNS (Serveur), Interface eth0, Adresse 192.168.1.10, Etat active 
 -PC_ADMIN (Poste client), Interface wlan0, Adresse 192.168.1.50, Etat inactive 
 -SW_CORE (Switch), Interface eth0, Adresse 192.168.1.2, Etat active 
 -AP_LABO (Point d'accès WiFi), Interface wlan0, Adresse 192.168.1.100, Etat active 
 -PC_INVITE (Poste client), Interface eth0, Adresse 192.168.1.51, Etat active 
 -TEST_NULL (Equipement test), Interface eth1, Adresse non configurée, Etat inactive 

Réseaux créés :
- 192.168.1.0/24 c'est le Réseau principal du laboratoire IRT
- 10.0.0.0/24 c'est le Réseau WiFi invité

Resultat du test
run:
===== IPPlan-Manager : TP1 =====
Découverte des premières classes du projet

----- Réseaux créés -----
Réseau : 192.168.1.0/24
Description : Réseau principal du laboratoire

Réseau : 192.168.2.0/24
Description : Réseau WiFi des étudiants

----- Équipements créés -----

Nom de l'équipement : R1_EDGE
Type d'équipement : Routeur
Interface : eth0
Adresse IP : 192.168.1.1
État : active

Nom de l'équipement : SRV_DNS
Type d'équipement : Serveur
Interface : eth0
Adresse IP : 192.168.1.10
État : active

Nom de l'équipement : PC_ADMIN
Type d'équipement : Poste client
Interface : wlan0
Adresse IP : 192.168.1.50
État : inactive

Nom de l'équipement : PC_SECRETARIAT
Type d'équipement : Poste client
Interface : wlan1
Adresse IP : 192.168.1.60
État : inactive

Nom de l'équipement : SW_CORE
Type d'équipement : Switch
Interface : FastEthernet0/1
Adresse IP : 192.168.1.2
État : active

Nom de l'équipement : AP_WIFI
Type d'équipement : Point d'accès WiFi
Interface : GigabitEthernet0/1
Adresse IP : 192.168.2.1
État : active

Nom de l'équipement : PC_TEST
Type d'équipement : Poste client
Interface : eth1
Adresse IP : non configurée
État : inactive
BUILD SUCCESSFUL (total time: 1 second)


## Réponses aux questions

### 1. Pourquoi une adresse IP a-t-elle été représentée par une classe au lieu d'une simple String ?

Une adresse IP n'est pas qu'une chaîne de caractères.
La classe permet d'ajouter des méthodes de validation, de calcul, et facilite l'évolution du code (ex: support IPv6).

### 2. Différence entre classe et objet ?

Une classe est un modèle ou un plan qui définit la structure (attributs) et les comportements (méthodes)
d'un concept. Un objet est une instance concrète de cette classe, créée en mémoire avec l'opérateur new. 
Par exemple, AdresseIP est la classe, tandis que new AdresseIP("192.168.1.1") est un objet.

### 3. Rôle du constructeur ?

Le constructeur est une méthode spéciale qui porte le même nom que la classe. Il est appelé automatiquement
lors de la création d'un objet avec new. Son rôle est d'initialiser les attributs de l'objet, c'est-à-dire 
de leur donner des valeurs cohérentes dès la naissance de l'objet.

### 4. Pourquoi InterfaceReseau contient-elle un objet AdresseIP ?
Car une interface réseau possède une adresse IP. L'objet AdresseIP apporte ses propres fonctionnalités.

### 5. Pourquoi Equipement contient-il un objet InterfaceReseau ?

Car un équipement communique via ses interfaces réseau.

### 6. Limite de la classe Equipement ?

La principale limite est qu'un équipement ne peut avoir qu'une seule interface réseau, alors que dans la 
réalité, un routeur possède plusieurs interfaces (WAN, LAN, etc.), un switch peut en avoir 24 ou 48. 


### 7. Pourquoi cette version est insuffisante pour un plan d'adressage automatique ?

Cette version ne contient que la structure de stockage des données (adresse, réseau, équipement). 
Pour produire automatiquement un plan d'adressage, il faudrait ajouter :
    -La logique de validation des adresses IP
    -Le calcul automatique des sous-réseaux à partir d'un CIDR
    -La détection des chevauchements entre réseaux
    -L'attribution automatique des adresses aux interfaces
    -La vérification de cohérence globale du plan d'adressage
    Actuellement, le programme se contente d'afficher des objets sans aucun calcul.

## Exécution

Le programme s'exécute sans erreur dans NetBeans. La console affiche tous les objets créés avec leurs 
caractéristiques.

## Dépôt Git

Lien du dépôt : https://github.com/TAGNE-MOGUE/TAGNE-MOGUE-POO-2026

## Conclusion

Ce TP1 a permis de maîtriser les bases de la POO en Java : création de classes, attributs, constructeurs, 
méthodes et instanciation d'objets dans le contexte d'une application de gestion de réseau.
