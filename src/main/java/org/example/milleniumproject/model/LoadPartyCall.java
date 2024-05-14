package org.example.milleniumproject.model;

import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LoadPartyCall {
    Party party= new Party();

    public LoadPartyCall(Stage primaryStage, String nameFile) {

        party.LoadParty(primaryStage, nameFile);
    }

}
