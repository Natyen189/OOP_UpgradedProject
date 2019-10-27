package TowerDefense.Button;

import TowerDefense.GameEntity.Enemy.EnemySpawner;
import TowerDefense.LevelManagement;
import javafx.animation.Animation;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class MenuButton extends GameButton {

    public MenuButton(String imageLocation, Node buttonImage) {
        super(imageLocation, buttonImage);
        setMouseOnClick();
    }

    public void setMouseOnClick() {

        setOnMouseClicked(mouseEvent -> {
            setDisable(true);
            setOpacity(0);
            EnemySpawner.timeline.play();
        });
    }
}
