package TowerDefense.GameTile.Tower;

import TowerDefense.Button.TowerButton;
import TowerDefense.Config;
import TowerDefense.GameEntity.Enemy.Enemy;
import TowerDefense.GameEntity.Enemy.EnemySpawner;
import TowerDefense.GameEntity.GameEntity;
import TowerDefense.GameEntity.Player.PlayerStats;
import TowerDefense.GameStage;
import TowerDefense.GameTile.Mountain;
import TowerDefense.GameTile.Road;
import javafx.animation.*;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.io.File;

public class Tower extends GameEntity {
    private int TowerValue;
    private int TowerLevel;
    private double TowerDamage;
    private double ShootRange;
    private double ShootSpeed;
    private boolean draggable;
    private boolean canSpawnBullet;
    public boolean isSelected;
    private TowerType currentType;
    private Circle fireRange = null;
    private TowerStats towerStats;

    public Tower(TowerType towerType) {
        draggable = true;
        canSpawnBullet = true;
        isSelected = false;
        currentType = towerType;
        towerStats = new TowerStats(this);
        towerStats.toggleStat(false);
        TowerLevel = 0;
        loadImage(towerType);
        displayTowerStats();
        DragTower();
        this.getChildren().add(image);
    }

    private void loadImage(TowerType type) {
        switch (type) {
            case NormalTower:
                imageLocation = new File("Asset\\TowerTile\\1.png");
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                ShootRange = 90;
                ShootSpeed = 0.5;
                TowerDamage = 0.3;
                TowerValue = 20;
                break;
            case SniperTower:
                imageLocation = new File("Asset\\TowerTile\\2.png");
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                ShootRange = 110;
                ShootSpeed = 0.7;
                TowerDamage = 0.2;
                TowerValue = 50;
                break;
            case MachineGun:
                imageLocation = new File("Asset\\TowerTile\\3.png");
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                ShootRange = 80;
                ShootSpeed = 0.2;
                TowerDamage = 0.1;
                TowerValue = 150;
                break;
            case AirTower:
                imageLocation = new File("Asset\\TowerTile\\4.png");
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                ShootRange = 80;
                ShootSpeed = 0.5;
                TowerDamage = 0.3;
                TowerValue = 50;
                break;
            case RayTower:
                imageLocation = new File("Asset\\TowerTile\\5.png");
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                ShootRange = 80;
                ShootSpeed = 0.5;
                TowerDamage = 0.3;
                TowerValue = 100;
                break;
            case IceTurret:
                imageLocation = new File("Asset\\TowerTile\\6.png");
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                ShootRange = 80;
                ShootSpeed = 0.5;
                TowerDamage = 0.3;
                TowerValue = 100;
                break;
        }
    }

