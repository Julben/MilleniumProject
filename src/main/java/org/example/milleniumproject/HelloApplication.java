package org.example.milleniumproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.milleniumproject.model.Buttons.ButtonsMenu;
import org.example.milleniumproject.model.LoadVideo;
import org.example.milleniumproject.view.Menu;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        LoadVideo videoChargement = new LoadVideo();
        Menu menu = new Menu(); // Création de l'instance de la classe Menu

        String videoPath = "src/main/resources/Video.mp4";
        MediaPlayer mediaPlayer = videoChargement.chargerVideo(primaryStage, videoPath);

        // Chargement de l'image du menu
        Image menuImage = new Image("TestBckgrnd.png");
        ImageView menuImageView = new ImageView(menuImage);
        menuImageView.setPreserveRatio(true); // Conserver le ratio de l'image
        menuImageView.setFitWidth(primaryStage.getWidth());
        menuImageView.setFitHeight(primaryStage.getHeight());

        // Création des boutons du menu
        ButtonsMenu buttonsMenuCreator = new ButtonsMenu();
        List<Button> boutonsComplets = buttonsMenuCreator.creerBoutons();
        List<Button> boutons1 = new ArrayList<>(boutonsComplets.subList(0, 5)); // Les 5 premiers boutons
        List<Button> boutons2 = new ArrayList<>(boutonsComplets.subList(5, boutonsComplets.size())); // Les 5 suivants


        // Ajout d'un écouteur d'événements pour détecter la fin de la vidéo
        mediaPlayer.setOnEndOfMedia(() -> {
            // Lorsque la vidéo est terminée, affichez l'image du menu
            menu.afficherMenu(primaryStage, menuImageView, boutons1, boutons2, boutonsComplets);
        });

        // Ajout d'un gestionnaire d'événements pour la touche espace
        primaryStage.getScene().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE && !mediaPlayer.getCurrentTime().equals(mediaPlayer.getTotalDuration())) {
                mediaPlayer.stop();
                menu.afficherMenu(primaryStage, menuImageView, boutons1, boutons2, boutonsComplets);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
