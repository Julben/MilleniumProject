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
 * Classe pour appliquer un style aux boutons.
 */
public class ButtonsStyle {

    /**
     * Applique un style aux boutons.
     * @param bouton Le bouton auquel appliquer le style.
     * @param width  La largeur du bouton.
     * @param height La hauteur du bouton.
     * @param size   La taille du texte.
     */
    public static void applyStyle(Button bouton, double width, double height, double size) {
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