package TowerDefense.GameEntity.Player;

import TowerDefense.Config;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class HighScore extends VBox {

    private ArrayList<Integer> score = new ArrayList<>();
    private Label[] highScore = new Label[5];
    public static int playerScore = 0;

    public HighScore() throws IOException {
        loadScore();
        displayScore();
    }

    private void displayScore() {
        double startX = Config.TILE_SIZE*3;
        double startY = Config.TILE_SIZE*7.67;

        for(int i = 0; i < 5; i++) {
            highScore[i] = new Label(String.valueOf(score.get(i)));
            highScore[i].setTranslateX(startX);
            highScore[i].setTranslateY(startY);
            if(score.get(i) == playerScore) highScore[i].setTextFill(Color.YELLOW);
            else highScore[i].setTextFill(Color.WHITE);
            highScore[i].setFont(Font.loadFont("file:Asset\\Font\\UTM BanqueR.ttf", 30));

            startY += (float)Config.TILE_SIZE/5.8;
        }

        for (Label label : highScore) {
            if (label != null)
                this.getChildren().add(label);
        }
    }

    private void loadScore() throws IOException {
        FileReader fileReader = new FileReader("Asset\\HighScore.txt");
        BufferedReader test = new BufferedReader(fileReader);
        String line;

        while((line = test.readLine()) != null) {
            score.add(Integer.valueOf(line));
        }

        test.close();

        Collections.sort(score, Collections.reverseOrder());

//        for(int i = 0; i < score.size(); i++) {
//            System.out.println(score.get(i));
//        }
    }

    public static void writeScore() throws IOException {
        FileWriter fileWriter = new FileWriter("Asset\\HighScore.txt", true);
        BufferedWriter test = new BufferedWriter(fileWriter);

        test.write(String.valueOf(playerScore));
        test.newLine();
        test.close();
        System.out.println("HighScore: " + playerScore);
    }
}
