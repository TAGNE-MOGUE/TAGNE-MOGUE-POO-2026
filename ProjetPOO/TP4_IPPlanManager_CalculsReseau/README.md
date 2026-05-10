# TP4 - Calculs réseau automatiques

## Objectif
Introduction des calculs automatiques réseau dans IPPlan-Manager. Ce TP permet d'ajouter une classe utilitaire capable d'effectuer des calculs réseau essentiels : détermination de la classe réseau, conversion CIDR → masque décimal, calcul du nombre d'hôtes, détection des adresses privées.

## Notions étudiées

- Méthodes statiques : Fonctions appelables sans instanciation d'objet

- Calculs réseau : Formule 2^(32-CIDR) - 2 pour le nombre d'hôtes

- CIDR (Classless Inter-Domain Routing) : Notation /24, /16...

- Logique algorithmique : Détection de classe réseau, plages privées

- Classes utilitaires : Centralisation des fonctions techniques

- éparation des préoccupations : Logique métier vs logique de calcul


## Tests réalisés

### Test 1 : Calcul du nombre d'hôtes

Plusieurs CIDR ont été testés pour vérifier le calcul automatique du nombre maximal d’hôtes.
| CIDR | Formule  | Résultat attendu  | Résultat obtenu |
|------|--------- |------------------ |-----------------|
| /24  | 2^8 - 2  |  254 hôtes        |  254 hôtes      |
| /25  | 2^7 - 2  |  126 hôtes        |  126 hôtes      |
| /26  | 2^6 - 2  |  62 hôtes         |  62 hôtes       |
| /27  | 2^5 - 2  |  30 hôtes         |  30 hôtes       |
| /28  | 2^4 - 2  |  14 hôtes         |  14 hôtes       |
| /16  | 2^16 - 2 |  65534 hôtes      |  65534 hôtes    |
| /8   | 2^24 - 2 |  16777214 hôtes   |  16777214 hôtes |

### Test 2 : Détection de la classe réseau

Les classes réseau IPv4 ont été testées à partir du premier octet de l’adresse IP
| Adresse IP  | Classe attendue | Résultat obtenu |
|------------ |-----------------|-----------------|
| 192.168.1.0 |   Classe C      |   Classe C      |
| 172.16.0.0  |   Classe B      |   Classe B      |
| 10.0.0.0    |   Classe A      |   Classe A      |
| 8.8.8.8     |   Classe A      |   Classe A      |
| 224.0.0.1   |   Classe D      |   Classe D (Multicast) |

### Test 3 : Conversion CIDR → Masque décimal

Les masques générés automatiquement ont été vérifiés
| CIDR |  Masques obtenu  |
|------|----------------- |
| /24  |  255.255.255.0   |
| /25  |  255.255.255.128 |
| /16  |  255.255.0.0     |
| /8   |  255.0.0.0       |
| /26  |  255.255.255.192 |

### Test 4 : Détection des adresses privées

La méthode estReseauPrive() a été testée sur plusieurs adresses
| Adresse IP  | Résultat obtenu |
|------------ |-----------------|
| 192.168.1.0 |  PRIVEE         |
| 172.16.0.0  |  PRIVEE         |
| 8.8.8.8     |  PUBLIQUE       |
| 172.32.0.0  |  PUBLIQUE       |
| 127.0.0.1   |  PRIVEE         |

### Test 5: Affichage automatique des réseaux

Les informations suivantes ont été affichées automatiquement pour chaque réseau :
adresse réseau, CIDR, masque décimal, classe réseau, nombre d’hôtes, statut privé/public.

Tous les calculs et affichages étaient corrects
-  8 réseaux créés avec différents CIDR
-  8 sous-réseaux ajoutés à l'infrastructure
-  6 équipements créés avec multiples interfaces
-  Tous les calculs automatiques fonctionnels

## Difficultés rencontrées

1. Compréhension des méthodes statiques
La première difficulté rencontrée concernait l’utilisation des méthodes static. Il fallait comprendre 
qu’une méthode statique appartient à la classe elle-même et peut être appelée sans créer d’objet.

2. Calcul du nombre d’hôtes
Le calcul automatique du nombre maximal d’hôtes a demandé une bonne compréhension de la formule réseau :
La difficulté principale était de comprendre : le rôle du CIDR, la signification des bits d’hôtes,et 
pourquoi il faut retirer 2 adresses (réseau et broadcast).

3. Manipulation des adresses IP
Les adresses IP étant stockées sous forme de chaînes de caractères (String), il fallait : découper 
les octets,convertir les valeurs numériques,éviter les erreurs de format.

La méthode split("\\.") était particulièrement difficile à comprendre au début car le point est un caractère
spécial en expressions régulières.

