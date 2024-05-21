package org.example.milleniumproject.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.io.File;
/**
 * La classe MusicPlayer gère la lecture de la musique.
 */
public class MusicPlayer {

    private static MediaPlayer mediaPlayer;
    /**
     * Joue la musique.
     *
     * @param musicFile Le fichier de la musique.
     */
    public static void musicPlay(String musicFile) {

        stopPlaying();

        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
        mediaPlayer.play();
        mediaPlayer.setVolume(AudioData.getMusicVolume());
    }
    /**
     * Arrête la lecture de la musique.
     */
    public static void stopPlaying() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
            mediaPlayer = null;
        }
    }
    /**
     * Définit le volume de la musique.
     *
     * @param volume Le volume.
     */
    public static void setVolume(double volume) {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volume);
        }
    }
// pas appeler pour l'instant
    public static MediaPlayer getMediaPlayer() {

        return mediaPlayer;
    }

    public static void setMediaPlayer(MediaPlayer mediaPlayer) {

        MusicPlayer.mediaPlayer = mediaPlayer;
    }
}