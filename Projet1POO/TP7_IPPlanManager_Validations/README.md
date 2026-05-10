# TP7 - Validations avancées et détection des conflits

## Objectif

Ajouter des validations avancées pour détecter les incohérences dans un plan d'adressage réseau. Ce TP transforme IPPlan-Manager en un outil plus fiable, capable d'alerter l'utilisateur lorsqu'un plan d'adressage contient des erreurs.

## Notions étudiées

- Exceptions personnalisées : Création de classes d'exception spécifiques au domaine réseau
- try/catch : Gestion des erreurs sans interruption brutale du programme
- throw: Déclenchement volontaire d'exceptions
- Validation réseau : Vérification des adresses IP, chevauchements, conflits VLAN
-Détection de chevauchement : Comparaison des plages d'adresses
- Conflit VLAN : Empêcher deux VLANs avec le même ID
- Robustesse logicielle : Application qui résiste aux erreurs utilisateur

## Scénarios testés

### Scénario 1 : Plan d'adressage valide
- Adresse de départ: 192.168.1.0
- Besoins : ADMINISTRATION(50), TECHNIQUE(120), WIFI(80), SERVEURS(20)
- Résultat attendu: Génération du plan, validation réussie

| Besoin         | Adresse      | CIDR | Capacité | Plage |
|--------        |---------     |------|----------|-------|
| ADMINISTRATION | 192.168.1.0  | /26  | 62       | 192.168.1.1 - 192.168.1.62   |
| TECHNIQUE      | 192.168.1.64 | /25  | 126      | 192.168.1.65 - 192.168.1.190 |
| WIFI           | 192.168.1.192 | /25 | 126      | 192.168.1.193 - 192.168.2.62 |
| SERVEURS       | 192.168.2.64  | /27 | 30       | 192.168.2.65 - 192.168.2.94  |

### Scénario 2 : Adresse de départ invalide
- Adresse de départ : 192.168.300.0
- Besoins : TEST(10)
- Résultat attendu : Détection de l'adresse invalide

### Scénario 3 : Chevauchement de réseaux
- Réseau 1 : 192.168.1.0/25
- Réseau 2 : 192.168.1.64/26
-Résultat attendu : Détection du chevauchement

### Scénario 4 : Conflit VLAN
- VLANs créés : ID 10 (ADMINISTRATION), ID 20 (TECHNIQUE)
- VLAN en conflit: ID 10 (WIFI)
- Résultat attendu : Rejet du VLAN avec ID déjà utilisé

### Scénario 5 : Trois VLANs valides puis conflit

- VLANs valides : ID 100 (COMMERCIAL), ID 200 (COMPTABILITE), ID 300 (DIRECTION)
- VLAN en conflit : ID 100 (RH)
- Résultat attendu : Détection du conflit après 3 ajouts réussis


## Résultats obtenus

### Tests réussis

 -Plan VLSM généré correctement  
 -Validation des adresses IP fonctionnelle  
 -Détection des adresses invalides (192.168.300.0)  
 -Détection des chevauchements de réseaux  
 -Détection des conflits VLAN (ID déjà utilisé)  
- Application ne plante pas malgré les erreurs  


### sortie console apres excecution run

╔══════════════════════════════════╗
║ TP7 - VALIDATIONS AVANCEES ET DETECTION DES CONFLITS  ║
╚══════════════════════════════════╝


    TEST AVEC PLAN VALIDE
    ============================================================

📡 PLAN GENERE :
ADMINISTRATION -> 192.168.1.0/26
Capacite : 62 hotes
TECHNIQUE -> 192.168.1.64/25
Capacite : 126 hotes
WIFI -> 192.168.1.192/25
Capacite : 126 hotes
SERVEURS -> 192.168.2.64/27
Capacite : 30 hotes

✅ Validation terminee : aucun conflit critique detecte.

================================================================
2. TEST DE CONFLIT VLAN
================================================================
✅ VLAN 10 ajoute avec succes
✅ VLAN 20 ajoute avec succes
❌ Conflit VLAN : l'identifiant 10 est déjà utilisé par le VLAN ADMINISTRATION

================================================================
3. TEST AVEC ADRESSE DE DEPART INVALIDE
================================================================
✅ Erreur detectee : adresse de depart invalide (192.168.300.0)

================================================================
4. TEST DE CHEVAUCHEMENT RESEAU
================================================================
✅ Chevauchement detecte : Chevauchement entre Reseau1 (192.168.1.0/25) et Reseau2 (192.168.1.64/26)

================================================================
5. TEST TROIS VLANS VALIDES PUIS CONFLIT
================================================================
✅ VLAN 100 ajoute avec succes
✅ VLAN 200 ajoute avec succes
✅ VLAN 300 ajoute avec succes
✅ Conflit detecte : l'identifiant 100 est déjà utilisé


