package TowerDefense.GameTile.Tower;

import TowerDefense.Config;
import TowerDefense.GameEntity.Enemy.Enemy;
import TowerDefense.GameEntity.GameEntity;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.io.File;

public class Bullet extends GameEntity {

    private float speed;
    private float damage;

    public Bullet(GameEntity.TowerType towerType) {
        loadBulletImage(towerType);
        this.getChildren().add(image);
        move();
    }

    public void loadBulletImage(GameEntity.TowerType towerType) {
        switch (towerType) {
            case NormalTower:
                imageLocation = new File("D:\\OOP Project\\OOP\\Asset\\BulletTile\\Normal.png");
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                break;
            case SniperTower:
                imageLocation = new File("D:\\OOP Project\\OOP\\Asset\\BulletTile\\Sniper.png");
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                break;
            case MachineGunTower:
                imageLocation = new File("D:\\OOP Project\\OOP\\Asset\\BulletTile\\Machine.png");
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                break;
            case UnnamedTower:
                imageLocation = new File("D:\\OOP Project\\OOP\\Asset\\BulletTile\\Unamed.png");
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                break;
            case IceTurret:
                imageLocation = new File("D:\\OOP Project\\OOP\\Asset\\BulletTile\\Turret.png");
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                break;
            case RayTower:
                imageLocation = new File("D:\\OOP Project\\OOP\\Asset\\BulletTile\\Ray.png");
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                 break;
        }
    }

    public void move() {
        Path shootingPath = new Path();
        shootingPath.getElements().add(new MoveTo(Tower.xPos + Config.TILE_SIZE,Tower.yPos + Config.TILE_SIZE));
        shootingPath.getElements().add(new HLineTo(100));

        PathTransition path = new PathTransition();
        path.setNode(image);
        path.setPath(shootingPath);
        path.setDuration(Duration.millis(1000));
        path.setCycleCount(Animation.INDEFINITE);
        path.setAutoReverse(false);
        path.setOnFinished(event-> {
            onDestroy();
        });
        path.play();
    }

    @Override
    public boolean isDestroy() {
        return false;
    }

    public void onDestroy() {
        this.getChildren().remove(image);
        image = null;
    }
}
