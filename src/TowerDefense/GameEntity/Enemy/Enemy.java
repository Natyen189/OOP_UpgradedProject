package TowerDefense.GameEntity.Enemy;

import TowerDefense.Config;
import TowerDefense.GameEntity.GameEntity;
import TowerDefense.GameEntity.Player.PlayerStats;
import TowerDefense.GameStage;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.shape.*;
import javafx.util.Duration;

import java.io.File;

public class Enemy extends GameEntity {

    public static double speed;
    public double health = 1;
    private double armor;
    public EnemyType currentType;
    private ProgressBar healthBar;

    public Enemy(EnemyType type) {
        currentType = type;
        generateHealthBar();
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
                armor = 1;
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
                armor = 3;
                break;
            case SmallerEnemy:
                if(random % 2 == 0) {
                    imageLocation = new File("Asset\\EnemyTile\\3.png");
                }
                else {
                    imageLocation = new File("Asset\\EnemyTile\\3.1.png");
                }
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                armor = 0.7;
                speed = 20;
                break;
            case BossEnemy:
                imageLocation = new File("Asset\\EnemyTile\\4.png");
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                image.setFitHeight(128);
                image.setFitWidth(162);
                armor = 30;
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
            quadTo.setY(318.0f);
            LineTo line = new LineTo();
            line.setX(960);
            line.setY(126);

            movePath.getElements().add(new MoveTo(0, 318));
            movePath.getElements().add(quadTo);
            movePath.getElements().add(new VLineTo(574));
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
        this.getChildren().remove(healthBar);
        image = null;
        healthBar = null;
        EnemySpawner.enemies.remove(this);
    }

    private void generateHealthBar() {
        healthBar = new ProgressBar(this.health);
        healthBar.setPrefSize(Config.TILE_SIZE - 10, (float)Config.TILE_SIZE/6);
        healthBar.setStyle("-fx-accent: red;");
        this.getChildren().add(healthBar);

        Timeline test = new Timeline(new KeyFrame(Duration.seconds(0.01), event -> {
            if(healthBar != null) {
                switch (this.currentType) {
                    case NormalEnemy:
                        healthBar.setTranslateX(image.getTranslateX() - 10);
                        break;
                    case TankerEnemy:
                        healthBar.setTranslateX(image.getTranslateX());
                        break;
                    case SmallerEnemy:
                        healthBar.setTranslateX(image.getTranslateX() + 10);
                        break;
                    case BossEnemy:
                        healthBar.setTranslateX(image.getTranslateX() + 50);
                        break;
                }
                healthBar.setTranslateY(image.getTranslateY() - 20);
            }
        }));
        test.setCycleCount(Animation.INDEFINITE);
        test.setAutoReverse(false);
        test.play();
    }

    public void subtractHealth(double damage) {
        if(healthBar != null) {
            health -= damage/armor;
            healthBar.setProgress(health);
        }
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
        armor += level*0.2;
    }

}
