package TowerDefense.GameEntity.Player;

import TowerDefense.Config;
import TowerDefense.GameEntity.Enemy.EnemySpawner;
import TowerDefense.GameStage;
import TowerDefense.Main;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PlayerStats {

    public static int health = 20;
    public static int money = 400;
    Text level;
    Text lives;
    Text playerMoney;

    public PlayerStats() {
        createPlayerLabel();
        updateStats();
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

        GameStage.mainWindow.getChildren().add(level);
        GameStage.mainWindow.getChildren().add(lives);
        GameStage.mainWindow.getChildren().add(playerMoney);
    }

    public static void subtractHealth() {
        if(health > 0) {
            health -= 1;
            System.out.println("Enemy's breaking in. Player health: " + health + ".");
        }
        else {
            restartGame();
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

    private static void restartGame() {

    }
}
