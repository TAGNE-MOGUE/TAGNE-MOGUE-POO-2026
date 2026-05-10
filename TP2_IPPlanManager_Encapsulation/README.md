# TP2 - Encapsulation

## Objectif
Introduction de l’encapsulation et des validations.


## Notions étudiées

 - private: Utilisation du modificateur d'accès privé pour protéger les attributs.  

 -getters & setters : Mise en place de méthodes pour lire et modifier les données de manière contrôlée. 
 
 -validation : Vérification de la cohérence des données (ex: empêcher une adresse IP vide ou un masque CIDR 
  hors limite). 

 -this : Utilisation du mot-clé pour lever l'ambiguïté entre les attributs et les paramètres.


## Tests réalisés

Les validations suivantes ont été testées et fonctionnent correctement :

 -Validation d'adresse IP : Si une chaîne vide ou null est fournie, la valeur par défaut 0.0.0.0 est appliquée.  

 -Validation du masque CIDR : Si le masque est inférieur à 0 ou supérieur à 32, il est automatiquement ramené à la valeur par défaut 24.  

 -Validation du nom d'équipement : Un équipement créé sans nom reçoit automatiquement la mention "equipement inconnu".  

 -Test de l'adresse locale : La méthode estAdresseLocale() identifie correctement les adresses commençant par 192.

Resultat obtenu apres avoir fais le run: 

===== TP2 Encapsulation =====
Erreur : adresse IP invalide.
Erreur : adresse IP invalide.
IP1 locale ? true
Adresse réseau invalide.
Masque CIDR invalide.

--- Réseau 1 ---
Réseau : 192.168.1.0/24
Description : Réseau OK

--- Réseau 2 ---
Réseau : 0.0.0.0/24
Description : Aucune description

--- Equipement 1 ---
Nom : Routeur1
Type : Routeur
Interface : eth0
Adresse IP : 192.168.1.1
État : active

--- Equipement 2 ---
Nom : equipement_inconnu
Type : Type inconnu
Interface : interface_inconnue
Adresse IP : 0.0.0.0
État : inactive
BUILD SUCCESSFUL (total time: 1 second)


## Difficultés rencontrées

1 : La confusion entre attribut et paramètre (Le rôle de this)

La principale difficulté a été de comprendre pourquoi le programme n'enregistrait pas les données lors de 
l'appel des setters. J'ai réalisé que sans l'utilisation du mot-clé this, le programme confondait l'attribut
de la classe et le paramètre de la méthode. L'utilisation de this.valeur = valeur a permis de résoudre ce conflit.

2 : L'appel des setters dans le constructeur

Au départ, j'initialisais les variables directement dans le constructeur (ex: this.valeur = valeur). Je me 
suis rendu compte que cela sautait l'étape de validation. La difficulté a été de restructurer le constructeur
pour qu'il appelle les méthodes setValeur(), garantissant ainsi que même un objet nouvellement créé respecte 
les règles de gestion (comme l'adresse par défaut 0.0.0.0).

3 : La logique de validation du masque CIDR

L'implémentation de la validation du masque réseau a demandé une attention particulière. Il a fallu mettre 
en place une structure conditionnelle if/else pour interdire les valeurs aberrantes (négatives ou supérieures
à 32) et s'assurer qu'une valeur cohérente (24) soit attribuée en cas d'erreur de saisie.

4 : La manipulation des méthodes de chaînes (pour estAdresseLocale)

Pour la méthode estAdresseLocale(), la difficulté était de trouver la fonction Java la plus efficace pour 
vérifier le début de l'adresse IP. J'ai finalement utilisé la méthode .startsWith("192.") de la classe String, 
ce qui permet une vérification simple et robuste.


## Réponses aux questions

1. Pourquoi utilise-t-on private dans les classes ? 

On utilise private pour empêcher que n'importe quelle partie du programme ne puisse modifier directement les 
données sensibles, ce qui évite les erreurs de configuration et les incohérences.  


2. Quelle différence existe entre un attribut public et un attribut privé ? 

Un attribut public est accessible et modifiable par n'importe quelle autre classe. Un attribut privé n'est 
accessible que depuis l'intérieur de sa propre classe.  


3. Pourquoi utilise-t-on des getters et setters ? 

Ils servent d'intermédiaires. Le getter permet de lire la donnée sans la modifier, et le setter permet de 
modifier la donnée tout en appliquant des règles de validation.  


4. Pourquoi les validations sont-elles importantes dans un logiciel réseau ? 

Elles évitent les pannes logiques, les conflits d'adresses et les comportements imprévisibles du réseau 
(ex: un masque de sous-réseau impossible comme 45).  


5. Quel est le rôle du mot-clé this ? 

this désigne l'instance courante de l'objet. Il permet de distinguer l'attribut de la classe du paramètre 
reçu dans une méthode quand ils portent le même nom.  


6. Pourquoi le constructeur appelle-t-il les setters ? 

Cela permet de réutiliser la logique de validation déjà écrite dans les setters dès la création de l'objet, 
garantissant qu'un objet ne soit jamais créé avec des données invalides.  


7. Pourquoi la validation du masque CIDR est-elle importante ? 

En réseau, un masque CIDR doit impérativement être compris entre 0 et 32 bits. Une valeur en dehors de cette 
plage rendrait les calculs réseau impossibles.  


8. Pourquoi l'encapsulation améliore-t-elle la sécurité logicielle ? 

Elle limite les points d'entrée vers les données. En contrôlant chaque modification, on s'assure que l'état 
interne de l'application reste toujours cohérent et protégé contre les mauvaises manipulations.
