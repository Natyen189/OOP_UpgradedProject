package TowerDefense.Button;

import javafx.scene.Node;

public class SellButton extends GameButton {

    public SellButton(String imageLocation, Node buttonImage) {
        super(imageLocation, buttonImage);
        sellTower();
    }

    public void sellTower() {
        setOnMouseClicked(event -> {
            for(int i = 0; i < TowerButton.towerList.size(); i++) {
                if(TowerButton.towerList.get(i).isSelected) {
                    TowerButton.towerList.get(i).sellTower();
                }
            }
        });
    }
}
