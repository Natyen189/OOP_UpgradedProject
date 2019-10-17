package TowerDefense;

import javafx.scene.Scene;
import javafx.scene.image.Image;
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
    }

    public Stage getState() {
        return gameStage;
    }

    public void setBackgroundImage() {
        File backGroungLocation = new File("D:\\OOP Project\\OOP\\Asset\\Background.png");
        Image background = new Image(backGroungLocation.toURI().toString());
        BackgroundImage gameBackground = new BackgroundImage(background, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

        mainWindow.setBackground(new Background(gameBackground));
    }

}
