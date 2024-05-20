package org.example.milleniumproject.presentation;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Classe utilitaire pour appliquer des styles aux boutons.
 */
public class ButtonsStyle {

    /**
     * Applique un style spécifié aux boutons.
     * @param bouton Le bouton auquel appliquer le style.
     * @param width  La largeur préférée du bouton.
     * @param height La hauteur préférée du bouton.
     * @param size   La taille de la police du texte.
     */
    public static void appliquerStyle(Button bouton, double width, double height, double size) {
        bouton.setPrefSize(width, height);
        bouton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(50), javafx.geometry.Insets.EMPTY)));
        bouton.setTextFill(Color.WHITE);
        bouton.setFont(Font.font("Cardo", FontWeight.BOLD, size));
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(3);
        dropShadow.setOffsetX(2);
        dropShadow.setOffsetY(2);
        bouton.setEffect(dropShadow);

        bouton.setOnMouseEntered(event -> {
            bouton.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(50), javafx.geometry.Insets.EMPTY)));
            bouton.setTextFill(Color.BLACK);
        });

        bouton.setOnMouseExited(event -> {
            bouton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(50), javafx.geometry.Insets.EMPTY)));
            bouton.setTextFill(Color.WHITE);
        });
    }
}