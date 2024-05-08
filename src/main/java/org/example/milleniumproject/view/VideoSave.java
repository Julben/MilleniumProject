/*
package org.example.milleniumproject.view;

import org.example.milleniumproject.model.BG;
import org.example.milleniumproject.model.BackButtons;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import static org.example.milleniumproject.model.Constant.screenHeight;
import static org.example.milleniumproject.model.Constant.screenWidth;

public class Video extends StackPane {

    public Video(Stage primaryStage){

        // Créer un fond d'écran
        BG ground = new BG("src/main/resources/BGVIDEO.png");
        setBackground(ground.getCustomBackground());

        // Créer un bouton retour
        Button retourButton = BackButtons.createBackButton(primaryStage);
        setAlignment(retourButton, Pos.TOP_RIGHT); // Positionner le bouton retour en haut à droite
        setMargin(retourButton, new javafx.geometry.Insets(0.0208*screenHeight, 0.0117*screenWidth, 0, 0));
        getChildren().add(retourButton); // Ajouter le bouton retour à la pile principale
    }
}

 */