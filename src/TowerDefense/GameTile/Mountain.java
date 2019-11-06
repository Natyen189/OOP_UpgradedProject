package TowerDefense.GameTile;

import TowerDefense.Config;
import TowerDefense.GameStage;
import TowerDefense.GameTile.Tower.Tower;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Mountain extends VBox {
    public static Rectangle [][]mapTile = new Rectangle[Config.TILE_VERTICAL][Config.TILE_HORIZONTAL];
    private double xPos = 0;
    private double yPos = 0;

    public Mountain() {

        for(int i = 0; i < Config.TILE_VERTICAL; i++) {
            for(int j = 0; j < Config.TILE_HORIZONTAL; j++) {
                mapTile[i][j] = new Rectangle();
                mapTile[i][j].setX(xPos);
                mapTile[i][j].setY(yPos);
                mapTile[i][j].setWidth(Config.TILE_SIZE);
                mapTile[i][j].setHeight(Config.TILE_SIZE);
                mapTile[i][j].setFill(Color.TRANSPARENT);
                mapTile[i][j].setStroke(Color.BLACK);
                mapTile[i][j].setVisible(false);
                xPos += Config.TILE_SIZE;
            }
            xPos = 0;
            yPos += Config.TILE_SIZE;
        }

        /*Xoá các tile nằm trên đường*/
        for(int i = 0; i <= 4; i++) {
            mapTile[4][i] = null;
            mapTile[5][i] = null;
        }

        for(int i = 6; i <= 8; i++) {
            mapTile[i][3] = null;
            mapTile[i][4] = null;
        }

        for(int i = 5; i <= 10; i++) {
            mapTile[8][i] = null;
        }

        for(int i = 3; i <= 10; i++) {
            mapTile[9][i] = null;
        }

        for(int i = 1; i <= 7; i++) {
            mapTile[i][9] = null;
            mapTile[i][10] = null;
        }

        for(int i = 11; i <= 14; i++) {
            mapTile[1][i] = null;
            mapTile[2][i] = null;
        }

        /*Vẽ tile vào map*/
        for(int i = 0; i < Config.TILE_VERTICAL; i++) {
            for(int j = 0; j < Config.TILE_HORIZONTAL; j++) {
                if(mapTile[i][j] != null)
                GameStage.mainWindow.getChildren().add(mapTile[i][j]);
            }
        }
    }

    public static void toggleVisibility(boolean toggle) {
        for(int i = 0; i < Config.TILE_VERTICAL; i++) {
            for(int j = 0; j < Config.TILE_HORIZONTAL; j++) {
                if(mapTile[i][j] != null)
                mapTile[i][j].setVisible(toggle);
            }
        }
    }
}
