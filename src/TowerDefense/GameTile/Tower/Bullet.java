package TowerDefense.GameTile.Tower;

import TowerDefense.Config;
import TowerDefense.GameEntity.Enemy.Enemy;
import TowerDefense.GameEntity.Enemy.EnemySpawner;
import TowerDefense.GameEntity.GameEntity;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
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

    @Override
    public boolean isDestroy() {
        return false;
    }

    public void onDestroy() {
        this.getChildren().remove(image);
        image = null;
    }
}
