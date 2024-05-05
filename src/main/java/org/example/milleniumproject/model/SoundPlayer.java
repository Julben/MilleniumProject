package org.example.milleniumproject.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

//Créer une classe pour que les boutons fassent un bruit lorsque l'on clique dessus
public class SoundPlayer {

    public static void soundPlay() {
        String clickSoundFile = "src/main/resources/button-202966.mp3";
        Media clickSound = new Media(new File(clickSoundFile).toURI().toString());
        MediaPlayer clickPlayer = new MediaPlayer(clickSound);

        clickPlayer.stop(); // Arrête le son précédent pour que les sons ne se chevauchent pas
        clickPlayer.play(); // Joue le son de clic
    }
}