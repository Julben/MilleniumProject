package org.example.milleniumproject.model;

import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import org.example.milleniumproject.view.AudioData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class MusicPlayerTest {
    private MediaPlayer mediaPlayer;

    @BeforeEach
    void setUp() {
        mediaPlayer = new MediaPlayer(null); // Utilisation d'un lecteur média factice pour les tests
    }

    @Test
    void testMusicPlay() {
        // Initialisation du MediaPlayer
        MusicPlayer.setMediaPlayer(mediaPlayer);

        // Appel de la méthode musicPlay avec un fichier de musique fictif
        MusicPlayer.musicPlay("src/main/resources/testMusic.mp3");

        // Vérification que le MediaPlayer a été initialisé correctement
        assertNotNull(mediaPlayer);

        // Vérification que la lecture de la musique a commencé
        assertTrue(mediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING));

        // Vérification que le volume a été réglé correctement
        assertEquals(AudioData.getMusicVolume(), mediaPlayer.getVolume());

        // Vérification que la lecture revient au début une fois terminée
        mediaPlayer.seek(Duration.seconds(10));
        mediaPlayer.setOnEndOfMedia(() -> {
            assertEquals(Duration.ZERO, mediaPlayer.getCurrentTime());
        });
    }

    @Test
    void testStopPlaying() {
        // Initialisation du MediaPlayer
        MusicPlayer.setMediaPlayer(mediaPlayer);

        // Démarrage de la musique
        MusicPlayer.musicPlay("src/main/resources/testMusic.mp3");

        // Arrêt de la musique
        MusicPlayer.stopPlaying();

        // Vérification que la musique a été arrêtée
        assertTrue(mediaPlayer.getStatus().equals(MediaPlayer.Status.STOPPED));

        // Vérification que le MediaPlayer a été libéré
        assertNull(MusicPlayer.getMediaPlayer());
    }

    @Test
    void testSetVolume() {
        // Initialisation du MediaPlayer
        MusicPlayer.setMediaPlayer(mediaPlayer);

        // Réglage du volume
        MusicPlayer.setVolume(0.5);

        // Vérification que le volume a été réglé correctement
        assertEquals(0.5, mediaPlayer.getVolume());
    }
}
