package TowerDefense.Button;

import TowerDefense.GameEntity.Enemy.EnemySpawner;
import javafx.animation.Animation;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class MenuButton extends GameButton {

    public enum ButtonName {
        PlayButton,
        GuideButton,
        RetryButton,
        ExitButton
    }

    static public boolean startGame = false;
    private ButtonName buttonName;

    public MenuButton(String imageLocation, Node buttonImage, ButtonName buttonName) {
        super(imageLocation, buttonImage);
        this.buttonName = buttonName;
        setMouseOnClick();
    }

    public void setMouseOnClick() {

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
                break;

            case GuideButton:
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
