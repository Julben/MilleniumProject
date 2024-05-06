package org.example.milleniumproject.view;

import org.example.milleniumproject.model.BG;
import org.example.milleniumproject.model.BackButtons;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Classe représentant la vue pour les paramètres vidéo.
 * Cette vue affiche les paramètres vidéo et permet à l'utilisateur de les ajuster.
 */
public class Video extends StackPane {

    /**
     * Constructeur de la classe Video.
     *
     * @param primaryStage La fenêtre principale de l'application.
     */
    public Video(Stage primaryStage){

        // Créer un fond d'écran
        BG ground = new BG("src/main/resources/BGVIDEO.png");
        setBackground(ground.getCustomBackground());

        // Créer un bouton retour
        Button retourButton = BackButtons.createBackButton(primaryStage);
        StackPane.setAlignment(retourButton, Pos.TOP_RIGHT); // Positionner le bouton retour en haut à droite
        getChildren().add(retourButton); // Ajouter le bouton retour à la pile principale
    }
}
