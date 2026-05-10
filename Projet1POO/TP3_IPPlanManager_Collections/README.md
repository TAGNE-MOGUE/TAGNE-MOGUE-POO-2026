# TP3 - Collections et composition

## Objectif
Introduction des collections et des relations entre objets dans le projet IPPlan-Manager. Ce TP permet de passer d'une gestion individuelle des objets à une gestion structurée utilisant les collections Java.

## Notions étudiées

- Composition : Un objet peut contenir d'autres objets (ex: InfrastructureReseau contient 
  des Equipements et des SousReseaux)

- ArrayList : Collection dynamique permettant d'ajouter, supprimer et parcourir des objet

- Collections : Structures de données pour gérer des ensembles d'objets

- Parcours de listes : Utilisation de la boucle for-each

- Relations entre objets : Liens entre Equipement, InterfaceReseau, SousReseau et InfrastructureReseau


## Tests réalisés

### Tests de composition
- InfrastructureReseau contient 3 sous-réseaux (ADMIN, TECH, WIFI)
- InfrastructureReseau contient 8 équipements
- Chaque équipement possède plusieurs interfaces (routeur: 3 interfaces, switch: 3 
  interfaces, serveurWeb: 2 interfaces, etc.)

### Tests des collections
-  Ajout dynamique d'équipements avec `ajouterEquipement()`
-  Ajout dynamique de sous-réseaux avec `ajouterSousReseau()`
-  Parcours des collections avec boucle for-each
- Affichage complet de l'infrastructure avec `afficher()`

### Test de la méthode de recherche
- rechercherEquipement("ServeurWeb") → trouvé et affiché
- rechercherEquipement("SwitchCore") → trouvé et affiché
- rechercherEquipement("EquipementInexistant") → message "introuvable"

### Résultat de l'exécution
L'infrastructure affiche correctement :
- 3 sous-réseaux (ADMIN, TECH, WIFI)
- 8 équipements (RouteurPrincipal, SwitchCore, ServeurWeb, ServeurBDD, PosteAdmin1, PortableWiFi, ImprimanteReseau, AP_Principal)
- Chaque équipement affiche ses interfaces avec leurs adresses IP

### Resultat obtenu apres le run
Infrastructure : Infrastructure YFY
=========================================

=== SOUS-RESEAUX ===
Sous-reseau : ADMIN
Reseau : 192.168.1.0/24
Description : Reseau administration

Sous-reseau : TECH
Reseau : 192.168.2.0/24
Description : Reseau technique

Sous-reseau : WIFI
Reseau : 192.168.3.0/24
Description : Reseau WiFi


=== EQUIPEMENTS ===
Equipement : RouteurPrincipal
Type : Routeur
Nombre d'interfaces : 3
--- Interfaces ---
Interface : GigabitEthernet0/0 | Adresse IP : 192.168.1.1
Masque : 255.255.255.0
Interface : GigabitEthernet0/1 | Adresse IP : 192.168.2.1
Masque : 255.255.255.0
Interface : GigabitEthernet0/2 | Adresse IP : 192.168.3.1
Masque : 255.255.255.0

Equipement : SwitchCore
Type : Switch
Nombre d'interfaces : 3
--- Interfaces ---
Interface : FastEthernet0/1 | Adresse IP : 192.168.1.10
Masque : 255.255.255.0
Interface : FastEthernet0/2 | Adresse IP : 192.168.1.11
Masque : 255.255.255.0
Interface : FastEthernet0/24 | Adresse IP : 192.168.1.12
Masque : 255.255.255.0

Equipement : ServeurWeb
Type : Serveur
Nombre d'interfaces : 2
--- Interfaces ---
Interface : eth0 | Adresse IP : 192.168.1.100
Masque : 255.255.255.0
Interface : eth1 | Adresse IP : 192.168.1.101
Masque : 255.255.255.0

Equipement : ServeurBDD
Type : Serveur
Nombre d'interfaces : 2
--- Interfaces ---
Interface : eth0 | Adresse IP : 192.168.2.50
Masque : 255.255.255.0
Interface : eth1 | Adresse IP : 192.168.2.51
Masque : 255.255.255.0

Equipement : PosteAdmin1
Type : PC
Nombre d'interfaces : 1
--- Interfaces ---
Interface : eth0 | Adresse IP : 192.168.1.20
Masque : 255.255.255.0

Equipement : PortableWiFi
Type : Laptop
Nombre d'interfaces : 2
--- Interfaces ---
Interface : wlan0 | Adresse IP : 192.168.3.10
Masque : 255.255.255.0
Interface : eth0 | Adresse IP : 192.168.3.11
Masque : 255.255.255.0

Equipement : ImprimanteReseau
Type : Imprimante
Nombre d'interfaces : 1
--- Interfaces ---
Interface : eth0 | Adresse IP : 192.168.1.30
Masque : 255.255.255.0

Equipement : AP_Principal
Type : PointAcces
Nombre d'interfaces : 2
--- Interfaces ---
Interface : eth0 | Adresse IP : 192.168.3.2
Masque : 255.255.255.0
Interface : wlan0 | Adresse IP : 192.168.3.3
Masque : 255.255.255.0

=========================================

*** TEST DE RECHERCHE D'EQUIPEMENTS ***
----------------------------------------
=== Equipement trouve ===
Equipement : ServeurWeb
Type : Serveur
Nombre d'interfaces : 2
--- Interfaces ---
Interface : eth0 | Adresse IP : 192.168.1.100
Masque : 255.255.255.0
Interface : eth1 | Adresse IP : 192.168.1.101
Masque : 255.255.255.0

