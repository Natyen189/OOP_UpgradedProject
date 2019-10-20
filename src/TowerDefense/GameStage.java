package TowerDefense;

import TowerDefense.Button.GameButton;
import TowerDefense.Button.MenuButton;
import TowerDefense.Button.TowerButton;
import TowerDefense.GameEntity.Enemy.Enemy;
import TowerDefense.GameEntity.GameEntity;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class GameStage {

    private AnchorPane mainWindow;
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
        ArrayList<File> file = new ArrayList<>();
        ArrayList<TowerButton> button = new ArrayList<>();
        ArrayList<ImageView> buttonImage = new ArrayList<>();

        file.add(new File("Asset\\TowerTile\\1.png"));
        file.add(new File("Asset\\TowerTile\\2.png"));
        file.add(new File("Asset\\TowerTile\\3.png"));
        file.add(new File("Asset\\TowerTile\\4.png"));
        file.add(new File("Asset\\TowerTile\\5.png"));

        double startXPos = 1007;
        double startYPos = 25;

        for (int i = 0; i < file.size(); i++) {
            buttonImage.add(new ImageView(file.get(i).toURI().toString()));
            button.add(new TowerButton("", buttonImage.get(i)));
            mainWindow.getChildren().add(button.get(i));
        }

        button.get(0).setLayoutX(startXPos);
        button.get(0).setLayoutY(startYPos);
        button.get(1).setLayoutX(startXPos + 150);
        button.get(1).setLayoutY(startYPos);
        button.get(2).setLayoutX(startXPos);
        button.get(2).setLayoutY(startYPos + 110);
        button.get(3).setLayoutX(startXPos + 150);
        button.get(3).setLayoutY(startYPos + 110);
        button.get(4).setLayoutX(startXPos + 150*2);
        button.get(4).setLayoutY(startYPos + 110);

    }

    private void createEnemy() {
        Enemy test = new Enemy(GameEntity.EnemyType.BossEnemy);
        mainWindow.getChildren().add(test);
    }

}
