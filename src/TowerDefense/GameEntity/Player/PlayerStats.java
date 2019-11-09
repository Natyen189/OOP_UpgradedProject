package TowerDefense.GameEntity.Player;

import TowerDefense.Config;
import TowerDefense.GameStage;
import TowerDefense.Main;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PlayerStats {

    public static int health = 20;
    public static int money;

    public static void subtractHealth() {
        if(health <= 0) {
            restartGame();
        }
        health -= 1;
        System.out.println("Enemy's breaking in. Player health: " + health + ".");
    }

    private static void restartGame() {

    }
}
