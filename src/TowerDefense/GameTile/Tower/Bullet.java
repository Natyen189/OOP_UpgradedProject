package TowerDefense.GameTile.Tower;

import TowerDefense.GameEntity.GameEntity;
import TowerDefense.GameStage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class Bullet extends GameEntity {

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
            case MachineGun:
                imageLocation = new File("D:\\OOP Project\\OOP\\Asset\\BulletTile\\Machine.png");
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                break;
            case AirTower:
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

    public void onDestroy() {
        GameStage.mainWindowPane.getChildren().remove(this);
        this.getChildren().remove(image);
        image = null;
    }
}
