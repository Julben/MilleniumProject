package org.example.milleniumproject.view;

import javafx.scene.control.Menu;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.example.milleniumproject.model.Buttons.ReturnButton;
import org.example.milleniumproject.model.ImageCarrousel;
import org.example.milleniumproject.model.LabelCarrousel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Profil extends BorderPane {
    public static final double AVATAR_SIZE = 130.0;
    private Stage primaryStage;
    private ImageView menuImageView;
    private List<Button> boutons1;
    private List<Button> boutons2;
    private List<Button> boutonsComplets;

    public Profil(Stage primaryStage, ImageView menuImageView, List<Button> boutons1, List<Button> boutons2, List<Button> boutonsComplets) {
        this.primaryStage = primaryStage;
        this.menuImageView = menuImageView;
        this.boutons1 = boutons1;
        this.boutons2 = boutons2;
        this.boutonsComplets = boutonsComplets;

        // Fond d'écran du profil
        Image backgroundImage = null;
        try {
            backgroundImage = new Image(new FileInputStream("src/main/resources/BGProfil.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace(); // Gérer l'erreur de chargement de l'imageee
        }
        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
        Background background = new Background(new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize));
        setBackground(background);

        Label labelJ1 = new Label("Joueur 1");
        labelJ1.setTextFill(Color.WHITE); // Couleur du texte
        labelJ1.setFont(Font.font("Cardo", 60)); // Police et taille du texte
        Label labelJ2 = new Label("Joueur 2");
        labelJ2.setTextFill(Color.WHITE); // Couleur du texte
        labelJ2.setFont(Font.font("Cardo", 60)); // Police et taille du texte

        String[] avatarPathsJ1 = {"src/main/resources/Avatar/1.png", "src/main/resources/Avatar/2.png", "src/main/resources/Avatar/3.png", "src/main/resources/Avatar/4.png", "src/main/resources/Avatar/5.png", "src/main/resources/Avatar/6.png", "src/main/resources/Avatar/7.png", "src/main/resources/Avatar/8.png", "src/main/resources/Avatar/9.png", "src/main/resources/Avatar/10.png", "src/main/resources/Avatar/11.png", "src/main/resources/Avatar/12.png",};
        ImageCarrousel avatarJ1 = new ImageCarrousel(avatarPathsJ1);
        String[] avatarPathsJ2 = {"src/main/resources/Avatar/1.png", "src/main/resources/Avatar/2.png", "src/main/resources/Avatar/3.png", "src/main/resources/Avatar/4.png", "src/main/resources/Avatar/5.png", "src/main/resources/Avatar/6.png", "src/main/resources/Avatar/7.png", "src/main/resources/Avatar/8.png", "src/main/resources/Avatar/9.png", "src/main/resources/Avatar/10.png", "src/main/resources/Avatar/11.png", "src/main/resources/Avatar/12.png",};
        ImageCarrousel avatarJ2 = new ImageCarrousel(avatarPathsJ2);

        TextField textFieldJ1 = new TextField();
        textFieldJ1.setPromptText("Entrez votre pseudo");
        textFieldJ1.setPrefSize(300, 45);
        textFieldJ1.setStyle("-fx-text-fill: white; -fx-background-color: rgba(240,240,240,0.35);"); // Définit une transparence de 70%
        textFieldJ1.setAlignment(Pos.CENTER);
        textFieldJ1.setFont(Font.font("Cardo", FontWeight.BOLD, 30)); // Taille de police basée sur la hauteur de l'écran
        textFieldJ1.setBackground(null);
        TextField textFieldJ2 = new TextField();
        textFieldJ2.setPromptText("Entrez votre pseudo");
        textFieldJ2.setPrefSize(300, 45);
        textFieldJ2.setStyle("-fx-text-fill: white; -fx-background-color: rgba(240, 240, 240, 0.35);"); // Définit une transparence de 70%
        textFieldJ2.setAlignment(Pos.CENTER);
        textFieldJ2.setFont(Font.font("Cardo", FontWeight.BOLD, 30)); // Taille de police basée sur la hauteur de l'écran
        textFieldJ2.setBackground(null);

        String[] TitreJ1 = {"Padawan", "Apprenti Jedi", "Jeune Jedi", "Jedi", "Maitre Jedi", "Seigneur Sith", "Wookie", "Mandalorian"};
        LabelCarrousel rangJ1 = new LabelCarrousel(TitreJ1);
        String[] TitreJ2 = {"Padawan", "Apprenti Jedi", "Jeune Jedi", "Jedi", "Maitre Jedi", "Seigneur Sith", "Wookie", "Mandalorian"};
        LabelCarrousel rangJ2 = new LabelCarrousel(TitreJ2);

        String[] vaisseauJ1 = {"src/main/resources/Pion/PionDestroyer.png", "src/main/resources/Pion/PionFaucon.png", "src/main/resources/Pion/PionTfighter.png", "src/main/resources/Pion/PionXwing.png"};
        ImageCarrousel pionJ1 = new ImageCarrousel(vaisseauJ1);
        String[] vaisseauJ2 = {"src/main/resources/Pion/PionDestroyer.png", "src/main/resources/Pion/PionFaucon.png", "src/main/resources/Pion/PionTfighter.png", "src/main/resources/Pion/PionXwing.png"};
        ImageCarrousel pionJ2 = new ImageCarrousel(vaisseauJ2);

        VBox vBox1 = new VBox(15); // Espacement vertical entre les carrousels
        vBox1.getChildren().addAll(labelJ1, avatarJ1, textFieldJ1, rangJ1, pionJ1);
        vBox1.setAlignment(Pos.CENTER);

        VBox vBox2 = new VBox(15); // Espacement vertical entre les carrousels
        vBox2.getChildren().addAll(labelJ2, avatarJ2, textFieldJ2, rangJ2, pionJ2);
        vBox2.setAlignment(Pos.CENTER);

        HBox hBox = new HBox(345); // Espacement horizontal entre les Vbox
        hBox.getChildren().addAll(vBox1, vBox2);
        hBox.setAlignment(Pos.CENTER);

        // Créez une instance de la classe Menu
        Menu menu = new Menu();
        // Création du bouton de retour
        ReturnButton returnButton = new ReturnButton(() -> {
            // Action du bouton de retour pour afficher le menu
            //menu.afficherMenu(primaryStage, menuImageView, boutons1, boutons2, boutonsComplets);
        });
        BorderPane.setMargin(returnButton, new Insets(25, 0, 0, 25)); // Définissez les marges ici selon vos besoins
        BorderPane.setMargin(hBox, new Insets(0, 10, 30, 0)); // Définissez les marges ici selon vos besoins
        // Ajouter le bouton de retour à la région supérieure gauche
        setTop(returnButton);
        // Ajouter la hBox à la région centrale
        setCenter(hBox);
    }
}
