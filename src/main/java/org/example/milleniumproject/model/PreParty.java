package org.example.milleniumproject.model;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PreParty extends StackPane {

    public PreParty(Stage primaryStage) {

        Button retourButton = BackButtons.createBackButton(primaryStage);

        BG ground = new BG("src/main/resources/BGAUDIO.png");
        setBackground(ground.getCustomBackground());

        Button jouer = new Button("Jouer");

        jouer.setOnAction(event -> {
            Party party = new Party(primaryStage);
            primaryStage.getScene().setRoot(party);
        });

        getChildren().addAll(jouer, retourButton);
    }
}