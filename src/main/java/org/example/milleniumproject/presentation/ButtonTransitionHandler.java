package org.example.milleniumproject.presentation;

import javafx.animation.TranslateTransition;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.example.milleniumproject.model.SoundPlayer;

import java.util.List;

public class ButtonTransitionHandler {

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
                    SoundPlayer.soundPlay();
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