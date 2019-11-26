package TowerDefense.GameEntity.Player.PlayerSpecial;

import TowerDefense.Button.MenuButton;
import TowerDefense.Config;
import TowerDefense.GameEntity.Enemy.EnemySpawner;
import TowerDefense.GameStage;
import javafx.animation.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.io.File;

public class HotPotSpecial extends PlayerSpecial {

    private int frame = 0;
    private Image[] explode = new Image[17];

    public HotPotSpecial() {
        loadThumbnail();
        loadGif();
        displayDescription();
        generateCountdownBar();
        cast();
    }

    @Override
    void loadThumbnail() {
        File thumbnailDir = new File("Asset\\Special1.png");
        specialThumbnail = new ImageView(new Image(thumbnailDir.toURI().toString()));
        specialThumbnail.setFitWidth(Config.TILE_SIZE*2);
        specialThumbnail.setPreserveRatio(true);
        this.setViewOrder(-3);
        this.setLayoutX(Config.TILE_SIZE*10 - Config.TILE_SIZE*2);
        this.setLayoutY(-(double)Config.TILE_SIZE*(1.7));

        this.getChildren().add(specialThumbnail);
    }

    @Override
    void displayText() {
        actionText = new Text();
        actionText.setText("Brace Yourself, HotPot incoming...");
        actionText.setFont(Font.loadFont("file:Asset\\Font\\UTM BanqueR.ttf", 11));
        actionText.setStroke(Color.DARKRED);
        actionText.setStrokeWidth(0.5);
        actionText.setFill(Color.WHITE);
        actionText.setScaleX(4);
        actionText.setScaleY(4);
        actionText.setLayoutX(Config.TILE_SIZE*8);
        actionText.setLayoutY(Config.TILE_SIZE*5);
        GameStage.mainWindowPane.getChildren().add(actionText);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), actionText);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.4);
        fadeTransition.setCycleCount(5);
        fadeTransition.play();

    }

    @Override
    void displayDescription() {
        File description = new File("Asset\\Special1Des.png");
        specialDescription = new ImageView(new Image(description.toURI().toString()));
        specialDescription.setFitWidth(Config.TILE_SIZE*4);
        specialDescription.setPreserveRatio(true);
        specialDescription.setLayoutX(Config.TILE_SIZE*6);
        specialDescription.setLayoutY(Config.TILE_SIZE/2);
        specialDescription.setVisible(false);
        specialDescription.setOpacity(0.8);
        specialDescription.setViewOrder(-3);
        GameStage.mainWindowPane.getChildren().add(specialDescription);
    }

    @Override
    void cast() {

        setOnMouseClicked(event -> {
            if(MenuButton.startGame && canCastSpecial && EnemySpawner.enemies.size() != 0) {
                displayText();
                System.out.println("Casting Hotpot.");
                canCastSpecial = false;

                Timeline displayTimeline = new Timeline(new KeyFrame(Duration.seconds(5), displayEvent -> {
                    actionText.setVisible(false);
                    GameStage.mainWindowPane.getChildren().remove(actionText);
                    actionText = null;
                }));
                displayTimeline.setOnFinished(displayEvent -> {
                    for(int i = 0; i < EnemySpawner.enemies.size(); i++) {
                        EnemySpawner.enemies.get(i).subtractHealth(0.5*(EnemySpawner.enemies.get(i).getArmor()));
                        countdownBar.setProgress(0);
                    }
                    canCastSpecial = true;
                    displayExplosion();
                    resetSpecial();
                });
                displayTimeline.setCycleCount(1);
                displayTimeline.setAutoReverse(false);
                displayTimeline.play();
            }
        });
    }

    private void loadGif() {

        explode[0] = (new Image("file:Asset\\Explode\\10000.png"));
        explode[1] = (new Image("file:Asset\\Explode\\10001.png"));
        explode[2] = (new Image("file:Asset\\Explode\\10002.png"));
        explode[3] = (new Image("file:Asset\\Explode\\10003.png"));
        explode[4] = (new Image("file:Asset\\Explode\\10004.png"));
        explode[5] = (new Image("file:Asset\\Explode\\10005.png"));
        explode[6] = (new Image("file:Asset\\Explode\\10006.png"));
        explode[7] = (new Image("file:Asset\\Explode\\10007.png"));
        explode[8] = (new Image("file:Asset\\Explode\\10008.png"));
        explode[9] = (new Image("file:Asset\\Explode\\10009.png"));;
        explode[10] = (new Image("file:Asset\\Explode\\10010.png"));
        explode[11] = (new Image("file:Asset\\Explode\\10011.png"));
        explode[12] = (new Image("file:Asset\\Explode\\10012.png"));
        explode[13] = (new Image("file:Asset\\Explode\\10013.png"));
        explode[14] = (new Image("file:Asset\\Explode\\10014.png"));
        explode[15] = (new Image("file:Asset\\Explode\\10015.png"));
        explode[16] = (new Image("file:Asset\\Explode\\10016.png"));

    }

    private void displayExplosion() {

        ImageView test = new ImageView();
        test.setFitWidth(Config.TILE_SIZE*13);
        test.setPreserveRatio(true);
        test.setLayoutX(Config.TILE_SIZE*3);
        test.setLayoutY(-Config.TILE_SIZE*2);
        test.setLayoutY(0);
        GameStage.mainWindowPane.getChildren().add(test);

        Timeline displayTimeline = new Timeline(new KeyFrame(Duration.seconds(0.07), event -> test.setImage(explode[frame++])));
        displayTimeline.setCycleCount(explode.length);
        displayTimeline.setAutoReverse(false);
        displayTimeline.setOnFinished(event-> {
            GameStage.mainWindowPane.getChildren().remove(test);
            frame = 0;
        });
        displayTimeline.play();
    }

}
