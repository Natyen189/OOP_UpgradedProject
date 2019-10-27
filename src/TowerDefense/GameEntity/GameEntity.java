package TowerDefense.GameEntity;

import TowerDefense.GameEntity.Player.Tower;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.File;

public abstract class GameEntity extends VBox {

    protected ImageView image;
    protected File imageLocation;
    protected int health;

    public enum EnemyType {
        NormalEnemy,
        TankerEnemy,
        SmallerEnemy,
        BossEnemy
    }

    public enum TowerType {
        NormalTower,
        SniperTower,
        MachineGunTower
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