    /*Thêm hiệu ứng khi di chuột vào trong khung ảnh và hiển thị thông số của tháp*/
    private void displayTowerStats() {

        image.setOnMouseEntered(mouseEvent -> {
            setEffect(new Glow());
        });

        image.setOnMouseExited(mouseEvent -> {
            setEffect(null);
        });

        image.setOnMouseReleased(event -> {
            for(int i = 0; i < TowerButton.towerList.size(); i++) {
                TowerButton.towerList.get(i).isSelected = false;
                if( TowerButton.towerList.get(i).fireRange != null)  {
                    TowerButton.towerList.get(i).fireRange.setVisible(false);
                }
                TowerButton.towerList.get(i).towerStats.toggleStat(false);
            }
            if(fireRange != null) fireRange.setVisible(true);
            towerStats.toggleStat(true);
            this.isSelected = true;
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

        setOnMouseReleased(event -> {
            /*Kiểm tra xem nếu Tower nằm trong map và không va chạm với đường đi thì mới đặt được tháp*/
            if((this.getLayoutX() <= Config.SCREEN_WIDTH - Config.MENU_WIDTH
                    && this.getLayoutY() <= Config.SCREEN_HEIGHT - Config.MENU_HEIGHT) && !collideWithRoad()) {
                draggable = false;
                snapTowerToGrid();
                Mountain.toggleVisibility(false);
                if(canSpawnBullet) {
                    generateFireRange();
                    spawnBullet();
                    canSpawnBullet = false;
                    upgradeTower();
                }
            }
        });
    }

    /*Xoay tháp theo hướng đi của địch*/
    private void rotateTower() {
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), this);
        rotateTransition.setFromAngle(90);
        rotateTransition.setToAngle(250);
        rotateTransition.setCycleCount(1);
        rotateTransition.setAutoReverse(true);
        rotateTransition.play();
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
    private void spawnBullet() {

        Timeline bulletTimeline = new Timeline(new KeyFrame(Duration.seconds(ShootSpeed), event -> {
            if(this.image != null) {
                Enemy targetEnemy = null;
                for(int i = 0; i < EnemySpawner.enemies.size(); i++) {
                    /*Kiểm tra xem quân dịch có ở trong tầm bắn không*/
                    if(EnemySpawner.enemies.get(i) != null) {
                        if (EnemySpawner.enemies.get(i).getBound().intersects(fireRange.getBoundsInParent())) {
                            targetEnemy = EnemySpawner.enemies.get(i);
                            if (targetEnemy.currentType == EnemyType.SmallerEnemy && (this.currentType != TowerType.AirTower && this.currentType != TowerType.RayTower)) return;
                            if (targetEnemy.currentType != EnemyType.SmallerEnemy && this.currentType == TowerType.AirTower) return;
                            else
                                break;
                        }
                    }
                }

                if(targetEnemy != null) {
                    /*Bắn đạn theo hướng quân địch*/
                    generateBulletPath(targetEnemy);
                    /*Xoay tháp theo hướng quân địch*/
                    rotateTower();
                }
            }
        }));
        bulletTimeline.setCycleCount(Animation.INDEFINITE);
        bulletTimeline.play();
    }

    /*Tạo tầm bắn cho tháp*/
    private void generateFireRange() {
        fireRange = new Circle(this.getLayoutX() + (float)Config.TILE_SIZE/2, this.getLayoutY() + (float)Config.TILE_SIZE/2, ShootRange);
        fireRange.setFill(Color.TRANSPARENT);
        fireRange.setStroke(Color.BLUEVIOLET);
        fireRange.setVisible(true);
        fireRange.setViewOrder(1);
        GameStage.mainWindow.getChildren().add(fireRange);
    }

    /*Tạo đường bắn cho đạn*/
    private void generateBulletPath(Enemy targetEnemy) {

        Bullet bullet = new Bullet(this.currentType);
        Line line = new Line();
        GameStage.mainWindow.getChildren().add(bullet);

        line.setStartX(this.getLayoutX() + (float) Config.TILE_SIZE / 2);
        line.setStartY(this.getLayoutY() + (float) Config.TILE_SIZE / 2);
        line.setEndX(targetEnemy.getXPos() + targetEnemy.getWidth()/2);
        line.setEndY(targetEnemy.getYPos() + targetEnemy.getHeight()/2);

        PathTransition pathTransition = new PathTransition();
        pathTransition.setNode(bullet);
        pathTransition.setPath(line);
        pathTransition.setCycleCount(1);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setDuration(Duration.seconds(0.5));
        pathTransition.setOnFinished(actionEvent -> {
            if(!targetEnemy.outOfHealth()) {
                targetEnemy.subtractHealth(TowerDamage);
            }
            else {
                targetEnemy.onDestroy();
            }
            bullet.onDestroy();
        });
        pathTransition.play();
    }

    private void snapTowerToGrid() {
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

    public void upgradeTower() {
        if(TowerLevel < 5) {
            ShootRange += 5;
            TowerDamage += 0.5;
            PlayerStats.money -= TowerValue;
            TowerValue += 10;
            TowerLevel++;
            towerStats.updateStats(this);
        }
    }

    public void sellTower() {
        int sellValue = TowerValue/3;
        onDestroy();
        PlayerStats.money += sellValue;
    }

    public void onDestroy() {
        GameStage.mainWindow.getChildren().remove(fireRange);
        this.getChildren().remove(image);
        image = null;
        towerStats.onDestroy();
        TowerButton.towerList.remove(this);
    }

    public int getTowerValue() {
        return TowerValue;
    }

    public int getTowerLevel() {
        return TowerLevel;
    }

    public double getTowerDamage() {
        return TowerDamage;
    }

    public TowerType getCurrentType() {
        return currentType;
    }

    public double getShootRange() {
        return ShootRange;
    }

}
