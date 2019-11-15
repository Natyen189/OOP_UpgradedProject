package TowerDefense;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GameStage gameWindow = new GameStage();
        primaryStage = gameWindow.getState();
        primaryStage.setResizable(true);
        primaryStage.setMaximized(true);
        primaryStage.setFullScreen(false);
        primaryStage.setTitle(Config.GAME_NAME);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
