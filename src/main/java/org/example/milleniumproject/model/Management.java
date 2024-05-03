package org.example.milleniumproject.model;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.example.milleniumproject.view.Audio;
import org.example.milleniumproject.view.Jcj;
import org.example.milleniumproject.view.Profil;
import org.example.milleniumproject.view.Video;

import java.util.List;

public class Management {

    public static void gererEvenements(Stage primaryStage, List<Button> boutonsComplets) {

        for (Button bouton : boutonsComplets) {
            bouton.setOnAction(event -> {
                SoundPlayer.soundPlay();
                if (bouton.getText().equals("Nouvelle Partie")) {
                    InvisibleButtons.renderInvisibleButtons(boutonsComplets, bouton);
                } else if (bouton.getText().equals("Campagne")) {
                    InvisibleButtons.renderInvisibleButtons(boutonsComplets, bouton);
                } else if (bouton.getText().equals("Jouer contre l'ordinateur")) {
                    InvisibleButtons.renderInvisibleButtons(boutonsComplets, bouton);
                } else if (bouton.getText().equals("Joueur contre joueur")) {
                    InvisibleButtons.renderInvisibleButtons(boutonsComplets, bouton);
                    Jcj jcj = new Jcj(primaryStage);
                    primaryStage.getScene().setRoot(jcj);
                } else if (bouton.getText().equals("Charger une Partie")) {
                    InvisibleButtons.renderInvisibleButtons(boutonsComplets, bouton);
                } else if (bouton.getText().equals("Profil")) {
                    InvisibleButtons.renderInvisibleButtons(boutonsComplets, bouton);
                    Profil profil = new Profil(primaryStage);
                    primaryStage.getScene().setRoot(profil);
                } else if (bouton.getText().equals("Paramètres")) {
                    InvisibleButtons.renderInvisibleButtons(boutonsComplets, bouton);
                } else if (bouton.getText().equals("Audio")) {
                    InvisibleButtons.renderInvisibleButtons(boutonsComplets, bouton);
                    Audio audio = new Audio(primaryStage);
                    primaryStage.getScene().setRoot(audio);
                } else if (bouton.getText().equals("Video")) {
                    InvisibleButtons.renderInvisibleButtons(boutonsComplets, bouton);
                    Video video = new Video(primaryStage);
                    primaryStage.getScene().setRoot(video);
                } else if (bouton.getText().equals("Quitter")) {
                    Platform.exit();
                }
            });
        }
    }
}