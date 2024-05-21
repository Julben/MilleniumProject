package org.example.milleniumproject.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

/**
 * La classe SoundPlayer permet de créer des sons lorsque un bouton est clické.
 */
public class SoundPlayer {

    private static MediaPlayer clickPlayer;

    /**
     * Diffuse le son avec le volume demandé.
     *
     * @param volume Le volume du son.
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
     * Diffuse le son récupéré depuis les données audio.
     */
    public static void soundPlay() {
        double volume = AudioData.getSoundVolume();
        soundPlay(volume);
    }


}