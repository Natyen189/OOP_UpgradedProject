package TowerDefense.GameEntity.Player.PlayerSpecial;

import TowerDefense.Config;
import TowerDefense.GameEntity.Player.PlayerStats;
import TowerDefense.MusicContainer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

public abstract class PlayerSpecial extends VBox {

    protected ImageView specialThumbnail;
    protected ImageView specialDescription;
    protected Text actionText;
    protected ProgressBar countdownBar;
    protected boolean canCastSpecial = true;

    public PlayerSpecial() {
        handleMouse();
        resetToDefaultValue();
    }

     public void handleMouse() {

        setOnMouseEntered(event -> {
            this.setLayoutY(-Config.TILE_SIZE/2);
            this.setEffect(new Glow());
            countdownBar.setVisible(true);
            specialDescription.setVisible(true);
            MusicContainer.clickSpecialSound.play();
        });

         setOnMouseExited(event -> {
             this.setLayoutY(-(double)Config.TILE_SIZE*(1.7));
             this.setEffect(null);
             countdownBar.setVisible(false);
             specialDescription.setVisible(false);
             MusicContainer.clickSpecialSound.stop();
         });

     }

     public void generateCountdownBar() {
         countdownBar = new ProgressBar();
         countdownBar.setProgress(1);
         countdownBar.setPrefWidth(Config.TILE_SIZE*2 - 5);
         countdownBar.setVisible(false);
         countdownBar.setOpacity(0.8);
         this.getChildren().add(countdownBar);
     }

     public void resetSpecial() {
         Timeline reset = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
             if(countdownBar.getProgress() < 1) {
                 countdownBar.setProgress(countdownBar.getProgress() + 0.005);
             }
         }));
         reset.setCycleCount(200);
         reset.setAutoReverse(false);
         reset.setOnFinished(event -> {
             canCastSpecial = true;
         });
         reset.play();
     }

     private void resetToDefaultValue() {
        Timeline resetTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if(PlayerStats.restart) {
                countdownBar.setProgress(1);
                canCastSpecial = true;
            }
        }));
        resetTimeline.setCycleCount(Animation.INDEFINITE);
        resetTimeline.setAutoReverse(false);
        resetTimeline.play();
     }

    abstract void loadThumbnail();

    abstract void cast();

    abstract void displayText();

    abstract void displayDescription();
}
