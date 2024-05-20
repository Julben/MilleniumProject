package org.example.milleniumproject.presentation;

import javafx.animation.TranslateTransition;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.util.List;
/**
 * La classe ButtonTransitionHandler gère l'animation des pions'.
 */
public class ButtonTransitionHandler {
    /**
     * Effectue l'animation des pions.
     *
     * @param selectedButton Le bouton contenant le pion à déplacée.
     * @param clickedButton Le bouton où le pion sera déplacée.
     * @param buttons La liste des boutons du joueur 1 ou 2.
     * @param onComplete L'action à exécuter à la fin de l'animation.
     */
    public static void performTransition(Button selectedButton, Button clickedButton, List<Button> buttons,
                                         Runnable onComplete) {
        if (clickedButton.getGraphic() == null && selectedButton != null) {
            ImageView imageView = (ImageView) selectedButton.getGraphic();
            if (imageView != null) {
                Point2D start = selectedButton.localToScene(selectedButton.getWidth() / 2 - imageView.getFitWidth() / 2,
                        selectedButton.getHeight() / 2 - imageView.getFitHeight() / 2);
                Point2D end = clickedButton.localToScene(clickedButton.getWidth() / 2 - imageView.getFitWidth() / 2,
                        clickedButton.getHeight() / 2 - imageView.getFitHeight() / 2);

                TranslateTransition transition = new TranslateTransition(Duration.seconds(1), imageView);
                transition.setFromX(0);
                transition.setFromY(0);
                transition.setToX(end.getX() - start.getX());
                transition.setToY(end.getY() - start.getY());

                transition.setOnFinished(event -> {
                    imageView.setTranslateX(0);
                    imageView.setTranslateY(0);
                    clickedButton.setGraphic(imageView);
                    selectedButton.setGraphic(null);
                    buttons.remove(selectedButton);
                    buttons.add(clickedButton);
                    onComplete.run();
                });
                transition.play();
            }
        }
    }
}