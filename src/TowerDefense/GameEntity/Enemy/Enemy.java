package TowerDefense.GameEntity.Enemy;

import TowerDefense.Config;
import TowerDefense.GameEntity.GameEntity;
import TowerDefense.GameEntity.Player.PlayerStats;
import TowerDefense.GameStage;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.*;
import javafx.util.Duration;

import java.io.File;

public class Enemy extends GameEntity {

    public static double speed;
    public double health = 1;
    private double armor;
    private int value;
    public EnemyType currentType;
    private ProgressBar healthBar;
    public PathTransition pathTransition;

    public Enemy(EnemyType type) {
        currentType = type;
        generateHealthBar();
        loadImage(type);
        this.getChildren().add(image);
        handleAnimation(type);
        autoDestroy();
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
                value = 10;
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
                value = 30;
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
                value = 10;
                break;
            case BossEnemy:
                imageLocation = new File("Asset\\EnemyTile\\4.png");
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                image.setFitHeight(128);
                image.setFitWidth(162);
                armor = 30;
                speed = 50;
                value = 200;
                break;
        }
    }

    public void handleAnimation(EnemyType enemyType) {

        Path movePath;
        int random = (int)(Math.random()*11);

        if(enemyType == EnemyType.SmallerEnemy) {
            movePath = new Path();

            if(random%2 == 0) {
                QuadCurveTo quadTo = new QuadCurveTo();
                quadTo.setControlX(1800.0f);
                quadTo.setControlY(250.0f);
                quadTo.setX(600.0f);
                quadTo.setY(250.0f);
                LineTo line = new LineTo();
                line.setX(0);
                line.setY(441);

                movePath.getElements().add(new MoveTo(500, 0));
                movePath.getElements().add(quadTo);
                movePath.getElements().add(new VLineTo(574));
                movePath.getElements().add(line);
            }
            else {
                QuadCurveTo quadTo = new QuadCurveTo();
                quadTo.setControlX(400.0f);
                quadTo.setControlY(-189.0f);
                quadTo.setX(0.0f);
                quadTo.setY(441.0f);

                movePath.getElements().add(new MoveTo(1179, 693));
                movePath.getElements().add(quadTo);
            }
        }

        else {
            movePath = new Path();

            /*HLineTo: Đi theo trục hoành, VLineTo: Đi theo trục tung*/
            movePath.getElements().add(new MoveTo(0, 63));
            movePath.getElements().add(new HLineTo(819));
            movePath.getElements().add(new VLineTo(252));
            movePath.getElements().add(new HLineTo(1071));
            movePath.getElements().add(new VLineTo(441));
            movePath.getElements().add(new HLineTo(567));
            if(random%2 == 0) {
                movePath.getElements().add(new VLineTo(567));
            }
            else {
                movePath.getElements().add(new VLineTo(315));
            }
            movePath.getElements().add(new HLineTo(189));
            movePath.getElements().add(new VLineTo(441));
            movePath.getElements().add(new HLineTo(0));
        }

        /*Tạo đường đi dựa trên Path đã khai báo ở trên cho quân địch*/
        pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(speed));
        pathTransition.setNode(image);
        pathTransition.setPath(movePath);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(0);
        pathTransition.setAutoReverse(false);
        /*Quân địch khi đi đến cuối đường sẽ bị hủy và người chơi bị trừ máu*/
        pathTransition.setOnFinished(actionEvent -> {
            if(!this.outOfHealth()) {
                if(!PlayerStats.restart)
                PlayerStats.subtractHealth();
            }
            onDestroy();
        });
        pathTransition.play();
    }

    @Override
    public boolean outOfHealth() {
        return health <= 0;
    }

    public void onDestroy() {
        GameStage.mainWindowPane.getChildren().remove(this);
        this.getChildren().remove(image);
        this.getChildren().remove(healthBar);
        image = null;
        healthBar = null;
        if(EnemySpawner.enemies != null)
        EnemySpawner.enemies.remove(this);
    }

    public void OnDestroyRestart() {
        this.getChildren().remove(image);
        this.getChildren().remove(healthBar);
        image = null;
        healthBar = null;
    }

    public void autoDestroy() {
        Timeline destroyTimeline = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> {
            if(outOfHealth()) {
                onDestroy();
            }
        }));
        destroyTimeline.setCycleCount(Animation.INDEFINITE);
        destroyTimeline.setAutoReverse(false);
        destroyTimeline.play();

    }

    private void generateHealthBar() {
        healthBar = new ProgressBar(this.health);
        healthBar.setPrefSize(Config.TILE_SIZE - 10, (float)Config.TILE_SIZE/6);
        healthBar.setStyle("-fx-accent: red;");
        healthBar.setViewOrder(-1);
        this.getChildren().add(healthBar);

        Timeline test = new Timeline(new KeyFrame(Duration.seconds(0.01), event -> {
            if(healthBar != null) {
                switch (this.currentType) {
                    case NormalEnemy:
                        healthBar.setTranslateX(image.getTranslateX() - 15);
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
                healthBar.setTranslateY(image.getTranslateY() - 15);
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

    public void onEndureTowerSpecial(TowerType currentType) {
        if(currentType == TowerType.IceTurret) {
            Timeline test = new Timeline(new KeyFrame(Duration.millis(100), event -> {
                pathTransition.pause();
            }));
            test.setCycleCount(5);
            test.setOnFinished(event-> {
                pathTransition.play();
            });
            test.play();
        }
    }

    public void increaseStatByLevel(int level) {
        switch (currentType) {
            case NormalEnemy:
                armor += level;
                value += 2;
                break;
            case TankerEnemy:
                armor += level*1.2;
                value += 3;
                break;
            case SmallerEnemy:
                armor += level*0.6;
                value += 3;
                break;
            case BossEnemy:
                armor += level*30;
                value += 10;
                break;
        }
    }

    public double getXPos() {
        return image.getTranslateX();
    }

    public double getYPos() {
        return image.getTranslateY();
    }

    public double getImageWidth() {
        return this.getWidth();
    }

    public double getImageHeight() {
        return this.getHeight();
    }

    public Bounds getBound() {
        return image.getBoundsInParent();
    }

    public int getValue() {
        return value;
    }

    public double getArmor() {
        return armor;
    }

}
