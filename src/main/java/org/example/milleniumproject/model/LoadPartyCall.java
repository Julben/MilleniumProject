package org.example.milleniumproject.model;

import javafx.stage.Stage;

public class LoadPartyCall {
    Party party= new Party();
    PartyIA partyIA=new PartyIA();



    public LoadPartyCall(Stage primaryStage,int valeur, String nameFile) {
    if (valeur == 0){
        party.LoadParty(primaryStage, nameFile);
    }else if (valeur == 1){
        partyIA.LoadParty(primaryStage, nameFile);
    }
    }
}
