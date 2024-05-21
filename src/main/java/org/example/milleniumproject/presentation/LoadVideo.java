package org.example.milleniumproject.presentation;

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
import org.example.milleniumproject.model.Constant;
import java.io.File;
import static org.example.milleniumproject.model.Constant.screenHeight;

/**
 * Classe permettant de lancer une vidéo au démarrage du jeu.
 */
public class LoadVideo {

    /**
     * Charge et lit la vidéo au démarrage du jeu.
     * @param primaryStage La scène en premier plan.
     * @param videoPath    Le fichier vidéo
     * @return Le MediaPlayer utilisé pour lire la vidéo
     */
    public MediaPlayer loadVideo(Stage primaryStage, String videoPath) {

        Media media = new Media(new File(videoPath).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);

        mediaView.fitWidthProperty().bind(primaryStage.widthProperty());
        mediaView.fitHeightProperty().bind(primaryStage.heightProperty());

        StackPane root = new StackPane(mediaView);
        Scene scene = new Scene(root);

        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");

        primaryStage.setScene(scene);
        primaryStage.show();

        if(videoPath.equals("src/main/resources/VideoChargement.mp4")) {
            Label skipLabel = new Label("Pour passer la vidéo, appuyez sur la touche 'Espace'");
            skipLabel.setTextFill(Color.WHITE);
            skipLabel.setFont(Font.font("Cardo", FontWeight.BOLD, 0.0347 * screenHeight));
            StackPane.setAlignment(skipLabel, Pos.BOTTOM_RIGHT); // Aligner le label en bas à droite
            StackPane.setMargin(skipLabel, new Insets(0, 0.0078 * Constant.screenWidth, 0.0139 * screenHeight, 0));
            root.getChildren().add(skipLabel);


            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1.0), skipLabel);
            fadeTransition.setFromValue(1.0);
            fadeTransition.setToValue(0.0);
            fadeTransition.setCycleCount(Animation.INDEFINITE); // Répéter indéfiniment
            fadeTransition.setAutoReverse(true); // Revenir à la valeur initiale

            fadeTransition.play();
        }

        mediaPlayer.play();
        return mediaPlayer;
    }
}