/*
package org.example.milleniumproject.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class SoundPlayer {

    private static MediaPlayer clickPlayer;

    public static void soundPlay() {
        double volume = AudioData.getSoundVolume(); // Obtient le volume depuis AudioData
        soundPlay(volume);
    }

    public static void soundPlay(double volume) {
        // Chemin du fichier audio de clic
        String clickSoundFile = "src/main/resources/button-202966.mp3";

        // Crée un objet Media à partir du fichier audio
        Media clickSound = new Media(new File(clickSoundFile).toURI().toString());

        // Crée un lecteur MediaPlayer pour le son de clic
        if (clickPlayer == null) {
            clickPlayer = new MediaPlayer(clickSound);
        }

        // Arrête le son précédent pour éviter les chevauchements
        clickPlayer.stop();

        // Configure le volume du lecteur de médias
        clickPlayer.setVolume(volume);

        // Joue le son de clic
        clickPlayer.play();
    }
}
*/