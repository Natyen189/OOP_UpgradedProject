package TowerDefense.GameEntity.Enemy;

import TowerDefense.GameEntity.GameEntity;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.ArrayList;

public class EnemySpawner extends AnchorPane {

    public static int level = 0;
    public static ArrayList<Enemy> enemies = new ArrayList<>();
    public static Timeline timeline;

    public EnemySpawner() {
    }

    public void spawnEnemy(int level) {
        switch (level) {
            case 0:
                spawnByNumberAndType(5, GameEntity.EnemyType.NormalEnemy);
                break;
            case 1:
                spawnByNumberAndType(5, GameEntity.EnemyType.SmallerEnemy);
                break;
            case 2:
                spawnByNumberAndType(10, GameEntity.EnemyType.NormalEnemy);
                break;
            case 3:
                spawnByNumberAndType(10, GameEntity.EnemyType.NormalEnemy);
                break;
            case 4:
                spawnByNumberAndType(5, GameEntity.EnemyType.TankerEnemy);
                break;
            case 5:
                spawnByNumberAndType(5, GameEntity.EnemyType.TankerEnemy);
                break;
            case 6:
                spawnByNumberAndType(10, GameEntity.EnemyType.NormalEnemy);
                break;
            case 7:
                spawnByNumberAndType(7, GameEntity.EnemyType.SmallerEnemy);
                break;
            case 8:
                spawnByNumberAndType(12, GameEntity.EnemyType.NormalEnemy);
                break;
            case 9:
                spawnByNumberAndType(5, GameEntity.EnemyType.SmallerEnemy);
                break;
            case 10:
                spawnByNumberAndType(1, GameEntity.EnemyType.BossEnemy);
                break;
            case 11:
                spawnByNumberAndType(12, GameEntity.EnemyType.NormalEnemy);
                break;
            case 12:
                spawnByNumberAndType(7, GameEntity.EnemyType.SmallerEnemy);
                break;
            case 13:
                spawnByNumberAndType(5, GameEntity.EnemyType.TankerEnemy);
                break;
            case 14:
                spawnByNumberAndType(10, GameEntity.EnemyType.TankerEnemy);
                break;
            case 15:
                spawnByNumberAndType(10, GameEntity.EnemyType.TankerEnemy);
                break;
            case 16:
                spawnByNumberAndType(10, GameEntity.EnemyType.SmallerEnemy);
                break;
            case 17:
                spawnByNumberAndType(7, GameEntity.EnemyType.SmallerEnemy);
                break;
            case 18:
                spawnByNumberAndType(12, GameEntity.EnemyType.TankerEnemy);
                break;
            case 19:
                spawnByNumberAndType(1, GameEntity.EnemyType.BossEnemy);
                break;
        }

    }

    public void spawnByNumberAndType(int numberOfEnemySpawned, GameEntity.EnemyType enemyType) {
        timeline  = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            enemies.add(new Enemy(enemyType));
            this.getChildren().add(enemies.get(enemies.size()-1));
        }));

        timeline.setCycleCount(numberOfEnemySpawned);
        timeline.play();

        System.out.println("Enemy: " + enemyType + ", Number: " + numberOfEnemySpawned);
    }
}
