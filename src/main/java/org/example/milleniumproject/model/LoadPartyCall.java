package org.example.milleniumproject.model;

import javafx.stage.Stage;
/**
 * Cette classe permet de charger une partie en fonction du mode de jeu choisi.
 */
public class LoadPartyCall {
    Party party= new Party();
    PartyIA partyIA=new PartyIA();


    /**
     * Initialise et charge une partie en fonction du mode de jeu choisi.
     *
     * @param primaryStage La scène en premier plan.
     * @param valeur       La valeur représentant le mode de jeu : 0 pour le mode Joueur contre Joueur, 1 pour le mode Joueur contre IA.
     * @param nameFile     Le nom du fichier de sauvegarde de la partie.
     */
    public LoadPartyCall(Stage primaryStage,int valeur, String nameFile) {
    if (valeur == 0){
        party.LoadParty(primaryStage, nameFile);
    }else if (valeur == 1){
        partyIA.LoadParty(primaryStage, nameFile);
    }
    }
}
