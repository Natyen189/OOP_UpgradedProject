package TowerDefense;

import TowerDefense.Button.MenuButton;
import TowerDefense.Button.SellButton;
import TowerDefense.Button.TowerButton;
import TowerDefense.Button.UpgradeButton;
import TowerDefense.GameEntity.Enemy.EnemySpawner;
import TowerDefense.GameEntity.GameEntity;
import TowerDefense.GameEntity.Player.PlayerStats;
import TowerDefense.GameTile.Mountain;
import TowerDefense.GameTile.Road;
import TowerDefense.GameTile.Tower.Tower;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class GameStage {

    public static Pane mainWindow;
    public static Pane gameOverPane;
    public static Scene mainScene;
    public static Scene gameOverScene;
    public static Stage gameStage;

    public GameStage() {
        mainWindow = new Pane();
        gameOverPane = new Pane();
        gameStage = new Stage();
        mainScene = new Scene(mainWindow, gameStage.getMaxWidth(), gameStage.getMaxHeight());
        gameOverScene = new Scene(gameOverPane, gameStage.getMaxWidth(), gameStage.getMaxHeight());
        gameStage.setScene(mainScene);
        createGameOverScene();
        setBackgroundImage();
        createRoad();
        createButton();
        createEnemy();
        createPlayerStat();
    }

    public Stage getState() {
        return gameStage;
    }

    public void createGameOverScene() {
        File file = new File("Asset\\GameOver.jpg");
        ImageView gameOverBackground = new ImageView(new Image(file.toURI().toString()));
        gameOverBackground.setFitWidth(1540);
        gameOverBackground.setPreserveRatio(true);
        gameOverPane.getChildren().add(gameOverBackground);

        File retryButtonLocation = new File("Asset\\Retry.png");
        ImageView retryButtonImage = new ImageView(new Image(retryButtonLocation.toURI().toString()));
        MenuButton retryButton = new MenuButton("", retryButtonImage, MenuButton.ButtonName.RetryButton);
        retryButton.setLayoutX((float)Config.SCREEN_WIDTH/2 - 145);
        retryButton.setLayoutY((float)Config.SCREEN_HEIGHT/2 - 30);
        gameOverPane.getChildren().add(retryButton);

        File exitButtonLocation = new File("Asset\\ExitButton.png");
        ImageView exitButtonImage = new ImageView(new Image(exitButtonLocation.toURI().toString()));
        exitButtonImage.setFitWidth(236);
        exitButtonImage.setFitHeight(45);
        MenuButton exitButton = new MenuButton("", exitButtonImage, MenuButton.ButtonName.ExitButton);
        exitButton.setLayoutX((float)Config.SCREEN_WIDTH/2 - 130);
        exitButton.setLayoutY((float)Config.SCREEN_HEIGHT/2 + 40);
        gameOverPane.getChildren().add(exitButton);

    }

    public void setBackgroundImage() {
        File backGroundLocation = new File("Asset\\Background.png");
        ImageView background = new ImageView(backGroundLocation.toURI().toString());
        background.setFitHeight(800);
        background.setPreserveRatio(true);
        mainWindow.getChildren().add(background);

    }

    private void createButton() {
        createMenuButton();
        createTowerButton();
        createUpgradeButton();
        createSellButton();
    }

    private void createMenuButton() {
        File startButtonLocation = new File("Asset\\PlayButton.png");
        ImageView buttonImage = new ImageView(new Image(startButtonLocation.toURI().toString()));
        MenuButton startButton = new MenuButton("", buttonImage, MenuButton.ButtonName.PlayButton);
        startButton.setLayoutX((float)Config.SCREEN_WIDTH/2 - 195);
        startButton.setLayoutY((float)Config.SCREEN_HEIGHT/2 - 100);
        mainWindow.getChildren().add(startButton);

        File guideButtonLocation = new File("Asset\\GuideButton.png");
        ImageView guideButtonImage = new ImageView(new Image(guideButtonLocation.toURI().toString()));
        guideButtonImage.setFitWidth(236);
        guideButtonImage.setFitHeight(45);
        MenuButton guideButton = new MenuButton("", guideButtonImage, MenuButton.ButtonName.GuideButton);
        guideButton.setLayoutX((float)Config.SCREEN_WIDTH/2 - 180);
        guideButton.setLayoutY((float)Config.SCREEN_HEIGHT/2 - 30);
        mainWindow.getChildren().add(guideButton);

        File exitButtonLocation = new File("Asset\\ExitButton.png");
        ImageView exitButtonImage = new ImageView(new Image(exitButtonLocation.toURI().toString()));
        exitButtonImage.setFitWidth(206);
        exitButtonImage.setFitHeight(39);
        MenuButton exitButton = new MenuButton("", exitButtonImage, MenuButton.ButtonName.ExitButton);
        exitButton.setLayoutX((float)Config.SCREEN_WIDTH/2 - 165);
        exitButton.setLayoutY((float)Config.SCREEN_HEIGHT/2 + 40);
        mainWindow.getChildren().add(exitButton);

        startButton.setOnMouseReleased(event -> {
            guideButton.setDisable(true);
            guideButton.setOpacity(0);
            exitButton.setDisable(true);
            exitButton.setOpacity(0);
        });
    }

    private void createUpgradeButton() {
        File upgradeButtonPath = new File("Asset\\Upgrade.png");
        ImageView buttonImage = new ImageView(new Image(upgradeButtonPath.toURI().toString()));
        UpgradeButton upgradeButton = new UpgradeButton("", buttonImage);
        upgradeButton.setLayoutX((float)Config.SCREEN_WIDTH - 280);
        upgradeButton.setLayoutY((float)Config.SCREEN_HEIGHT - 470);
        mainWindow.getChildren().add(upgradeButton);
    }

    private void createSellButton() {
        File sellButtonPath = new File("Asset\\Sell.png");
        ImageView buttonImage = new ImageView(new Image(sellButtonPath.toURI().toString()));
        SellButton sellButton = new SellButton("", buttonImage);
        sellButton.setLayoutX((float)Config.SCREEN_WIDTH - 120);
        sellButton.setLayoutY((float)Config.SCREEN_HEIGHT - 470);
        mainWindow.getChildren().add(sellButton);
    }

    private void createTowerButton() {

        double startXPos = 1210;
        double startYPos = 27;

        TowerButton tower1 = new TowerButton(new Tower(GameEntity.TowerType.NormalTower));
        TowerButton tower2 = new TowerButton(new Tower(GameEntity.TowerType.SniperTower));
        TowerButton tower3 = new TowerButton(new Tower(GameEntity.TowerType.MachineGun));
        TowerButton tower4 = new TowerButton(new Tower(GameEntity.TowerType.AirTower));
        TowerButton tower5 = new TowerButton(new Tower(GameEntity.TowerType.RayTower));
        TowerButton tower6 = new TowerButton(new Tower(GameEntity.TowerType.IceTurret));

        tower1.setLayoutX(startXPos);
        tower1.setLayoutY(startYPos);

        tower2.setLayoutX(startXPos + 130);
        tower2.setLayoutY(startYPos + 2);

        tower3.setLayoutX(startXPos);
        tower3.setLayoutY(startYPos + 110);

        tower4.setLayoutX(startXPos + 130);
        tower4.setLayoutY(startYPos + 117);

        tower5.setLayoutX(startXPos + 260);
        tower5.setLayoutY(startYPos + 2);

        tower6.setLayoutX(startXPos + 260);
        tower6.setLayoutY(startYPos + 110);

        mainWindow.getChildren().add(tower1);
        mainWindow.getChildren().add(tower2);
        mainWindow.getChildren().add(tower3);
        mainWindow.getChildren().add(tower4);
        mainWindow.getChildren().add(tower5);
        mainWindow.getChildren().add(tower6);
    }

    public void createEnemy() {

        /*Gọi quân địch*/
        EnemySpawner enemySpawner = new EnemySpawner();
        mainWindow.getChildren().add(enemySpawner);

        /*Khoảng cách giữa 2 wave là 6 giây*/
        Timeline enemyTimeline  = new Timeline(new KeyFrame(Duration.seconds(6), event-> {
            if(MenuButton.startGame) {
                /*Tạo quân địch dựa trên level*/
                enemySpawner.spawnEnemy(EnemySpawner.level);
                if(EnemySpawner.level < 30)
                EnemySpawner.level += 1;
            }
        }));
        enemyTimeline.setCycleCount(Animation.INDEFINITE);
        enemyTimeline.play();
    }

    private void createRoad() {
        Road roadPath = new Road();
        Mountain mountain = new Mountain();
    }

    private void createPlayerStat() {
        PlayerStats playerStats = new PlayerStats();
    }
}
