package org.example.milleniumproject.model;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import org.example.milleniumproject.view.Profil;
import org.example.milleniumproject.model.ProfileData;

import java.io.File;

import static org.example.milleniumproject.model.Constant.screenHeight;
import static org.example.milleniumproject.model.Constant.screenWidth;

public class PreParty extends StackPane {
    //String[] vaisseau = {"src/main/resources/Pion/PionDestroyer.png", "src/main/resources/Pion/PionFaucon.png", "src/main/resources/Pion/PionTfighter.png", "src/main/resources/Pion/PionXwing.png"};
    String video;
    String shipIndex1 = ProfileData.getShip(1);
    String shipIndex2 = ProfileData.getShip(2);

    public PreParty(Stage primaryStage) {

        Button retourButton = BackButtons.createBackButton(primaryStage);

        BG ground = new BG("src/main/resources/BGAUDIO.png");
        setBackground(ground.getCustomBackground());



        Button jouer = new Button("Jouer");

        jouer.setOnAction(event -> {
            VideoLoad(primaryStage);
        });

        getChildren().addAll(jouer, retourButton);
    }

    public String ChooseVideo(String shipIndex1, String shipIndex2) {

        if ((shipIndex1.equals("src/main/resources/PionDestroyer.png") && shipIndex2.equals("src/main/resources/PionFaucon.png") )||(shipIndex1.equals("src/main/resources/PionFaucon.png")  && shipIndex2.equals("src/main/resources/PionDestroyer.png"))) {
            video ="src/main/resources/VideoLoad/FauconDestroyerNabooFinal.mp4";
        } else if ((shipIndex1.equals("src/main/resources/PionDestroyer.png")  && shipIndex2.equals("src/main/resources/PionTfighter.png") )||(shipIndex1.equals("src/main/resources/PionTfighter.png") && shipIndex2.equals("src/main/resources/PionDestroyer.png") )) {
            video =  "src/main/resources/VideoLoad/TfighterDestroyerNabooFinal.mp4";
        } else if ((shipIndex1.equals("src/main/resources/PionDestroyer.png")  && shipIndex2.equals("src/main/resources/PionXwing.png"))||(shipIndex1.equals("src/main/resources/PionXwing.png")  && shipIndex2.equals("src/main/resources/PionDestroyer.png") )) {
            video = "src/main/resources/VideoLoad/XwingDestroyerNabooFinal.mp4";
        } else if ((shipIndex1.equals("src/main/resources/PionFaucon.png") && shipIndex2.equals("src/main/resources/PionTfighter.png"))||(shipIndex1.equals("src/main/resources/PionTfighter.png") && shipIndex2.equals("src/main/resources/PionFaucon.png"))) {
            video = "src/main/resources/VideoLoad/FauconTfighterNabooFinal.mp4";
        } else if ((shipIndex1.equals("src/main/resources/PionFaucon.png") && shipIndex2.equals("src/main/resources/PionXwing.png"))||(shipIndex1.equals("src/main/resources/PionXwing.png") && shipIndex2.equals("src/main/resources/PionFaucon.png"))) {
            video = "src/main/resources/VideoLoad/FauconXwingNabooFinal.mp4";
        } else {
            video = "src/main/resources/VideoLoad/XwingTfighterNabooFinal.mp4";
        }
        return video ;
    }
    public void VideoLoad(Stage primaryStage) {
        File file = new File(ChooseVideo(shipIndex1, shipIndex2));
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);

        // Ajustez la taille du MediaView
        mediaView.setFitWidth(screenWidth);
        mediaView.setFitHeight(screenHeight);

        // Ajoutez le MediaView à la scène
        StackPane root = new StackPane();
        root.getChildren().add(mediaView);

        // Créez une nouvelle scène avec le StackPane contenant le MediaView
        Scene scene = new Scene(root, screenWidth, screenHeight);

        // Mettez à jour la scène du primaryStage
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();

        primaryStage.getScene().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE && !mediaPlayer.getCurrentTime().equals(mediaPlayer.getTotalDuration())) {
                mediaPlayer.stop();
                Party party = new Party(primaryStage); // Supposons que primaryStage soit accessible ici
                primaryStage.getScene().setRoot(party);

            }
        });

        mediaPlayer.setOnEndOfMedia(() -> {
            Party party = new Party(primaryStage); // Supposons que primaryStage soit accessible ici
            primaryStage.getScene().setRoot(party);
        });
        mediaPlayer.play();

    }

}