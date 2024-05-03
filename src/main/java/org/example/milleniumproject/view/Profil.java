package org.example.milleniumproject.view;

import org.example.milleniumproject.model.*;
import org.example.milleniumproject.view.Profil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.Arrays;

public class Profil extends StackPane {
    Menu menu = new Menu();
    public static final double AVATAR_SIZE = 130.0;
    private TextField textField1;
    private TextField textField2;
    private Carrousel avatarCarrousel1;
    private Carrousel avatarCarrousel2;
    private Carrousel rangCarrousel1;
    private Carrousel rangCarrousel2;
    private Carrousel vaisseauCarrousel1;
    private Carrousel vaisseauCarrousel2;

    public Profil(Stage primaryStage) {
        BG ground = new BG("src/main/resources/BGProfil.png");
        setBackground(ground.getCustomBackground());

        // Créer un bouton retour et l'ajouter à la pile
        Button retourButton = BackButtons.createBackButton(primaryStage);

        String[] avatar = {"src/main/resources/Avatar/1.png", "src/main/resources/Avatar/2.png", "src/main/resources/Avatar/3.png", "src/main/resources/Avatar/4.png", "src/main/resources/Avatar/5.png", "src/main/resources/Avatar/6.png", "src/main/resources/Avatar/7.png", "src/main/resources/Avatar/8.png", "src/main/resources/Avatar/9.png", "src/main/resources/Avatar/10.png", "src/main/resources/Avatar/11.png", "src/main/resources/Avatar/12.png",};
        String[] rang = {"Padawan", "Apprenti Jedi", "Jeune Jedi", "Jedi", "Maitre Jedi", "Seigneur Sith", "Wookie", "Mandalorian"};
        String[] vaisseau = {"src/main/resources/Pion/PionDestroyer.png", "src/main/resources/Pion/PionFaucon.png", "src/main/resources/Pion/PionTfighter.png", "src/main/resources/Pion/PionXwing.png"};

        // Charger les données du profil s'il existe
        String savedPlayerName1 = ProfileData.getPlayerName(1);
        String savedAvatar1 = ProfileData.getAvatar(1);
        String savedRank1 = ProfileData.getRank(1);
        String savedShip1 = ProfileData.getShip(1);
        int savedAvatarIndex1 = Arrays.asList(avatar).indexOf(savedAvatar1);
        int savedRankIndex1 = Arrays.asList(rang).indexOf(savedRank1);
        int savedShipIndex1 = Arrays.asList(vaisseau).indexOf(savedShip1);

        if (savedPlayerName1.isEmpty()) {
            savedPlayerName1 = "";
            savedAvatarIndex1 = 0; // Index par défaut pour le premier joueur
            savedRankIndex1 = 0; // Index par défaut pour le premier joueur
            savedShipIndex1 = 0; // Index par défaut pour le premier joueur
            // Sauvegarder les valeurs par défaut pour le joueur 1
            ProfileData.saveProfile(1, savedPlayerName1, avatar[savedAvatarIndex1], rang[savedRankIndex1], vaisseau[savedShipIndex1]);
        }

        String savedPlayerName2 = ProfileData.getPlayerName(2);
        String savedAvatar2 = ProfileData.getAvatar(2);
        String savedRank2 = ProfileData.getRank(2);
        String savedShip2 = ProfileData.getShip(2);
        int savedAvatarIndex2 = Arrays.asList(avatar).indexOf(savedAvatar2);
        int savedRankIndex2 = Arrays.asList(rang).indexOf(savedRank2);
        int savedShipIndex2 = Arrays.asList(vaisseau).indexOf(savedShip2);

        if (savedPlayerName2.isEmpty()) {
            savedPlayerName2 = "";
            savedAvatarIndex2 = 0; // Index par défaut pour le deuxième joueur
            savedRankIndex2 = 0; // Index par défaut pour le deuxième joueur
            savedShipIndex2 = 0; // Index par défaut pour le deuxième joueur
            // Sauvegarder les valeurs par défaut pour le joueur 2
            ProfileData.saveProfile(2, savedPlayerName2, avatar[savedAvatarIndex2], rang[savedRankIndex2], vaisseau[savedShipIndex2]);
        }

        VBox vBox1 = createPlayerBox("Joueur 1", avatar, rang, vaisseau, savedPlayerName1, savedAvatarIndex1, savedRankIndex1, savedShipIndex1);
        VBox vBox2 = createPlayerBox("Joueur 2", avatar, rang, vaisseau, savedPlayerName2, savedAvatarIndex2, savedRankIndex2, savedShipIndex2);

        retourButton.setOnAction(event -> {
            getProfileData(1, textField1, avatarCarrousel1, rangCarrousel1, vaisseauCarrousel1, avatar, rang, vaisseau);
            getProfileData(2, textField2, avatarCarrousel2, rangCarrousel2, vaisseauCarrousel2, avatar, rang, vaisseau);
            SoundPlayer.soundPlay();
            menu.afficherMenu(primaryStage);
        });

        HBox hBox = new HBox(345); // Espacement horizontal entre les Vbox
        hBox.getChildren().addAll(vBox1, vBox2);
        hBox.setAlignment(Pos.CENTER);

        getChildren().addAll(hBox, retourButton);
    }

