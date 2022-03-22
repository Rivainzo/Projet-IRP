# Projet IRP : guide d'utilisation

## Sommaire
1. [Préambule](#Préambule)
2. [Compilation du programme](#Compilation-du-programme)
3. [Jouer contre l'ordinateur](#Jouer-contre-l'ordinateur)
4. [Accéder aux tests des algorithmes de recherche](#Accéder-aux-tests-des-algorithmes-de-recherche)

### Préambule
***
Ceci est le guide de fonctionnement du projet d'IRP réalisé par Jules LINARD et Maximilien SCHOLLAERT. Pour les instructions dans les parties suivantes de ce guide, nous supposerons que l'utilisateur a ouvert un terminal et s'est placé dans le répertoire contenant le Makefile et le dossier jeux contenant les fichiers du projet.

### Compilation du programme
***
Pour compiler le programme et obtenir les fichiers permettant de jouer au jeu de Nim et au jeu Puissance 4, il suffit d'écrire la commande suivante dans le terminal : `make`. Les fichiers seront générés dans le dossier jeux.

Vous avez la possibilité de supprimer ces fichiers créés après la compilation grâce à la commande : `make clean`.

### Jouer contre l'ordinateur
***
Avant de continuer la lecture de cette partie, il faudra au préalable avoir compilé le code pour en assurer le bon fonctionnement.

Pour jouer contre l'ordinateur au jeu de Nim, il vous suffit d'écrire la commande `make nim` dans le terminal. Après avoir éxécuté la commande précédente, une partie du jeu de Nim sera automatiquement lancée. Il vous faudra ensuite choisir si vous désirez jouer en premier ou non en répondant par "Oui" ou "Non". Ensuite, à chaque tour, le nombre d'allumettes restantes est affiché et lorsque c'est à vous de jouer il vous suffira de rentrer un nombre entre 1 et 3 correspondant au nombre d'allumette(s) que vous voulez retirer.

Pour jouer contre l'ordinateur au jeu Puissance 4, il vous suffit d'écrire la commande `make puissance4` dans le terminal. Après avoir éxécuté la commande précédente, une partie du jeu Puissance4 sera automatiquement lancée. Il vous faudra ensuite choisir si vous désirez jouer en premier ou non en répondant par "Oui" ou "Non". Ensuite, à chaque tour, la grille du jeu est affichée : les pions "x" correspondent au pions du joueur et les pions "o" à ceux de l'ordinateur. Lorsque c'est à vous de jouer il vous suffira de rentrer un nombre entre 0 et 6 correspondant à la colonne dans laquelle vous voulez ajouter un pion.



## Accéder aux tests des algorithmes de recherche
***
Les tests des différents algorithmes de recherche sont disponibles à l'aide des commandes `make nim_test` (pour le cas du jeu de Nim) et `make puissance4_test` (pour le cas du jeu Puissance 4). Le nombre d'états possibles étant très élevé pour le jeu Puissance 4, les algorithmes Minimax et Alpha-Beta sans limite de profondeur ne seront pas testé afin d'avoir un temps d'éxécution raisonnable.