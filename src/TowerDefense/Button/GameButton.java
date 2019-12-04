package TowerDefense.Button;

import TowerDefense.MusicContainer;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;

public class GameButton extends Button {

    protected String BUTTON_STYLE = "-fx-background-color: transparent";

    public GameButton(String imageLocation, Node buttonImage) {
        super(imageLocation, buttonImage);
        setStyle(BUTTON_STYLE);
        HandlemouseEvent();
    }

    public void HandlemouseEvent() {

        setOnMouseEntered(mouseEvent -> {
            setEffect(new Glow());
            MusicContainer.clickSound.play();
        });

        setOnMouseExited(mouseEvent -> {
            setEffect(new DropShadow());
            MusicContainer.clickSound.stop();
        });
    }

}
