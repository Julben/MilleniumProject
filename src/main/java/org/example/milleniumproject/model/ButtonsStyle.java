package org.example.milleniumproject.model;

import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ButtonsStyle {

    // Méthode pour appliquer le style aux boutons
    public static void appliquerStyle(Button bouton, int width, int height, int size) {
        bouton.setPrefSize(width, height); // Taille préférée des boutons (largeur x hauteur)
        bouton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(5), javafx.geometry.Insets.EMPTY)));
        bouton.setTextFill(Color.WHITE); // Couleur du texte
        bouton.setFont(Font.font("Cardo", FontWeight.BOLD, size)); // Police et taille du texte

        // Changement de style lorsque survolé
        bouton.setOnMouseEntered(event -> {
            bouton.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(50), javafx.geometry.Insets.EMPTY)));
            bouton.setTextFill(Color.BLACK);
        });

        // Rétablissement du style initial lorsque le curseur quitte le bouton
        bouton.setOnMouseExited(event -> {
            bouton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(5), javafx.geometry.Insets.EMPTY)));
            bouton.setTextFill(Color.WHITE);
        });
    }
}