package TowerDefense.GameTile.Tower;

import TowerDefense.Button.MenuButton;
import TowerDefense.Button.TowerButton;
import TowerDefense.Config;
import TowerDefense.GameEntity.Enemy.Enemy;
import TowerDefense.GameEntity.Enemy.EnemySpawner;
import TowerDefense.GameEntity.GameEntity;
import TowerDefense.GameEntity.Player.HighScore;
import TowerDefense.GameEntity.Player.PlayerStats;
import TowerDefense.GameStage;
import TowerDefense.GameTile.Mountain;
import TowerDefense.GameTile.Road;
import javafx.animation.*;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.effect.ColorAdjust;
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
    private int TowerUpgradeCost;
    private int TowerSellValue;
    private double TowerDamage;
    private double ShootRange;
    private double ShootSpeed;
    private boolean canSpawnBullet;
    public boolean draggable;
    public boolean isSelected;
    private TowerType currentType;
    private Timeline bulletTimeline = null;
    private Circle fireRange = null;
    private double prevAngle = 0;
    public TowerStats towerStats;

    public Tower(TowerType towerType) {
        loadImage(towerType);
        displayTowerStats();
        DragTower();
        draggable = true;
        canSpawnBullet = true;
        isSelected = false;
        currentType = towerType;
        TowerLevel = 1;
        TowerUpgradeCost = TowerValue;
        towerStats = new TowerStats(this);
        towerStats.toggleStat(false);
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
                ShootRange = 150;
                ShootSpeed = 0.7;
                TowerDamage = 0.7;
                TowerValue = 70;
                break;
            case MachineGun:
                imageLocation = new File("Asset\\TowerTile\\3.png");
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                ShootRange = 90;
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
                ShootRange = 90;
                ShootSpeed = 0.5;
                TowerDamage = 0.05;
                TowerValue = 100;
                break;
            case IceTurret:
                imageLocation = new File("Asset\\TowerTile\\6.png");
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                ShootRange = 90;
                ShootSpeed = 0.8;
                TowerDamage = 0.01;
                TowerValue = 100;
                break;
        }

        image.setFitWidth(50);
        image.setPreserveRatio(true);
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

        GameStage.mainWindowPane.setOnMouseMoved(event -> {
            if(draggable && MenuButton.startGame) {
                this.setLayoutX(event.getSceneX() - (float)Config.TILE_SIZE/2);
                this.setLayoutY(event.getSceneY() - (float)Config.TILE_SIZE/2);
                Mountain.toggleVisibility(true);
            }
        });

        setOnMouseReleased(event -> {
            /*Kiểm tra xem nếu Tower nằm trong map và không va chạm với đường đi thì mới đặt được tháp*/
            if((this.getLayoutX() <= Config.SCREEN_WIDTH - Config.MENU_WIDTH
                    && this.getLayoutY() <= Config.SCREEN_HEIGHT - Config.MENU_HEIGHT) && !collideWithRoad() && !collideWithSelf()) {
                draggable = false;
                Mountain.toggleVisibility(false);
                if(canSpawnBullet) {
                    snapTowerToGrid();
                    generateFireRange();
                    spawnBullet();
                    canSpawnBullet = false;
                }
            }
        });
    }

    /*Xoay tháp theo hướng đi của địch*/
    private void rotateTower(Enemy targetEnemy) {

        Point2D a = new Point2D(targetEnemy.getXPos(), targetEnemy.getYPos());
        Point2D b = new Point2D(this.getLayoutX(), this.getLayoutY());
        double angle = calculateAngle(a,b);

        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.5), this);
        rotateTransition.setFromAngle(prevAngle);
        rotateTransition.setToAngle(angle);
        rotateTransition.setCycleCount(1);
        rotateTransition.setAutoReverse(false);
        rotateTransition.play();

        prevAngle = angle;
    }

    /*Kiểm tra va chạm với đường đi*/
    private boolean collideWithRoad() {

        ColorAdjust[]colorAdjust = new ColorAdjust[2];
        colorAdjust[0] = new ColorAdjust();
        colorAdjust[1] = new ColorAdjust();
        colorAdjust[0].setBrightness(-10);
        colorAdjust[1].setBrightness(0);

        for(int i = 0; i < Road.roadPath.length; i++) {
            if(this.getBoundsInParent().intersects(Road.roadPath[i].getBoundsInParent()) && draggable) {
                System.out.println("Can't place tower on road.");
                image.setEffect(colorAdjust[0]);
                return true;
            }
        }

        image.setEffect(colorAdjust[1]);
        return false;
    }

    /*Kiểm tra xem có bị trùng chỗ đặt tháp không*/
    private boolean collideWithSelf() {

        ColorAdjust[]colorAdjust = new ColorAdjust[2];
        colorAdjust[0] = new ColorAdjust();
        colorAdjust[1] = new ColorAdjust();
        colorAdjust[0].setBrightness(-10);
        colorAdjust[1].setBrightness(0);

        for(int i = TowerButton.towerList.size() - 2; i >= 0; i--) {
            if(this.getBoundsInParent().intersects(TowerButton.towerList.get(i).getTowerBound())
                    && draggable && this != TowerButton.towerList.get(i)) {

                System.out.println("This position is occupied.");
                image.setEffect(colorAdjust[0]);
                return true;
            }
        }

        image.setEffect(colorAdjust[1]);
        return false;
    }

    /*Bắn đạn*/
    private void spawnBullet() {

        bulletTimeline = new Timeline(new KeyFrame(Duration.seconds(ShootSpeed), event -> {
            if(this.image != null) {
                Enemy targetEnemy = null;
                if(EnemySpawner.enemies != null) {
                    for (int i = 0; i < EnemySpawner.enemies.size(); i++) {
                        /*Kiểm tra xem quân dịch có ở trong tầm bắn không*/
                        if (EnemySpawner.enemies.get(i) != null && fireRange != null) {
                            if (EnemySpawner.enemies.get(i).getBound().intersects(fireRange.getBoundsInParent())) {
                                targetEnemy = EnemySpawner.enemies.get(i);
                                /*Nếu quân địch là máy bay và tháp không phải loại AirTower hoặc RayTower thì không được bắn*/
                                if (targetEnemy.currentType == EnemyType.SmallerEnemy && (this.currentType != TowerType.AirTower && this.currentType != TowerType.RayTower))
                                    targetEnemy = null;
                                /*Nếu tháp hiện tại là AirTower mà quân địch không phải máy bay thì không được bắn*/
                                else if (targetEnemy.currentType != EnemyType.SmallerEnemy && this.currentType == TowerType.AirTower)
                                    targetEnemy = null;
                                /*RayTower có thể bắn nhiều quân địch cùng một lúc*/
                                else if (this.currentType == TowerType.RayTower) {
                                    generateBulletPath(EnemySpawner.enemies.get(i));
                                    rotateTower(targetEnemy);
                                }
                                else
                                    break;
                            }
                        }
                    }

                    if (targetEnemy != null) {
                        /*Bắn đạn theo hướng quân địch*/
                        generateBulletPath(targetEnemy);
                        /*Xoay tháp theo hướng quân địch*/
                        rotateTower(targetEnemy);
                    }
                }
            }
        }));
        bulletTimeline.setCycleCount(Animation.INDEFINITE);
        bulletTimeline.play();
    }

    /*Tạo đường bắn cho đạn*/
    private void generateBulletPath(Enemy targetEnemy) {

        Bullet bullet = new Bullet(this.currentType);
        Line line = new Line();
        GameStage.mainWindowPane.getChildren().add(bullet);

        line.setStartX(this.getLayoutX() + this.getWidth()/ 2);
        line.setStartY(this.getLayoutY() + this.getHeight()/ 2);
        line.setEndX(targetEnemy.getXPos() + targetEnemy.getImageWidth()/2);
        line.setEndY(targetEnemy.getYPos() + targetEnemy.getImageHeight()/2);

        PathTransition pathTransition = new PathTransition();
        pathTransition.setNode(bullet);
        pathTransition.setPath(line);
        pathTransition.setCycleCount(1);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setDuration(Duration.seconds(ShootSpeed));
        pathTransition.setOnFinished(actionEvent -> {
            if(!targetEnemy.outOfHealth()) {
                targetEnemy.subtractHealth(TowerDamage);
                targetEnemy.onEndureTowerSpecial(currentType);
            }
            else {
                PlayerStats.money += targetEnemy.getValue();
                HighScore.playerScore += targetEnemy.getValue()*targetEnemy.getArmor();
                targetEnemy.onDestroy();
            }
            bullet.onDestroy();
        });
        pathTransition.play();
    }

    /*Tạo tầm bắn cho tháp*/
    private void generateFireRange() {
        fireRange = new Circle(this.getLayoutX() + this.getWidth()/2, this.getLayoutY() + this.getHeight()/2, ShootRange);
        fireRange.setFill(Color.BLUEVIOLET);
        fireRange.setOpacity(0.3);
        fireRange.setVisible(true);
        fireRange.setViewOrder(0);
        GameStage.mainWindowPane.getChildren().add(fireRange);
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

        this.setLayoutX(xPos + (Config.TILE_SIZE - this.getWidth())/2);
        this.setLayoutY(yPos + (Config.TILE_SIZE - this.getHeight())/2);
    }

    private double calculateAngle(Point2D a, Point2D b) {
        return 180 - Math.toDegrees(Math.atan2(a.getX() - b.getX(), a.getY() - b.getY()));
    }

    public void upgradeTower() {
        if(TowerLevel < 5 && (PlayerStats.money - TowerUpgradeCost >= 0)) {
            switch (currentType) {
                case NormalTower:
                    TowerDamage += 0.3;
                    break;
                case SniperTower:
                    TowerDamage += 0.5;
                    break;
                case MachineGun:
                    TowerDamage += 0.2;
                    break;
                case AirTower:
                    TowerDamage += 0.3;
                    break;
                case RayTower:
                    TowerDamage += 0.06;
                    break;
                case IceTurret:
                    TowerDamage += 0.02;
                    break;
            }

            if(TowerLevel > 1)
            TowerUpgradeCost += TowerValue;
            TowerSellValue = TowerUpgradeCost/2;
            ShootRange += 5;
            fireRange.setRadius(ShootRange);
            PlayerStats.money -= TowerUpgradeCost;
            TowerLevel++;
            towerStats.updateStats(this);
        }
    }

    public void sellTower() {
        onDestroy();
        PlayerStats.money += TowerSellValue;
    }

    public void onDestroy() {
        GameStage.mainWindowPane.getChildren().remove(fireRange);
        GameStage.mainWindowPane.getChildren().remove(this);
        fireRange = null;
        this.getChildren().remove(image);
        image = null;
        towerStats.onDestroy();
        bulletTimeline.stop();
        bulletTimeline = null;
        TowerButton.towerList.remove(this);
    }

    public void OnDestroyRestart() {
        GameStage.mainWindowPane.getChildren().remove(fireRange);
        GameStage.mainWindowPane.getChildren().remove(this);
        fireRange = null;
        this.getChildren().remove(image);
        image = null;
        towerStats.onDestroy();
        if(bulletTimeline != null)
        bulletTimeline.stop();
        bulletTimeline = null;
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

    public int getTowerUpgradeCost() {
        return TowerUpgradeCost;
    }

    public int getTowerSellValue() {
        return TowerSellValue;
    }

    public TowerType getCurrentType() {
        return currentType;
    }

    public double getShootRange() {
        return ShootRange;
    }


    public Bounds getTowerBound() {
        return this.getBoundsInParent();
    }

}