## Difficultés rencontrées

1. Duplication de méthodes : Plusieurs classes avaient des méthodes dupliquées (convertirEntierEnIp, 
ajouterVLAN, constructeurs). Solution : suppression des doublons et conservation d'une seule version.

2. Vérification de capacité trop stricte : La méthode `genererPlan` vérifiait si l'espace était suffisant, 
ce qui bloquait même les plans valides. Solution : suppression temporaire de cette vérification.

3. Chevauchement non détecté : La logique de détection de chevauchement nécessite que les adresses soient 
converties en entiers pour une comparaison correcte des plages.

4. Problèmes de cache NetBeans : NetBeans garde en mémoire les anciennes versions des classes. Solution : 
Clean and Build à chaque modification.

5.Constructeurs dupliqués : La classe VLAN avait deux constructeurs identiques. Solution : suppression du doublon.

## Réponses aux questions

### 1. Pourquoi les validations avancées sont-elles indispensables dans un outil IPAM ?

Les validations avancées sont cruciales car un outil IPAM (IP Address Management) est utilisé par des administrateur
réseau pour planifier des infrastructures critiques. Une erreur de planification (chevauchement, conflit VLAN, 
adresse invalide) peut entraîner des pannes réseau réelles, des conflits d'adressage, ou des problèmes de routage. 
L'outil doit donc détecter ces erreurs avant qu'elles ne soient appliquées.

### 2. Quelle est la différence entre une erreur simple et une exception en Java ?

- Erreur simple : Un bug ou une condition anormale qui n'est pas gérée par le programme 
(ex: NullPointerException non catchée). Elle fait généralement planter le programme.
- Exception : Une condition anormale que le programme peut anticiper et gérer (ex: 
`AdresseIPInvalideException`). Avec try/catch, le programme peut réagir élégamment sans planter.

### 3. Pourquoi crée-t-on des exceptions personnalisées ?

Les exceptions personnalisées permettent de :
- Clarifier l'erreur : `ChevauchementReseauException` est plus explicite que `Exception`
- Regrouper les erreurs par domaine : Toutes les erreurs réseau dans des exceptions spécifiques
- Améliorer la maintenance : Plus facile de trouver et modifier la gestion d'une erreur particulière
- Documentation : Le nom de l'exception documente le type d'erreur possible

 4. Quel est le rôle du bloc try/catch ?

Le bloc try/catch permet de :
- Tenter une opération risquée dans le bloc `try`
- Capturer l'exception si elle se produit dans le bloc `catch`
- Éviter que le programme ne plante brutalement
- Afficher un message d'erreur compréhensible pour l'utilisateur

5. Pourquoi deux VLANs ne doivent-ils pas avoir le même identifiant dans une même infrastructure ?

Dans la norme IEEE 802.1Q, chaque VLAN est identifié par un ID unique (1-4094). Deux VLANs avec 
le même ID sur la même infrastructure créent :
- Confusion : Impossible de savoir à quel VLAN appartient un équipement
-Erreurs de routage : Le trafic peut être mal dirigé
- Bugs de configuration : Les switchs et routeurs ne peuvent pas gérer deux VLANs avec le même ID

 6. Pourquoi deux sous-réseaux ne doivent-ils pas se chevaucher ?

Des sous-réseaux qui se chevauchent cause :
- Conflits d'adressage : Une même IP peut appartenir à deux réseaux différents
- Erreurs de routage : Les routeurs ne savent pas où envoyer le trafic
- Pannes réseau : Les équipements peuvent recevoir des configurations incohérentes
- Problèmes de broadcast** : Les domaines de broadcast ne sont plus isolés correctement

7. Pourquoi transforme-t-on les adresses IP en entiers pour comparer des plages réseau ?

Convertir une IP en entier (ex: 192.168.1.0 → 3232235520) permet de :
- Comparer numériquement : Plus simple de vérifier si une IP est entre deux bornes
- Calculer des plages : Adresse réseau + taille du bloc = fin de plage
- Vérifier l'appartenance : IP >= debut && IP <= fin
- Détecter les chevauchements : Deux plages se chevauchent si deb1 <= fin2 && deb2 <= fin1

 8. Pourquoi la classe ValidateurPlanAdressage doit-elle être séparée du moteur VLSM ?

La séparation respecte le principe de **responsabilité unique** (Single Responsibility Principle) :
- MoteurVLSM : Responsable de la GÉNÉRATION du plan (calcul des CIDR, allocation des adresses)
- ValidateurPlanAdressage : Responsable de la VALIDATION du plan (vérification des incohérences)

Avantages :
- Le code est plus lisible et maintenable
- On peut modifier la validation sans toucher à la génération
- On peut réutiliser le validateur avec d'autres sources de plans
- On peut tester chaque classe indépendamment

