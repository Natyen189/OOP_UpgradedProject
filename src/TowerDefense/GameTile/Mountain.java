package TowerDefense.GameTile;

import TowerDefense.Config;
import TowerDefense.GameStage;
import TowerDefense.GameTile.Tower.Tower;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*Class để tạo các tile hình vuông khi đặt tháp*/
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
        for(int i = 0; i <= 1; i++) {
            for(int j = 0; j <= 13; j++)
            mapTile[i][j] = null;
        }

        for(int i = 1; i <= 4; i++) {
            mapTile[i][12] = null;
            mapTile[i][13] = null;
        }

        for(int i = 13; i <= 17; i++) {
            mapTile[3][i] = null;
            mapTile[4][i] = null;
        }

        for(int i = 5; i <= 7; i++) {
            mapTile[i][16] = null;
            mapTile[i][17] = null;
        }

        for(int i = 10; i <= 15; i++) {
            mapTile[6][i] = null;
            mapTile[7][i] = null;
        }

        for(int i = 0; i <= 1; i++) {
            mapTile[6][i] = null;
            mapTile[7][i] = null;
        }

        for(int i = 4; i <= 7; i++) {
            mapTile[4][i] = null;
            mapTile[5][i] = null;
        }

        for(int i = 4; i <= 7; i++) {
            mapTile[8][i] = null;
            mapTile[9][i] = null;
        }

        for(int i = 4; i <= 9; i++) {
            mapTile[i][2] = null;
            mapTile[i][3] = null;
        }

        for(int i = 4; i <= 9; i++) {
            mapTile[i][8] = null;
            mapTile[i][9] = null;
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
