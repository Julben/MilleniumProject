package org.example.milleniumproject.view;

import javafx.beans.binding.Bindings;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * VideoPlayer est une classe qui permet de gérer les vidéos de la campagne.
 */
public class VideoPlayer extends StackPane {

    private MediaPlayer mediaPlayer;

    /**
     * Construit un VideoPlayer pour lire les vidéos de la campagne.
     *
     * @param videoPath le chemin du fichier vidéo.
     */
    public VideoPlayer(String videoPath) {
        Media media = new Media(videoPath);
        mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);

        mediaView.fitWidthProperty().bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
        mediaView.fitHeightProperty().bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
        mediaView.setPreserveRatio(true);

        getChildren().add(mediaView);
        setStyle("-fx-alignment: center;");

        setFocusTraversable(true);
    }

    /**
     * Lance les vidéos de la campagne.
     *
     * @param onEnd le Runnable à exécuter lorsque la vidéo se termine.
     */
    public void playVideo(Runnable onEnd) {
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.pause();
            onEnd.run();
        });
        mediaPlayer.play();
    }

    public void stopVideo() {
        mediaPlayer.stop();
    }
}
