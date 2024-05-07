package org.example.milleniumproject.model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.example.milleniumproject.view.Menu;

public class ButtonPause extends StackPane {

    private VBox quitterMenu;

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

    static VBox boutonquitter(Stage primaryStage){
        VBox vbox = new VBox(30);

        Label confirmationLabel = new Label("Êtes-vous sûr de vouloir quitter la partie ?");
        confirmationLabel.setFont(Font.font("Cardo", FontWeight.BOLD, 22));
        confirmationLabel.setTextFill(Color.WHITE);

        HBox hbox = new HBox(30);
        Button ouiButton = new Button("Oui");
        Button nonButton = new Button("Non");

        ouiButton.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white; -fx-font-size: 12pt;");
        nonButton.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white; -fx-font-size: 12pt;");

        nonButton.setOnAction(e -> {
            // Masquer le menu pause
            vbox.setVisible(false);
        });

        ouiButton.setOnAction(e -> {
            Menu menu = new Menu();
            menu.afficherMenu(primaryStage);
        });

        hbox.getChildren().addAll(ouiButton, nonButton);
        vbox.getChildren().addAll(confirmationLabel, hbox);

        // Stylisation du menu pause avec un arrière-plan semi-transparent
        vbox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.6); -fx-padding: 20px;");

        // Positionnement du menu pause au centre de l'écran
        hbox.setAlignment(Pos.CENTER);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setPadding(new Insets(20, 0, 0, 0));

        return vbox;
    }
}
