# TP10 - Application finale IPPlan-Manager

## Objectif
Assembler toutes les fonctionnalités développées dans les TPs précédents afin de produire une 
application console complète de planification d'adressage IP.

## Fonctionnalités réalisées

les differentes fonctionalites que j'ai realise sont :
- Saisie utilisateur interactive
- Génération VLSM automatique
- Création automatique de VLANs
- Validation du plan (adresses, chevauchements)
- Moteur de recommandations techniques
- Sauvegarde CSV (plan, VLANs, recommandations)
- Génération de rapport technique détaillé
- Chargement des besoins depuis fichier CSV

## Organisation du projet

| Package | Rôle |
|---------|------|
| `ipplanmanager.model` | Classes métier (BesoinReseau, ResultatVLSM, VLAN, Recommandation) |
| `ipplanmanager.service` | Logique métier (CalculateurReseau, MoteurVLSM, GestionnaireVLAN, Validateur, MoteurRecommandation) |
| `ipplanmanager.repository` | Accès aux fichiers (lecture/écriture CSV) |
| `ipplanmanager.exception` | Exceptions personnalisées |
| `ipplanmanager.console` | Interaction utilisateur |
| `ipplanmanager.main` | Point d'entrée de l'application |

## Scénarios testés

### Scénario 1 : Campus IRT

| Besoin | Hôtes | Adresse | CIDR | Marge |
|--------|-------|---------|------|-------|
| ETUDIANTS | 500 | 10.10.0.0 | /23 | 10 |
| WIFI_INVITES | 200 | 10.10.2.0 | /24 | 54 |
| ENSEIGNANTS | 120 | 10.10.3.0 | /25 | 6 |
| LABORATOIRES | 60 | 10.10.3.128 | /26 | 2 |
| SERVEURS | 30 | 10.10.3.192 | /27 | 0 |

### Scénario 2 : PME

| Besoin | Hôtes | Adresse | CIDR | Marge |
|--------|-------|---------|------|-------|
| ADMINISTRATION | 50 | 192.168.1.0 | /26 | 12 |
| COMPTABILITE | 20 | 192.168.1.64 | /27 | 2 |
| WIFI_INVITES | 80 | 192.168.1.96 | /25 | 46 |
| SERVEURS | 15 | 192.168.1.160 | /27 | 2 |
| VOIP | 40 | 192.168.1.192 | /26 | 22 |

### Scénario 3 : Entreprise multi-services

| Besoin | Hôtes | Adresse | CIDR | Marge |
|--------|-------|---------|------|-------|
| TECHNIQUE | 120 | 10.0.0.0 | /25 | 6 |
| DIRECTION | 25 | 10.0.0.128 | /27 | 2 |
| CAMERAS | 60 | 10.0.0.160 | /26 | 2 |
| SUPPORT | 35 | 10.0.0.224 | /27 | 2 |
| INVITES | 100 | 10.0.1.0 | /25 | 26 |

## Fichiers générés dans exports

- `{nom_projet}_plan.csv` - Plan d'adressage VLSM
- `{nom_projet}_vlans.csv` - Liste des VLANs
- `{nom_projet}_recommandations.txt` - Recommandations techniques
- `{nom_projet}_rapport.txt` - Rapport technique complet

## Difficultés rencontrées

les difficultes que j'ai rencontre concernaient :
1. La gestion des imports entre packages
2. La coordination des exceptions entre services
3. La validation des adresses IP avant génération
4. l' Organisation des packages selon les bonnes pratiques

## Réponses aux questions

### 1. Pourquoi le TP10 représente-t-il une application plus complète ?

le TP10 représente une application plus complète car il intègre toutes les fonctionnalités des TPs 1 à 9 : 
saisie utilisateur, calculs, VLSM, VLANs, validation, recommandations, persistance.

### 2. Quel est le rôle de la classe ApplicationIPPlanManager ?

C'est l'orchestrateur principal qui coordonne tous les services (saisie, VLSM, VLANs, validation, 
recommandations, sauvegarde).

### 3. Pourquoi la classe Main doit-elle rester courte ?

la classe Main doit rester courte pour respecter le principe de responsabilité unique : Main ne fait 
que lancer l'application.

### 4. Pourquoi séparer les packages ?

On sépare les packages pour une meilleure organisation, maintenabilité et réutilisabilité du code.

### 5. Pourquoi la saisie utilisateur est-elle dans ConsoleService ?

la saisie utilisateur est dans ConsoleService pour séparer l'interaction utilisateur de la logique 
métier (principe de séparation des préoccupations).

### 6. Pourquoi valider l'adresse avant génération ?

Pour éviter des erreurs en cascade et garantir que le plan est basé sur une adresse valide.

### 7. Pourquoi les recommandations après les VLANs ?

Car les recommandations analysent les VLANs créés (capacité, nom, etc.)

### 8. Pourquoi la sauvegarde est-elle indispensable ?

la sauvegarde est indispensable pour conserver le travail et permettre sa réutilisation, partage ou archivage.

### 9. Pourquoi le rapport technique est-il important ?

Parce que le rapport technique fournit une documentation complète et lisible du plan d'adressage.

### 10. Améliorations futures possibles

les pespectives d'amelioration future sont:
- Interface graphique
- Export PDF
- Base de données
- Scénarios prédéfinis
- Mode batch pour lots de projets
