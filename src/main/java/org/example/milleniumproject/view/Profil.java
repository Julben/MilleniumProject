package org.example.milleniumproject.view;

import org.example.milleniumproject.model.*;
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
import org.example.milleniumproject.presentation.BG;

import java.util.Arrays;
import static org.example.milleniumproject.model.Constant.screenHeight;
import static org.example.milleniumproject.model.Constant.screenWidth;
/**
 * Classe gérant l'affichage et la gestion des profils des joueurs.
 * Elle permet la configuration des avatars, des rangs et des vaisseaux spatiaux des joueurs.
 */
public class Profil extends StackPane {

    Menu menu = new Menu();
    public static final double AVATAR_SIZE = 0.10156*screenWidth;
    private TextField textField1;
    private TextField textField2;
    private Carrousel avatarCarrousel1;
    private Carrousel avatarCarrousel2;
    private Carrousel rangCarrousel1;
    private Carrousel rangCarrousel2;
    private Carrousel vaisseauCarrousel1;
    private Carrousel vaisseauCarrousel2;
    private Label erreurLabel;
    /**
     * Constructeur de la classe Profil.
     * Permet de configurer et d'afficher les profils des joueurs.
     *
     * @param primaryStage La fenêtre principale de l'application.
     */

    public Profil(Stage primaryStage) {

        // Ajouter directement le label d'erreur à la scène principale (Profil)  Initialiser le label d'erreur
        erreurLabel = new Label();
        erreurLabel.setTextFill(Color.WHITE);
        erreurLabel.setFont(Font.font("Cardo", FontWeight.BOLD, 0.025*screenHeight));
        getChildren().add(erreurLabel);
        StackPane.setAlignment(erreurLabel, Pos.TOP_CENTER);
        StackPane.setMargin(erreurLabel, new Insets(0.02778*screenHeight, 0, 0, 0));

        //Créer un fond d'écran
        BG ground = new BG("src/main/resources/BGProfil.png");
        setBackground(ground.getCustomBackground());

        // Créer un bouton retour
        Button retourButton = BackButtons.createBackButton(primaryStage);

        String[] avatar = {"src/main/resources/Avatar/1.png", "src/main/resources/Avatar/2.png", "src/main/resources/Avatar/3.png", "src/main/resources/Avatar/4.png", "src/main/resources/Avatar/5.png", "src/main/resources/Avatar/6.png", "src/main/resources/Avatar/7.png", "src/main/resources/Avatar/8.png", "src/main/resources/Avatar/9.png", "src/main/resources/Avatar/10.png", "src/main/resources/Avatar/11.png", "src/main/resources/Avatar/12.png",};
        String[] rang = {"Padawan", "Apprenti Jedi", "Jeune Jedi", "Jedi", "Maitre Jedi", "Seigneur Sith", "Wookie", "Mandalorian"};
        String[] vaisseau = {"src/main/resources/PionDestroyer.png", "src/main/resources/PionFaucon.png", "src/main/resources/PionTfighter.png", "src/main/resources/PionXwing.png"};

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

        //Créer les VBox pour les profils
        VBox vBox1 = createPlayerBox("Joueur 1", avatar, rang, vaisseau, savedPlayerName1, savedAvatarIndex1, savedRankIndex1, savedShipIndex1);
        VBox vBox2 = createPlayerBox("Joueur 2", avatar, rang, vaisseau, savedPlayerName2, savedAvatarIndex2, savedRankIndex2, savedShipIndex2);

        //Créer les messages d'erreurs lorsque 2 joueurs ont des éléments en commun
        retourButton.setOnAction(event -> {
            // Vérifier si les joueurs ont entré leur pseudo
            if (textField1.getText().isEmpty() || textField2.getText().isEmpty()) {
                erreurLabel.setText("Veuillez entrer les pseudos des joueurs !");
            } else if (avatarCarrousel1.getCurrentIndex() == avatarCarrousel2.getCurrentIndex()) {
                erreurLabel.setText("Les avatars des joueurs ne doivent pas être identiques !");
            } else if (vaisseauCarrousel1.getCurrentIndex() == vaisseauCarrousel2.getCurrentIndex()) {
                erreurLabel.setText("Les pions des joueurs ne doivent pas être identiques !");
            } else {
                // Si les avatars, les vaisseaux spatiaux et les pseudos sont différents, poursuivre avec l'action de retour
                getProfileData(1, textField1, avatarCarrousel1, rangCarrousel1, vaisseauCarrousel1, avatar, rang, vaisseau);
                getProfileData(2, textField2, avatarCarrousel2, rangCarrousel2, vaisseauCarrousel2, avatar, rang, vaisseau);
                SoundPlayer.soundPlay();
                menu.afficherMenu(primaryStage);
            }
        });


        HBox hBox = new HBox(0.27344*screenWidth); // Espacement horizontal entre les Vbox
        hBox.getChildren().addAll(vBox1, vBox2);
        hBox.setAlignment(Pos.CENTER);
        getChildren().addAll(hBox, retourButton);
    }
    /**
     * Crée une VBox contenant les éléments du profil d'un joueur.
     *
     * @param playerNum       Le numéro du joueur.
     * @param avatar          Les chemins des images d'avatar disponibles.
     * @param rang            Les rangs disponibles.
     * @param vaisseau        Les vaisseaux spatiaux disponibles.
     * @param playerName      Le nom du joueur.
     * @param savedAvatarIndex L'index de l'avatar enregistré pour ce joueur.
     * @param savedRankIndex  L'index du rang enregistré pour ce joueur.
     * @param savedShipIndex  L'index du vaisseau enregistré pour ce joueur.
     * @return La VBox contenant les éléments du profil du joueur.
     */
    private VBox createPlayerBox(String playerNum, String[] avatar, String[] rang, String[] vaisseau, String playerName, int savedAvatarIndex, int savedRankIndex, int savedShipIndex) {
        Label label = new Label(playerNum);
        label.setTextFill(Color.WHITE); // Couleur du texte
        label.setFont(Font.font("Cardo", 0.0833*screenHeight)); // Police et taille du texte

        //Création des Carrousel pour les vaisseaux
        Carrousel avatarCarrousel = new Carrousel(avatar, true, savedAvatarIndex);
        Carrousel rangCarrousel = new Carrousel(rang, false, savedRankIndex);
        Carrousel vaisseauCarrousel = new Carrousel(vaisseau, true, savedShipIndex);

        //Instancie la zone de texte pour entrer les pseudos des joueurs
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

        VBox vBox = new VBox(0.02778*screenHeight); // Espacement vertical entre les carrousels
        vBox.getChildren().addAll(label, avatarCarrousel, textField, rangCarrousel, vaisseauCarrousel);
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }

