# C'est carré !

Comme on nous a demandé de faire quelque chose de **carré**, on a fait un truc **carré** et un jeu à base de **carrés**.    
L'objectif de ce TP est de créer un petit jeu qui a pour but de générer pendant un certain nombre de seconde des **carrés**. Chaque fois que l'on appuie sur l'un de ces carrés, notre score augmente. Il faut donc être rapide et vif pour tenter de faire le meilleur score.


## Le code fourni
* Une classe Game : C'est elle qui va gérer notre jeu, il faudra la compléter.
* Une classe Timer : Ce timer sera utile pour les besoins du jeu, **il n'est pas nécessaire de modifier cette classe.**.
* Une classe Square : Elle va gérer les carrés du jeu, il faudra aussi la compléter.


## Instructions

### Initialisation de l'activity et du layout
1. Ajouter une première Activity : 
    Clic droit sur le package : m2.iagl.ifi.cestcarre > New > Activity > Empty Activity
2. Nommer votre Activity ```MainActivity``` et ensuite cliquer sur ```Finish```.
3. Il faut créer le layout de notre application. Pour cela, on va modifier le fichier XML qui représente notre layout. (```res/layout/activity_main.xml```)
    * Il nous faut un LinearLayout principal qui va occuper tout l'écran, il va contenir les layouts enfants.
    * Un LinearLayout *TOP* enfant qui va occuper **1/10** de l'écran.
    * Un LinearLayout *MIDDLE* enfant qui va occuper **8/10** de l'écran.
    * Un  LinearLayout *BOTTOM* enfant qui va occuper **1/10** de l'écran.

> Remarque :
> Lorsque vous ouvrez un fichier XML avec Android Studio, il vous propose 2 vues : Design et Text. Le premier est un assistant visuel où vous pouvez ajouter vos composants à l'aide de glisser/déposser et l'autre le fichier XML textuel.

4. Après la création de ces layouts, veillez à :
    * ce que chaque layout ait un id différent, si aucun id n'est défini, préciser les de cette manière : ```android:id="@+id/myId"```.
    * ce que chaque orientation soit définie sur ```vertical```.
5. Pour les layouts enfants, définir la largeur à ```match_parent``` et la hauteur à ```0dp```.
6. Pour définir les proportions de nos layouts, nous allons utiliser les options :
    * ```weightSum``` (pour le layout principal) : représente le poids maximum, ici 10.
    * ```layout_weight``` (pour les layouts enfants) : représente la proportion du layout par rapport au ```weightSum```.
7. Il faut spécifier au LinearLayout *MIDDLE* les id des layouts au dessus et en dessous de lui, grâce aux attributs ```layout_above``` et ```layout_below```.
8. Les layouts sont prêts et positionnés. Il faut ajouter : 
    * un ```TextView``` qui servira pour le score au LinearLayout *TOP*.
    * un ```Button``` qui servira à lancer le jeu au LinearLayout *BOTTOM*.

> Remarque :
> Vérifier que le layout_width correspond bien à ```match_parent``` et le layout_height à ```wrap_content```
 

9. Renommer les ids de ces composants pour qu'ils correspondant à leurs fonctions.
10. Ajouter un texte par défaut à ces 2 composants en utilisant le fichier ```/res/values/strings.xml```.
11. Ajouter un événement ```onClick``` sur le bouton et lui assigner la méthode ```startGame```.


### Initialisation du code du jeu
12. Créer la méthode ```startGame``` dans la classe ```MainActivity```. Elle prend en paramètre un objet ```View```.
13. Dans la méthode ```onCreate``` de la classe ```MainActivity```, on va récupérer les objets LinearLayout *MIDDLE*, TextView et Button grâce à la méthode ```findViewById()```. Cette méthode prend, *comme son nom l'indique*, un id. Inspirez vous de la manière dont on récupère un layout. L'objet ```R``` possède un tas d'informations utiles.
14. Toujours dans cette méthode, on va initialiser un objet ```Game``` en passant l'objet courant suivi des 3 objets récupérés précédemment comme paramètres.
15. Il faut pouvoir démarrer le jeu lorsque l'on clique sur le ```Button```. 


### Création d'une nouvelle Vue : Square
16. Etendre la classe Square avec la classe View. 
17. Nous devons faire appel au constructeur de la classe parent en lui passant comme paramètre un objet ```Context```. Faites en sorte de pouvoir passer dans ce constructeur, le contexte que l'on a dans la classe ```Game```.
18. Dans le constructeur, initialiser 3 objets :
    * ```java.util.Random``` : cela nous servira a généré aléatoirement les positions de votre carré.
    * ```android.graphics.Paint``` : C'est un objet qui représente les caractéristiques esthétiques de votre carré.
    * ```android.graphics.Rect``` : C'est un objet qui représente votre carré.
