package org.example.milleniumproject.model;

import javafx.stage.Stage;

public class LoadPartyCall {
    Party party= new Party();

    public LoadPartyCall(Stage primaryStage) {

        party.LoadParty(primaryStage);
    }

}
