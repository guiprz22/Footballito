Le projet contient le fichier Maven "pom.xml" pour build le projet, normalement "mvn clean install" suffit.

## Patterns Utilisés

### Factory Method Pattern

Nous avons utilisé le pattern *Factory* pour créer des instances de Balle, Joueur, Terrain et LabelScore. Les classes `BalleFactory`, `JoueurFactory`, `LabelScoreFactory` et `TerrainFactory` sont toutes des fabriques qui implémentent une interface commune appelée `ObjetJeuxFactory`. Chaque classe est responsable de la création d'un objet spécifique.

### Singleton Pattern

On a ensuite choisi d'utiliser le pattern *Singleton* pour l'implémentation de la classe `ScoreSingleton`, qui permet de stocker le score des joueurs. La classe utilise une instance unique pour stocker et mettre à jour le score, ce qui assure qu'il n'y ait qu'une seule instance de cette classe dans tout le programme.

### Strategy Pattern

Enfin, nous utilisons le Pattern *Strategy* pour la définition des stratégies de contrôle des joueurs. La classe `PlayerControlStrategy` est une interface qui définit la méthode `update` qui sera implémentée par les différentes stratégies de contrôle. Les classes `UserPlayerControlStrategy` sont des stratégies de contrôle qui implémentent cette interface. Les stratégies de contrôle permettent de séparer le comportement de l'objet de son interface, ce qui permet de changer de stratégie à tout moment sans impacter le reste du programme. Cela permet également de faciliter l'ajout de nouvelles stratégies de contrôle.
