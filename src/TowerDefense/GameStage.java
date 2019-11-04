package TowerDefense;

import TowerDefense.Button.MenuButton;
import TowerDefense.Button.TowerButton;
import TowerDefense.GameEntity.Enemy.EnemySpawner;
import TowerDefense.GameEntity.GameEntity;
import TowerDefense.GameTile.Road;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

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
        createRoad();
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

        double startXPos = 1015.5;
        double startYPos = 27;

        TowerButton tower1 = new TowerButton(GameEntity.TowerType.NormalTower);
        TowerButton tower2 = new TowerButton(GameEntity.TowerType.SniperTower);
        TowerButton tower3 = new TowerButton(GameEntity.TowerType.MachineGunTower);
        TowerButton tower4 = new TowerButton(GameEntity.TowerType.UnnamedTower);
        TowerButton tower5 = new TowerButton(GameEntity.TowerType.RayTower);
        TowerButton tower6 = new TowerButton(GameEntity.TowerType.IceTurret);

        tower1.setLayoutX(startXPos);
        tower1.setLayoutY(startYPos);

        tower2.setLayoutX(startXPos + 148);
        tower2.setLayoutY(startYPos + 2);

        tower3.setLayoutX(startXPos);
        tower3.setLayoutY(startYPos + 110);

        tower4.setLayoutX(startXPos + 148);
        tower4.setLayoutY(startYPos + 117);

        tower5.setLayoutX(startXPos);
        tower5.setLayoutY(startYPos + 110*2-10);

        tower6.setLayoutX(startXPos + 148);
        tower6.setLayoutY(startYPos + 110*2-10);

        mainWindow.getChildren().add(tower1);
        mainWindow.getChildren().add(tower2);
        mainWindow.getChildren().add(tower3);
        mainWindow.getChildren().add(tower4);
        mainWindow.getChildren().add(tower5);
        mainWindow.getChildren().add(tower6);
    }

    public void createEnemy() {
        Random random = new Random();
        GameEntity.EnemyType [] enemyType = GameEntity.EnemyType.values();

        /*Gọi quân địch*/
        Timeline test  = new Timeline(new KeyFrame(Duration.seconds(10), event-> {
            if(MenuButton.startGame) {
                /*Tạo quân địch ngẫu nhiên*/
                int randomNumber = (int) (Math.random()*11);
                GameEntity.EnemyType randomEnemy = enemyType[random.nextInt(enemyType.length)];
                EnemySpawner normalEnemy = new EnemySpawner(randomNumber, randomEnemy);
                System.out.println("Enemy: " + randomEnemy + ", Size: " + randomNumber);
                mainWindow.getChildren().add(normalEnemy);
                EnemySpawner.timeline.play();
            }
        }));
        test.setCycleCount(Animation.INDEFINITE);
        test.play();
    }

    public void createRoad() {
        Road roadPath = new Road();
    }
}
