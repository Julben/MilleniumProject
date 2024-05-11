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

import static org.example.milleniumproject.model.Constant.screenHeight;
import static org.example.milleniumproject.model.Constant.screenWidth;

public class ButtonPause extends StackPane {

    public static VBox quitterMenu;

    public static void afficherRegles(StackPane root) {
        // Création de la StackPane pour contenir l'image et le bouton
        StackPane reglesPane = new StackPane();
        reglesPane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);"); // Fond semi-transparent

        // Création de l'image
        Image image = new Image("Règles.png"); // Remplacez "regles_image.png" par le chemin de votre image
        ImageView imageView = new ImageView(image);

        // Redimensionner l'image
        imageView.setFitWidth(0.9765*screenWidth); // Largeur souhaitée
        imageView.setFitHeight(1.04167*screenHeight); // Hauteur souhaitée

        // Création du bouton pour masquer l'image et le bouton
        Button fermerButton = new Button();

        fermerButton.setMinWidth(0.039*screenWidth);
        fermerButton.setMinHeight(0.0694*screenHeight);
        fermerButton.setStyle("-fx-background-color: transparent;"); // Fond transparent

        // Action du bouton pour masquer l'image et le bouton
        fermerButton.setOnAction(e -> {
            SoundPlayer.soundPlay();
            root.getChildren().remove(reglesPane); // Retirer la StackPane de la racine
        });

        StackPane.setMargin(fermerButton, new Insets(0, 0, 0.6667*screenHeight, 0.796875*screenWidth)); // Marge de 10 pixels

        // Ajout de l'image et du bouton à la StackPane
        reglesPane.getChildren().addAll(imageView, fermerButton);

        // Centrer la StackPane dans la fenêtre
        StackPane.setAlignment(reglesPane, Pos.CENTER);

        // Ajouter la StackPane à la racine de la scène
        root.getChildren().add(reglesPane);
    }

    public static VBox boutonquitter(Stage primaryStage){
        VBox vbox = new VBox(0.04167*screenHeight);

        Label confirmationLabel = new Label("Êtes-vous sûr de vouloir quitter la partie ?");
        confirmationLabel.setFont(Font.font("Cardo", FontWeight.BOLD, 0.0305*screenHeight));
        confirmationLabel.setTextFill(Color.WHITE);

        HBox hbox = new HBox(0.05139*screenHeight);
        Button ouiButton = new Button("Oui");
        Button nonButton = new Button("Non");

        ouiButton.setFont(Font.font("Cardo", FontWeight.BOLD, 17));
        nonButton.setFont(Font.font("Cardo", FontWeight.BOLD, 17));

        ouiButton.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white;");
        nonButton.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white;");

        nonButton.setOnAction(e -> {
            SoundPlayer.soundPlay();
            vbox.setVisible(false);
        });

        ouiButton.setOnAction(e -> {
            SoundPlayer.soundPlay();
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
        vbox.setPadding(new Insets(0.02778*screenHeight, 0, 0, 0));

        return vbox;
    }


}