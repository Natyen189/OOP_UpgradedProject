package TowerDefense.GameEntity.Enemy;

import TowerDefense.GameEntity.GameEntity;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.ArrayList;

public class EnemySpawner extends AnchorPane {

    private ArrayList<Enemy> enemies = new ArrayList<>();
    public static Timeline timeline;

    public EnemySpawner(int numberOfEnemySpawned, GameEntity.EnemyType enemyType) {
        timeline  = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            enemies.add(new Enemy(enemyType));
            this.getChildren().add(enemies.get(enemies.size()-1));
        }));

        timeline.setCycleCount(numberOfEnemySpawned);
        timeline.pause();
    }
}