4. Détection des réseaux privés
La méthode ReseauPrive() demandait plusieurs conditions logiques pour vérifier les plages privées :
10.x.x.x, 172.16.x.x à 172.31.x.x, 192.168.x.x
La plage 172.16 → 172.31 était la plus complexe à gérer.


5. Organisation des classes
Avec l’ajout de CalculateurReseau, il fallait bien séparer : la logique métier (ReseauIP, Equipement)
de la logique de calcul réseau.
Cette séparation était importante pour respecter les principes de la programmation orientée objet.


## Réponses aux questions

### 1. Pourquoi a-t-on créé une classe utilitaire ?

Une classe utilitaire regroupe des méthodes qui n'appartiennent naturellement à aucun 
objet métier spécifique. Les calculs réseau (nombre d'hôtes, masque, classe) sont des 
opérations techniques transversales. Les regrouper dans une classe dédiée permet :
- Centralisation : Une seule source de vérité pour les calculs
- Réutilisabilité : Toutes les classes peuvent utiliser ces méthodes
- Maintenabilité : Correction d'un calcul à un seul endroit
- Lisibilité : Le code métier reste concentré sur ses responsabilités

### 2. Quel est le rôle du mot-clé static ?

Le mot-clé `static` indique qu'une méthode ou variable appartient à la classe elle-même 
plutôt qu'à une instance. Avec `static` :
- On appelle la méthode via `Classe.methode()` sans créer d'objet
- La méthode ne peut pas accéder aux attributs non-static d'une instance
- C'est parfait pour les fonctions utilitaires qui n'ont pas d'état interne

### 3. Pourquoi les calculs réseau sont-ils importants dans un outil IPAM ?

Un outil IPAM (IP Address Management) doit automatiser les calculs réseau car :
- Évitement d'erreurs : Les calculs manuels sont source d'erreurs humaines
- Gain de temps : L'administrateur n'a pas à calculer chaque capacité
- Optimisation : Détection automatique des sous-réseaux surdimensionnés
- Planification : Vision rapide des capacités disponibles

### 4. Quelle est l'utilité du CIDR ?

Le CIDR (Classless Inter-Domain Routing) permet une allocation plus flexible des 
adresses IP que le système traditionnel des classes (A, B, C). Avec CIDR :
- Masque de longueur variable (VLSM)
- Réduction du gaspillage d'adresses
- Agrégation de routes (supernetting)
- Notation simple : `/24` au lieu de `255.255.255.0`

### 5. Pourquoi le nombre d'hôtes dépend-il du masque réseau ?
Le masque réseau détermine combien de bits sont réservés pour identifier le réseau. 
Les bits restants (bits d'hôtes) permettent d'adresser les hôtes. Formule :
- 32 bits = bits_réseau + bits_hôtes
- Nombre d'hôtes = 2^(bits_hôtes) - 2
- Le -2 soustrait l'adresse réseau et l'adresse de broadcast

### 6. Pourquoi certaines adresses IP sont-elles privées ?

Les adresses privées (RFC 1918) sont réservées pour les réseaux internes et ne sont pas 
routables sur Internet. Raisons :
- Préservation des IPv4 publiques : Limitation à 4,3 milliards d'adresses
- Sécurité : Isolation naturelle des réseaux internes
- Flexibilité : Réutilisation dans plusieurs organisations
- Économie : Pas besoin d'acheter des adresses publiques pour chaque machine interne

Plages privées :
- 10.0.0.0 à 10.255.255.255 (1 réseau /8)
- 172.16.0.0 à 172.31.255.255 (16 réseaux /16)
- 192.168.0.0 à 192.168.255.255 (256 réseaux /24)

### 7. Pourquoi la séparation entre logique métier et logique de calcul améliore-t-elle 
le projet ?

La séparation des préoccupations (Separation of Concerns) suit le principe de responsabilité
 unique (Single Responsibility Principle) de SOLID :
- Classe ReseauIP : Responsable de la représentation et gestion d'un réseau
- Classe CalculateurReseau : Responsable des calculs techniques
- Avantages : Code plus lisible, plus testable, plus facile à modifier sans effets de bord

### 8. Pourquoi les outils de planification réseau doivent-ils automatiser les calculs ?

L'automatisation des calculs dans les outils réseau est cruciale parce que : Les grands réseaux
ont des centaines de sous-réseaux, il est impossible à calculer manuellement à grande échelle, 
les calculs sont identiques pour tous les réseaux, la Possibilité de vérifier rapidement les 
configurations et les calculs alimentent d'autres fonctionnalités (alertes, réservations)

## Conclusion

Ce TP constitue une base importante pour la gestion automatisée des réseaux informatiques.