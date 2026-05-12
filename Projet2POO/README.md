# PROJET2POO - Simulation d’un Réseau GSM

## Étudiant
**Nom : TAGNE MOGUE STEVINE LAURA**  
Filière : BTS  
Cours : Programmation Orientée Objet (POO)

---

# Description du projet

Ce projet consiste à simuler le fonctionnement simplifié d’un réseau GSM en Java orienté objet.

L’application permet de :

- créer plusieurs BTS
- créer des utilisateurs mobiles
- gérer des smartphones et tablettes
- effectuer des appels entre utilisateurs
- rechercher un utilisateur dans le réseau
- afficher les statistiques du réseau
- tester la saturation d’une cellule BTS
- gérer les exceptions personnalisées

Le projet met en pratique plusieurs notions importantes de la programmation orientée objet :

- encapsulation
- héritage
- polymorphisme
- collections Java
- exceptions personnalisées
- organisation en packages

---

# Structure du projet

```text
PROJET2POO/
│
├── src/
│   └── gsm/
│       ├── model/
│       │   ├── Abonne.java
│       │   ├── Smartphone.java
│       │   ├── Tablet.java
│       │   ├── BTS.java
│       │   └── Reseau.java
│       │
│       ├── exception/
│       │   └── CelluleSatureeException.java
│       │
│       └── test/
│           └── TestReseau.java
│
├── README.md
├── Rapport_Projet_GSM_TAGNE_MOGUE_LAURA.pdf
└── captures/

Technologies utilisées
Java
NetBeans IDE
Git & GitHub
Programmation Orientée Objet
Fonctionnalités réalisées
1. Gestion des BTS

Le programme permet de :

créer plusieurs BTS
ajouter des utilisateurs dans les BTS
afficher les informations des BTS
supprimer une BTS
2. Gestion des abonnés

Deux types d’utilisateurs sont gérés :

Smartphone
Android / iOS
version système
stockage
Tablet
taille écran
support stylet
3. Gestion des appels

Les utilisateurs peuvent :

appeler un autre utilisateur
recevoir des appels
consulter l’historique des appels
4. Gestion des exceptions

Le projet gère la saturation d’une cellule BTS grâce à une exception personnalisée :

CelluleSatureeException
5. Recherche d’utilisateur

Le réseau peut rechercher la localisation d’un utilisateur à partir de son numéro MSISDN.

Exemple d’exécution
TAGNE appelle le numero : 692345678
Appel etabli avec succes
Captures d’écran
Capture 1 : Exécution complète du projet

➡ INSÉRER ICI LA CAPTURE DE L’EXÉCUTION COMPLÈTE

Capture 2 : Affichage des BTS

➡ INSÉRER ICI LA CAPTURE DES BTS

Capture 3 : Affichage des Smartphones et Tablettes

➡ INSÉRER ICI LA CAPTURE DES UTILISATEURS

Capture 4 : Test des appels

➡ INSÉRER ICI LA CAPTURE DES APPELS

Capture 5 : Test saturation cellule

➡ INSÉRER ICI LA CAPTURE DE L’EXCEPTION

Capture 6 : BUILD SUCCESSFUL

➡ INSÉRER ICI LA CAPTURE BUILD SUCCESSFUL

Explication des principales classes
Classe Abonne

Classe mère représentant un utilisateur du réseau GSM.

Classe Smartphone

Classe héritant de Abonne représentant un smartphone.

Classe Tablet

Classe héritant de Abonne représentant une tablette.

Classe BTS

Classe représentant une station de base GSM.

Classe Reseau

Classe représentant l’ensemble du réseau GSM.

Classe CelluleSatureeException

Exception levée lorsqu’une BTS atteint sa capacité maximale.

Difficultés rencontrées
1. Gestion des packages

L’organisation correcte des packages :

gsm.model
gsm.exception
gsm.test

était importante pour éviter les erreurs d’importation.

2. Gestion des collections

L’utilisation des ArrayList nécessitait une bonne maîtrise des boucles et des ajouts dynamiques.

3. Gestion des exceptions

La gestion de la saturation d’une cellule BTS demandait la création d’une exception personnalisée.

4. Héritage et polymorphisme

Il fallait bien différencier les comportements des Smartphones et des Tablettes tout en conservant une structure commune.

Conclusion

Ce projet m’a permis de comprendre concrètement les principes fondamentaux de la programmation orientée objet appliqués aux réseaux GSM.

J’ai appris à :

structurer une application Java
utiliser l’héritage
gérer les exceptions
manipuler des collections
organiser un projet professionnel avec GitHub

Ce projet représente une simulation réaliste simplifiée d’un réseau mobile GSM
