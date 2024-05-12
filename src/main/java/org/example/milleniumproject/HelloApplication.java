package org.example.milleniumproject;

import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.example.milleniumproject.model.LoadVideo;
import org.example.milleniumproject.model.MusicPlayer;
import org.example.milleniumproject.view.Menu;

/**
 * La classe HelloApplication est la classe principale de l'application Millenium Project.
 * Elle lance l'application, charge une vidéo de démarrage et affiche le menu principal.
 */
public class HelloApplication extends Application {

    /**
     * Méthode principale qui lance l'application.
     *
     * @param args Les arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Méthode principale de l'application.
     * Elle instancie la vidéo de démarrage, le menu principal et lance la musique de fond.
     *
     * @param primaryStage La fenêtre principale de l'application.
     */
    @Override
    public void start(Stage primaryStage) {
        LoadVideo loadVideo = new LoadVideo();
        Menu menu = new Menu();

        String videoPath = "src/main/resources/VideoChargement.mp4";
        MediaPlayer mediaPlayer = loadVideo.chargerVideo(primaryStage, videoPath);

        // Affiche le Menu et lance la musique lorsque la vidéo s'arrête
        mediaPlayer.setOnEndOfMedia(() -> showMenuAndPlayMusic(primaryStage, menu));

        // Skip la vidéo lorsque la touche "Espace" est pressée
        primaryStage.getScene().setOnKeyPressed(event -> skipVideo(event, mediaPlayer, primaryStage, menu));
    }

    /**
     * Passe à la vidéo suivante ou affiche le menu principal en cas de pression de la touche "Espace".
     *
     * @param event        L'événement de pression de touche.
     * @param mediaPlayer  Le lecteur multimédia de la vidéo.
     * @param primaryStage La fenêtre principale de l'application.
     * @param menu         Le menu à afficher.
     */
    public static void skipVideo(javafx.scene.input.KeyEvent event, MediaPlayer mediaPlayer, Stage primaryStage, Menu menu) {
        if (event.getCode() == KeyCode.SPACE && !mediaPlayer.getCurrentTime().equals(mediaPlayer.getTotalDuration())) {
            mediaPlayer.stop();
            showMenuAndPlayMusic(primaryStage, menu);
        }
    }

    /**
     * Affiche le menu principal et lance la musique de fond.
     *
     * @param primaryStage La fenêtre principale de l'application.
     * @param menu          Le menu à afficher.
     */
    public static void showMenuAndPlayMusic(Stage primaryStage, Menu menu) {
        menu.afficherMenu(primaryStage);
        MusicPlayer.musicPlay("src/main/resources/MusicMenu.mp3");
    }
}
