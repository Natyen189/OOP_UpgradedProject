package TowerDefense.GameEntity.Player;

import TowerDefense.Config;
import TowerDefense.GameEntity.Enemy.EnemySpawner;
import TowerDefense.GameStage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class PlayerCheat extends HBox {

    private ImageView cheatBox;
    private TextField textField;
    public  boolean toggle = false;
    private String TEXT_STYLE = "-fx-text-fill: white; " +
                                "-fx-text-box-border: transparent;  " +
                                "-fx-background-color: transparent;";
    private String[] cheatSheet = {
            "show me the money",
            "i dont do level",
            "there is no cow level",
            "power overwhelming",
            "hey im bored"
    };
    private VBox prevCheatBox = new VBox();
    private Text[] prevCheat = new Text[4];
    private Image [] cat = new Image[12];
    private int index = 0;
    private int frame =0;

    public PlayerCheat() {
        loadImage();
        displayCheat();
        checkFunction();
        this.setVisible(false);
        this.setDisable(true);
    }

    private void loadImage() {
        cheatBox = new ImageView(new Image("file:Asset\\CheatBox.png"));
        cheatBox.setTranslateX(Config.TILE_SIZE/2);
        cheatBox.setTranslateY(Config.TILE_SIZE*7);
        cheatBox.setFitWidth(400);
        cheatBox.setPreserveRatio(true);
        cheatBox.setOpacity(0.5);
        this.getChildren().add(cheatBox);

        textField = new TextField();
        textField.setTranslateX(-Config.TILE_SIZE*5 - 10);
        textField.setTranslateY(Config.TILE_SIZE*10 + 10);
        textField.setPrefWidth(Config.TILE_SIZE*4 - 40);
        textField.setStyle(TEXT_STYLE);
        textField.setFont(Font.loadFont("file:Asset\\Font\\UTM Dax.ttf", 14));
        textField.setViewOrder(-1);
        this.getChildren().add(textField);

        prevCheatBox.setTranslateX(-Config.TILE_SIZE*8 - 35);
        prevCheatBox.setTranslateY(Config.TILE_SIZE*7 + 25);
        this.getChildren().add(prevCheatBox);

        loadGif();
    }

    private void displayCheat() {
        prevCheat[0] = new Text();
        prevCheat[0].setFill(Color.LIGHTGREEN);
        prevCheat[0].setFont(Font.loadFont("file:Asset\\Font\\UTM Dax.ttf", 25));
        prevCheatBox.getChildren().add(prevCheat[0]);

        prevCheat[1] = new Text();
        prevCheat[1].setFill(Color.LIGHTGREEN);
        prevCheat[1].setFont(Font.loadFont("file:Asset\\Font\\UTM Dax.ttf", 25));
        prevCheatBox.getChildren().add(prevCheat[1]);

        prevCheat[2] = new Text();
        prevCheat[2].setFill(Color.LIGHTGREEN);
        prevCheat[2].setFont(Font.loadFont("file:Asset\\Font\\UTM Dax.ttf", 25));
        prevCheatBox.getChildren().add(prevCheat[2]);

        prevCheat[3] = new Text();
        prevCheat[3].setFill(Color.LIGHTGREEN);
        prevCheat[3].setFont(Font.loadFont("file:Asset\\Font\\UTM Dax.ttf", 25));
        prevCheatBox.getChildren().add(prevCheat[3]);
    }

    private void checkFunction() {
        textField.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                for(int i = 0; i < cheatSheet.length; i++) {
                    if(textField.getText().equals(cheatSheet[i])) {
                        executeFunction(cheatSheet[i]);
                        if(index >= 3) {
                            index = 3;
                            prevCheat[index].setText("- " + textField.getText());
                        }
                        else {
                            prevCheat[index].setText("- " + textField.getText());
                            index++;
                        }
                        textField.clear();
                    }
                }
            }
        });
    }

    private void executeFunction(String cheat) {
        switch (cheat) {
            case "show me the money":
                PlayerStats.money = 20000;
                break;
            case "i dont do level":
                EnemySpawner.level += 5;
                break;
            case "hey im bored":
                displayCat();
                break;
            case "there is no cow level":
                PlayerStats.health = 0;
                PlayerStats.restartGame();
                break;
            case "power overwhelming":
                PlayerStats.health = 100;
                break;
        }
    }

    public void toggleVisibility(boolean toggle) {
        this.setVisible(toggle);
        this.setDisable(!toggle);
    }

    private void loadGif() {

        cat[0] = (new Image("file:Asset\\Cat\\1.png"));
        cat[1] = (new Image("file:Asset\\Cat\\2.png"));
        cat[2] = (new Image("file:Asset\\Cat\\3.png"));
        cat[3] = (new Image("file:Asset\\Cat\\4.png"));
        cat[4] = (new Image("file:Asset\\Cat\\5.png"));
        cat[5] = (new Image("file:Asset\\Cat\\6.png"));
        cat[6] = (new Image("file:Asset\\Cat\\7.png"));
        cat[7] = (new Image("file:Asset\\Cat\\8.png"));
        cat[8] = (new Image("file:Asset\\Cat\\9.png"));
        cat[9] = (new Image("file:Asset\\Cat\\10.png"));;
        cat[10] = (new Image("file:Asset\\Cat\\11.png"));
        cat[11] = (new Image("file:Asset\\Cat\\12.png"));
    }

    private void displayCat() {

        ImageView test = new ImageView();
        test.setLayoutX(Config.TILE_SIZE*6);
        test.setTranslateY(Config.TILE_SIZE*1.5);
        GameStage.mainWindowPane.getChildren().add(test);

        Timeline displayTimeline = new Timeline(new KeyFrame(Duration.seconds(0.2), event ->  {
            test.setImage(cat[frame++]);
            test.setFitWidth(600);
            test.setFitHeight(530);
        }));
        displayTimeline.setCycleCount(cat.length);
        displayTimeline.setAutoReverse(false);
        displayTimeline.setOnFinished(event-> {
            GameStage.mainWindowPane.getChildren().remove(test);
            frame = 0;
        });
        displayTimeline.play();
    }
}
