package TowerDefense.Button;

import TowerDefense.Config;
import TowerDefense.GameEntity.Enemy.EnemySpawner;
import TowerDefense.GameEntity.Player.PlayerStats;
import TowerDefense.GameStage;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.io.File;

public class MenuButton extends GameButton {

    public enum ButtonName {
        PlayButton,
        RetryButton,
        ExitButton
    }

    public static boolean startGame = false;
    private ButtonName buttonName;

    public MenuButton(String imageLocation, Node buttonImage, ButtonName buttonName) {
        super(imageLocation, buttonImage);
        this.buttonName = buttonName;
        setMouseOnClick();
    }

    private void setMouseOnClick() {

        switch (buttonName) {
            case PlayButton:
                /*Gọi quân địch và ẩn nút "Play" khi ấn chuột vào*/
                setOnMouseClicked(mouseEvent -> {
                    setDisable(true);
                    setOpacity(0);
                    startGame = true;
                });
                break;

            case RetryButton:
                setOnMouseClicked(mouseEvent -> {
                    PlayerStats.resetDefaultValue();
                });
                break;

            case ExitButton:
                setOnMouseClicked(mouseEvent -> {
                    Platform.exit();
                    System.exit(0);
                });
                break;
        }
    }

}
