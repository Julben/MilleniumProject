package org.example.milleniumproject.model;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.example.milleniumproject.presentation.InvisibleButtons;
import org.example.milleniumproject.view.PreParty;
import org.example.milleniumproject.view.PrePartyIA;
import org.example.milleniumproject.view.Audio;
import org.example.milleniumproject.view.Campaign;
import org.example.milleniumproject.view.Profile;
import org.example.milleniumproject.view.Video;
import java.util.List;

/**
 * Classe qui gère les évènements liés aux boutons du menu.
 */
public class Management {

    /**
     * Gère les événements associés aux boutons du menu.
     *
     * @param primaryStage   La scène en premier plan.
     * @param boutonsComplets La liste des boutons du menu
     */
    public static void handleEvents(Stage primaryStage, List<Button> boutonsComplets) {

        for (Button bouton : boutonsComplets) {
            bouton.setOnAction(event -> {
                SoundPlayer.soundPlay();
                if (bouton.getText().equals("Nouvelle Partie")) {
                    InvisibleButtons.renderInvisibleButtons(boutonsComplets, bouton);

                } else if (bouton.getText().equals("Campagne")) {
                    InvisibleButtons.renderInvisibleButtons(boutonsComplets, bouton);
                    Campaign campagne=new Campaign(primaryStage);
                    primaryStage.getScene().setRoot(campagne);
                    campagne.startCampaign();
                }
                else if (bouton.getText().equals("Jouer contre l'ordinateur")) {
                    InvisibleButtons.renderInvisibleButtons(boutonsComplets, bouton);
                    PrePartyIA preia = new PrePartyIA(primaryStage);
                    primaryStage.getScene().setRoot(preia);
                }
                else if (bouton.getText().equals("Joueur contre joueur")) {
                    InvisibleButtons.renderInvisibleButtons(boutonsComplets, bouton);
                    PreParty pre = new PreParty(primaryStage);
                    primaryStage.getScene().setRoot(pre);
                }
                else if (bouton.getText().equals("Charger une Partie")) {
                    InvisibleButtons.renderInvisibleButtons(boutonsComplets, bouton);
                    Loading chargement = new Loading(primaryStage);
                    primaryStage.getScene().setRoot(chargement);

                } else if (bouton.getText().equals("Profil")) {
                    InvisibleButtons.renderInvisibleButtons(boutonsComplets, bouton);
                    Profile profil = new Profile(primaryStage);
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