    /**
     * Crée un champ de texte pour entrer le nom du joueur.
     *
     * @param playerName Le nom du joueur.
     * @return Le champ de texte pour le nom du joueur.
     */
    private TextField createTextField(String playerName) {
        TextField textField = new TextField();
        textField.setPromptText("Entrez votre pseudo");
        textField.setPrefSize(0.2344*screenWidth, 0.0625*screenHeight);
        textField.setAlignment(Pos.CENTER);
        textField.setFont(Font.font("Cardo", FontWeight.BOLD, 0.04167*screenHeight)); // Taille de police basée sur la hauteur de l'écran
        textField.setBackground(new Background(new BackgroundFill(Color.rgb(240, 240, 240, 0.35), CornerRadii.EMPTY, Insets.EMPTY)));
        textField.setStyle("-fx-text-fill: white;");
        textField.setText(playerName); // Remplir le champ avec le nom du joueur actuel
        return textField;
    }

    /**
     * Récupère les données du profil du joueur.
     *
     * @param playerNumber   Le numéro du joueur.
     * @param playerNameField Le champ de texte contenant le nom du joueur.
     * @param avatarCarrousel Le carrousel d'avatar du joueur.
     * @param rankCarrousel  Le carrousel de rang du joueur.
     * @param shipCarrousel  Le carrousel de vaisseau du joueur.
     * @param avatar         Les chemins des images d'avatar disponibles.
     * @param rang           Les rangs disponibles.
     * @param vaisseau       Les vaisseaux spatiaux disponibles.
     */
    private void getProfileData(int playerNumber, TextField playerNameField, Carrousel avatarCarrousel, Carrousel rankCarrousel, Carrousel shipCarrousel, String[] avatar, String[] rang, String[] vaisseau) {
        String playerName = playerNameField.getText();
        int avatarIndex = avatarCarrousel.getCurrentIndex();
        int rankIndex = rankCarrousel.getCurrentIndex();
        int shipIndex = shipCarrousel.getCurrentIndex();
        saveProfileData(playerNumber, playerName, avatarIndex, rankIndex, shipIndex, avatar, rang, vaisseau);
    }

    /**
     * Sauvegarde les données du profil du joueur.
     *
     * @param playerNumber  Le numéro du joueur.
     * @param playerName    Le nom du joueur.
     * @param avatarIndex   L'index de l'avatar du joueur.
     * @param rankIndex     L'index du rang du joueur.
     * @param shipIndex     L'index du vaisseau du joueur.
     * @param avatar        Les chemins des images d'avatar disponibles.
     * @param rang          Les rangs disponibles.
     * @param vaisseau      Les vaisseaux spatiaux disponibles.
     */
    private void saveProfileData(int playerNumber, String playerName, int avatarIndex, int rankIndex, int shipIndex, String[] avatar, String[] rang, String[] vaisseau) {
        String selectedAvatar = avatar[avatarIndex];
        String selectedRank = rang[rankIndex];
        String selectedShip = vaisseau[shipIndex];
        ProfileData.saveProfile(playerNumber, playerName, selectedAvatar, selectedRank, selectedShip);
    }
}