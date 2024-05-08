package org.example.milleniumproject.model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import static org.example.milleniumproject.model.ButtonColorChecker.checkAndChangeButtonColor;
import static org.example.milleniumproject.model.ButtonPause.afficherRegles;
import static org.example.milleniumproject.model.ButtonUtils.isNeighbourButton;
import static org.example.milleniumproject.model.Constant.screenWidth;

public class Party extends StackPane {
    // Déclarations des variables d'instance
    private int currentPlayer = 1;
    private boolean isPlayer1Turn = true; // Variable pour suivre le tour des joueurs
    private VBox leftVBox;
    private VBox rightVBox;
    private int turns = 0;
    private int currentImageIndex = 0;
    private ToggleGroup toggleGroup3;
    private HBox hbox3;
    private ToggleGroup toggleGroup2;
    private HBox hbox2;
    private Button selectedButton = null;
    private boolean isMovePhase = false;
    private List<Button> buttonsJ1 = new ArrayList<>();
    private List<Button> buttonsJ2 = new ArrayList<>();
    private VBox pauseMenu; // Conteneur pour le menu pause
    private VBox quitterMenu;
    private ButtonUtils buttonUtils;

    public Party(Stage primaryStage, ToggleGroup toggleGroup3, HBox hbox3, ToggleGroup toggleGroup2, HBox hbox2) {
        this.toggleGroup3 = toggleGroup3;
        this.hbox3 = hbox3;

        buttonUtils = new ButtonUtils();

        int selectedIndex = PreParty.getSelectedIndex(toggleGroup3, hbox3);

        String backgroundImage = "";
        if (selectedIndex == 0) {
            backgroundImage = "src/main/resources/FENABOO.png";
        } else if (selectedIndex == 1) {
            backgroundImage = "src/main/resources/FECORUSCANT.png";
        } else if (selectedIndex == 2) {
            backgroundImage = "src/main/resources/FEMUSTAPHAR.png";
        }
        BG ground = new BG(backgroundImage);
        setBackground(ground.getCustomBackground());

        this.toggleGroup2 = toggleGroup2;
        this.hbox2 = hbox2;

        int selectedIndexchrono = PreParty.getSelectedIndexchrono(toggleGroup2, hbox2);
        System.out.println("selectedIndexchrono: " + selectedIndexchrono);

        // Création du GridPane
        GridPane gridPane = new GridPane();
        gridPane.setHgap(42); // Espacement horizontal entre les boutons
        gridPane.setVgap(42); // Espacement vertical entre les boutons
        gridPane.setAlignment(Pos.CENTER); // Positionnement au centre de la StackPane

        // Ajout des boutons au GridPane avec leurs positions
        String[] buttonLabels = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X"};
        int[] rowIndices = {0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 6};
        int[] colIndices = {0, 3, 6, 1, 3, 5, 2, 3, 4, 0, 1, 2, 4, 5, 6, 2, 3, 4, 1, 3, 5, 0, 3, 6, 6};

        for (int i = 0; i < buttonLabels.length; i++) {
            Button button = ButtonSelector.createStyledButton(buttonLabels[i]);

            gridPane.add(button, colIndices[i], rowIndices[i]);
        }

        // Création des Vbox pour les images des joueurs
        String str = ProfileData.getShip(1);
        int lastIndex = str.lastIndexOf('/');
        String vaisseau1 = str.substring(lastIndex + 1);

        String str2 = ProfileData.getShip(2);
        int lastIndex2 = str2.lastIndexOf('/');
        String vaisseau2 = str2.substring(lastIndex2 + 1);

        leftVBox = ProfilParty.createVBoxWithImages(vaisseau1, 9);
        rightVBox = ProfilParty.createVBoxWithImages(vaisseau2, 9);

        HBox hBox = new HBox(0.6 * screenWidth); // Espacement horizontal entre les Vbox
        hBox.getChildren().addAll(leftVBox, rightVBox);
        hBox.setAlignment(Pos.CENTER);

        // Positionnement des Vbox à gauche et à droite du GridPane
        StackPane.setAlignment(leftVBox, Pos.CENTER_LEFT);
        StackPane.setAlignment(rightVBox, Pos.CENTER_RIGHT);

        // Récupération des données des profils
        String avatar1 = ProfileData.getAvatar(1);
        String playerName1 = ProfileData.getPlayerName(1);
        String rank1 = ProfileData.getRank(1);

        String avatar2 = ProfileData.getAvatar(2);
        String playerName2 = ProfileData.getPlayerName(2);
        String rank2 = ProfileData.getRank(2);

        // Extraction des noms de fichiers à partir des chemins complets
        String avatarFileName1 = avatar1.substring(avatar1.lastIndexOf('/') + 1);
        String avatarFileName2 = avatar2.substring(avatar2.lastIndexOf('/') + 1);

        // Création des VBox pour afficher les profils
        VBox profileBox1 = ProfilParty.createProfileBox(avatarFileName1, playerName1, rank1, true);
        VBox profileBox2 = ProfilParty.createProfileBox(avatarFileName2, playerName2, rank2, false);
        HBox hbox1 = new HBox(); // HBox pour le profil 1
        HBox hbox4 = new HBox(); // HBox pour le profil 2
        hbox1.getChildren().add(profileBox1);
        hbox4.getChildren().add(profileBox2);
        hbox1.setAlignment(Pos.BOTTOM_LEFT);
        hbox4.setAlignment(Pos.BOTTOM_RIGHT);
        setMargin(hbox1, new Insets(0, 0, 10, 15));
        setMargin(hbox4, new Insets(0, 15, 10, 0));

        // Création du bouton pause avec une image
        Image pauseImage = new Image("pause.png"); // Remplacez "chemin/vers/votre/image.png" par le chemin de votre image
        ImageView imageView = new ImageView(pauseImage);
        imageView.setFitWidth(32); // Ajustez la largeur de l'image selon vos besoins
        imageView.setFitHeight(32); // Ajustez la hauteur de l'image selon vos besoins

        Button pauseButton = new Button();
        pauseButton.setGraphic(imageView); // Définit l'image comme graphique du bouton

        // Rendre l'arrière-plan du bouton invisible
        pauseButton.setStyle("-fx-background-color: transparent; -fx-background-radius: 0; -fx-border-color: transparent;");

        // Ajout d'une action pour afficher le menu pause lors du clic sur le bouton pause
        pauseButton.setOnAction(e -> {
            // Afficher le menu pause
            pauseMenu.setVisible(true);
        });

        // Positionnement du bouton pause en haut à droite
        StackPane.setAlignment(pauseButton, Pos.TOP_RIGHT);
        setMargin(pauseButton, new Insets(10, 10, 0, 0));

        pauseMenu = createPauseMenu(primaryStage);
        pauseMenu.setVisible(false);
        quitterMenu.setVisible(false);

        getChildren().addAll(hBox, hbox1, hbox4, gridPane, pauseMenu, quitterMenu, pauseButton);

        // Gestionnaire d'événements pour les boutons du GridPane
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                button.setOnAction(e -> handleButtonClick(button, gridPane));
            }
        }
    }

    // Méthode pour gérer le clic sur le bouton
    private void handleButtonClick(Button button, GridPane gridpane) {

        // Vérifier si le bouton n'a pas déjà d'image et si tous les tours n'ont pas été joués
        if (button.getGraphic() == null && turns < 9) {
            // Placer l'image du joueur sur le bouton en fonction du joueur actuel
            if (currentPlayer == 1) {
                placePlayerImage(button, leftVBox);
                buttonsJ1.add(button);
                currentPlayer = 2;
            } else {
                placePlayerImage(button, rightVBox);
                buttonsJ2.add(button);

                currentPlayer = 1;
                turns++;
            }
        } else {
            // Vérifier si le bouton cliqué appartient à la liste des boutons autorisés à être sélectionnés par le joueur actuel
            if (currentPlayer == 1 && (buttonsJ1.contains(button) || button.getGraphic() == null)) {
                handleSelection(buttonsJ1, button);
            } else if (currentPlayer == 2 && (buttonsJ2.contains(button) || button.getGraphic() == null) ) {
                handleSelection(buttonsJ2, button);
            }
        }

        String[][] buttonCombinations = {{"A", "B", "C"}, {"D", "E", "F"}, {"G", "H", "I"}, {"J", "K", "L"}, {"M", "N", "O"}, {"P", "Q", "R"}, {"S", "T", "U"}, {"V", "W", "X"}, {"A", "J", "V"}, {"D", "K", "S"}, {"G", "L", "P"}, {"B", "E", "H"}, {"Q", "T", "W"}, {"I", "M", "R"}, {"F", "N", "U"}, {"C", "O", "X"}};
        for (String[] combination : buttonCombinations) {
            checkAndChangeButtonColor(combination[0], combination[1], combination[2], gridpane);
        }
    }

    // Méthode pour gérer la sélection du bouton
    private void handleSelection(List<Button> buttons ,Button clickedButton) {
        if (selectedButton == null) {
            if (buttons.contains(clickedButton)) {
                selectedButton = clickedButton;// Sélectionner le bouton actuel
                ButtonSelector.selectButton(selectedButton);
            }} else {
            // Vérifier si le bouton actuel est voisin du bouton sélectionné
            if (isNeighbourButton(selectedButton, clickedButton)) {
                // Échanger les images des boutons
                if (clickedButton.getGraphic() == null) {
                    ImageView imageView = (ImageView) selectedButton.getGraphic();
                    clickedButton.setGraphic(imageView);
                    selectedButton.setGraphic(null);
                    buttons.remove(selectedButton);
                    buttons.add(clickedButton);
                    // Changer de joueur après avoir effectué l'échange
                    currentPlayer = (currentPlayer == 1) ? 2 : 1;
                }
            }
            // Désélectionner le bouton sélectionné
            ButtonSelector.deselectButton(selectedButton);
            selectedButton = null;
        }

    }

    // Méthode pour placer l'image du joueur sur un bouton
    private void placePlayerImage(Button button, VBox playerVBox) {
        // Obtenir le GridPane enfant de la VBox
        GridPane gridPane = (GridPane) playerVBox.getChildren().get(0);

        // Vérifier si le GridPane contient des images
        if (!gridPane.getChildren().isEmpty()) {
            // Récupérer l'image correspondant à l'index du joueur actuel
            ImageView imageView = (ImageView) gridPane.getChildren().get(currentImageIndex);

            // Appliquer l'image sur le bouton
            button.setGraphic(imageView);

            // Supprimer l'image du GridPane
            gridPane.getChildren().remove(imageView);

            // Passer au joueur suivant après chaque tour complet
            if (currentImageIndex >= gridPane.getChildren().size()) {
                currentPlayer = currentPlayer == 1 ? 2 : 1;
                currentImageIndex = 0; // Réinitialiser l'index d'image
            }
        }
    }

    private VBox createPauseMenu(Stage primaryStage) {
        VBox menu = new VBox(15); // Conteneur pour les boutons du menu pause

        // Ajout des boutons nécessaires (Reprendre, Options, Quitter, etc.)
        Button resumeButton = new Button("Reprendre");
        Button regles = new Button("Règles");
        Button parametres = new Button("Paramètres");
        Button quitter = new Button("Quitter la Partie");

        resumeButton.setFont(Font.font("Cardo", FontWeight.BOLD, 20));
        regles.setFont(Font.font("Cardo", FontWeight.BOLD, 20));
        parametres.setFont(Font.font("Cardo", FontWeight.BOLD, 20));
        quitter.setFont(Font.font("Cardo", FontWeight.BOLD, 20));

        // Stylisation des boutons du menu pause
        resumeButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        regles.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        parametres.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
        quitter.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white;");

        // Ajout d'une action pour le bouton "Reprendre" pour masquer le menu pause
        resumeButton.setOnAction(e -> {
            // Masquer le menu pause
            menu.setVisible(false);
        });

        // Action du bouton "Règles" pour afficher les règles
        regles.setOnAction(e -> {
            afficherRegles(this); // Passer la racine de la scène pour ajouter la StackPane
        });

        quitter.setOnAction(e -> {
            quitterMenu.setVisible(true);
        });

        quitterMenu = ButtonPause.boutonquitter(primaryStage);

        // Ajout des boutons au menu
        menu.getChildren().addAll(resumeButton, regles, parametres, quitter);

        // Stylisation du menu pause avec un arrière-plan semi-transparent
        menu.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7); -fx-padding: 20px;");

        // Positionnement du menu pause au centre de l'écran
        menu.setAlignment(Pos.CENTER);

        return menu;
    }
}