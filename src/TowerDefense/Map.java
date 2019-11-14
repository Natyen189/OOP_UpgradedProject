package TowerDefense;

import TowerDefense.Button.TowerButton;
import TowerDefense.GameEntity.Enemy.Enemy;
import TowerDefense.GameEntity.Enemy.EnemySpawner;
import TowerDefense.GameTile.Tower.Tower;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.ArrayList;

public class Map extends ScrollPane {

    ImageView imageView;
    File imageLoc;

    public Map() {
        generateMap();
        this.setContent(imageView);
        setOnInput();
    }

    private void generateMap() {
        imageLoc = new File("Asset\\Test.jpg");
        imageView = new ImageView(new Image(imageLoc.toURI().toString()));
        imageView.fitWidthProperty().set(Config.SCREEN_WIDTH);
        imageView.fitHeightProperty().set(Config.SCREEN_HEIGHT);
        this.setViewOrder(2);
    }

    private void setOnInput() {

        ArrayList<Tower> testTower = TowerButton.towerList;
        ArrayList<Enemy> enemyTest = EnemySpawner.enemies;

        GameStage.mainWindow.setOnKeyPressed(event -> {
            double offset = 10;
            switch (event.getCode()) {
                case W:
                    System.out.println("W is pressed.");
                    this.setLayoutY(this.getLayoutY() + offset);
                    break;
                case A:
                    System.out.println("A is pressed.");
                    this.setLayoutX(this.getLayoutX() + offset);
                    break;
                case S:
                    System.out.println("S is pressed.");
                    this.setLayoutY(this.getLayoutY() - offset);
                    break;
                case D:
                    System.out.println("D is pressed.");
                    this.setLayoutX(this.getLayoutX() - offset);
                    break;
            }
        });

    }

}
