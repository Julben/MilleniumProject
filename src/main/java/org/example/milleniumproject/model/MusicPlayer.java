package org.example.milleniumproject.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.io.File;

public class MusicPlayer {

    private static MediaPlayer mediaPlayer;

    public static void musicPlay(String musicFile) {

        //Si il n'y a pas de musique, relance la musique
        if (mediaPlayer == null) {
            Media sound = new Media(new File(musicFile).toURI().toString());
            mediaPlayer = new MediaPlayer(sound);
        }

        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
            }
        });

        // Ne démarrez la musique que si elle n'est pas déjà en train de jouer
        if (mediaPlayer.getStatus() != MediaPlayer.Status.PLAYING) {
            mediaPlayer.play();
        }
    }

    public static void stopPlaying() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}