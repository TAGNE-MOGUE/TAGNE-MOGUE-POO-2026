# TP8 - Moteur de recommandations intelligentes

## Objectif

Ajouter un moteur de recommandations capable d'analyser un plan VLAN et de proposer des conseils techniques 
basés sur les bonnes pratiques réseau.

## Notions étudiées

les differentes notions etudiees sont:
- **Interfaces Java** : Définition d'un contrat commun pour toutes les règles
- **Polymorphisme** : Manipulation d'objets de types différents via une interface commune
- **Règles métier** : Encapsulation de chaque recommandation dans une classe dédiée
- **Moteur de recommandations** : Application de plusieurs règles sur une collection d'objets
- **Séparation des responsabilités** : Isolation de la logique de recommandation
- **Extensibilité logicielle** : Facilité d'ajout de nouvelles règles

## Règles de recommandation implémentées

| Règle                         | Déclencheur            | Priorité | Message                                    |
|-------                        |-------------           |----------|---------                                   |
| `RecommandationWiFiInvite`    | Nom contient "WIFI"    | ÉLEVÉE   | Isoler le VLAN WiFi des réseaux internes   |
| `RecommandationServeurs`      | Nom contient "SERVEUR" | ÉLEVÉE   | Protéger par ACL et surveiller en priorité |
| `RecommandationGrandVLAN`     | Capacité > 200 hôtes   | MOYENNE  | Surveiller les broadcasts                  |
| `RecommandationAdministration`| Nom contient "ADMIN"   | HAUTE    | Accès réservé aux administrateurs          |
| `RecommandationMargeAdresse`  | Marge < 10 hôtes       | MOYENNE  | Prévoir une marge d'évolution              |

## Architecture du moteur
┌─────────────────────────────────────────────────────────────┐
│ MoteurRecommandation │
│ ┌─────────────────────────────────────────────────────────┐│
│ │ regles : ArrayList<RegleRecommandation> ││
│ └─────────────────────────────────────────────────────────┘│
│ │
│ + ajouterRegle(RegleRecommandation) │
│ + analyserVLANs(ArrayList<VLAN>) : ArrayList<Recommandation>│
│ + afficherRecommandations() │
└─────────────────────────────────────────────────────────────┘
│
│ utilise
▼
┌───────────────────────────────┐
│ Interface RegleRecommandation │
│ + analyser(VLAN) : Recommandation │
└───────────────────────────────┘
│
│ implémentée par
┌─────────────────────┼─────────────────────┐
│ │ │
▼ ▼ ▼
┌───────────────┐ ┌───────────────┐ ┌───────────────┐
│Recommandation │ │Recommandation │ │Recommandation │
│ WiFiInvite │ │ Serveurs │ │ GrandVLAN │
└───────────────┘ └───────────────┘ └───────────────┘
│ │ │
└─────────────────────┼─────────────────────┘
│
▼
┌───────────────────────────────┐
│ RecommandationAdministration │
│ RecommandationMargeAdresse │
└───────────────────────────────┘


## Scénarios testés

### Scénario 1 : Université (Exemple du cours)

- **Adresse de départ** : 10.10.0.0
- **Besoins** : 
  | Besoin      |Hôtes|
  |--------     |-----|
  | ETUDIANTS   | 500 |
  | WIFI_INVITES| 200 |
  | ENSEIGNANTS | 120 |
  | LABORATOIRES| 60  |
  | SERVEURS    | 30  |

**Recommandations générées :**

1. [ÉLEVÉE] Isolation du WiFi invite : Le VLAN WIFI_INVITES doit être isolé des VLANs internes sensibles.
2. [ÉLEVÉE] Protection du VLAN Serveurs : Le VLAN SERVEURS doit être protégé par des ACL.
3. [MOYENNE] VLAN de grande taille : Le VLAN ETUDIANTS possède une grande capacité (510 hôtes). Surveiller les broadcasts.
4. [MOYENNE] Marge d'adresses insuffisante : Le VLAN LABORATOIRES a une marge de seulement 2 adresses.

### Scénario 2 : PME (Travail demandé)

- **Adresse de départ** : 192.168.1.0
- **Besoins** :
  | Besoin         | Hôtes |
  |--------        |-------|
  | ADMINISTRATION | 50 |
  | COMPTABILITE   | 20 |
  | WIFI_INVITES   | 80 |
  | SERVEURS       | 15 |
  | VOIP           | 40 |

**Recommandations générées :**
1. [HAUTE] Restriction accès Administration : Le VLAN ADMINISTRATION doit être accessible uniquement aux 
administrateurs réseau.
2. [ÉLEVÉE] Isolation du WiFi invite : Le VLAN WIFI_INVITES doit être isolé des VLANs internes sensibles.
3. [ÉLEVÉE] Protection du VLAN Serveurs : Le VLAN SERVEURS doit être protégé par des ACL.

### Scénario 3 : Petite entreprise
- **Adresse de départ** : 10.0.0.0
- **Besoins** :
  | Besoin | Hôtes |
  |--------|-------|
  | COMMERCIAL | 30 |
  | COMPTABILITE | 15 |
  | RH | 10 |
  | DIRECTION | 8 |

