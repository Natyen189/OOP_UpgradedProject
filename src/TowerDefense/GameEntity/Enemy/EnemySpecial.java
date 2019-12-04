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
    private Image[] explode = new Image[17];
    private int frame = 0;

    public EnemySpecial() {
        loadGif();
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
            displayExplosion(target.getLayoutX(), target.getLayoutY());
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

    private void loadGif() {

        explode[0] = (new Image("file:Asset\\Explode\\10000.png"));
        explode[1] = (new Image("file:Asset\\Explode\\10001.png"));
        explode[2] = (new Image("file:Asset\\Explode\\10002.png"));
        explode[3] = (new Image("file:Asset\\Explode\\10003.png"));
        explode[4] = (new Image("file:Asset\\Explode\\10004.png"));
        explode[5] = (new Image("file:Asset\\Explode\\10005.png"));
        explode[6] = (new Image("file:Asset\\Explode\\10006.png"));
        explode[7] = (new Image("file:Asset\\Explode\\10007.png"));
        explode[8] = (new Image("file:Asset\\Explode\\10008.png"));
        explode[9] = (new Image("file:Asset\\Explode\\10009.png"));;
        explode[10] = (new Image("file:Asset\\Explode\\10010.png"));
        explode[11] = (new Image("file:Asset\\Explode\\10011.png"));
        explode[12] = (new Image("file:Asset\\Explode\\10012.png"));
        explode[13] = (new Image("file:Asset\\Explode\\10013.png"));
        explode[14] = (new Image("file:Asset\\Explode\\10014.png"));
        explode[15] = (new Image("file:Asset\\Explode\\10015.png"));
        explode[16] = (new Image("file:Asset\\Explode\\10016.png"));

    }

    private void displayExplosion(double xPos, double yPos) {

        ImageView test = new ImageView();
        test.setFitWidth(Config.TILE_SIZE);
        test.setPreserveRatio(true);
        test.setLayoutX(xPos);
        test.setLayoutY(yPos);
        GameStage.mainWindowPane.getChildren().add(test);

        Timeline displayTimeline = new Timeline(new KeyFrame(Duration.seconds(0.05), event -> {
            if(frame < 17)
            test.setImage(explode[frame++]);
        }));
        displayTimeline.setCycleCount(explode.length);
        displayTimeline.setAutoReverse(false);
        displayTimeline.setOnFinished(event-> {
            GameStage.mainWindowPane.getChildren().remove(test);
            frame = 0;
        });
        displayTimeline.play();
    }
}
