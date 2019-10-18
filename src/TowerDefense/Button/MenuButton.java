package TowerDefense.Button;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class MenuButton extends GameButton {

    public MenuButton(String imageLocation, Node buttonImage) {
        super(imageLocation, buttonImage);
        setMouseOnClick();
    }

    public void setMouseOnClick() {

        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setDisable(true);
                setOpacity(0);
            }
        });
    }
}
