package TowerDefense.Button;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;

public class GameButton extends Button {

    protected String BUTTON_STYLE = "-fx-background-color: transparent";

    public GameButton(String imageLocation, Node buttonImage) {
        super(imageLocation, buttonImage);
        setStyle(BUTTON_STYLE);
        setMouseEnteredEffect();
    }

    public void setMouseEnteredEffect() {

        setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(new Glow());
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(new DropShadow());
            }
        });
    }

}
