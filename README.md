# projet_ipo_jules_slimane
## Frogger game

Slimane Mesbah - Jules Rohault de Fleury
Projet IPO 2021 - L2 Info - Groupe 1

![image](https://user-images.githubusercontent.com/72779962/165159218-3a874a75-98ac-406a-9ff4-d94bd9fa341f.png)


Les premières implémentations se sont concentrées sur l'implémentation des méthodes de base, nécessaires pour que le jeu soit possible. Ces implémentations se sont majoritairement centrées autour de la classe Frog. Les difficultés à ce moment se sont concentrées sur la mise en place du projet lui-même, par exemple avec le fonctionnement de Git, d'IntelliJ, et de VSCode.

Les ajouts suivants ont implémenté les méthodes permettant de passer d'un simple squelette à un jeu (jouable). La mise en place des voitures sur les Lane et de la fin de jeu, ainsi que du ticker et du score, a été faite à ce moment. Il n'y a pas eu de problème particulier sur cette étape à part l'organisation entre les classes et les interfaces.

Les développements suivants se sont concentrés sur les ajouts des parties 3 et 4. Le défilement infini nécessita de revoir la manière dont les lignes étaient stockées dans l'environnement, avec l'ajout et retrait régulier des lignes d'après le scrolling (et le retrait de la condition de victoire). Pour éviter la gestion de toutes les lignes derrière, une limite à 5 lignes de recul existe (au-delà, les lignes antérieures ne sont plus chargées et la frog ne peut pas reculer plus).

La mise en place du timer et des rivières/rondins a suivi, avec des légères difficultés par rapport à la relation entre voitures et rondins (codées avec la même classe) ; par exemple le fait que la Frog ne doit mourir que si l'objet est une voiture (pas un rondin, résolu en rajoutant une condition sur la méthode isSafe()). Le fait que la Frog doive bouger avec le rondin sur lequel elle est a aussi brièvement posé problème mais fut résolu en appellant la fonction move() de la frog.

L'affichage graphique amélioré a été un problème de taille. D'abord avec une tentative d'implémentation manuelle (en dessinant des groupes de pixels), le choix fut fait d'afficher des images ; ce qui a soulevé des problèmes supplémentaires par rapport à la manière dont les images sont faites, leur transparence, et leur position (alignement par rapport aux objets). En général, la relation entre la classe de graphiques et le reste des classes du projet a été un point difficile pendant tout le projet.

L'une des dernières étapes a été d'optimiser certaines parties du codes. Le chargement des images a été simplifié (en chargeant les images à chaque frame au lieu de chaque élément) et la taille des images réduite. Les interfaces d'environnement et de graphismes ont été supprimées car ne manipulant qu'une unique instance d'une seule classe chacune ; le mode de génération de la carte a aussi été modifié pour être plus régulier et plus modulable.
