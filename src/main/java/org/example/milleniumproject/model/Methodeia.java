package org.example.milleniumproject.model;

import javafx.animation.PauseTransition;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.List;

public class Methodeia {

    public static void disableMouseInteractions(GridPane gridpane, boolean disable) {
        for (Node node : gridpane.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                button.setMouseTransparent(disable);
                if (disable) {
                    button.setOnMouseClicked(e -> e.consume()); // Consommer l'événement de clic pour empêcher l'action
                } else {
                    button.setOnMouseClicked(null); // Réinitialiser l'événement de clic
                }
            }
        }
    }
}