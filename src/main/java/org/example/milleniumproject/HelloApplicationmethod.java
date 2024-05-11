package org.example.milleniumproject;

import javafx.scene.input.KeyCode;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.example.milleniumproject.model.MusicPlayer;
import org.example.milleniumproject.view.Menu;

public class HelloApplicationmethod  {

    public static void showMenuAndPlayMusic(Stage primaryStage, Menu menu) {
        menu.afficherMenu(primaryStage);
        MusicPlayer.musicPlay("src/main/resources/Star Wars_ Battlefront OST - Main Menu Music.mp3");
    }

    public static void skipVideo(javafx.scene.input.KeyEvent event, MediaPlayer mediaPlayer, Stage primaryStage, Menu menu) {
        if (event.getCode() == KeyCode.SPACE && !mediaPlayer.getCurrentTime().equals(mediaPlayer.getTotalDuration())) {
            mediaPlayer.stop();
            showMenuAndPlayMusic(primaryStage, menu);
        }
    }
}
