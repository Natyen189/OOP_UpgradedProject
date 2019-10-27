package TowerDefense;

import TowerDefense.Button.GameButton;
import TowerDefense.Button.MenuButton;
import TowerDefense.Button.TowerButton;
import TowerDefense.GameEntity.Enemy.Enemy;
import TowerDefense.GameEntity.Enemy.EnemySpawner;
import TowerDefense.GameEntity.GameEntity;
import TowerDefense.GameEntity.Player.Tower;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class GameStage {

    public static AnchorPane mainWindow;
    private Scene scene;
    private Stage gameStage;

    public GameStage() {
        mainWindow = new AnchorPane();
        scene = new Scene(mainWindow, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        gameStage = new Stage();
        gameStage.setScene(scene);
        setBackgroundImage();
        createButton();
        createEnemy();

    }

    public Stage getState() {
        return gameStage;
    }

    public void setBackgroundImage() {
        File backGroundLocation = new File("Asset\\Background.png");
        Image background = new Image(backGroundLocation.toURI().toString());
        BackgroundImage gameBackground = new BackgroundImage(background, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

        mainWindow.setBackground(new Background(gameBackground));
    }

    private void createButton() {
        createMenuButton();
        createTowerButton();
    }

    private void createMenuButton() {
        File startButtonLocation = new File("Asset\\PlayButton.png");
        ImageView buttonImage = new ImageView(new Image(startButtonLocation.toURI().toString()));
        MenuButton startButton = new MenuButton("", buttonImage);
        startButton.setLayoutX((float)Config.SCREEN_WIDTH/2 - 180);
        startButton.setLayoutY((float)Config.SCREEN_HEIGHT/2);
        mainWindow.getChildren().add(startButton);
    }

    private void createTowerButton() {

        double startXPos = 1015;
        double startYPos = 25;

        TowerButton tower1 = new TowerButton(GameEntity.TowerType.NormalTower);
        TowerButton tower2 = new TowerButton(GameEntity.TowerType.SniperTower);
        TowerButton tower3 = new TowerButton(GameEntity.TowerType.MachineGunTower);
        TowerButton tower4 = new TowerButton(GameEntity.TowerType.NormalTower);
        TowerButton tower5 = new TowerButton(GameEntity.TowerType.SniperTower);
        TowerButton tower6 = new TowerButton(GameEntity.TowerType.MachineGunTower);

        tower1.setLayoutX(startXPos);
        tower1.setLayoutY(startYPos);

        tower2.setLayoutX(startXPos + 150);
        tower2.setLayoutY(startYPos);

        tower3.setLayoutX(startXPos);
        tower3.setLayoutY(startYPos + 110);

        tower4.setLayoutX(startXPos + 150);
        tower4.setLayoutY(startYPos + 110);

        tower5.setLayoutX(startXPos);
        tower5.setLayoutY(startYPos + 110*2);

        tower6.setLayoutX(startXPos + 150);
        tower6.setLayoutY(startYPos + 110*2);

        mainWindow.getChildren().add(tower1);
        mainWindow.getChildren().add(tower2);
        mainWindow.getChildren().add(tower3);
        mainWindow.getChildren().add(tower4);
        mainWindow.getChildren().add(tower5);
        mainWindow.getChildren().add(tower6);
    }

    public void createEnemy() {

//        switch (LevelManagement.getLevel()) {
//            case 1:
//                break;
//            case 2:
//                break;
//            case 3:
//                break;
//            case 4:
//                break;
//            case 5:
//                break;
//            case 6:
//                break;
//            case 7:
//                break;
//            case 8:
//                break;
//            case 9:
//                break;
//            case 10:
//                break;
//        }
        EnemySpawner normalEnemy = new EnemySpawner(5, GameEntity.EnemyType.TankerEnemy);
        mainWindow.getChildren().add(normalEnemy);
    }
}
