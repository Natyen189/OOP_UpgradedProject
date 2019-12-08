package TowerDefense.GameEntity;

import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.File;

public abstract class GameEntity extends Pane {

    protected ImageView image;
    protected File imageLocation;

    public enum EnemyType {
        NormalEnemy,
        TankerEnemy,
        SmallerEnemy,
        BossEnemy
    }

    public enum TowerType {
        NormalTower,
        SniperTower,
        MachineGun,
        AirTower,
        IceTurret,
        RayTower
    }

    public void loadImage() {

    }

    public void handleAnimation() {}

    public boolean outOfHealth() {return false;}

}
