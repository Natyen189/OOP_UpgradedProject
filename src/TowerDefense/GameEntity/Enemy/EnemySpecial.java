package TowerDefense.GameEntity.Enemy;

import TowerDefense.Button.TowerButton;
import TowerDefense.Config;
import TowerDefense.GameStage;
import TowerDefense.GameTile.Tower.Tower;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

/*Class quản lý những kỹ năng đặc biệt của quân địch*/
public class EnemySpecial extends Pane {

    private ImageView []imageView;
    private ArrayList<Tower> targetTower;

    public EnemySpecial() {
    }

    private void loadImage(int numberOfTarget) {
        File dir = new File("Asset\\EnemyTile\\Special.png");
        imageView = new ImageView[numberOfTarget];
        for(int i = 0; i < numberOfTarget; i++) {
            imageView[i] = new ImageView(new Image(dir.toURI().toString()));
            imageView[i].setFitHeight(Config.TILE_SIZE);
            imageView[i].setPreserveRatio(true);
            imageView[i].setViewOrder(-2);
            GameStage.mainWindowPane.getChildren().add(imageView[i]);
        }
    }

    public void cast(int numberOfTarget) {

        if(TowerButton.towerList != null && TowerButton.towerList.size() >= numberOfTarget) {
            Random randomIndex = new Random();
            targetTower = new ArrayList<>();

            for (int i = 0; i < numberOfTarget; i++) {
                targetTower.add(TowerButton.towerList.get(randomIndex.nextInt(TowerButton.towerList.size())));
            }

            loadImage(targetTower.size());

            for (int i = 0; i < targetTower.size(); i++) {
                handleAnimation(imageView[i], targetTower.get(i));
            }
        }
    }

    private void handleAnimation(ImageView image,Tower target) {

        double xPos = target.getLayoutX() + target.getWidth()/2;
        double yPos = target.getLayoutY() + target.getHeight()/2;

        Path path = new Path();
        LineTo line = new LineTo(xPos, yPos);
        path.getElements().add(new MoveTo(xPos, yPos - Config.TILE_SIZE*11));
        path.getElements().add(line);

        PathTransition movePath = new PathTransition();
        movePath.setNode(image);
        movePath.setPath(path);
        movePath.setDuration(Duration.seconds(3));
        movePath.setAutoReverse(false);
        movePath.setCycleCount(1);
        movePath.setOnFinished(event -> {
            OnDestroy(image);
            destroyTower(target);
        });
        movePath.play();
    }

    private void OnDestroy(ImageView imageView) {
        GameStage.mainWindowPane.getChildren().remove(imageView);
    }

    private void destroyTower(Tower target) {
        if(TowerButton.towerList != null) {
            for (int i = 0; i < TowerButton.towerList.size(); i++) {
                if (target == TowerButton.towerList.get(i)) {
                    TowerButton.towerList.get(i).onDestroy();
                }
            }
        }
    }

}
