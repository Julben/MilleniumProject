package org.example.milleniumproject.model;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import static org.example.milleniumproject.model.Constant.screenHeight;
import static org.example.milleniumproject.model.Constant.screenWidth;
/**
 * Cette classe fournit des méthodes pour la sélection, la désélection et la création d'un style pour les boutons'.
 */
public class ButtonSelector {
    /**
     * Met en surbrillance et agrandit l'image du pion.
     *
     * @param button Le bouton à sélectionner.
     */
    static void selectButton(Button button) {
        button.setStyle("-fx-background-color: yellow; -fx-background-radius: 50%");
        if (button.getGraphic() != null && button.getGraphic() instanceof ImageView) {
            ImageView originalImageView = (ImageView) button.getGraphic();
            originalImageView.setScaleX(1.5);
            originalImageView.setScaleY(1.5);
        }
    }
    /**
     * Désélectionne le bouton seléctionné et lui retire sa surbrillance et son image.
     *
     * @param button Le bouton à désélectionner.
     */
    static void deselectButton(Button button) {
        button.setStyle("-fx-background-color: transparent");
        if(button.getGraphic() != null){
            ImageView originalImageView = (ImageView) button.getGraphic();
            originalImageView.setScaleX(1.0);
            originalImageView.setScaleY(1.0);
        }
    }
    /**
     * Crée un bouton avec un texte et lui attribue des dimensions et un style prédéfini.
     *
     * @param label Le texte à afficher sur le bouton.
     * @return Le bouton créé avec le style.
     */
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