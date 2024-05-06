package org.example.milleniumproject.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

/**
 * Classe permettant de jouer un son lorsque les boutons sont cliqués.
 */
public class SoundPlayer {

    /**
     * Joue un son lorsque les boutons sont cliqués.
     */
    public static void soundPlay() {
        // Chemin du fichier audio de clic
        String clickSoundFile = "src/main/resources/button-202966.mp3";

        // Crée un objet Media à partir du fichier audio
        Media clickSound = new Media(new File(clickSoundFile).toURI().toString());

        // Crée un lecteur MediaPlayer pour le son de clic
        MediaPlayer clickPlayer = new MediaPlayer(clickSound);

        // Arrête le son précédent pour éviter les chevauchements
        clickPlayer.stop();

        // Joue le son de clic
        clickPlayer.play();
    }
}
