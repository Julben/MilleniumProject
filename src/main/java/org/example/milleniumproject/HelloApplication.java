package org.example.milleniumproject;

import org.example.milleniumproject.model.LoadVideo;
import org.example.milleniumproject.model.MusicPlayer;
import org.example.milleniumproject.view.Menu;
import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 * Classe principale de l'application Millenium Project.
 * Cette classe lance l'application, charge une vidéo de démarrage et affiche le menu principal.
 */
public class HelloApplication extends Application {

    /**
     * Méthode principale de l'application.
     * Elle instancie la vidéo de démarrage, le menu principal et lance la musique de fond.
     *
     * @param primaryStage La fenêtre principale de l'application.
     */
    @Override
    public void start(Stage primaryStage) {

        // Instanciation de la vidéo de démarrage
        LoadVideo loadVideo = new LoadVideo();
        // Instanciation du menu principal
        Menu menu = new Menu();

        // Chemin de la vidéo de démarrage
        String videoPath = "src/main/resources/Video.mp4";
        // Chargement de la vidéo
        MediaPlayer mediaPlayer = loadVideo.chargerVideo(primaryStage, videoPath);

        // Affiche le Menu et lance la musique lorsque la vidéo s'arrête
        mediaPlayer.setOnEndOfMedia(() -> {
            menu.afficherMenu(primaryStage);
            MusicPlayer.musicPlay("src/main/resources/Star Wars_ Battlefront OST - Main Menu Music.mp3");
        });

        // Skip la vidéo lorsque la touche "Espace" est pressée
        primaryStage.getScene().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE && !mediaPlayer.getCurrentTime().equals(mediaPlayer.getTotalDuration())) {
                mediaPlayer.stop();
                menu.afficherMenu(primaryStage);
                MusicPlayer.musicPlay("src/main/resources/Star Wars_ Battlefront OST - Main Menu Music.mp3");
            }
        });
    }

    /**
     * Méthode principale qui lance l'application.
     *
     * @param args Les arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
