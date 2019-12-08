package TowerDefense;

import javafx.animation.Animation;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public final class MusicContainer {

    private static File menuTrackLocation = new File("Asset\\Music\\Menu.mp3");
    public static final MediaPlayer menuTrack = new MediaPlayer(new Media(menuTrackLocation.toURI().toString()));

    private static File mainTrackLocation = new File("Asset\\Music\\Main.mp3");
    public static final MediaPlayer mainTrack = new MediaPlayer(new Media(mainTrackLocation.toURI().toString()));

    private static File lostLocation = new File("Asset\\Music\\Lost.mp3");
    public static final MediaPlayer lostTrack = new MediaPlayer(new Media(lostLocation.toURI().toString()));

    private static File clickLocation = new File("Asset\\Music\\Click.mp3");
    public static final MediaPlayer clickSound = new MediaPlayer(new Media(clickLocation.toURI().toString()));

    private static File clickSpecialLocation = new File("Asset\\Music\\Click_Special.mp3");
    public static final MediaPlayer clickSpecialSound = new MediaPlayer(new Media(clickSpecialLocation.toURI().toString()));

    private static File placeTowerLocation = new File("Asset\\Music\\PlaceTower.mp3");
    public static final MediaPlayer playTowerSound = new MediaPlayer(new Media(placeTowerLocation.toURI().toString()));

    private static File hotPotLocation = new File("Asset\\Music\\HotPot.mp3");
    public static final MediaPlayer hotPotSound = new MediaPlayer(new Media(hotPotLocation.toURI().toString()));

    private static File freezeLocation = new File("Asset\\Music\\FreezeSpecial.mp3");
    public static final MediaPlayer freezeSound = new MediaPlayer(new Media(freezeLocation.toURI().toString()));

    public static void playMenuMusic() {
        menuTrack.setVolume(0.06);
        menuTrack.setCycleCount(Animation.INDEFINITE);
        menuTrack.play();
    }

    public static void playMainMusic() {
        mainTrack.setVolume(0.05);
        mainTrack.setCycleCount(Animation.INDEFINITE);
        mainTrack.play();
    }

}
