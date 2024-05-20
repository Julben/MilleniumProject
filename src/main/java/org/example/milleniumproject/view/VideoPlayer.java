package org.example.milleniumproject.view;

import javafx.beans.binding.Bindings;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.milleniumproject.model.Party;
import org.example.milleniumproject.model.PartyIA;

public class VideoPlayer extends StackPane {
    private MediaPlayer mediaPlayer;

    public VideoPlayer(String videoPath) {
        Media media = new Media(videoPath);
        mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);

        mediaView.fitWidthProperty().bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
        mediaView.fitHeightProperty().bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
        mediaView.setPreserveRatio(true);

        getChildren().add(mediaView);
        setStyle("-fx-alignment: center;");

        setOnKeyPressed(event -> handleKeyPress(event));
        setFocusTraversable(true);
    }

    private void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            Duration duration = mediaPlayer.getTotalDuration();
            if (duration != null && !duration.isUnknown()) {
                Duration seekTime = duration.subtract(Duration.seconds(5));
                mediaPlayer.seek(seekTime);
                mediaPlayer.play();
            }
        }
    }

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