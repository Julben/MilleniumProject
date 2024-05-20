package org.example.milleniumproject.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.example.milleniumproject.view.AudioData;

import java.io.File;

/**
 * La classe SoundPlayer permet de gérer la lecture des sons dans l'application.
 * Elle utilise JavaFX pour jouer les fichiers audio.
 */
public class SoundPlayer {

    // Le lecteur de média pour les clics.
    private static MediaPlayer clickPlayer;

    /**
     * Joue le son avec le volume spécifié.
     *
     * @param volume Le volume du son (entre 0.0 et 1.0).
     */
    public static void soundPlay(double volume) {
        // Chemin du fichier audio de clic
        String clickSoundFile = "src/main/resources/button-202966.mp3";

        // Crée un objet Media à partir du fichier audio
        Media clickSound = new Media(new File(clickSoundFile).toURI().toString());

        // Crée un lecteur MediaPlayer pour le son de clic s'il n'existe pas encore
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

    /**
     * Joue le son avec le volume récupéré depuis les données audio.
     */
    public static void soundPlay() {
        double volume = AudioData.getSoundVolume(); // Obtient le volume depuis AudioData
        soundPlay(volume);
    }
}