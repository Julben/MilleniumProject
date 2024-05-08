package org.example.milleniumproject.model;

import org.example.milleniumproject.view.Menu;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static org.example.milleniumproject.model.Constant.screenHeight;
import static org.example.milleniumproject.model.Constant.screenWidth;

/**
 * Classe pour créer un bouton "Retour" avec un style défini et une fonctionnalité pour retourner au menu principal.
 */
public class BackButtons {

    /**
     * Crée un bouton "Retour" avec le style défini dans ButtonsStyle et ajoute la fonctionnalité pour retourner au menu principal.
     *
     * @param primaryStage La fenêtre principale de l'application.
     * @return Le bouton "Retour" créé.
     */
    public static Button createBackButton(Stage primaryStage) {
        //Instanciation du bouton retour+style
        Button retourButton = new Button("Retour");
        ButtonsStyle.appliquerStyle(retourButton, 0.125*screenWidth, 0.00694*screenHeight, 0.0278*screenHeight); // Appliquer le style défini dans ButtonsStyle

        Menu menu = new Menu(); // Création de l'instance de la classe Menu
        retourButton.setOnAction(event -> { SoundPlayer.soundPlay(); menu.afficherMenu(primaryStage); });

        // Positionnement du bouton en haut à gauche
        StackPane.setAlignment(retourButton, javafx.geometry.Pos.TOP_LEFT);
        StackPane.setMargin(retourButton, new javafx.geometry.Insets(0.0208*screenHeight, 0, 0, 0.0117*screenWidth));
        return retourButton;
    }
}