**Recommandations générées :** Aucune (aucune condition déclenchée)

## Résultats obtenus

### Exemple de sortie console

VLAN ID : 10
Nom : ETUDIANTS
Description : VLAN ETUDIANTS
Reseau : 10.10.0.0/23
Plage : 10.10.0.1 - 10.10.1.254
Capacite : 510 hotes

VLAN ID : 20
Nom : WIFI_INVITES
Description : VLAN WIFI_INVITES
Reseau : 10.10.2.0/24
Plage : 10.10.2.1 - 10.10.2.254
Capacite : 254 hotes

VLAN ID : 30
Nom : ENSEIGNANTS
Description : VLAN ENSEIGNANTS
Reseau : 10.10.3.0/25
Plage : 10.10.3.1 - 10.10.3.126
Capacite : 126 hotes

VLAN ID : 40
Nom : LABORATOIRES
Description : VLAN LABORATOIRES
Reseau : 10.10.3.128/26
Plage : 10.10.3.129 - 10.10.3.190
Capacite : 62 hotes

VLAN ID : 50
Nom : SERVEURS
Description : VLAN SERVEURS
Reseau : 10.10.3.192/27
Plage : 10.10.3.193 - 10.10.3.222
Capacite : 30 hotes

### Résumé des recommandations par scénario

| Scénario   | Nb Recommandations | Types de recommandations |
|----------  |------------------- |------------------------- |
| Université | 4                  | WiFi, Serveurs, GrandVLAN, Marge |
| PME        | 3                  | Administration, WiFi, Serveurs   |
| Petite Entreprise | 0           | Aucune |


## Difficultés rencontrées

1. **Interface vs Classe abstraite** : le choix d'une interface car les règles n'ont pas d'état commun et n'héritent 
pas de comportement partagé.

2. **Détection insensible à la casse** : au depart je ne savais pas qu'il fallait utiliser `toUpperCase().contains()` pour détecter "WIFI"

3. **Calcul de la marge** : j'ai eu besoin de modifier ResultatVLSM pour conserver le nombre d'hôtes demandés initialement.

4. **Gestion des nulls** : il etait necessaire de vérifier que `reseauAssocie != null` avant d'accéder à la capacité.

5. **Extensibilité** : L'architecture par interface m'a permis d'ajouter facilement de nouvelles règles sans modifier 
le moteur.


## Réponses aux questions

### 1. Quel est le rôle d'un moteur de recommandations dans un outil IPAM ?

Il analyse le plan d'adressage généré et propose des conseils pour améliorer la sécurité, la performance et 
la maintenabilité du réseau. Il transforme un outil passif (qui calcule) en un assistant actif (qui conseille).


### 2. Pourquoi utilise-t-on une interface pour les règles de recommandation ?

Pour définir un contrat commun (méthode `analyser()`) que toutes les règles doivent respecter. Le moteur peut 
ainsi manipuler toutes les règles sans connaître leur type concret.


### 3. Quelle est la différence entre une classe concrète et une interface ?

- **Classe concrète** : Implémentation complète, peut avoir des attributs et des méthodes avec du code exécutable.
- **Interface** : Définit uniquement un contrat (signatures de méthodes), pas d'implémentation. Une classe peut 
implémenter plusieurs interfaces.


### 4. Pourquoi la méthode analyser() peut-elle retourner null ?

Car une règle peut ne pas s'appliquer à un VLAN donné. Exemple : un VLAN qui ne contient pas "WIFI" ne doit 
pas déclencher la recommandation WiFi. Retourner null signifie "pas de recommandation".


### 5. Pourquoi le moteur de recommandations illustre-t-il le polymorphisme ?
Car la méthode `analyserVLANs()` appelle `regle.analyser(vlan)` sur une collection d'objets de type 
`RegleRecommandation`. Chaque objet (WiFiInvite, Serveurs, GrandVLAN) exécute sa propre version de `analyser()`.


### 6. Pourquoi est-il préférable de créer une classe par règle ?

Pour respecter le **principe de responsabilité unique** : une classe = une règle. Cela facilite l'ajout, 
la suppression et la modification des règles sans impacter les autres. C'est aussi plus lisible.


### 7. Pourquoi un VLAN WiFi invité doit-il être isolé des réseaux internes ?

Pour des raisons de **sécurité** : les utilisateurs du WiFi invité ne sont pas forcément de confiance. 
Ils ne doivent pas pouvoir accéder aux ressources sensibles de l'entreprise (serveurs, postes administratifs). 
L'isolation empêche les attaques ou les accès non autorisés.


### 8. Pourquoi les VLANs de grande taille doivent-ils être surveillés ?

Les VLANs de grande taille (>200 hôtes) peuvent générer beaucoup de **broadcasts** et de trafic multicast, 
ce qui peut :
- Dégrader les performances du réseau
- Augmenter la surface d'attaque (compromission d'un hôte affecte beaucoup de machines)
- Rendre la supervision plus complexe

