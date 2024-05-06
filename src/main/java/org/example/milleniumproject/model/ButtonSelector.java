package org.example.milleniumproject.model;

import javafx.scene.control.Button;

/**
 * Classe pour sélectionner et désélectionner les boutons et pour créer des boutons stylisés.
 */
public class ButtonSelector {

    /**
     * Sélectionne le bouton en lui appliquant un style jaune.
     *
     * @param button Le bouton à sélectionner.
     */
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
                "-fx-max-height: 65px;" +
                "-fx-background-color: transparent; -fx-border-color: transparent;"); // Limiter la hauteur

        return button;
    }
}
