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

Dans notre version, avec des bots peu intelligents, pour éviter que la partie ne soit interminable
on réduit de 5 le nombre d'objectifs à atteindre pour gagner, et on fixe nombre de tours prédéterminé 
dans le cas où aucun joueur n'arrive à compléter le nombre requis d'objectifs

## Fonctionnalités du jeu
* Implementation de parcelle avec couleur.
* Implementation et detection de tous les objectifs parcelles et Panda dans le jeu.
* Implementation d'une intelligence de jeu 
## Régles du jeu
On choisit 2 operation parmis les suivantes
* Choix d'Objectifs à realiser
* Choix de parcelles à ajouter au jeu
* Choix de deplacer le Panda
On vérifie s'il n'y a pas d'Objectif à valider.
Le jeu continue jusqu'à ce qu'il n'y ait plus de parcelles à poser ou quand un joueur realise 3 objectifs. 
## Exécution
### Jeu
Pour avoir un demo, veuillez executer le Main.
### Test
Notre rendu contient 139 tests contenus dans le package fr/cotedazur/univ/polytech/startingpoint
