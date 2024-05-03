package org.example.milleniumproject.model;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.ArrayList;
import java.util.List;

public class ButtonsIA {

    public List<Button> creerBoutonsia() {

        List<Button> boutonsia = new ArrayList<>();
        String[] nomsBoutonsia = {"Facile","Moyen","Difficile"};

        for (String nom : nomsBoutonsia) {
            Button boutonia = new Button(nom);
            boutonia.setPrefSize(300, 75); // Taille préférée des boutons (largeur x hauteur)
            boutonia.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(50), Insets.EMPTY)));
            boutonia.setTextFill(Color.WHITE); // Couleur du texte
            boutonia.setFont(Font.font("Cardo", FontWeight.BOLD, 25)); // Police et taille du texte

            boutonia.setOnMouseEntered(event -> { // Changement de style lorsque survolé
                boutonia.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(50), Insets.EMPTY)));
                boutonia.setTextFill(Color.BLACK);
            });

            boutonia.setOnMouseExited(event -> { // Rétablissement du style initial lorsque le curseur quitte le bouton
                boutonia.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(50), Insets.EMPTY)));
                boutonia.setTextFill(Color.WHITE);
            });
            boutonsia.add(boutonia);
        }
        return boutonsia;
    }
}