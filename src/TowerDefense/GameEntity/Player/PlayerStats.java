package TowerDefense.GameEntity.Player;

import TowerDefense.Button.TowerButton;
import TowerDefense.GameEntity.Enemy.EnemySpawner;
import TowerDefense.GameStage;
import TowerDefense.MusicContainer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

/*Class sẽ để hiển thị những thông số cơ bản của người chơi như: mạng, tiền, ...*/
public class PlayerStats {

    public static int health = 20;
    public static int money = 1000;
    public static boolean restart = false;
    private static HighScore playerScore;
    private Text level;
    private Text lives;
    private Text playerMoney;

    public PlayerStats() {
        createPlayerLabel();
        updateStats();
        restartGame();
    }

    private void createPlayerLabel() {
        level = new Text(Integer.toString(EnemySpawner.level));
        lives = new Text(Integer.toString(health));
        playerMoney = new Text(Integer.toString(money));

        level.setFont(Font.loadFont("file:Asset/Font/UTM Akashi.ttf", 60));
        level.setLayoutX(225);
        level.setLayoutY(765);
        level.setFill(Color.WHITE);
        level.setStroke(Color.BLACK);
        level.setStrokeWidth(3);

        lives.setFont(Font.loadFont("file:Asset/Font/UTM Akashi.ttf", 60));
        lives.setLayoutX(560);
        lives.setLayoutY(765);
        lives.setFill(Color.WHITE);
        lives.setStroke(Color.BLACK);
        lives.setStrokeWidth(3);

        playerMoney.setFont(Font.loadFont("file:Asset/Font/UTM Akashi.ttf", 60));
        playerMoney.setLayoutX(895);
        playerMoney.setLayoutY(765);
        playerMoney.setFill(Color.WHITE);
        playerMoney.setStroke(Color.BLACK);
        playerMoney.setStrokeWidth(3);

        GameStage.mainWindowPane.getChildren().add(level);
        GameStage.mainWindowPane.getChildren().add(lives);
        GameStage.mainWindowPane.getChildren().add(playerMoney);
    }

    public static void subtractHealth() {
        if(health > 0) {
            health -= 1;
            System.out.println("Enemy's breaking in. Player health: " + health + ".");
        }
    }

    private void updateStats() {
        Timeline updateTimeLine = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> {
            level.setText(Integer.toString(EnemySpawner.level));
            lives.setText(Integer.toString(health));
            playerMoney.setText(Integer.toString(money));
        }));
        updateTimeLine.setCycleCount(Animation.INDEFINITE);
        updateTimeLine.setAutoReverse(false);
        updateTimeLine.play();
    }

    public static void restartGame() {
        Timeline restartTimeline = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> {
            if(health == 0 && !restart) {
                try {
                    GameStage.gameOverPane.getChildren().remove(playerScore);
                    HighScore.writeScore();
                    updateHighScore();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                switchToGameOverScene();
                restart = true;
                cleanUp();
            }
        }));
        restartTimeline.setCycleCount(Animation.INDEFINITE);
        restartTimeline.setAutoReverse(false);
        restartTimeline.play();
    }

    private static void updateHighScore() throws IOException {
        playerScore = new HighScore();
        GameStage.gameOverPane.getChildren().add(playerScore);
    }

    private static void switchToGameOverScene() {
        GameStage.gameStage.setScene(GameStage.gameOverScene);
        GameStage.gameStage.setMaximized(false);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.01)));
        timeline.setOnFinished(actionEvent -> {
            GameStage.gameStage.setMaximized(true);
        });
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);
        timeline.play();

        MusicContainer.mainTrack.stop();
        MusicContainer.lostTrack.play();
    }

    private static void switchToMainScene() {
        GameStage.gameStage.setScene(GameStage.mainScene);
        GameStage.gameStage.setMaximized(false);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.01)));
        timeline.setOnFinished(actionEvent -> {
            GameStage.gameStage.setMaximized(true);
        });
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);
        timeline.play();

        MusicContainer.lostTrack.stop();
        MusicContainer.playMainMusic();
    }

    private static void cleanUp() {
        if(EnemySpawner.timeline != null)
        EnemySpawner.timeline.stop();

        if(EnemySpawner.enemies != null) {
            for (int i = 0; i < EnemySpawner.enemies.size(); i++) {
                EnemySpawner.enemies.get(i).pathTransition.stop();
            }

            for (int i = 0; i < EnemySpawner.enemies.size(); i++) {
                GameStage.mainWindowPane.getChildren().remove(EnemySpawner.enemies.get(i));
                EnemySpawner.enemies.get(i).OnDestroyRestart();
            }
        }

        if(TowerButton.towerList != null) {
            for (int i = 0; i < TowerButton.towerList.size(); i++) {
                if(TowerButton.towerList.get(i) != null) {
                    TowerButton.towerList.get(i).OnDestroyRestart();
                }
            }
        }

        EnemySpawner.enemies = null;
        TowerButton.towerList = null;
    }

    public static void resetDefaultValue() {
        health = 20;
        money = 1000;
        EnemySpawner.level = 0;
        restart = false;
        EnemySpawner.enemies = new ArrayList<>();
        TowerButton.towerList = new ArrayList<>();
        switchToMainScene();
    }

}
