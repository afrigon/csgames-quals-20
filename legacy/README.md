# CSGames Qualifications 2020

## Legacy code

La terre est en danger. Partez à l'aventure à bord de votre navette intergalactique Asteria pour trouver une nouvelle planète pour la race humaine. Pour passer d'un système à un autre, vous utilisez un réseau de portail que vous a laissé une race extraterrestre technologiquement plus avancée.

Mais vous avez un avantage indéniable sur cette race, une technologie qui vous permet de changer le monde qui vous entoure. Pour atteindre votre objectif de sauver la race humaine, vous devrez ajouter un certain nombre de fonctionnalités à votre univers.

Soyez prudent, les êtres supérieurs qui ont construit le monde autour de vous se sont révélés être des étudiants de cégep ... Il est donc possible qu'il existe quelques ~~bogues~~ fonctionnalités inattendues!

# Carnet de produit (idéalement à faire dans l'ordre)
2. Vous allez bientôt manquer de carburant... Ajoutez à votre monde des bidons d'essence pour recharger votre vaisseau!
    - Tous les vaisseaux peuvent récupérer les bidons d'essence.
    - Les bidons d'essence ne sont pas attirés par les planètes et n'attirent pas les vaisseaux, quel que soit leur poids.
    - Si quelque chose touche un bidon, les deux objets ont une collision parfaitement elastique.
    - Il est possible d'ajouter des bidons d'essence via l'éditeur de niveaux.
    - Pour être plus attractif, les bidons d'essence tournent sur eux-mêmes.
    - Lorsque vous récurérez un bidon, vous ne pouvez pas dépasser la quantité maximale de votre réservoir.
    
    ![imgur](https://i.imgur.com/SCs9Oaj.png)

4. Vous voulez vous défendre contre les navires ennemis. Equipez votre vaisseau avec un blaster laser.
    - La puissance de votre laser est configurable via l'éditeur et est enregistrée à chaque niveau.
    - Tirer avec votre laser consomme du carburant.

    ![imgur](https://i.imgur.com/B1IjGJF.png)
    
5. Vous approchez de plus en plus le centre de la galaxie et vous commencez à rencontrer des astres ne produisant aucune lumière. Une chose est sur, tout ce qui touche cet objet disparait pour l'éternitée.
    - Les trous noirs peuvent dévier la courbe des lasers en les attirants.
    - Si quelque chose touche un trou noir, il disparait instantanément.

6. Afin d'améliorer l'efficatité de votre moteur, faite en sorte que la masse de votre vaisseau soir liée a votre quantité de carburant.
    - Basez-vous sur la masse volumique de l'essence pour trouver quel devrait être le poid d'un litre d'essence.
    - Déterminez également quelle devrait être la consommation d'essence de votre moteur en vous basant sur le moteur des fusées de SpaceX.

3. Pour mieux anticiper vos mouvements, ajoutez un indicateur de trajectoire vous permettant de prédire votre prochaine direction!    

    ![imgur](https://i.imgur.com/EaWz5LG.png)

6. Vous remarquez que votre vaisseau semble toucher des objets qui sont en réalité hors de sa portée. Modifiez la hitbox des vaiseaux pour qu'elle soit un meilleur calque de leur apparence.

8. Vous entrez dans une zone étrange de la galaxie. Vous êtes entourés de plein de roché pouvant se déplacer. S'ils vous touchent, vous mourrez, mais si vous les touchez avec votre laser, ils se divisent en deux morceaux qui partent dans une direction opposée (en conservant leur inertie bien sur). Les astéroïdes finissent par se détruire après 3 divisions. 

8. Revirement de situation, une race extraterrestre vous emploi comme tueur à gage. Faites en sorte que certain niveaux puissent se terminé après que vous ayez tué un certain nombre de vaisseaux ennemis.

7. Vous réalisez qu'il serait beaucoup moins couteux de vous déplacer dans l'espace en vous laissant attirer pas les astres. Ajoutez à votre éditeur un moyen de créer des trajectoires aux planètes et aux trous noirs.

5. Parfois, vous n'êtes pas certain si votre navire, ou votre environnement est en mouvement. Surtout quand votre vaisseau est près du bord de votre écran radar. Remplacez l’arrière-plan immobile de votre radar par une parallaxe.


## Petits trucs à prendre en compte
- Évidement, si vous trouvez des bugs, corrigez-les!
- Ne passez pas trop de temps à faire du ménage. Le but n'est pas de cleaner tout le code, c'est d'ajouter des features.
- Les tests actuels sont d'une qualitée assez douteuse... Si vous décidez d'en ajouter, assurez-vous qu'ils soient bien fait !
- Si vous avez à ajouter de la logique, le TDD et les tests de caractérisation peuvent être un allié de choix.
- Lorsque vous créez un `objet`, assurez-vous qu'il soit gabage collecté. On ne devrait pas trop voir l'utilisation de la mémoire monter lorsqu'on utilise le laser.
- L'application comporte deux parties importantes. Le jeu et l'éditeur de niveau. Une feature ne sera pas complétée si elle n'est pas utilisable dans l'un ou l'autre.

#### Dédicace à mon pote Gilles et son fantastique cours de Science de l'Espace !

![Gilly Joncas](https://i.imgur.com/wu5UjFC.png)