package TowerDefense.GameEntity.Enemy;

import TowerDefense.GameEntity.GameEntity;
import TowerDefense.GameStage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;

public class EnemySpawner extends Pane {

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
            case 20:
                spawnByNumberAndType(6, GameEntity.EnemyType.NormalEnemy);
                spawnByNumberAndType(6, GameEntity.EnemyType.TankerEnemy);
                break;
            case 21:
                spawnByNumberAndType(12, GameEntity.EnemyType.TankerEnemy);
                break;
            case 22:
                spawnByNumberAndType(7, GameEntity.EnemyType.SmallerEnemy);
                spawnByNumberAndType(6, GameEntity.EnemyType.TankerEnemy);
                break;
            case 23:
                spawnByNumberAndType(1, GameEntity.EnemyType.BossEnemy);
                spawnByNumberAndType(7, GameEntity.EnemyType.TankerEnemy);
                break;
            case 24:
                spawnByNumberAndType(10, GameEntity.EnemyType.TankerEnemy);
                break;
            case 25:
                spawnByNumberAndType(7, GameEntity.EnemyType.TankerEnemy);
                spawnByNumberAndType(5, GameEntity.EnemyType.SmallerEnemy);
                break;
            case 26:
                spawnByNumberAndType(6, GameEntity.EnemyType.SmallerEnemy);
                spawnByNumberAndType(6, GameEntity.EnemyType.SmallerEnemy);
                break;
            case 27:
                spawnByNumberAndType(10, GameEntity.EnemyType.TankerEnemy);
                break;
            case 28:
                spawnByNumberAndType(6, GameEntity.EnemyType.TankerEnemy);
                spawnByNumberAndType(7, GameEntity.EnemyType.SmallerEnemy);
                break;
            case 29:
                spawnByNumberAndType(1, GameEntity.EnemyType.BossEnemy);
                spawnByNumberAndType(5, GameEntity.EnemyType.TankerEnemy);
                spawnByNumberAndType(5, GameEntity.EnemyType.SmallerEnemy);
                break;
        }

    }

    public void spawnByNumberAndType(int numberOfEnemySpawned, GameEntity.EnemyType enemyType) {

        timeline  = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            Enemy tempEnemy = new Enemy(enemyType);
            tempEnemy.increaseHealthByLevel(level);
            enemies.add(tempEnemy);
            GameStage.mainWindow.getChildren().add(enemies.get(enemies.size()-1));
        }));
        timeline.setCycleCount(numberOfEnemySpawned);
        timeline.play();

        System.out.println("Enemy: " + enemyType + ", Number: " + numberOfEnemySpawned);
    }
}
