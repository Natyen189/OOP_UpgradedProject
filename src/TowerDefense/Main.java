package TowerDefense;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        MainWindow gameWindow = new MainWindow();
        primaryStage = gameWindow.getState();
        primaryStage.setTitle("Tower Defense");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
