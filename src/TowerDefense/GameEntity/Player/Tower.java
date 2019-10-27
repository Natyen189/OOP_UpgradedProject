package TowerDefense.GameEntity.Player;

import TowerDefense.Button.TowerButton;
import TowerDefense.GameEntity.GameEntity;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;

public class Tower extends GameEntity {
    private int TowerValue;
    private int TowerLevel;
    private boolean draggable;

    public Tower(TowerType towerType) {
        draggable = true;
        loadImage(towerType);
        this.getChildren().add(image);
        setMouse();
        DragTower();
        move();
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
        }
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

    void DragTower() {

        setOnMouseDragged(event -> {
            if(draggable) {
                this.setLayoutX(event.getSceneX());
                this.setLayoutY(event.getSceneY());
            }
        });

        setOnMouseReleased(event -> {
            System.out.println("Drag Complete.");
            draggable = false;
        });
    }

    @Override
    public boolean isDestroy() {
        return false;
    }
}
