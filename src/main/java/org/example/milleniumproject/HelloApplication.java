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

        LoadVideo loadVideo = new LoadVideo();  //Instanciation de la vidéo de démarrage
        Menu menu = new Menu();  // Instanciation de la classe Menu

        String videoPath = "src/main/resources/Video.mp4";
        MediaPlayer mediaPlayer = loadVideo.chargerVideo(primaryStage, videoPath);

        // Affiche le Menu et lance la musique lorsque la vidéo s'arrète
        mediaPlayer.setOnEndOfMedia(() -> {
            menu.afficherMenu(primaryStage);
            MusicPlayer.musicPlay("src/main/resources/Star Wars_ Battlefront OST - Main Menu Music.mp3");
        });

        // Skip la vidéo lorsque l'on presse la touche "Espace"
        primaryStage.getScene().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE && !mediaPlayer.getCurrentTime().equals(mediaPlayer.getTotalDuration())) {
                mediaPlayer.stop();
                menu.afficherMenu(primaryStage);
                MusicPlayer.musicPlay("src/main/resources/Star Wars_ Battlefront OST - Main Menu Music.mp3");
            }
        });
    }
    public static void main(String[] args) {
        launch(args);
    }
}