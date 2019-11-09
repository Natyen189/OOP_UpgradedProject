package TowerDefense.GameTile.Tower;

import TowerDefense.GameStage;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class TowerStats extends Label {

    private Label[] baseInformation = new Label[5];
    private Label[] towerStats = new Label[5];
    private String towerDamage;
    private String towerRange;
    private String towerLevel;
    private String towerPrice;

    public TowerStats(Tower tower) {
        towerDamage = Double.toString(tower.getTowerDamage());
        towerRange = Double.toString(tower.getShootRange());
        towerLevel = String.valueOf(tower.getTowerLevel());
        towerPrice = String.valueOf(tower.getTowerValue());
        createBaseInfo(tower);
        createTowerStats(tower);
    }

    private void createBaseInfo(Tower tower) {
        baseInformation[0] = new Label(tower.getCurrentType().toString());
        baseInformation[0].setLayoutX(1080);
        baseInformation[0].setLayoutY(415);
        baseInformation[0].setScaleX(2.5);
        baseInformation[0].setScaleY(2.5);
        baseInformation[0].setFont(Font.loadFont("file:Asset\\Font\\UTM BanqueR.ttf", 10));

        baseInformation[1] = new Label("Damage: ");
        baseInformation[1].setLayoutX(1020);
        baseInformation[1].setLayoutY(465);
        baseInformation[1].setScaleX(2);
        baseInformation[1].setScaleY(2);
        baseInformation[1].setTextFill(Color.RED);
        baseInformation[1].setFont(Font.loadFont("file:Asset\\Font\\UTM BanqueR.ttf", 10));

        baseInformation[2] = new Label("Range: ");
        baseInformation[2].setLayoutX(1020);
        baseInformation[2].setLayoutY(495);
        baseInformation[2].setScaleX(2);
        baseInformation[2].setScaleY(2);
        baseInformation[2].setTextFill(Color.BLUE);
        baseInformation[2].setFont(Font.loadFont("file:Asset\\Font\\UTM BanqueR.ttf", 10));

        baseInformation[3] = new Label("Level: ");
        baseInformation[3].setLayoutX(1020);
        baseInformation[3].setLayoutY(525);
        baseInformation[3].setScaleX(2);
        baseInformation[3].setScaleY(2);
        baseInformation[3].setTextFill(Color.GREEN);
        baseInformation[3].setFont(Font.loadFont("file:Asset\\Font\\UTM BanqueR.ttf", 10));

        baseInformation[4] = new Label("Price$: ");
        baseInformation[4].setLayoutX(1020);
        baseInformation[4].setLayoutY(575);
        baseInformation[4].setScaleX(2);
        baseInformation[4].setScaleY(2);
        baseInformation[4].setTextFill(Color.PURPLE);
        baseInformation[4].setFont(Font.loadFont("file:Asset\\Font\\UTM BanqueR.ttf", 13));


        for (Label baseInfo : baseInformation) {
            GameStage.mainWindow.getChildren().add(baseInfo);
        }
    }

    private void createTowerStats(Tower tower) {
        switch(tower.getCurrentType()) {
            case NormalTower:
                towerStats[0] = new Label("Tower Special: " + "\n" + "Just a regular one," + "\n" + "Can't shoot helicopters tho.");
                break;
            case SniperTower:
                towerStats[0] = new Label("Tower Special: " + "\n" + "Like normal tower," + "\n" + "But with wider range" + "\n" + "and shittier damage.");
                break;
            case MachineGun:
                towerStats[0] = new Label("Tower Special: " + "\n" + "Its bullets drop as fast" + "\n" + "as your grades.");
                break;
            case AirTower:
                towerStats[0] = new Label("Tower Special: " + "\n" + "Yep, it can shoot helicopters" + "\n" + "But other enemies? Well...");
                break;
            case RayTower:
                towerStats[0] = new Label("Tower Special: " + "\n" + "Finally a tower that can" + "\n" + "shoot all types of enemy.");
                break;
            case IceTurret:
                towerStats[0] = new Label("Tower Special: " + "\n" + "It shoot sand, not ice." + "\n");
                break;
        }
        towerStats[0].setLayoutX(990);
        towerStats[0].setLayoutY(625);
        towerStats[0].setScaleX(1);
        towerStats[0].setScaleY(1);
        towerStats[0].setFont(Font.loadFont("file:Asset\\Font\\UTM BanqueR.ttf", 14));

        towerStats[1] = new Label(towerDamage);
        towerStats[1].setLayoutX(1130);
        towerStats[1].setLayoutY(465);
        towerStats[1].setScaleX(2);
        towerStats[1].setScaleY(2);
        towerStats[1].setTextFill(Color.RED);
        towerStats[1].setFont(Font.loadFont("file:Asset\\Font\\UTM BanqueR.ttf", 10));

        towerStats[2] = new Label(towerRange);
        towerStats[2].setLayoutX(1130);
        towerStats[2].setLayoutY(495);
        towerStats[2].setScaleX(2);
        towerStats[2].setScaleY(2);
        towerStats[2].setTextFill(Color.BLUE);
        towerStats[2].setFont(Font.loadFont("file:Asset\\Font\\UTM BanqueR.ttf", 10));

        towerStats[3] = new Label(towerLevel);
        towerStats[3].setLayoutX(1130);
        towerStats[3].setLayoutY(525);
        towerStats[3].setScaleX(2);
        towerStats[3].setScaleY(2);
        towerStats[3].setTextFill(Color.GREEN);
        towerStats[3].setFont(Font.loadFont("file:Asset\\Font\\UTM BanqueR.ttf", 10));

        towerStats[4] = new Label(towerPrice);
        towerStats[4].setLayoutX(1130);
        towerStats[4].setLayoutY(575);
        towerStats[4].setScaleX(2);
        towerStats[4].setScaleY(2);
        towerStats[4].setTextFill(Color.PURPLE);
        towerStats[4].setFont(Font.loadFont("file:Asset\\Font\\UTM BanqueR.ttf", 13));

        for (Label towerStat : towerStats) {
            GameStage.mainWindow.getChildren().add(towerStat);
        }

    }

    public void toggleStat(boolean toggle) {
        for (Label baseInfo : baseInformation) {
            baseInfo.setVisible(toggle);
        }

        for (Label towerStat : towerStats) {
            towerStat.setVisible(toggle);
        }
    }
}
