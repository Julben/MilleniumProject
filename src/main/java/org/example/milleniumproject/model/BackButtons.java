package org.example.milleniumproject.model;

import org.example.milleniumproject.view.Menu;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class BackButtons {

    // Méthode pour créer un bouton "Retour" avec le style défini dans ButtonsStyle
    public static Button createBackButton(Stage primaryStage) {
        Button retourButton = new Button("Retour");
        ButtonsStyle.appliquerStyle(retourButton, 160, 5, 20); // Appliquer le style défini dans ButtonsStyle

        Menu menu = new Menu(); // Création de l'instance de la classe Menu
        retourButton.setOnAction(event -> {SoundPlayer.soundPlay(); menu.afficherMenu(primaryStage);});

        // Positionnement du bouton en haut à gauche
        StackPane.setAlignment(retourButton, Pos.TOP_LEFT);
        StackPane.setMargin(retourButton, new javafx.geometry.Insets(15));

        return retourButton;
    }
}