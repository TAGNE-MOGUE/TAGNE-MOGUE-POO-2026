
# TP9 - Sauvegarde et organisation professionnelle

## Objectif

Ajouter la persistance des données pour sauvegarder les plans d'adressage, les VLANs, les recommandations 
et les rapports techniques dans des fichiers CSV et texte.

## Notions étudiées

les differentes notions que j'ai étudié sont:
- **Persistance** : Conservation des données entre les exécutions
- **Fichiers CSV** : Format standard pour l'échange de données tabulaires
- **Lecture/Écriture de fichiers** : FileWriter, BufferedReader
- **Repository** : Couche d'accès aux données
- **Service** : Couche métier
- **Architecture professionnelle** : Séparation en packages

## Architecture par packages

ipplanmanager/
├── model/  Classes métier (BesoinReseau, ResultatVLSM, VLAN, Recommandation)
├── service/  Logique métier (CalculateurReseau, MoteurVLSM, GestionnaireVLAN)
├── repository/ Accès aux données (BesoinRepository, FichierPlanRepository, RapportService)
├── exception/ Exceptions personnalisées
├── console/ Interaction utilisateur (ConsoleService)
└── main/ Point d'entrée (Main)


## Fichiers utilisés

### Fichiers d'entrée
| Fichier | Format | Description |
|---------|--------|-------------|
| `exports/besoins.csv` | CSV | Besoins utilisateur (Nom;Hotes) |
| `exports/besoins_pme.csv` | CSV | Besoins pour scénario PME |

### Fichiers de sortie
| Fichier | Format | Description |
|---------|--------|-------------|
| `{projet}_plan.csv` | CSV | Plan VLSM (Adresse, CIDR, Capacité, Marge) |
| `{projet}_vlans.csv` | CSV | Liste des VLANs (ID, Nom, Adresse, CIDR) |
| `{projet}_recommandations.txt` | Texte | Recommandations techniques |
| `{projet}_rapport.txt` | Texte | Rapport technique complet |

## Contenu des fichiers

### besoins.csv (entrée)
```csv
Nom;Hotes
ETUDIANTS;500
WIFI_INVITES;200
ENSEIGNANTS;120
LABORATOIRES;60
SERVEURS;30

### plan_adressage.csv (sortie)

Nom;AdresseReseau;CIDR;Masque;HotesDemandes;Capacite;Marge
ETUDIANTS;10.10.0.0;23;255.255.254.0;500;510;10
WIFI_INVITES;10.10.2.0;24;255.255.255.0;200;254;54
ENSEIGNANTS;10.10.3.0;25;255.255.255.128;120;126;6
LABORATOIRES;10.10.3.128;26;255.255.255.192;60;62;2
SERVEURS;10.10.3.192;27;255.255.255.224;30;30;0

### recommandations.txt (sortie)

===== RECOMMANDATIONS IPPLAN-MANAGER =====
[ELEVEE] Isolation du WiFi invite : Le VLAN WIFI_INVITES doit etre isole des VLANs internes sensibles.
[ELEVEE] Protection du VLAN Serveurs : Le VLAN SERVEURS doit etre protege par des ACL.
[MOYENNE] VLAN de grande taille : Le VLAN ETUDIANTS a une capacite de 510 hotes. Surveiller les broadcasts.


### Diagramme de flux

┌─────────────┐     ┌─────────────┐     ┌─────────────┐
│ besoins.csv │────▶│ BesoinRepo  │────▶│ ConsoleService │
└─────────────┘     └─────────────┘     └─────────────┘
                        │                      │
                        ▼                      ▼
                 ┌─────────────┐     ┌─────────────────┐
                 │ MoteurVLSM  │◀────│  BesoinReseau   │
                 └─────────────┘     └─────────────────┘
                        │
                        ▼
                 ┌─────────────┐     ┌─────────────┐
                 │ResultatVLSM │────▶│GestionnaireVLAN│
                 └─────────────┘     └─────────────┘
                        │                    │
                        ▼                    ▼
┌─────────────────────────────────────────────────┐
│              FichierPlanRepository               │
│  - sauvegarderPlanCSV()                         │
│  - sauvegarderVLANsCSV()                        │
│  - sauvegarderRecommandations()                 │
└─────────────────────────────────────────────────┘
                        │
        ┌───────────────┼───────────────┐
        ▼               ▼               ▼
┌─────────────┐  ┌─────────────┐  ┌─────────────┐
│ plan.csv    │  │ vlans.csv   │  │recommandations.txt│
└─────────────┘  └─────────────┘  └─────────────┘

## Scénarios testés

### Scénario 1 : Université (fichier besoins.csv)

Lecture du fichier: 5 besoins chargés
Génération VLSM: Plan cohérent
Création VLANs: 5 VLANs créés
Sauvegarde fichiers: 4 fichiers générés


###Scénario 2 : PME (fichier besoins_pme.csv)

Lecture du fichier: 5 besoins chargés
Génération VLSM: Plan cohérent
Création VLANs: 5 VLANs créés
Rapport généré: rapport_pme.txt créé


## Difficultés rencontrées

les principales difficultes concernaient :

    -Séparateur CSV : Utilisation du point-virgule (;) au lieu de la virgule pour éviter les conflits avec les nombres décimaux.

    -Gestion des en-têtes : Ignorer la première ligne du fichier CSV lors de la lecture.

    -Fermeture des fichiers : Toujours appeler close() ou utiliser try-with-resources.

    -Exceptions IOException : Propagation des exceptions vers la couche supérieure pour gestion centralisée.

    -Création du dossier exports : Le dossier doit exister avant l'écriture des fichiers.

## Réponses aux questions

1. Qu'est-ce que la persistance des données ?

C'est la capacité d'une application à conserver les données entre deux exécutions, généralement via des 
fichiers ou une base de données.


2. Pourquoi une application professionnelle doit-elle sauvegarder ses résultats ?

Pour archiver le travail, partager les plans avec l'équipe, générer des rapports, et reprendre le travail 
ultérieurement.

3. Différence entre CSV et rapport texte ?

    CSV : Format structuré pour exploitation par d'autres logiciels (tableur, analyse)
    Rapport texte : Format lisible directement par un humain pour documentation

4. Pourquoi créer un package repository ?

Pour isoler le code d'accès aux données (lecture/écriture fichiers) du reste de l'application.

5. Pourquoi créer un package service ?

Pour regrouper la logique métier et la séparer des données et de l'interface utilisateur.

6. Pourquoi ne pas tout mettre dans Main ?

Respect du principe de responsabilité unique : chaque classe a un rôle précis, ce qui facilite la maintenance
et les tests.

7. Pourquoi besoins.csv rend l'application plus flexible ?

besoins.csv rend l'application plus flexible car elle permet de changer les besoins sans recompiler le code, 
et de traiter plusieurs scénarios facilement.

8. Pourquoi la séparation en packages améliore la maintenabilité ?

Chaque package a une responsabilité claire, on sait où chercher pour modifier un comportement spécifique.