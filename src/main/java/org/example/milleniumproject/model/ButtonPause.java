package org.example.milleniumproject.model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class ButtonPause {

    public static void afficherRegles(StackPane root) {
        // Création de la StackPane pour contenir l'image et le bouton
        StackPane reglesPane = new StackPane();
        reglesPane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);"); // Fond semi-transparent

        // Création de l'image
        Image image = new Image("Règles.png"); // Remplacez "regles_image.png" par le chemin de votre image
        ImageView imageView = new ImageView(image);

        // Redimensionner l'image
        imageView.setFitWidth(1250); // Largeur souhaitée
        imageView.setFitHeight(750); // Hauteur souhaitée

        // Création du bouton pour masquer l'image et le bouton
        Button fermerButton = new Button();

        fermerButton.setMinWidth(50);
        fermerButton.setMinHeight(50);
        fermerButton.setStyle("-fx-background-color: transparent;"); // Fond transparent

        // Action du bouton pour masquer l'image et le bouton
        fermerButton.setOnAction(e -> {
            root.getChildren().remove(reglesPane); // Retirer la StackPane de la racine
        });

        StackPane.setMargin(fermerButton, new Insets(0, 0, 480, 1020)); // Marge de 10 pixels

        // Ajout de l'image et du bouton à la StackPane
        reglesPane.getChildren().addAll(imageView, fermerButton);

        // Centrer la StackPane dans la fenêtre
        StackPane.setAlignment(reglesPane, Pos.CENTER);

        // Ajouter la StackPane à la racine de la scène
        root.getChildren().add(reglesPane);
    }
}
