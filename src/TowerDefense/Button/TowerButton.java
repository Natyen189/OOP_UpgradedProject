package TowerDefense.Button;

import TowerDefense.Config;
import TowerDefense.GameEntity.GameEntity;
import TowerDefense.GameEntity.Player.PlayerStats;
import TowerDefense.GameTile.Tower.Tower;
import TowerDefense.GameStage;
import TowerDefense.GameTile.Tower.TowerStats;
import javafx.event.EventHandler;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.util.ArrayList;

/*Class để hiển thị những thông số của tháp như: sát thương, tiền nâng cấp, tầm bắn, ...*/
public class TowerButton extends GameEntity {

    private TowerType towerType;
    public static ArrayList<Tower> towerList = new ArrayList<>();
    private TowerStats towerStats;

    public TowerButton(Tower tower) {
        towerStats = new TowerStats(tower);
        towerStats.toggleStat(false);
        this.towerType = tower.getCurrentType();
        loadImage(towerType);
        this.getChildren().add(image);
        setMouse();
        spawnTower();
    }

    public void loadImage(TowerType type) {
        switch (type) {
            case NormalTower:
                imageLocation = new File("Asset\\TowerTile\\1B.png");
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                break;
            case SniperTower:
                imageLocation = new File("Asset\\TowerTile\\2B.png");
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                break;
            case MachineGun:
                imageLocation = new File("Asset\\TowerTile\\3B.png");
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                break;
            case AirTower:
                imageLocation = new File("Asset\\TowerTile\\4B.png");
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                break;
            case RayTower:
                imageLocation = new File("Asset\\TowerTile\\5B.png");
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                break;
            case IceTurret:
                imageLocation = new File("Asset\\TowerTile\\6B.png");
                image = new ImageView(new Image(imageLocation.toURI().toString()));
                break;
        }
    }

    void setMouse() {

        setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                for(int i = 0; i < towerList.size(); i++) {
                    towerList.get(i).isSelected = false;
                    TowerButton.towerList.get(i).towerStats.toggleStat(false);
                }
                setEffect(new Glow());
                towerStats.toggleStat(true);
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(null);
                towerStats.toggleStat(false);
            }
        });
    }

    void spawnTower() {
        setOnMouseClicked(event-> {
            if(MenuButton.startGame) {
                towerStats.toggleStat(false);
                Tower temp = new Tower(towerType);
                if(PlayerStats.money >= temp.getTowerValue()) {
                    PlayerStats.money -= temp.getTowerValue();
                    temp.setViewOrder(-1);
                    temp.setLayoutX(event.getSceneX() - (float) Config.TILE_SIZE/2);
                    temp.setLayoutY(event.getSceneY() - (float)Config.TILE_SIZE/2);
                    towerList.add(temp);
                    GameStage.mainWindow.getChildren().add(towerList.get(towerList.size()-1));
                    System.out.println(towerType + " spawned.");
                }
            }
        });
    }
}
