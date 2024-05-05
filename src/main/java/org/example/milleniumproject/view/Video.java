package org.example.milleniumproject.view;

import org.example.milleniumproject.model.BG;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import org.example.milleniumproject.model.BackButtons;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

public class Video extends StackPane {

    public Video(Stage primaryStage){

        //Créer un fond d'écran
        BG ground = new BG("src/main/resources/BGVIDEO.png");
        setBackground(ground.getCustomBackground());

        // Créer un bouton retour
        Button retourButton = BackButtons.createBackButton(primaryStage);
        StackPane.setAlignment(retourButton, Pos.TOP_RIGHT);
        getChildren().add(retourButton);
    }
}