# TP6 - VLAN et segmentation logique

## Objectif

Mettre en place la gestion des VLANs dans IPPlan-Manager et associer automatiquement les sous-réseaux générés par le moteur VLSM à des VLANs logiques. Ce TP introduit la notion de segmentation logique des réseaux.

## Notions étudiées

- Segmentation logique : Création de réseaux logiques indépendants sur une même infrastructure physique
- VLAN (Virtual Local Area Network) : Isolateurs de broadcast logiques
- Gestionnaires métier : Classes dédiées à la gestion de collections d'objets
- Collections : ArrayList pour stocker et manipuler les VLANs
- Associations entre objets : Relation VLAN ↔ ResultatVLSM
- Architecture métier : Séparation claire des responsabilités

## Scénarios testés

### Scénario 1 : Entreprise (Exemple du cours)
- Réseau de départ : 192.168.1.0
- Besoins : TECHNIQUE(120), WIFI(80), ADMINISTRATION(50), SERVEURS(20)

| VLAN ID | Nom            | Sous-réseau     | Capacité  |
|---------|-----           |-------------    |---------- |
| 10      | TECHNIQUE      | 192.168.1.0/25  | 126 hôtes |
| 20      | WIFI           | 192.168.1.128/25| 126 hôtes |
| 30      | ADMINISTRATION | 192.168.2.0/26  | 62 hôtes  |
| 40      | SERVEURS       | 192.168.2.64/27 | 30 hôtes  |

### Scénario 2 : Université (Travail demandé)
- Réseau de départ : 172.16.0.0
- Besoins : ETUDIANTS(500), ENSEIGNANTS(120), LABORATOIRES(60), WIFI_PUBLIC(200), SERVEURS(30)

| VLAN ID | Nom          | Sous-réseau   | Capacité   |
|---------|-----         |-------------  |----------  |
| 100     | ETUDIANTS    | 172.16.0.0/23 | 510 hôtes  |
| 110     | WIFI_PUBLIC  | 172.16.2.0/24 | 254 hôtes  |
| 120     | ENSEIGNANTS  | 172.16.3.0/25 | 126 hôtes  |
| 130     | LABORATOIRES | 172.16.3.128/26 | 62 hôtes |
| 140     | SERVEURS     | 172.16.3.192/27 | 30 hôtes |

### Scénario 3 : Petite Entreprise
- **Réseau de départ** : 10.0.0.0
- **Besoins** : COMMERCIAL(30), COMPTABILITE(15), RH(10), DIRECTION(8), ACCUEIL(5)

| VLAN ID | Nom          | Sous-réseau | Capacité |
|---------|-----         |-------------|----------|
| 200     | COMMERCIAL   | 10.0.0.0/27 | 30 hôtes |
| 210     | COMPTABILITE | 10.0.0.32/28| 14 hôtes |
| 220     | RH           | 10.0.0.48/28| 14 hôtes |
| 230     | DIRECTION    | 10.0.0.64/28| 14 hôtes |
| 240     | ACCUEIL      | 10.0.0.80/29| 6 hôtes  |

## Résultats obtenus

### Observations générales

-  Les VLANs sont créés automatiquement à partir des résultats VLSM
-  Chaque VLAN possède un ID unique entre 1 et 4094
-  L'association VLAN ↔ sous-réseau est automatique
-  La recherche de VLAN par ID fonctionne
-  Les statistiques (nombre total, plus grande capacité) sont calculées

### VLANs critiques détectés (>100 hôtes)

**Scénario 1 (Entreprise)** :
- VLAN 10 - TECHNIQUE - 126 hôtes 
- VLAN 20 - WIFI - 126 hôtes 

**Scénario 2 (Université)** :
- VLAN 100 - ETUDIANTS - 510 hôtes 
- VLAN 110 - WIFI_PUBLIC - 254 hôtes 
- VLAN 120 - ENSEIGNANTS - 126 hôtes 

**Scénario 3 (Petite Entreprise)** : Aucun VLAN critique

### Résultats des tests de recherche
-  Recherche VLAN ID 20 → trouvé (WIFI)
-  Recherche VLAN ID 999 → non trouvé

  ### RÉSUMÉ DES TESTS
1. Scenario 1 (Entreprise) : 4 VLANs crees
2. Scenario 2 (Universite) : 5 VLANs crees
3. Scenario 3 (Petite Entreprise) : 5 VLANs crees

