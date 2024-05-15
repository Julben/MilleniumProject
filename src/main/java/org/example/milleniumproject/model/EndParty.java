package org.example.milleniumproject.model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.example.milleniumproject.view.Menu;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.example.milleniumproject.model.Constant.screenHeight;
import static org.example.milleniumproject.model.Constant.screenWidth;

public class EndParty extends StackPane {

    public static void afficherFinPartie(StackPane root, Stage primaryStage, int currentPlayer){
        // Création de la StackPane pour contenir l'image et le bouton
        StackPane reglesPane = new StackPane();
        reglesPane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);"); // Fond semi-transparent

        List<String> stringList;
        if (currentPlayer == 2) {
            stringList = Arrays.asList("FinParty1.png", "FinParty2.png", "FinParty3.png", "FinParty4.png", "FinParty5.png", "FinParty6.png");
        } else {
            stringList = Arrays.asList("FinParty7.png", "FinParty8.png", "FinParty9.png", "FinParty10.png", "FinParty11.png", "FinParty12.png");
        }
        // Récupération d'une chaîne de manière aléatoire
        Random rand = new Random();
        int index = rand.nextInt(stringList.size());
        String randomString = stringList.get(index);

        // Création de l'image
        Image image = new Image(randomString); // Remplacez "regles_image.png" par le chemin de votre image
        ImageView imageView = new ImageView(image);

        // Redimensionner l'image pour qu'elle remplisse l'écran
        imageView.setFitWidth(screenWidth);
        imageView.setFitHeight(screenHeight);

        Image quitterImage = new Image("BoutonQuitter.png"); // Remplacez "chemin/vers/votre/image.png" par le chemin de votre image
        ImageView imageView1 = new ImageView(quitterImage);
        imageView1.setFitWidth(0.03125*screenWidth); // Ajustez la largeur de l'image selon vos besoins
        imageView1.setFitHeight(0.05556*screenHeight); // Ajustez la hauteur de l'image selon vos besoins

        Button quitterButton = new Button();
        quitterButton.setGraphic(imageView1); // Définit l'image comme graphique du bouton

        // Rendre l'arrière-plan du bouton invisible
        quitterButton.setStyle("-fx-background-color: transparent");

        // Action du bouton pour masquer l'image et le bouton
        quitterButton.setOnAction(e -> {
            SoundPlayer.soundPlay();
            MusicPlayer.musicPlay("src/main/resources/MusicMenu.mp3");
            Menu menu = new Menu();
            menu.afficherMenu(primaryStage);
        });

        // Positionnement du bouton pause en haut à droite
        StackPane.setAlignment(quitterButton, Pos.TOP_RIGHT);
        setMargin(quitterButton, new Insets(0.011719*screenHeight, 5, 0, 0));

        MusicPlayer.musicPlay("src/main/resources/MusicEndParty.mp3");

        // Ajout de l'image et du bouton à la StackPane
        reglesPane.getChildren().addAll(imageView, quitterButton);

        // Centrer la StackPane dans la fenêtre
        StackPane.setAlignment(reglesPane, Pos.CENTER);

        // Ajouter la StackPane à la racine de la scène
        root.getChildren().add(reglesPane);
    }
}