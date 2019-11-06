package TowerDefense.GameTile.Tower;

import TowerDefense.Config;
import TowerDefense.GameEntity.Enemy.Enemy;
import TowerDefense.GameEntity.Enemy.EnemySpawner;
import TowerDefense.GameEntity.GameEntity;
import TowerDefense.GameStage;
import TowerDefense.GameTile.Mountain;
import TowerDefense.GameTile.Road;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

import java.io.File;

public class Tower extends GameEntity {
    private int TowerValue;
    private int TowerLevel;
    private boolean draggable;
    private boolean canSpawnBullet;
    private static TowerType currentType;
    private float ShootRange;
    private Circle fireRange = null;

    public Tower(TowerType towerType) {
        draggable = true;
        canSpawnBullet = true;
        currentType = towerType;
        loadImage(towerType);
        setMouse();
        DragTower();
        move();
        rotateTower();
        this.getChildren().add(image);
    }

    private void loadImage(TowerType type) {
        switch (type) {
            case NormalTower:
                imageLocation = new File("Asset\\TowerTile\\1.png");
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                ShootRange = 110;
                break;
            case SniperTower:
                imageLocation = new File("Asset\\TowerTile\\2.png");
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                ShootRange = 130;
                break;
            case MachineGunTower:
                imageLocation = new File("Asset\\TowerTile\\3.png");
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                ShootRange = 90;
                break;
            case UnnamedTower:
                imageLocation = new File("Asset\\TowerTile\\4.png");
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                ShootRange = 100;
                break;
            case RayTower:
                imageLocation = new File("Asset\\TowerTile\\5.png");
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                ShootRange = 100;
                break;
            case IceTurret:
                imageLocation = new File("Asset\\TowerTile\\6.png");
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                ShootRange = 100;
                break;
        }
    }

    /*Thêm hiệu ứng khi di chuột vào trong khung ảnh*/
    private void setMouse() {

        setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(new Glow());
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(null);
            }
        });
    }

    /*Quản lý thao tác kéo thả tháp*/
    private void DragTower() {

        setOnMouseDragged(event -> {
            if(draggable) {
                this.setLayoutX(event.getSceneX() - (float)Config.TILE_SIZE/2);
                this.setLayoutY(event.getSceneY() - (float)Config.TILE_SIZE/2);
                Mountain.toggleVisibility(true);
            }
        });

        setOnMouseClicked(event -> {
            /*Kiểm tra xem nếu Tower nằm trong map và không va chạm với đường đi thì mới đặt được tháp*/
            if((this.getLayoutX() <= Config.SCREEN_WIDTH - Config.MENU_WIDTH
                    && this.getLayoutY() <= Config.SCREEN_HEIGHT - Config.MENU_HEIGHT) && !collideWithRoad()) {
                draggable = false;
                snapTowerToGrid();
                Mountain.toggleVisibility(false);
                xPos = this.getLayoutX();
                yPos = this.getLayoutY();
                generateFireRange();
                if(canSpawnBullet) {
                    spawnBullet(currentType);
                    canSpawnBullet = false;
                }
            }
        });
    }

    /*Xoay tháp theo hướng đi của địch*/
    void rotateTower() {

        Circle circle = new Circle();
        circle.setCenterX(this.getLayoutX() + (float)Config.TILE_SIZE/2);
        circle.setCenterY(this.getLayoutY() + (float)Config.TILE_SIZE/2);
        circle.setRadius((float)Config.TILE_SIZE/4);

        PathTransition rotateTimeline = new PathTransition();
        rotateTimeline.setDuration(Duration.millis(5000));
        rotateTimeline.setPath(circle);
        rotateTimeline.setNode(this);
        rotateTimeline.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        rotateTimeline.setCycleCount(Animation.INDEFINITE);
        rotateTimeline.play();
    }

    /*Kiểm tra va chạm với đường đi*/
    private boolean collideWithRoad() {
        ColorAdjust adjust = new ColorAdjust();
        for(int i = 0; i < Road.roadPath.length; i++) {
            if(this.getBoundsInParent().intersects(Road.roadPath[i].getBoundsInParent())) {
                adjust.setBrightness(-1);
                this.setEffect(adjust);
                System.out.println("Can't place tower on road.");
                return true;
            }
        }
        return false;
    }

    /*Bắn đạn*/
    private void spawnBullet(TowerType towerType) {
        Line line = new Line();

        Timeline test = new Timeline(new KeyFrame(Duration.seconds(0.3), event -> {
            Enemy temp = null;
            for(int i = 0; i < EnemySpawner.enemies.size(); i++) {
                /*Kiểm tra xem quân dịch có ở trong tầm bắn không*/
                if (EnemySpawner.enemies.get(i).getBound().intersects(fireRange.getBoundsInParent())) {
                    temp = EnemySpawner.enemies.get(i);
                    break;
                }
            }

            if(temp != null) {
                Bullet bullet = new Bullet(towerType);
                GameStage.mainWindow.getChildren().add(bullet);

                line.setStartX(this.getLayoutX() + (float)Config.TILE_SIZE/2);
                line.setStartY(this.getLayoutY() + (float)Config.TILE_SIZE/2);
                line.setEndX(temp.getXPos() + (float)Config.TILE_SIZE/2);
                line.setEndY(temp.getYPos() + (float)Config.TILE_SIZE/2);

                PathTransition pathTransition = new PathTransition();
                pathTransition.setNode(bullet);
                pathTransition.setPath(line);
                pathTransition.setCycleCount(1);
                pathTransition.setDuration(Duration.seconds(0.3));
                pathTransition.setOnFinished(actionEvent -> {
                    bullet.onDestroy();
                });
                pathTransition.play();
            }
        }));
        test.setCycleCount(Animation.INDEFINITE);
        test.play();

    }

    /*Tạo tầm bắn cho tháp*/
    private void generateFireRange() {
        fireRange = new Circle(xPos + (float)Config.TILE_SIZE/2, yPos + (float)Config.TILE_SIZE/2, ShootRange);
        fireRange.setFill(Color.TRANSPARENT);
        fireRange.setStroke(Color.BLUEVIOLET);
        GameStage.mainWindow.getChildren().add(fireRange);
    }

    void snapTowerToGrid() {
        double distance = Double.POSITIVE_INFINITY;
        double xPos = 0;
        double yPos = 0;

        for(int i = 0; i < Config.TILE_VERTICAL; i++) {
            for(int j = 0; j < Config.TILE_HORIZONTAL; j++) {
                if(Mountain.mapTile[i][j] != null) {
                    double temp = Math.sqrt(Math.pow(this.getLayoutX() - Mountain.mapTile[i][j].getX(),2)
                            + Math.pow(this.getLayoutY() - Mountain.mapTile[i][j].getY(),2));
                    if(temp <= distance) {
                        distance = temp;
                        xPos = Mountain.mapTile[i][j].getX();
                        yPos = Mountain.mapTile[i][j].getY();
                    }
                }
            }
        }
        this.setLayoutX(xPos);
        this.setLayoutY(yPos);
    }


    @Override
    public boolean isDestroy() {
        return false;
    }
}
