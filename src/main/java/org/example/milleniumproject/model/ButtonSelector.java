package org.example.milleniumproject.model;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import static org.example.milleniumproject.model.Constant.screenHeight;
import static org.example.milleniumproject.model.Constant.screenWidth;

public class ButtonSelector {

    static void selectButton(Button button) {
        button.setStyle("-fx-background-color: yellow; -fx-background-radius: 50%");
        if (button.getGraphic() != null && button.getGraphic() instanceof ImageView) {
            ImageView originalImageView = (ImageView) button.getGraphic();
            originalImageView.setScaleX(1.5);
            originalImageView.setScaleY(1.5);
        }
    }

    static void deselectButton(Button button) {
        button.setStyle("-fx-background-color: transparent");
        if(button.getGraphic() != null){
            ImageView originalImageView = (ImageView) button.getGraphic();
            originalImageView.setScaleX(1.0);
            originalImageView.setScaleY(1.0);
        }
    }

    static Button createStyledButton(String label) {
        Button button = new Button(label);
        button.setId(label);
        button.setPrefSize(0.03906*screenWidth, 0.069444*screenHeight);
        button.setMinSize(0.03906*screenWidth, 0.069444*screenHeight);
        button.setMaxSize(0.03906*screenWidth, 0.069444*screenHeight);
        button.setStyle("-fx-background-color: transparent");
        button.setTextFill(Color.TRANSPARENT);
        return button;
    }
}