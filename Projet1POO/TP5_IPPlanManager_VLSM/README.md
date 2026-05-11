# TP5 - Moteur VLSM

## Objectif

Développer un moteur VLSM (Variable Length Subnet Mask) capable de proposer automatiquement un plan d'adressage à partir 
des besoins exprimés par l'utilisateur.

## Notions étudiées

- **VLSM** : Variable Length Subnet Mask - découpage en sous-réseaux de tailles variables
- **Tri de collections** : `Collections.sort()` avec un Comparator personnalisé
- **Classe de service métier** : `MoteurVLSM` qui applique la logique métier
- **Calcul CIDR** : Détermination automatique du masque adapté à un nombre d'hôtes
- **Conversion IP ↔ entier** : Facilite les calculs d'adresses
- **Génération automatique de sous-réseaux** : Allocation séquentielle sans chevauchement

## Scénarios testés

### Scénario 1 : Entreprise (Exemple du cours)

- **Adresse de départ** : 192.168.1.0
- **Besoins** :
  | Besoin | Hôtes | CIDR | Capacité | Plage |
  |--------|-------|------|----------|-------|
  | TECHNIQUE | 120 | /25 | 126 | 192.168.1.1 - 192.168.1.126 |
  | WIFI | 80 | /25 | 126 | 192.168.1.129 - 192.168.1.254 |
  | ADMINISTRATION | 50 | /26 | 62 | 192.168.2.1 - 192.168.2.62 |
  | SERVEURS | 20 | /27 | 30 | 192.168.2.65 - 192.168.2.94 |
  | DIRECTION | 10 | /28 | 14 | 192.168.2.97 - 192.168.2.110 |

### Scénario 2 : Petite Entreprise (Travail demandé)

- **Adresse de départ** : 10.0.0.0
- **Besoins** :
  | Besoin | Hôtes | CIDR | Capacité | Plage |
  |--------|-------|------|----------|-------|
  | WIFI_INVITES | 40 | /26 | 62 | 10.0.0.1 - 10.0.0.62 |
  | ADMIN | 25 | /27 | 30 | 10.0.0.65 - 10.0.0.94 |
  | COMPTABILITE | 12 | /28 | 14 | 10.0.0.97 - 10.0.0.110 |
  | SERVEURS | 8 | /28 | 14 | 10.0.0.113 - 10.0.0.126 |

### Scénario 3 : Campus Universitaire (Travail demandé)

- **Adresse de départ** : 172.16.0.0
- **Besoins** :
  | Besoin | Hôtes | CIDR | Capacité | Plage |
  |--------|-------|------|----------|-------|
  | ETUDIANTS | 500 | /23 | 510 | 172.16.0.1 - 172.16.1.254 |
  | WIFI_PUBLIC | 200 | /24 | 254 | 172.16.2.1 - 172.16.2.254 |
  | PERSONNEL | 120 | /25 | 126 | 172.16.3.1 - 172.16.3.126 |
  | LABORATOIRE | 60 | /26 | 62 | 172.16.3.129 - 172.16.3.190 |
  | ADMINISTRATION | 40 | /26 | 62 | 172.16.3.193 - 172.16.3.254 |

## Résultats obtenus

### Observations générales

-  Les besoins sont triés par ordre décroissant avant traitement
-  Chaque besoin reçoit le plus petit CIDR répondant à sa demande
-  Les adresses réseau s'enchaînent sans chevauchement
-  Les plages d'adresses utilisables sont correctement calculées



## Difficultés rencontrées

1. Calcul automatique du CIDR adapté

La principale difficulté a été d'implémenter la méthode calculerCidrPourHotes(). Il a fallu parcourir tous les CIDR possibles
 (de /32 à /0) pour trouver le premier dont la capacité est suffisante par rapport au nombre d'hôtes demandé. Par exemple,
 un besoin de 50 hôtes doit trouver /26 (62 hôtes) et non /27 (30 hôtes insuffisant).

2. Conversion entre adresse IP et entier

Pour calculer les plages d'adresses et les chevauchements, j'ai dû convertir les adresses IP (ex: 192.168.1.0) en entiers (3232235520). 
Les opérations bit à bit (décalages et masquages) ont été complexes à maîtriser au début.

3. Calcul de la taille des blocs

La formule tailleBloc = 2^(32 - CIDR) n'est pas intuitive. Par exemple, un /23 donne une taille de 512 adresses, 
ce qui n'est pas évident pour un débutant.

4. Tri des besoins par ordre décroissant

L'utilisation de Collections.sort() avec un Comparator personnalisé a été délicate. La ligne return b2.getNombreHotes() 
- b1.getNombreHotes() permet un tri du plus grand au plus petit, mais son fonctionnement n'est pas immédiat.

5.  Évitement des chevauchements

Il a fallu s'assurer que chaque nouveau sous-réseau commence là où le précédent se termine, en utilisant la formule adresseCourante
 = adresseCourante + tailleBloc.

## Réponses aux questions

### 1. Pourquoi le VLSM permet-il d'économiser les adresses IP ?

Le VLSM permet d'attribuer à chaque sous-réseau exactement la taille dont il a besoin, ni plus, ni moins. Sans VLSM, on utiliserait 
un seul masque pour tous les sous-réseaux, ce qui gaspillerait des adresses.

### 2. Pourquoi faut-il traiter les plus grands besoins en premier ?

Si on commence par les petits besoins, on risque de fragmenter l'espace adressage au point qu'il ne reste plus un bloc contigu assez 
grand pour les grands besoins.

### 3. Quelle est la différence entre un besoin réseau et un résultat VLSM ?

- **Besoin réseau** : Ce que l'utilisateur demande (ex: "J'ai besoin de 50 adresses IP")
- **Résultat VLSM** : Ce que le moteur propose (ex: "Utilisez 192.168.1.0/26")

### 4. Pourquoi la classe MoteurVLSM est-elle une classe de service métier ?

Car elle ne stocke pas de données mais applique des règles de planification réseau. C'est le "cerveau" qui transforme les besoins en 
solution technique.

### 5. Pourquoi transforme-t-on une adresse IP en entier pour certains calculs ?

Les adresses IP en notation décimale sont difficiles pour les calculs arithmétiques. Convertir en entier permet de faire des additions facilement.

### 6. Quel est le rôle de la méthode calculerCidrPourHotes() ?

Elle détermine le plus petit CIDR capable d'accueillir un nombre donné d'hôtes.

### 7. Pourquoi les adresses de réseau et de broadcast ne sont-elles pas attribuées aux machines ?

- **Adresse réseau** : Identifie le réseau lui-même (bits d'hôte à 0)
- **Adresse broadcast** : Permet d'envoyer un message à toutes les machines (bits d'hôte à 1)

### 8. Pourquoi le moteur VLSM représente-t-il une étape importante ?

C'est la première fonctionnalité véritablement intelligente d'IPPlan-Manager, automatisant une tâche complexe et sujette à erreurs.
