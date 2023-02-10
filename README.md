# projet2-ps5-22-23-takenoko-2023-s
projet2-ps5-22-23-takenoko-2023-s created by GitHub Classroom

## But du jeu
À la cour impériale du Japon, il y a fort longtemps...
Après de longues querelles, les relations entre la Chine et le Japon sont enfin au beau fixe. 
Pour célébrer cette entente, l’empereur chinois a offert à son homologue nippon un animal sacré, 
un grand panda de Chine, symbole de paix.
L’empereur du Japon confie aux joueurs, ses courtisans, la délicate mission de prendre soin de 
l’animal en lui aménageant une bambouseraie.
Les joueurs vont cultiver des parcelles de terrain, les irriguer et y faire pousser l’une des 
trois variétés de bambou (vert, jaune et rose) par l’intermédiaire du jardinier.
Ils devront composer avec l’animal sacré et son goût immodéré pour les tiges croquantes et les 
tendres feuilles...
Le joueur qui fera pousser le plus de bambous en gérant au mieux ses parcelles et en satisfaisant 
l’appétit délicat du panda remportera la partie.

## Etat du rendu
Notre version du jeu a cet état permet à 2 bots de realiser une partie
jusqu'à ce qu'un joueur ne realise ses objectifs
À ce stade, Tous les objectifs parcelles, panda, et jardinier sont ajoutés et detectable,
Chaque joueur a droit cinq(5) objectifs.
À chaque tour chaque joueur realise 2 actions parmis les 5 possibles.
Nous avons donc un joueur qui realise que les Objectifs panda et un autre qui joue sans strategie precis

## Fonctionnalités du jeu
Toutes les fonctionalités du jeu ont été implementer, il y en a pas un qui manque
### Fonctionalités de bases
* Implementation de parcelle avec couleur.
* Implementation et detection de tous les objectifs parcelles et Panda dans le jeu.
* Implementation de plusieurs intelligences de jeu 
* Implementation de la pose de canaux d'irrigationns et de Jardinier dans le jeu.
### Fonctionalités additionnelles 1
* Simulation de 1000 parties de votre meilleur bot contre le second
* Simulation de 1000 parties de votre meilleur bot contre lui-même
*  L'affichage sur la sortie standard doit comprendre le nombre et pourcentage de
   parties gagnées/perdues/nulles, et le score moyen de chaque bot
*  Le passage en mode démo 2 x 1000 parties ou une seule partie avec des logs se fait par options sur la ligne de commande avec mvn exec:java -Dexec.args="--2thousands"
*   Mode démo d’une seule partie avec log complet mvn exec:java -Dexec.args="--demo"
### Fonctionalités additionelles 2
*  En complément, un fichier .csv regroupant les statistiques des bots devra être produit à
   l’emplacement "stats/gamestats.csv.ce fichier est présent, il sera relu et les
   nouvelles statistiques seront agrégées, puis de nouveau sauvegardées
* Lancement d’une simulation à plusieurs parties (pas forcément 1000) avec
  relecture de "stats/gamestats.csv” s’il existe et ajout des nouvelles statistiques avec mvn exec:java -Dexec.args="--csv"
### Fonctionnalités additionnelles 3
Implémentation (et tests assurant que le comportement est conforme) d’un bot avec le
comportement suivant :
*  Il récupère un maximum de bambous, même s’il n’a pas de cartes avec la couleur correspondante.
*  Il essaie d’avoir 5 cartes objectives en main tout le temps. Les deux premiers mouvements du bot
   devraient donc être de prendre une carte objectif et de prendre un canal d’irrigation.
*  Quand il tire une météo « ? » dans les premiers tours, il prend une irrigation.
*  Il essaie de se focaliser sur plusieurs (pas toutes) cartes à la fois. S’il a deux cartes Panda, il va se
   focaliser sur les deux en même temps.
*  Il surveille les mouvements de ses adversaires (ou de l’adversaire potentiellement en tête), et s’il
   détecte qu’il essaie de réaliser un objectif particulier, il essaie de saboter ou de ralentir la
   réalisation.
## Réglés du jeu
On choisit 2 operation parmis les suivantes
* Choix d'Objectifs à realiser
* Choix de parcelles à ajouter au jeu
* Choix de deplacer le Panda
On vérifie s'il n'y a pas d'Objectif à valider.
Le jeu continue jusqu'à ce qu'il n'y ait plus de parcelles à poser ou quand un joueur realise 9 objectifs 
puis un denier tour est donnée au autre joueur s'ils sont juste 2 joeur.
s'ils sont 3 le meme procesus mais commence aprés la realisation de 8 objectifs,
s'ils ont 4 aprés 7 Objectifs.
## Exécution
### Jeu
Pour avoir un demo, veuillez executer le Main (fr/cotedazur/univ/polytech/startingpoint/gameplay)
ou je vous prie d'entrer les commandes suivantes
#### Pour charger le projet
s'il n'ai pas fait automatiquement utiliser les commandes ci-dessous.
* mvn clean package
* mvn clean install
#### Pour avoir une demo du jeu 
*  mvn exec:java '"-Dexec.args=--demo"'
#### Pour plus d'info sur les statistiques
* mvn exec:java '"-Dexec.args=--csv"' -e
#### Pour avoir les statiques entre nos meilleurs Bots
*  mvn exec:java '"-Dexec.args=--2thousands"'
#### Pour avoir plus d'info sur la qualité du code
*veuillez installez sonarqube https://www.sonarsource.com/products/sonarqube/downloads/ la version commuty
ensuite, allez dans le bin puis le dossier correspondant a votre os
et dans le terminal, lancez StartSonar
* allez le repertoire du projet et lancez les commandes suivantes
* mvn clean install
* mvn sonar:sonar

### Test
Notre rendu contient 208 tests contenus dans le package fr/cotedazur/univ/polytech/startingpoint/

Les tests implementés sont des tests critiques et pertinents
