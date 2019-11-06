package TowerDefense.Button;

import TowerDefense.Config;
import TowerDefense.GameEntity.GameEntity;
import TowerDefense.GameTile.Tower.Tower;
import TowerDefense.GameStage;
import javafx.event.EventHandler;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;

public class TowerButton extends GameEntity {

    private TowerType towerType;

    public TowerButton(GameEntity.TowerType towerType) {
        this.towerType = towerType;
        loadImage(towerType);
        this.getChildren().add(image);
        setMouse();
        spawnTower();
    }

    public void loadImage(TowerType type) {
        switch (type) {
            case NormalTower:
                imageLocation = new File("Asset\\TowerTile\\1.png");
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                break;
            case SniperTower:
                imageLocation = new File("Asset\\TowerTile\\2.png");
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                break;
            case MachineGunTower:
                imageLocation = new File("Asset\\TowerTile\\3.png");
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                break;
            case UnnamedTower:
                imageLocation = new File("Asset\\TowerTile\\4.png");
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                break;
            case RayTower:
                imageLocation = new File("Asset\\TowerTile\\5.png");
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                break;
            case IceTurret:
                imageLocation = new File("Asset\\TowerTile\\6.png");
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                break;
        }
    }

    @Override
    public boolean isDestroy() {
        return false;
    }

    void setMouse() {

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

    void spawnTower() {
        setOnMouseClicked(event-> {
            if(MenuButton.startGame) {
                Tower tower = new Tower(towerType);
                tower.setLayoutX(event.getSceneX() - (float) Config.TILE_SIZE/2);
                tower.setLayoutY(event.getSceneY() - (float)Config.TILE_SIZE/2);
                GameStage.mainWindow.getChildren().add(tower);
                System.out.println(towerType + " spawned.");
            }
        });
    }
}
