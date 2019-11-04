package TowerDefense.GameTile.Tower;

import TowerDefense.Config;
import TowerDefense.GameEntity.GameEntity;
import TowerDefense.GameStage;
import TowerDefense.GameTile.Road;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.event.EventHandler;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.File;

public class Tower extends GameEntity {
    private int TowerValue;
    private int TowerLevel;
    private boolean draggable;
    private boolean canSpawnBullet;
    private static TowerType currentType;
    private float ShootRange;


    public Tower(TowerType towerType) {
        draggable = true;
        canSpawnBullet = true;
        currentType = towerType;
        loadImage(towerType);
        this.getChildren().add(image);
        setMouse();
        DragTower();
        move();
        rotateTower();
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

    private void DragTower() {

        setOnMouseDragged(event -> {
            if(draggable) {
                this.setLayoutX(event.getSceneX() - Config.TILE_SIZE);
                this.setLayoutY(event.getSceneY() - Config.TILE_SIZE);
            }
        });

        setOnMouseReleased(event -> {
            /*Kiểm tra xem nếu Tower nằm trong map và không va chạm với đường đi thì mới đặt được tháp*/
            if((this.getLayoutX() <= Config.SCREEN_WIDTH - Config.MENU_WIDTH
                    && this.getLayoutY() <= Config.SCREEN_HEIGHT - Config.MENU_HEIGHT) && !collideWithRoad()) {
                draggable = false;
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

    void rotateTower() {

        Circle circle = new Circle();
        circle.setCenterX(this.getLayoutX() + Config.TILE_SIZE);
        circle.setCenterY(this.getLayoutY() + Config.TILE_SIZE);
        circle.setRadius(Config.TILE_SIZE/2);

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
        for(int i = 0; i < Road.roadPath.length; i++) {
            if(this.getBoundsInParent().intersects(Road.roadPath[i].getBoundsInParent())) {
                System.out.println("Can't place tower on road.");
                return true;
            }
        }
        return false;
    }

    /*Bắn đạn*/
    private void spawnBullet(TowerType towerType) {
        Bullet bullet = new Bullet(towerType);
        GameStage.mainWindow.getChildren().add(bullet);
    }

    private void generateFireRange() {
        Circle fireRange = new Circle(xPos + Config.TILE_SIZE, yPos + Config.TILE_SIZE, ShootRange);
        fireRange.setFill(Color.TRANSPARENT);
        fireRange.setStroke(Color.BLUEVIOLET);
        GameStage.mainWindow.getChildren().add(fireRange);
    }

    @Override
    public boolean isDestroy() {
        return false;
    }
}
