package TowerDefense.GameTile;

import TowerDefense.Config;
import TowerDefense.GameStage;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Road extends VBox {

    public static final Rectangle[] roadPath = new Rectangle[10];

    public Road() {
        double offset = 25;

        /*Tạo các hình chữ nhật biểu thị cho đường đi*/
        roadPath[0] = new Rectangle();
        roadPath[0].setX(0);
        roadPath[0].setY(offset);
        roadPath[0].setWidth(Config.TILE_SIZE*13);
        roadPath[0].setHeight(Config.TILE_SIZE*2 - offset*2);
        roadPath[0].setVisible(true);

        roadPath[1] = new Rectangle();
        roadPath[1].setX(Config.TILE_SIZE*12 + offset);
        roadPath[1].setY(offset);
        roadPath[1].setWidth(Config.TILE_SIZE*2 - offset*2);
        roadPath[1].setHeight(Config.TILE_SIZE*4);
        roadPath[1].setVisible(true);

        roadPath[2] = new Rectangle();
        roadPath[2].setX(Config.TILE_SIZE*12 + offset);
        roadPath[2].setY(Config.TILE_SIZE*3 + offset);
        roadPath[2].setWidth(Config.TILE_SIZE*5 + offset/2);
        roadPath[2].setHeight(Config.TILE_SIZE*2 - offset*2);
        roadPath[2].setVisible(true);

        roadPath[3] = new Rectangle();
        roadPath[3].setX(Config.TILE_SIZE*16 + offset);
        roadPath[3].setY(Config.TILE_SIZE*4 - offset);
        roadPath[3].setWidth(Config.TILE_SIZE*2 - offset*2);
        roadPath[3].setHeight(Config.TILE_SIZE*4);
        roadPath[3].setVisible(true);

        roadPath[4] = new Rectangle();
        roadPath[4].setX(Config.TILE_SIZE*8 + offset);
        roadPath[4].setY(Config.TILE_SIZE*6 + offset);
        roadPath[4].setWidth(Config.TILE_SIZE*9 - offset);
        roadPath[4].setHeight(Config.TILE_SIZE*2 - offset*2);
        roadPath[4].setVisible(true);

        roadPath[5] = new Rectangle();
        roadPath[5].setX(Config.TILE_SIZE*8 + offset);
        roadPath[5].setY(Config.TILE_SIZE*4 + offset);
        roadPath[5].setWidth(Config.TILE_SIZE*2 - offset*2);
        roadPath[5].setHeight(Config.TILE_SIZE*6 - offset*2);
        roadPath[5].setVisible(true);

        roadPath[6] = new Rectangle();
        roadPath[6].setX(Config.TILE_SIZE*2 + offset);
        roadPath[6].setY(Config.TILE_SIZE*4 + offset);
        roadPath[6].setWidth(Config.TILE_SIZE*2 - offset*2);
        roadPath[6].setHeight(Config.TILE_SIZE*6 - offset*2);
        roadPath[6].setVisible(true);

        roadPath[7] = new Rectangle();
        roadPath[7].setX(0);
        roadPath[7].setY(Config.TILE_SIZE*6 + offset);
        roadPath[7].setWidth(Config.TILE_SIZE*4 - offset);
        roadPath[7].setHeight(Config.TILE_SIZE*2 - offset*2);
        roadPath[7].setVisible(true);

        roadPath[8] = new Rectangle();
        roadPath[8].setX(Config.TILE_SIZE*3 + offset);
        roadPath[8].setY(Config.TILE_SIZE*4 + offset);
        roadPath[8].setWidth(Config.TILE_SIZE*6 - offset);
        roadPath[8].setHeight(Config.TILE_SIZE*2 - offset*2);
        roadPath[8].setVisible(true);

        roadPath[9] = new Rectangle();
        roadPath[9].setX(Config.TILE_SIZE*3 + offset);
        roadPath[9].setY(Config.TILE_SIZE*8 + offset);
        roadPath[9].setWidth(Config.TILE_SIZE*6 - offset);
        roadPath[9].setHeight(Config.TILE_SIZE*2 - offset*2);
        roadPath[9].setVisible(true);


        for(int i = 0; i < roadPath.length; i++) {
            roadPath[i].setVisible(false);
            GameStage.mainWindow.getChildren().add(roadPath[i]);
        }
    }

}