=== Equipement trouve ===
Equipement : SwitchCore
Type : Switch
Nombre d'interfaces : 3
--- Interfaces ---
Interface : FastEthernet0/1 | Adresse IP : 192.168.1.10
Masque : 255.255.255.0
Interface : FastEthernet0/2 | Adresse IP : 192.168.1.11
Masque : 255.255.255.0
Interface : FastEthernet0/24 | Adresse IP : 192.168.1.12
Masque : 255.255.255.0

Equipement "EquipementInexistant" introuvable dans l'infrastructure.
BUILD SUCCESSFUL (total time: 0 seconds)


## Difficultés rencontrées

1. Compréhension de la composition entre les classes

Au début du TP, il a été difficile de bien comprendre les relations entre les différentes 
classes (InfrastructureReseau, Equipement, InterfaceReseau et SousReseau). La notion de 
composition (une classe contenant d’autres objets) demandait une bonne visualisation de 
l’architecture globale du réseau.

2. Manipulation des collections (ArrayList)

L’utilisation des collections Java, notamment ArrayList, a nécessité une adaptation. 
Il fallait comprendre comment : initialiser correctement les listes dans les constructeurs,
ajouter des éléments avec add(), parcourir les listes avec la boucle for-each.
Au début, des erreurs comme NullPointerException apparaissaient lorsque les listes 
n’étaient pas bien initialisées.

3. Gestion des relations entre objets

Il a été difficile de bien gérer les liens entre les objets, notamment :
associer une AdresseIP à une InterfaceReseau,
relier plusieurs interfaces à un Equipement,
et connecter les équipements à une InfrastructureReseau.
Une mauvaise association entraînait des affichages incomplets ou incorrects.

4. Mise en place de la méthode de recherche

La méthode rechercherEquipement() a demandé une réflexion particulière. Il fallait :
parcourir toute la liste des équipements,
comparer les noms sans tenir compte de la casse (equalsIgnoreCase), et gérer le cas où 
l’équipement n’existe pas.
La difficulté principale était de bien gérer le cas "introuvable" avec un indicateur booléen.

5. Cohérence des noms et respect de la structure du professeur

Il a fallu faire attention à respecter exactement : les noms des classes, les packages 
(ipplanmanager), et la structure demandée.
Une petite erreur de nom (majuscule/minuscule ou orthographe) pouvait empêcher la compilation 
du projet.


## Réponses aux questions

 1. Qu'est-ce qu'une composition en Programmation Orientée Objet ?

La composition est un principe où une classe contient des instances d'autres classes 
comme attributs. C'est une relation "a-un" (has-a). Exemple : une InfrastructureReseau 
a des Equipements et des SousReseaux. La composition permet de construire des objets 
complexes à partir d'objets plus simples.

 2. Pourquoi utilise-t-on ArrayList dans ce TP ?

ArrayList est une collection dynamique qui permet d'ajouter ou supprimer des objets 
sans se soucier de la taille maximale. Contrairement à un tableau classique (taille fixe), 
ArrayList s'agrandit automatiquement. Elle offre aussi des méthodes pratiques comme `add()`, 
`remove()`, `size()`, et permet le parcours avec for-each.

3. Quelle différence existe entre une variable simple et une collection ?

- Variable simple : stocke une seule valeur ou une seule référence d'objet
- Collection (ArrayList) : stocke plusieurs références d'objets et fournit des 
méthodes pour les gérer collectivement (ajout, suppression, parcours, recherche)

4. Pourquoi un équipement possède-t-il plusieurs interfaces ?

Dans un réseau réel, un équipement (routeur, switch, serveur) peut être connecté à plusieurs 
réseaux différents. Par exemple, un routeur a besoin d'une interface par réseau auquel il 
est connecté. Cela permet de router le trafic entre différents sous-réseaux.

5. Pourquoi une infrastructure réseau contient-elle plusieurs sous-réseaux ?

Une entreprise découpe son réseau en sous-réseaux pour des raisons de :
- Sécurité : isoler différents services (administration, technique, invités)
- Performance : réduire la taille des domaines de broadcast
- Organisation : faciliter la gestion par département ou fonction
- Évolutivité : permettre l'ajout de nouveaux segments sans perturber l'existant

6. Quel est le rôle de la boucle for-each ?

La boucle for-each (ou enhanced for loop) permet de parcourir simplement tous les éléments
d'une collection sans utiliser d'indice. Syntaxe : `for (Type element : collection)`. Elle 
est plus lisible et évite les erreurs de dépassement d'indice.

7. Pourquoi la classe StringBuilder devient-elle importante dans le projet ?

StringBuilder est utile pour construire efficacement des chaînes de caractères longues 
(comme un affichage d'infrastructure complète) sans créer plusieurs objets String inter
médiaires. Elle améliore les performances lors de concaténations nombreuses.

8. Pourquoi les collections sont-elles indispensables dans les applications professionnelles ?

Les applications professionnelles manipulent des centaines ou milliers d'objets. Les 
collections offrent :
- Stockage dynamique : pas de limite fixe
- Recherche efficace : possibilité de trouver des éléments
- Parcours simplifiés : boucles, streams
- Interopérabilité : connexion avec bases de données
- Maintenabilité : code plus propre et plus organisé


