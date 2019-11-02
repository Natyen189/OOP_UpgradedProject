package TowerDefense.GameEntity;

import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.File;

public abstract class GameEntity extends VBox {

    protected ImageView image;
    protected File imageLocation;
    protected int health;
    protected double posX;
    protected double posY;

    public enum EnemyType {
        NormalEnemy,
        TankerEnemy,
        SmallerEnemy,
        BossEnemy
    }

    public enum TowerType {
        NormalTower,
        SniperTower,
        MachineGunTower,
        UnnamedTower,
        IceTurret,
        RayTower

    }

    public void loadImage() {

    }

    public void move() {

    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public abstract boolean isDestroy();

}
