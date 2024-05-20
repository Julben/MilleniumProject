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
 * Classe gérant l'affichage des profils des joueurs durant la partie.
 * Elle permet de gérer le choix des avatars, des rangs et des pions des deux joueurs.
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
     * Crée la classe Profil.
     * Permet de configurer les profils des deux joueurs.
     *
     * @param primaryStage La scène en premier plan.
     */

    public Profil(Stage primaryStage) {

        erreurLabel = new Label();
        erreurLabel.setTextFill(Color.WHITE);
        erreurLabel.setFont(Font.font("Cardo", FontWeight.BOLD, 0.025*screenHeight));
        getChildren().add(erreurLabel);
        StackPane.setAlignment(erreurLabel, Pos.TOP_CENTER);
        StackPane.setMargin(erreurLabel, new Insets(0.02778*screenHeight, 0, 0, 0));

        BG ground = new BG("src/main/resources/BGProfil.png");
        setBackground(ground.getCustomBackground());

        Button retourButton = BackButtons.createBackButton(primaryStage);

        String[] avatar = {"src/main/resources/Avatar/1.png", "src/main/resources/Avatar/2.png", "src/main/resources/Avatar/3.png", "src/main/resources/Avatar/4.png", "src/main/resources/Avatar/5.png", "src/main/resources/Avatar/6.png", "src/main/resources/Avatar/7.png", "src/main/resources/Avatar/8.png", "src/main/resources/Avatar/9.png", "src/main/resources/Avatar/10.png", "src/main/resources/Avatar/11.png", "src/main/resources/Avatar/12.png",};
        String[] rang = {"Padawan", "Apprenti Jedi", "Jeune Jedi", "Jedi", "Maitre Jedi", "Seigneur Sith", "Wookie", "Mandalorian"};
        String[] vaisseau = {"src/main/resources/PionDestroyer.png", "src/main/resources/PionFaucon.png", "src/main/resources/PionTfighter.png", "src/main/resources/PionXwing.png"};

        String savedPlayerName1 = ProfileData.getPlayerName(1);
        String savedAvatar1 = ProfileData.getAvatar(1);
        String savedRank1 = ProfileData.getRank(1);
        String savedShip1 = ProfileData.getShip(1);
        int savedAvatarIndex1 = Arrays.asList(avatar).indexOf(savedAvatar1);
        int savedRankIndex1 = Arrays.asList(rang).indexOf(savedRank1);
        int savedShipIndex1 = Arrays.asList(vaisseau).indexOf(savedShip1);

        if (savedPlayerName1.isEmpty()) {
            savedPlayerName1 = "";
            savedAvatarIndex1 = 0;
            savedRankIndex1 = 0;
            savedShipIndex1 = 0;
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
            savedAvatarIndex2 = 0;
            savedRankIndex2 = 0;
            savedShipIndex2 = 0;
            ProfileData.saveProfile(2, savedPlayerName2, avatar[savedAvatarIndex2], rang[savedRankIndex2], vaisseau[savedShipIndex2]);
        }

        VBox vBox1 = createPlayerBox("Joueur 1", avatar, rang, vaisseau, savedPlayerName1, savedAvatarIndex1, savedRankIndex1, savedShipIndex1);
        VBox vBox2 = createPlayerBox("Joueur 2", avatar, rang, vaisseau, savedPlayerName2, savedAvatarIndex2, savedRankIndex2, savedShipIndex2);

        retourButton.setOnAction(event -> {
            if (textField1.getText().isEmpty() || textField2.getText().isEmpty()) {
                erreurLabel.setText("Veuillez entrer les pseudos des joueurs !");
            } else if (avatarCarrousel1.getCurrentIndex() == avatarCarrousel2.getCurrentIndex()) {
                erreurLabel.setText("Les avatars des joueurs ne doivent pas être identiques !");
            } else if (vaisseauCarrousel1.getCurrentIndex() == vaisseauCarrousel2.getCurrentIndex()) {
                erreurLabel.setText("Les pions des joueurs ne doivent pas être identiques !");
            } else {
                getProfileData(1, textField1, avatarCarrousel1, rangCarrousel1, vaisseauCarrousel1, avatar, rang, vaisseau);
                getProfileData(2, textField2, avatarCarrousel2, rangCarrousel2, vaisseauCarrousel2, avatar, rang, vaisseau);
                SoundPlayer.soundPlay();
                menu.afficherMenu(primaryStage);
            }
        });


        HBox hBox = new HBox(0.27344*screenWidth);
        hBox.getChildren().addAll(vBox1, vBox2);
        hBox.setAlignment(Pos.CENTER);
        getChildren().addAll(hBox, retourButton);
    }
    /**
     * Affiche les éléments du profil de chaque joueur durant la partie.
     *
     * @param playerNum       Le numéro du joueur.
     * @param avatar          L'avatar du joueur.
     * @param rang            Le rang du joueur.
     * @param vaisseau        Le vaisseau(pion) du joueur.
     * @param playerName      Le pseudo du joueur.
     * @param savedAvatarIndex L'enregistrement de l'index de l'avatar.
     * @param savedRankIndex  L'enregistrement de l'index du rang enregistré.
     * @param savedShipIndex  L'enregistrement de l'index du vaisseau.
     * @return L'affichage durant la partie des éléments du profil du joueur.
     */
    private VBox createPlayerBox(String playerNum, String[] avatar, String[] rang, String[] vaisseau, String playerName, int savedAvatarIndex, int savedRankIndex, int savedShipIndex) {
        Label label = new Label(playerNum);
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("Cardo", 0.0833*screenHeight));

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

        VBox vBox = new VBox(0.02778*screenHeight);
        vBox.getChildren().addAll(label, avatarCarrousel, textField, rangCarrousel, vaisseauCarrousel);
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }



    /**
     * Obtiens les données du profil du joueur.
     *
     * @param playerNumber   Le numéro du joueur.
     * @param playerNameField Le champ de texte avec le pseudo du joueur.
     * @param avatarCarrousel Le carrousel d'avatar du joueur.
     * @param rankCarrousel  Le carrousel de rang du joueur.
     * @param shipCarrousel  Le carrousel de vaisseau(pion) du joueur.
     * @param avatar         L'avatar du joueur.
     * @param rang           Le rang du joueur.
     * @param vaisseau       Le vaisseau spatial .
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
     * @param playerName    Le pseudo du joueur.
     * @param avatarIndex   L'index de l'avatar du joueur.
     * @param rankIndex     L'index du rang du joueur.
     * @param shipIndex     L'index du vaisseau du joueur.
     * @param avatar        L'image d'avatar du joueur.
     * @param rang          Le rang du joueur.
     * @param vaisseau      Le vaisseaux(pion) du joueur.
     */
    private void saveProfileData(int playerNumber, String playerName, int avatarIndex, int rankIndex, int shipIndex, String[] avatar, String[] rang, String[] vaisseau) {
        String selectedAvatar = avatar[avatarIndex];
        String selectedRank = rang[rankIndex];
        String selectedShip = vaisseau[shipIndex];
        ProfileData.saveProfile(playerNumber, playerName, selectedAvatar, selectedRank, selectedShip);
    }
    /**
     * Crée un champ de texte pour le pseudo du joueur.
     *
     * @param playerName Le pseudo du joueur.
     * @return Le champ de texte pour le pseudo.
     */
    private TextField createTextField(String playerName) {
        TextField textField = new TextField();
        textField.setPromptText("Entrez votre pseudo");
        textField.setPrefSize(0.2344*screenWidth, 0.0625*screenHeight);
        textField.setAlignment(Pos.CENTER);
        textField.setFont(Font.font("Cardo", FontWeight.BOLD, 0.04167*screenHeight));
        textField.setBackground(new Background(new BackgroundFill(Color.rgb(240, 240, 240, 0.35), CornerRadii.EMPTY, Insets.EMPTY)));
        textField.setStyle("-fx-text-fill: white;");
        textField.setText(playerName);
        return textField;
    }
}