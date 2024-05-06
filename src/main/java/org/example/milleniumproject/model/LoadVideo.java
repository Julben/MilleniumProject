package org.example.milleniumproject.model;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;

/**
 * Classe permettant de charger et de lire une vidéo au démarrage de l'application.
 */
public class LoadVideo {

    /**
     * Charge et lit la vidéo au démarrage de l'application.
     * @param primaryStage La fenêtre principale de l'application
     * @param videoPath    Le chemin vers le fichier vidéo
     * @return Le MediaPlayer utilisé pour lire la vidéo
     */
    public MediaPlayer chargerVideo(Stage primaryStage, String videoPath) {

        // Création du lecteur média et du lecteur vidéo
        Media media = new Media(new File(videoPath).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);

        // Redimensionner la vidéo pour qu'elle s'adapte à la taille de la fenêtre
        mediaView.setPreserveRatio(false);
        mediaView.fitWidthProperty().bind(primaryStage.widthProperty());
        mediaView.fitHeightProperty().bind(primaryStage.heightProperty());

        // Création de la scène et ajout du lecteur vidéo
        StackPane root = new StackPane(mediaView);
        Scene scene = new Scene(root);

        // Plein écran
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");

        // Ajout de la scène à la fenêtre principale et affichage de la fenêtre
        primaryStage.setScene(scene);
        primaryStage.show();

        // Ajout de la phrase pour passer la vidéo
        Label skipLabel = new Label("Pour passer la vidéo, appuyez sur la touche 'Espace'");
        skipLabel.setTextFill(Color.WHITE);
        skipLabel.setFont(Font.font("Cardo", FontWeight.BOLD, 25));
        StackPane.setAlignment(skipLabel, Pos.BOTTOM_RIGHT); // Aligner le label en bas à droite
        StackPane.setMargin(skipLabel, new Insets(0, 10, 10, 0)); // Marge pour le label
        root.getChildren().add(skipLabel); // Ajouter le label au StackPane utilisé dans la scène vidéo

        // Animation de clignotement de la phrase
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1.0), skipLabel);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(Animation.INDEFINITE); // Répéter indéfiniment
        fadeTransition.setAutoReverse(true); // Revenir à la valeur initiale

        // Démarrer l'animation
        fadeTransition.play();

        // Lecture de la vidéo
        mediaPlayer.play();
        return mediaPlayer; // Retourner le MediaPlayer pour pouvoir ajouter un écouteur d'événements dans HelloApplication
    }
}
