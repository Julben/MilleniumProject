package org.example.milleniumproject.model;

import javafx.scene.control.Button;

public class ButtonSelector
{
    public static void selectButton(Button button) {
        button.setStyle("-fx-background-color: yellow;"+
                "-fx-background-radius: 50%; " + // Rendre les coins ronds
                "-fx-min-width: 65px; " + // Définir la largeur
                "-fx-min-height: 65px; " + // Définir la hauteur
                "-fx-max-width: 65px; " + // Limiter la largeur
                "-fx-max-height: 65px;");
    }

    // Méthode pour désélectionner un bouton
    public static void deselectButton(Button button) {
        button.setStyle("-fx-background-radius: 50%; " + // Rendre les coins ronds
                "-fx-min-width: 65px; " + // Définir la largeur
                "-fx-min-height: 65px; " + // Définir la hauteur
                "-fx-max-width: 65px; " + // Limiter la largeur
                "-fx-max-height: 65px;" +
                "-fx-background-color: transparent; " + // Couleur de fond transparente
                "-fx-border-color: transparent;"); // Couleur de bordure transparente
    }

    // Méthode pour créer un bouton stylisé
    public static Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-radius: 50%; " + // Rendre les coins ronds
                "-fx-min-width: 65px; " + // Définir la largeur
                "-fx-min-height: 65px; " + // Définir la hauteur
                "-fx-max-width: 65px; " + // Limiter la largeur
                "-fx-max-height: 65px;"+
                "-fx-background-color: transparent; -fx-border-color: transparent;"); // Limiter la hauteur

        return button;
    }
}