    private VBox createPlayerBox(String playerNum, String[] avatar, String[] rang, String[] vaisseau, String playerName, int savedAvatarIndex, int savedRankIndex, int savedShipIndex) {
        Label label = new Label(playerNum);
        label.setTextFill(Color.WHITE); // Couleur du texte
        label.setFont(Font.font("Cardo", 60)); // Police et taille du texte

        Carrousel avatarCarrousel = new Carrousel(avatar, true, savedAvatarIndex);
        Carrousel rangCarrousel = new Carrousel(rang, false, savedRankIndex);
        Carrousel vaisseauCarrousel = new Carrousel(vaisseau, true, savedShipIndex);

        TextField textField = createTextField(playerName);

        if (playerNum.equals("Joueur 1")) {
            avatarCarrousel1 = avatarCarrousel;
            rangCarrousel1 = rangCarrousel;
            vaisseauCarrousel1 = vaisseauCarrousel;
            textField1 = textField;
        } else {
            avatarCarrousel2 = avatarCarrousel;
            rangCarrousel2 = rangCarrousel;
            vaisseauCarrousel2 = vaisseauCarrousel;
            textField2 = textField;
        }

        VBox vBox = new VBox(15); // Espacement vertical entre les carrousels
        vBox.getChildren().addAll(label, avatarCarrousel, textField, rangCarrousel, vaisseauCarrousel);
        vBox.setAlignment(Pos.CENTER);

        return vBox;
    }


    private TextField createTextField(String playerName) {
        TextField textField = new TextField();
        textField.setPromptText("Entrez votre pseudo");
        textField.setPrefSize(300, 45);
        textField.setAlignment(Pos.CENTER);
        textField.setFont(Font.font("Cardo", FontWeight.BOLD, 30)); // Taille de police basée sur la hauteur de l'écran
        textField.setBackground(new Background(new BackgroundFill(Color.rgb(240, 240, 240, 0.35), CornerRadii.EMPTY, Insets.EMPTY)));
        textField.setStyle("-fx-text-fill: white;");
        textField.setText(playerName); // Remplir le champ avec le nom du joueur actuel
        return textField;
    }

    private void getProfileData(int playerNumber, TextField playerNameField, Carrousel avatarCarrousel, Carrousel rankCarrousel, Carrousel shipCarrousel, String[] avatar, String[] rang, String[] vaisseau) {
        String playerName = playerNameField.getText();
        int avatarIndex = avatarCarrousel.getCurrentIndex();
        int rankIndex = rankCarrousel.getCurrentIndex();
        int shipIndex = shipCarrousel.getCurrentIndex();
        saveProfileData(playerNumber, playerName, avatarIndex, rankIndex, shipIndex, avatar, rang, vaisseau);
    }

    private void saveProfileData(int playerNumber, String playerName, int avatarIndex, int rankIndex, int shipIndex, String[] avatar, String[] rang, String[] vaisseau) {
        String selectedAvatar = avatar[avatarIndex];
        String selectedRank = rang[rankIndex];
        String selectedShip = vaisseau[shipIndex];
        ProfileData.saveProfile(playerNumber, playerName, selectedAvatar, selectedRank, selectedShip);
    }
}