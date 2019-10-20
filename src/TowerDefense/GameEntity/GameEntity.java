package TowerDefense.GameEntity;

import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.File;

public abstract class GameEntity extends VBox {

    protected ImageView enemyImage;
    protected File imageLocation;
    protected int health;

    public enum EnemyType {
        NormalEnemy,
        TankerEnemy,
        SmallerEnemy,
        BossEnemy
    }

    public abstract void loadImage(EnemyType type);

    public abstract void move();

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public abstract boolean isDestroy();

}