## Difficultés rencontrées

1. Validation des IDs VLAN** : Les IDs doivent être compris entre 1 et 4094 (norme IEEE 802.1Q). 
J'ai implémenté une validation dans `setId()`.

2. Association VLAN ↔ ResultatVLSM : La classe VLAN doit stocker directement l'objet ResultatVLSM 
pour conserver toutes les informations réseau.

3. Calcul des VLANs critiques : Pour déterminer si un VLAN est critique (>100 hôtes), j'utilise la 
capacité stockée dans ResultatVLSM.

4. Séparation des responsabilités : Le GestionnaireVLAN s'occupe uniquement de la gestion des VLANs, 
pas de la logique VLSM.

5. Affichage formaté : J'ai ajouté des bordures pour améliorer la lisibilité des résultats.

## Réponses aux questions

 1. Pourquoi les VLANs sont-ils importants dans les réseaux modernes ?

Les VLANs sont essentiels car ils permettent de :
- Réduire les domaines de broadcast : Moins de trafic inutile sur le réseau
- Améliorer la sécurité : Isolation des flux entre départements
- Simplifier l'administration : Regroupement logique indépendant de la topologie physique
- Optimiser les performances : Réduction des collisions et du broadcast
- Flexibilité : Reconfiguration logique sans changer le câblage

 2. Pourquoi un VLAN est-il souvent associé à un sous-réseau spécifique ?

L'association VLAN → sous-réseau est une bonne pratique car :
- Simplification du routage : Un sous-réseau = un VLAN = une route
- Clarté de gestion : Association logique compréhensible
- Isolation : Les broadcasts restent dans le VLAN et le sous-réseau
- Sécurité : Les ACLs peuvent être appliquées par VLAN

 3. Pourquoi la séparation logique améliore-t-elle la sécurité ?

La séparation logique (VLANs) améliore la sécurité car :
- Isolation des flux : Trafic d'un VLAN inaccessible aux autres par défaut
- Contrôle d'accès : Possibilité d'appliquer des politiques par VLAN
- Réduction de l'attaque : Un compromis dans un VLAN n'affecte pas les autres
- Segmentation des risques : Les données sensibles dans des VLANs isolés

 4. Quel est le rôle de la classe GestionnaireVLAN ?

La classe GestionnaireVLAN est responsable de :
- Stockage : Maintient une collection de tous les VLANs
- Ajout : Permet d'ajouter de nouveaux VLANs
- Recherche : Trouve un VLAN par son ID
- Statistiques : Calcule le nombre total de VLANs
- Filtrage : Identifie les VLANs critiques (>100 hôtes)

 5. Pourquoi la classe VLAN contient-elle un objet ResultatVLSM ?

La classe VLAN contient un objet ResultatVLSM car :
- Relation directe : Un VLAN est associé à exactement un sous-réseau
- Évitement de redondance : Pas besoin de stocker adresse, CIDR, etc. séparément
- Cohérence : Toutes les informations réseau sont accessibles via l'objet associé
- Réutilisabilité : Les méthodes d'affichage de ResultatVLSM sont directement utilisables

 6. Pourquoi utilise-t-on encore ArrayList dans ce TP ?

ArrayList reste la collection la plus adaptée car :
- Accès par index : Rapide pour les recherches par position
- Parcours simple : Boucle for-each naturelle
- Taille dynamique : S'adapte au nombre de VLANs créés
- Recherche simple : Parcours linéaire pour trouver par ID

 7. Pourquoi les responsabilités des classes doivent-elles être séparées ?

La séparation des responsabilités (principe SOLID) est fondamentale car :
- Maintenabilité : Changer un comportement n'affecte qu'une classe
- Testabilité : Chaque classe peut être testée indépendamment
- Lisibilité : Le code est plus compréhensible
- Réutilisabilité : Les classes peuvent être utilisées dans d'autres contextes

 8. Pourquoi le projet commence-t-il maintenant à ressembler à une véritable application professionnelle ?

Le projet devient professionnel car il intègre maintenant :
- Architecture en couches : Objets métier, services, gestionnaires
- Automatisation complète : Des besoins aux VLANs opérationnels
- Respect des standards : IDs VLAN conformes IEEE 802.1Q
- Fonctionnalités utiles : Recherche, statistiques, détection de VLANs critiques
- Extensibilité : Facilité d'ajout de nouvelles fonctionnalités

