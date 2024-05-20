package org.example.milleniumproject.model;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
/**
 * Cette classe fournit des méthodes utiles pour le fonctionnement de l'IA.
 */
public class Methodeia {
    /**
     * Désactive ou réactive la souris de l'utilisateur .
     *
     * @param gridpane Le GridPane contenant les boutons.
     * @param disable  true ou false pour activer ou désactiver la souris.
     */    public static void disableMouseInteractions(GridPane gridpane, boolean disable) {
        for (Node node : gridpane.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                button.setMouseTransparent(disable);
                if (disable) {
                    button.setOnMouseClicked(e -> e.consume());
                } else {
                    button.setOnMouseClicked(null);
                }
            }
        }
    }
}