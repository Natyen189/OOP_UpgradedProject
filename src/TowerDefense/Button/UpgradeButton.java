package TowerDefense.Button;

import javafx.scene.Node;

public class UpgradeButton extends GameButton {

    public UpgradeButton(String imageLocation, Node buttonImage) {
        super(imageLocation, buttonImage);
        upgradeTower();
    }

    private void upgradeTower() {

        setOnMouseClicked(event -> {
            for(int i = 0; i < TowerButton.towerList.size(); i++) {
                if(TowerButton.towerList.get(i).isSelected) {
                    TowerButton.towerList.get(i).upgradeTower();
                }
            }
        });
    }
}
