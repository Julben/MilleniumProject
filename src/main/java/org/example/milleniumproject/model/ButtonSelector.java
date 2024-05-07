package org.example.milleniumproject.model;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.List;

import static org.example.milleniumproject.model.ButtonUtils.isNeighbourButton;

/**
 * Classe pour sélectionner et désélectionner les boutons et pour créer des boutons stylisés.
 */
public class ButtonSelector {


    public static void handleSelection(List<Button> buttons, Button clickedButton, Button selectedButton, int currentPlayer) {
        if (selectedButton == null) {
            if (buttons.contains(clickedButton)) {
                selectedButton = clickedButton;
                selectButton(selectedButton);
            }
        } else {
            if (isNeighbourButton(selectedButton, clickedButton)) {
                if (clickedButton.getGraphic() == null) {
                    ImageView imageView = (ImageView) selectedButton.getGraphic();
                    clickedButton.setGraphic(imageView);
                    selectedButton.setGraphic(null);
                    buttons.remove(selectedButton);
                    buttons.add(clickedButton);
                    currentPlayer = (currentPlayer == 1) ? 2 : 1;
                }
            }
            deselectButton(selectedButton);
            selectedButton = null;
        }
    }

    public static void selectButton(Button button) {
        button.setStyle("-fx-background-color: yellow;" +
                "-fx-background-radius: 50%; " + // Rendre les coins ronds
                "-fx-min-width: 65px; " + // Définir la largeur
                "-fx-min-height: 65px; " + // Définir la hauteur
                "-fx-max-width: 65px; " + // Limiter la largeur
                "-fx-max-height: 65px;");
    }

    /**
     * Désélectionne le bouton en lui appliquant un style par défaut.
     *
     * @param button Le bouton à désélectionner.
     */
    public static void deselectButton(Button button) {
        button.setStyle("-fx-background-radius: 50%; " + // Rendre les coins ronds
                "-fx-min-width: 65px; " + // Définir la largeur
                "-fx-min-height: 65px; " + // Définir la hauteur
                "-fx-max-width: 65px; " + // Limiter la largeur
                "-fx-max-height: 65px;" +
                "-fx-background-color: transparent; " + // Couleur de fond transparente
                "-fx-border-color: transparent;"); // Couleur de bordure transparente
    }

    /**
     * Crée un bouton stylisé avec un texte spécifié.
     *
     * @param text Le texte du bouton.
     * @return Le bouton stylisé.
     */
    public static Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-radius: 50%; " + // Rendre les coins ronds
                "-fx-min-width: 65px; " + // Définir la largeur
                "-fx-min-height: 65px; " + // Définir la hauteur
                "-fx-max-width: 65px; " + // Limiter la largeur
                "-fx-max-height: 65px;"+
                "-fx-background-color: transparent; -fx-border-color: transparent;"); // Limiter la hauteur
        button.setTextFill(Color.TRANSPARENT);

        return button;
    }


}
