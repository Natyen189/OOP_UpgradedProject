package TowerDefense;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GameStage gameWindow = new GameStage();
        primaryStage = gameWindow.getState();
        primaryStage.setResizable(false);
        primaryStage.setTitle(Config.GAME_NAME);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
