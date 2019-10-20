package TowerDefense.GameEntity.Enemy;

import TowerDefense.GameEntity.GameEntity;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;

import java.io.File;

public class Enemy extends GameEntity {

    private double startXPos = 0;
    private double startYPos = 200;

    public Enemy(EnemyType type) {
        loadImage(type);
        this.getChildren().add(enemyImage);
        move();
    }

    @Override
    public void loadImage(EnemyType type) {
        switch (type) {
            case NormalEnemy:
                imageLocation = new File("Asset\\EnemyTile\\1.png");
                enemyImage = new ImageView(new Image(imageLocation.toURI().toString()));
                break;
            case TankerEnemy:
                imageLocation = new File("Asset\\EnemyTile\\2.png");
                enemyImage = new ImageView(new Image(imageLocation.toURI().toString()));
                break;
            case SmallerEnemy:
                imageLocation = new File("Asset\\EnemyTile\\3.png");
                enemyImage = new ImageView(new Image(imageLocation.toURI().toString()));
                break;
            case BossEnemy:
                imageLocation = new File("Asset\\EnemyTile\\4.png");
                enemyImage = new ImageView(new Image(imageLocation.toURI().toString()));
                break;
        }
    }

    @Override
    public void move() {

        Path movePath = new Path();
        movePath.getElements().add(new MoveTo(0,318));
        movePath.getElements().add(new HLineTo(256));
        movePath.getElements().add(new VLineTo(574));
        movePath.getElements().add(new HLineTo(640));
        movePath.getElements().add(new VLineTo(126));
        movePath.getElements().add(new HLineTo(960));

        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(15000));
        pathTransition.setNode(enemyImage);
        pathTransition.setPath(movePath);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(Animation.INDEFINITE);
        pathTransition.setAutoReverse(false);

        pathTransition.play();

    }

    @Override
    public boolean isDestroy() {
        return health == 0;
    }


}
