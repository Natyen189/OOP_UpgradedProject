package TowerDefense.GameEntity.Enemy;

import TowerDefense.GameEntity.GameEntity;
import TowerDefense.GameStage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;

public class EnemySpawner extends Pane {

    public static int level = 0;
    public static ArrayList<Enemy> enemies = new ArrayList<>();
    public static Timeline timeline;
    private EnemySpecial enemySpecial;

    public EnemySpawner() {
    }

    public void spawnEnemy(int level) {
        switch (level) {
            case 0:
                spawnByNumberAndType(10, GameEntity.EnemyType.NormalEnemy);
                break;
            case 1:
                spawnByNumberAndType(10, GameEntity.EnemyType.SmallerEnemy);
                break;
            case 2:
                spawnByNumberAndType(15, GameEntity.EnemyType.NormalEnemy);
                break;
            case 3:
                spawnByNumberAndType(15, GameEntity.EnemyType.NormalEnemy);
                break;
            case 4:
                spawnByNumberAndType(10, GameEntity.EnemyType.TankerEnemy);
                break;
            case 5:
                spawnByNumberAndType(10, GameEntity.EnemyType.TankerEnemy);
                castEnemySpecial(1, 2);
                break;
            case 6:
                spawnByNumberAndType(15, GameEntity.EnemyType.NormalEnemy);
                break;
            case 7:
                spawnByNumberAndType(12, GameEntity.EnemyType.SmallerEnemy);
                break;
            case 8:
                spawnByNumberAndType(17, GameEntity.EnemyType.NormalEnemy);
                break;
            case 9:
                spawnByNumberAndType(10, GameEntity.EnemyType.SmallerEnemy);
                break;
            case 10:
                spawnByNumberAndType(1, GameEntity.EnemyType.BossEnemy);
                castEnemySpecial(2, 3);
                break;
            case 11:
                spawnByNumberAndType(17, GameEntity.EnemyType.NormalEnemy);
                break;
            case 12:
                spawnByNumberAndType(12, GameEntity.EnemyType.SmallerEnemy);
                break;
            case 13:
                spawnByNumberAndType(10, GameEntity.EnemyType.TankerEnemy);
                break;
            case 14:
                spawnByNumberAndType(15, GameEntity.EnemyType.TankerEnemy);
                castEnemySpecial(2, 2);
                break;
            case 15:
                spawnByNumberAndType(15, GameEntity.EnemyType.TankerEnemy);
                break;
            case 16:
                spawnByNumberAndType(15, GameEntity.EnemyType.SmallerEnemy);
                break;
            case 17:
                spawnByNumberAndType(12, GameEntity.EnemyType.SmallerEnemy);
                break;
            case 18:
                spawnByNumberAndType(17, GameEntity.EnemyType.TankerEnemy);
                break;
            case 19:
                spawnByNumberAndType(1, GameEntity.EnemyType.BossEnemy);
                castEnemySpecial(3, 2);
                break;
            case 20:
                spawnByNumberAndType(11, GameEntity.EnemyType.NormalEnemy);
                spawnByNumberAndType(11, GameEntity.EnemyType.TankerEnemy);
                break;
            case 21:
                spawnByNumberAndType(17, GameEntity.EnemyType.TankerEnemy);
                break;
            case 22:
                spawnByNumberAndType(12, GameEntity.EnemyType.SmallerEnemy);
                spawnByNumberAndType(11, GameEntity.EnemyType.TankerEnemy);
                break;
            case 23:
                spawnByNumberAndType(1, GameEntity.EnemyType.BossEnemy);
                spawnByNumberAndType(12, GameEntity.EnemyType.TankerEnemy);
                break;
            case 24:
                spawnByNumberAndType(10, GameEntity.EnemyType.TankerEnemy);
                castEnemySpecial(2, 2);
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
                castEnemySpecial(2, 3);
                break;
        }

    }

    private void spawnByNumberAndType(int numberOfEnemySpawned, GameEntity.EnemyType enemyType) {

        timeline  = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if(enemies != null) {
                Enemy tempEnemy = new Enemy(enemyType);
                tempEnemy.increaseStatByLevel(level);
                enemies.add(tempEnemy);
                GameStage.mainWindowPane.getChildren().add(enemies.get(enemies.size()-1));
            }
        }));
        timeline.setCycleCount(numberOfEnemySpawned);
        timeline.play();

        System.out.println("Enemy: " + enemyType + ", Number: " + numberOfEnemySpawned);
    }

    private void castEnemySpecial(int numberOfTarget, int numberOfLoop) {
        enemySpecial = new EnemySpecial();

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            enemySpecial.cast(numberOfTarget);
        }));
        timeline.setCycleCount(numberOfLoop);
        timeline.setAutoReverse(false);
        timeline.setOnFinished(event -> {
            enemySpecial = null;
        });
        timeline.play();
    }
}
