package TowerDefense;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;

public class MainWindow {

    private AnchorPane mainWindow;
    private Scene scene;
    private Stage gameStage;

    public MainWindow() {
        mainWindow = new AnchorPane();
        scene = new Scene(mainWindow, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        gameStage = new Stage();
        gameStage.setScene(scene);
        setBackgroundImage();
        createButton();
    }

    public Stage getState() {
        return gameStage;
    }

    public void setBackgroundImage() {
        File backGroundLocation = new File("Asset\\Background.png");
        Image background = new Image(backGroundLocation.toURI().toString());
        BackgroundImage gameBackground = new BackgroundImage(background, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

        mainWindow.setBackground(new Background(gameBackground));
    }

    private void createButton() {
        createMenuButton();
    }

    private void createMenuButton() {
        File startButtonLocation = new File("Asset\\PlayButton.png");
        ImageView buttonImage = new ImageView(new Image(startButtonLocation.toURI().toString()));
        Button startButton = new GameButton("", buttonImage);
        startButton.setLayoutX((float)Config.SCREEN_WIDTH/2);
        startButton.setLayoutY((float)Config.SCREEN_HEIGHT/2);
        mainWindow.getChildren().add(startButton);
    }

}
