package TowerDefense.GameEntity.Enemy;

import TowerDefense.GameEntity.GameEntity;
import javafx.animation.PathTransition;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.*;
import javafx.util.Duration;

import java.io.File;

public class Enemy extends GameEntity {

    protected double speed;
    protected int value;

    public Enemy(EnemyType type) {
        loadImage(type);
        this.getChildren().add(image);
        xPos = image.getLayoutX();
        yPos = image.getLayoutY();
        move();
    }

    public void loadImage(EnemyType type) {
        switch (type) {
            case NormalEnemy:
                imageLocation = new File("Asset\\EnemyTile\\1.png");
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                break;
            case TankerEnemy:
                imageLocation = new File("Asset\\EnemyTile\\2.png");
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                break;
            case SmallerEnemy:
                imageLocation = new File("Asset\\EnemyTile\\3.png");
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                break;
            case BossEnemy:
                imageLocation = new File("Asset\\EnemyTile\\4.png");
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                image.setFitHeight(200);
                image.setFitWidth(200);
                break;
        }
    }

    @Override
    public void move() {

        Path movePath = new Path();

        /*HLineTo: Đi theo trục hoành, VLineTo: Đi theo trục tung*/
        movePath.getElements().add(new MoveTo(0,318));
        movePath.getElements().add(new HLineTo(256));
        movePath.getElements().add(new VLineTo(574));
        movePath.getElements().add(new HLineTo(640));
        movePath.getElements().add(new VLineTo(126));
        movePath.getElements().add(new HLineTo(960));

        /*Tạo đường đi dựa trên Path đã khai báo ở trên cho quân địch*/
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(25000));
        pathTransition.setNode(image);
        pathTransition.setPath(movePath);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(0);
        pathTransition.setAutoReverse(false);
        /*Quân địch khi đi đến cuối path sẽ bị hủy*/
        pathTransition.setOnFinished(actionEvent -> {
            onDestroy();
            System.out.println("Enemy removed.");
        });
        pathTransition.play();

    }

    @Override
    public boolean isDestroy() {
        return health == 0;
    }

    public void onDestroy() {
        this.getChildren().remove(image);
        EnemySpawner.enemies.remove(this);
        image = null;
    }

    public double getXPos() {
        return image.getTranslateX();
    }

    public double getYPos() {
        return image.getTranslateY();
    }

    public Bounds getBound() {
        return image.getBoundsInParent();
    }

}
