package TowerDefense.Button;

import TowerDefense.Config;
import TowerDefense.GameStage;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.io.File;

public class GuideButton extends GameButton {

    private TranslateTransition goIn = new TranslateTransition();
    private TranslateTransition goOut = new TranslateTransition();
    public ImageView guideImage = new ImageView();
    private boolean clicked = false;

    public GuideButton(String imageLocation, Node buttonImage) {
        super(imageLocation, buttonImage);
        createGuidePanel();
        handleMouse();
    }

    private void handleMouse() {
        setOnMouseClicked(event -> {
            guideImage.setVisible(true);
            if(!clicked) {
                goIn.playFromStart();
                clicked = true;
            }
            else {
                goOut.playFromStart();
                clicked = false;
            }

        });
    }

    private void createGuidePanel() {
        File guideLocation = new File("Asset\\GuidePanel.png");
        guideImage = new ImageView(new Image(guideLocation.toURI().toString()));
        guideImage.setTranslateX(Config.SCREEN_WIDTH);
        guideImage.setTranslateY(0);
        guideImage.setFitHeight(800);
        guideImage.setPreserveRatio(true);
        guideImage.setVisible(false);
        guideImage.setViewOrder(-2);
        GameStage.mainWindow.getChildren().add(guideImage);

        goIn = new TranslateTransition();
        goIn.setNode(guideImage);
        goIn.setFromX(guideImage.getTranslateX());
        goIn.setToX(Config.TILE_SIZE *15);
        goIn.setDuration(Duration.seconds(1));

        goOut = new TranslateTransition();
        goOut.setNode(guideImage);
        goOut.setFromX(Config.TILE_SIZE *15);
        goOut.setToX(Config.SCREEN_WIDTH + Config.TILE_SIZE);
        goOut.setDuration(Duration.seconds(1));
    }
}
