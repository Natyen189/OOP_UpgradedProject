package TowerDefense.GameEntity.Player.PlayerSpecial;

import TowerDefense.Button.MenuButton;
import TowerDefense.Config;
import TowerDefense.GameEntity.Enemy.EnemySpawner;
import TowerDefense.GameStage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.File;

public class FreezeSpecial extends PlayerSpecial {

    public FreezeSpecial() {
        loadThumbnail();
        displayDescription();
        generateCountdownBar();
        cast();
    }

    @Override
    void loadThumbnail() {
        File thumbnailDir = new File("Asset\\Special2.png");
        specialThumbnail = new ImageView(new Image(thumbnailDir.toURI().toString()));
        specialThumbnail.setFitWidth(Config.TILE_SIZE*2);
        specialThumbnail.setPreserveRatio(true);
        this.setViewOrder(-3);
        this.setLayoutX(Config.TILE_SIZE*12 - Config.TILE_SIZE*2);
        this.setLayoutY(-(double)Config.TILE_SIZE*(1.7));

        this.getChildren().add(specialThumbnail);
    }

    @Override
    void cast() {

        setOnMouseClicked(event -> {
            if(MenuButton.startGame && canCastSpecial && EnemySpawner.enemies.size() != 0) {
                displayText();
                System.out.println("Casting Freezer.");
                canCastSpecial = false;
                for(int i = 0; i < EnemySpawner.enemies.size(); i++) {
                    EnemySpawner.enemies.get(i).pathTransition.pause();
                    countdownBar.setProgress(0);
                }

                Timeline displayTimeline = new Timeline(new KeyFrame(Duration.seconds(7), displayEvent -> {
                    actionText.setVisible(false);
                    GameStage.mainWindow.getChildren().remove(actionText);
                    actionText = null;
                }));
                displayTimeline.setOnFinished(displayEvent -> {
                    for(int i = 0; i < EnemySpawner.enemies.size(); i++) {
                        EnemySpawner.enemies.get(i).pathTransition.play();
                    }
                    resetSpecial();
                });
                displayTimeline.setCycleCount(1);
                displayTimeline.setAutoReverse(false);
                displayTimeline.play();
            }
        });
    }

    @Override
    void displayText() {
        actionText = new Text();
        actionText.setText("Enjoy while it lasts...");
        actionText.setStroke(Color.BLACK);
        actionText.setStrokeWidth(0.3);
        actionText.setFill(Color.WHITE);
        actionText.setScaleX(4);
        actionText.setScaleY(4);
        actionText.setLayoutX(Config.TILE_SIZE*9);
        actionText.setLayoutY(Config.TILE_SIZE*5);
        GameStage.mainWindow.getChildren().add(actionText);
    }

    @Override
    void displayDescription() {
        File description = new File("Asset\\Special2Des.png");
        specialDescription = new ImageView(new Image(description.toURI().toString()));
        specialDescription.setFitWidth(Config.TILE_SIZE*4);
        specialDescription.setPreserveRatio(true);
        specialDescription.setLayoutX(Config.TILE_SIZE*10);
        specialDescription.setLayoutY(Config.TILE_SIZE/2);
        specialDescription.setVisible(false);
        specialDescription.setOpacity(0.8);
        specialDescription.setViewOrder(-3);
        GameStage.mainWindow.getChildren().add(specialDescription);
    }
}
