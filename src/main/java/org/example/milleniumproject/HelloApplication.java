package org.example.milleniumproject;

import org.example.milleniumproject.model.MusicPlayer;
import org.example.milleniumproject.view.Menu;
import org.example.milleniumproject.model.LoadVideo;
import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        LoadVideo loadVideo = new LoadVideo();
        Menu menu = new Menu(); // Création de l'instance de la classe Menu

        String videoPath = "src/main/resources/Video.mp4";
        MediaPlayer mediaPlayer = loadVideo.chargerVideo(primaryStage, videoPath);

        // Ajout d'un écouteur d'événements pour détecter la fin de la vidéo
        mediaPlayer.setOnEndOfMedia(() -> {
            // Lorsque la vidéo est terminée, affichez l'image du menu
            menu.afficherMenu(primaryStage);
            MusicPlayer.musicPlay("src/main/resources/MenuMusic.wav");
        });

        // Ajout d'un gestionnaire d'événements pour la touche espace
        primaryStage.getScene().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE && !mediaPlayer.getCurrentTime().equals(mediaPlayer.getTotalDuration())) {
                mediaPlayer.stop();
                menu.afficherMenu(primaryStage);
                MusicPlayer.musicPlay("src/main/resources/MenuMusic.wav");
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}