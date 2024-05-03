package org.example.milleniumproject.model;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Party extends StackPane {

    public Party(Stage primaryStage) {
        // Création du fond d'écran
        BG ground = new BG("src/main/resources/BackgroundTestBoutons.png");
        setBackground(ground.getCustomBackground());

        // Création du GridPane
        GridPane gridPane = new GridPane();

        // Ajout des boutons au GridPane
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 6; col++) {
                Button button = new Button("Button " + (row * 6 + col + 1)); // Nom du bouton basé sur sa position
                gridPane.add(button, col, row); // Ajout du bouton à la position appropriée dans le GridPane
            }
        }

        // Positionnement du GridPane au centre de la StackPane
        StackPane.setAlignment(gridPane, Pos.CENTER);

        // Ajout du GridPane à la StackPane
        getChildren().addAll(gridPane);
    }
}
