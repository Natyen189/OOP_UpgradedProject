package TowerDefense.GameTile;

import TowerDefense.Config;
import TowerDefense.GameStage;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Road extends VBox {

    public static final Rectangle[] roadPath = new Rectangle[5];

    public Road() {
        /*Tạo các hình chữ nhật biểu thị cho đường đi*/
        roadPath[0] = new Rectangle();
        roadPath[0].setX(0);
        roadPath[0].setY(Config.TILE_SIZE*9);
        roadPath[0].setWidth(Config.TILE_SIZE*9);
        roadPath[0].setHeight(Config.TILE_SIZE*2);
        roadPath[0].setVisible(false);

        roadPath[1] = new Rectangle();
        roadPath[1].setX(Config.TILE_SIZE*7);
        roadPath[1].setY(Config.TILE_SIZE*9);
        roadPath[1].setWidth(Config.TILE_SIZE*2);
        roadPath[1].setHeight(Config.TILE_SIZE*9.5);
        roadPath[1].setVisible(false);

        roadPath[2] = new Rectangle();
        roadPath[2].setX(Config.TILE_SIZE*7);
        roadPath[2].setY(Config.TILE_SIZE*17);
        roadPath[2].setWidth(Config.TILE_SIZE*13.5);
        roadPath[2].setHeight(Config.TILE_SIZE*2);
        roadPath[2].setVisible(false);

        roadPath[3] = new Rectangle();
        roadPath[3].setX(Config.TILE_SIZE*19);
        roadPath[3].setY(Config.TILE_SIZE*4);
        roadPath[3].setWidth(Config.TILE_SIZE*2);
        roadPath[3].setHeight(Config.TILE_SIZE*15);
        roadPath[3].setVisible(false);

        roadPath[4] = new Rectangle();
        roadPath[4].setX(Config.TILE_SIZE*19);
        roadPath[4].setY(Config.TILE_SIZE*3);
        roadPath[4].setWidth(Config.TILE_SIZE*11);
        roadPath[4].setHeight(Config.TILE_SIZE*2);
        roadPath[4].setVisible(false);


        for(int i = 0; i < roadPath.length; i++) {
            GameStage.mainWindow.getChildren().add(roadPath[i]);
        }

    }
}
