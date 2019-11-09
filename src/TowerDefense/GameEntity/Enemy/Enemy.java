package TowerDefense.GameEntity.Enemy;

import TowerDefense.GameEntity.GameEntity;
import TowerDefense.GameEntity.Player.PlayerStats;
import TowerDefense.GameStage;
import javafx.animation.PathTransition;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.*;
import javafx.util.Duration;

import java.io.File;

public class Enemy extends GameEntity {

    public static double speed;
    public double health = 1;
    public EnemyType currentType;

    public Enemy(EnemyType type) {
        currentType = type;
        loadImage(type);
        this.getChildren().add(image);
        move(type);
    }

    public void loadImage(EnemyType type) {

        int random = (int)(Math.random()*11);

        switch (type) {
            case NormalEnemy:
                if(random % 2 == 0) {
                    imageLocation = new File("Asset\\EnemyTile\\1.png");
                }
                else {
                    imageLocation = new File("Asset\\EnemyTile\\1.1.png");
                }
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                speed = 35;
                break;
            case TankerEnemy:
                if(random % 2 == 0) {
                    imageLocation = new File("Asset\\EnemyTile\\2.png");
                }
                else {
                    imageLocation = new File("Asset\\EnemyTile\\2.1.png");
                }
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                speed = 40;
                break;
            case SmallerEnemy:
                if(random % 2 == 0) {
                    imageLocation = new File("Asset\\EnemyTile\\3.png");
                }
                else {
                    imageLocation = new File("Asset\\EnemyTile\\3.1.png");
                }
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                speed = 25;
                break;
            case BossEnemy:
                imageLocation = new File("Asset\\EnemyTile\\4.png");
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                image.setFitHeight(192);
                image.setFitWidth(192);
                speed = 50;
                break;
        }
    }

    public void move(EnemyType enemyType) {

        Path movePath;

        if(enemyType != EnemyType.SmallerEnemy) {
            movePath = new Path();

            /*HLineTo: Đi theo trục hoành, VLineTo: Đi theo trục tung*/
            movePath.getElements().add(new MoveTo(0, 318));
            movePath.getElements().add(new HLineTo(256));
            movePath.getElements().add(new VLineTo(574));
            movePath.getElements().add(new HLineTo(640));
            movePath.getElements().add(new VLineTo(126));
            movePath.getElements().add(new HLineTo(960));
        }
        else {
            movePath = new Path();
            QuadCurveTo quadTo = new QuadCurveTo();
            quadTo.setControlX(200.0f);
            quadTo.setControlY(-100.0f);
            quadTo.setX(400.0f);
            quadTo.setY(318);
            LineTo line = new LineTo();
            line.setX(960);
            line.setY(126);

            movePath.getElements().add(new MoveTo(0, 318));
            movePath.getElements().add(quadTo);
            movePath.getElements().add(new VLineTo(500));
            movePath.getElements().add(line);
        }

        /*Tạo đường đi dựa trên Path đã khai báo ở trên cho quân địch*/
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(speed));
        pathTransition.setNode(image);
        pathTransition.setPath(movePath);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(0);
        pathTransition.setAutoReverse(false);
        /*Quân địch khi đi đến cuối đường sẽ bị hủy và người chơi bị trừ máu*/
        pathTransition.setOnFinished(actionEvent -> {
            onDestroy();
            if(!this.outOfHealth())
            PlayerStats.subtractHealth();
        });
        pathTransition.play();
    }

    @Override
    public boolean outOfHealth() {
        return health <= 0;
    }

    public void onDestroy() {
        this.getChildren().remove(image);
        image = null;
        EnemySpawner.enemies.remove(this);
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

    public void increaseHealthByLevel(int level) {
        health += level*0.2;
    }

}