19. Pour afficher votre carré, il vous faut redéfinir la méthode ```onDraw(Canvas)``` de la classe parent, elle ne retourne rien.
20. Colorer votre carré avec l'objet ```Paint```. La méthode qui le permet prend en paramètre un entier. Pour que ce soit plus lisible pour un être humain, n'hésitez pas à utiliser la méthode static ```rgb``` de la classe ```Color```.
21. Générer 2 entiers aléatoires pour les positions X et Y du coin supérieur gauche de votre carré. **(Attention à ce que les positions générées soit cohérentes avec la taille de votre carré. Autrement dit, éviter qu'une position soit égale au maximum de l'écran, représenter par le ```Canvas``` )**
22. Grâce à la méthode ```set``` de la classe ```Rect```, vous allez pouvoir définir la position et la taille de votre carré. Sachez qu'elle prend 4 paramètres. Les 2 premiers correspondent au coin supérieur gauche et les 2 derniers au coin inférieur droit.
23. Votre carré est coloré et défini, il faut donc le **dessiner** dans le ```Canvas```
24. Il vous faut redéfinir la méthode ```onTouchEvent(MotionEvent)``` de la classe parent. Elle retourne un booléen. Dans cette méthode, il vous faudra appeler la méthode ```increaseScore(Square)``` de la classe ```Game``` en s'assurant que lors d'un clic, représenté par l'objet ```MotionEvent``` s'est effectué dans le périmètre de votre carré on incrémente le score de 1.

> Remarque : 
> Il existe beaucoup d'événements différents, choissisez le bon !    
> La classe ```Rect``` possède un méthode ```contains``` qui prend en paramètre un X et un Y, elle vous aidera sûrement !

### Développement du jeu !
25. La première méthode qui est appelée dans la méthode ```start``` de la classe ```Game``` est la méthode ```resetGame```. Elle a pour but de réinitialiser le score ainsi que le texte du TextView.
25. Ensuite, on fait appel à la méthode ```start``` de la classe ```Timer```. Le timer va ainsi se mettre en route et alerter toutes les secondes la classe ```Game``` au travers de la méthode ```update(Observable, Object)```. L'objet qui est envoyé ici est un booléen que vous pouvez vérifier pour savoir si le compte-à-rebours est arrivé à 0. Dans cette méthode :
    * Mettre à jour le texte du bouton, avec le temps restant.
    * Faire appel à la méthode ```stop``` si le booléen est vrai, ce qui signifie que le compte-à-rebours est fini.
26. Compléter la méthode ```generateSquare```. Cette méthode va devoir ajouter au LinearLayout un objet ```Square``` si le jeu est en cours.
27. Pour finir, compléter la méthode ```increaseScore```. Cette méthode va devoir :
    * Mettre à jour le score.
    * Mettre à jour le ```TextView``` avec le score.
    * Retirer du ```LinearLayout``` le carré passé en paramètre.

## Lancement de l'application
28. Il faut mettre à jour le AndroidManifest.xml pour lui dire que notre Activity est l'Activity principale et qu'il faut la démarrer au lancement de l'application. Il suffit d'ajouter le block de code ci-dessous dans le corps de la balise de notre Activity :
```xml
<intent-filter>
    <action android:name="android.intent.action.MAIN" />

    <category android:name="android.intent.category.LAUNCHER" />
</intent-filter>
``` 


**Tout est désormais prêt pour lancer l'application ! Vous pouvez maintenant try hard pour vous surpasser à chaque essai.**


## Rétrospective
Durant ce tutoriel vous avez donc vu comment :
* Créer une Activity et la définir comme principale
* Créer des layouts, les hiérarchiser et les organiser
* Ajouter des composants et widgets aux layouts
* Faire le lien entre des composants et du code java pour leurs faire éxécuter des traitements
* Créer une vue personnalisée
* Mettre à jour dynamiquement des composants


## Objectifs Bonus
Vous faites partie des grands champions de ce tutoriel, pour définitivement prouver votre valeur, vous devez accomplir ces objectifs bonus :
* Créer une nouvelle entité du jeu, un rond, un triangle, un losange... ça c'est vous qui choisissez.
* Assigner à cette entité un comportement de malus. Si vous cliquez dessus, vous faites diminuer votre score.
* Faites en sorte que les entités ne s'affichent pas plus de 1 seconde.
* Générer davantage d'entités

> Remarque :
> Durant le développement, vous serez sûrement amené à modifier le code de la classe Timer et Game.





