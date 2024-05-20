package org.example.milleniumproject.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

/**
 * La classe SoundPlayer permet de gérer la lecture des sons dans l'application.
 * Elle utilise JavaFX pour jouer les fichiers audio.
 */
public class SoundPlayer {

    private static MediaPlayer clickPlayer;

    /**
     * Joue le son avec le volume spécifié.
     *
     * @param volume Le volume du son (entre 0.0 et 1.0).
     */
    public static void soundPlay(double volume) {
        String clickSoundFile = "src/main/resources/button-202966.mp3";

        Media clickSound = new Media(new File(clickSoundFile).toURI().toString());

        if (clickPlayer == null) {
            clickPlayer = new MediaPlayer(clickSound);
        }

        clickPlayer.stop();

        clickPlayer.setVolume(volume);

        clickPlayer.play();
    }

    /**
     * Joue le son avec le volume récupéré depuis les données audio.
     */
    public static void soundPlay() {
        double volume = AudioData.getSoundVolume();
        soundPlay(volume);
    }
}