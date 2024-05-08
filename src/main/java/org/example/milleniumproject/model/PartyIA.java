package org.example.milleniumproject.model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.example.milleniumproject.model.ButtonColorChecker.checkAndChangeButtonColor;
import static org.example.milleniumproject.model.ButtonPause.afficherRegles;
import static org.example.milleniumproject.model.ButtonSelector.*;
import static org.example.milleniumproject.model.ButtonUtils.*;
import static org.example.milleniumproject.model.Methodeia.disableMouseInteractions;
import static org.example.milleniumproject.model.ProfilParty.*;
import javafx.animation.PauseTransition;
import javafx.util.Duration;



public class PartyIA extends StackPane {

    // Déclarations des variables d'instance
    private int currentPlayer = 1;
    private VBox leftVBox;
    private VBox rightVBox;
    private int turns = 0;
    private int currentImageIndex = 0;
    private ToggleGroup toggleGroup3;
    private HBox hbox3;
    private Button selectedButton=null;
    private VBox pauseMenu; // Conteneur pour le menu pause
    private VBox quitterMenu;
    private List<Button> buttonsJ1 = new ArrayList<>();
    private List<Button> buttonsJ2 = new ArrayList<>();
    private ButtonUtils buttonUtils;
    private Random random = new Random();
    private List<String> player2Positions = new ArrayList<>();






    public PartyIA(Stage primaryStage, ToggleGroup toggleGroup3, HBox hbox3) {

        this.toggleGroup3 = toggleGroup3;
        this.hbox3 = hbox3;
        buttonUtils = new ButtonUtils();
        int selectedIndex = PrePartyIA.getSelectedIndex(toggleGroup3, hbox3);



        // Création du fond d'écran
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
            Button button = createStyledButton(buttonLabels[i]);
            gridPane.add(button, colIndices[i], rowIndices[i]);
        }

        // Création des Vbox pour les images des joueurs
        String str = ProfileData.getShip(1);
        int lastIndex = str.lastIndexOf('/');
        String vaisseau1 = str.substring(lastIndex + 1);

        String str2 = ProfileData.getShip(2);
        int lastIndex2 = str2.lastIndexOf('/');
        String vaisseau2 = str2.substring(lastIndex2 + 1);

        leftVBox = createVBoxWithImages(vaisseau1, 9);
        rightVBox = createVBoxWithImages(vaisseau2, 9);

        HBox hBox = new HBox(0.6 * Constant.screenWidth); // Espacement horizontal entre les Vbox
        hBox.getChildren().addAll(leftVBox, rightVBox);
        hBox.setAlignment(Pos.CENTER);

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
        VBox profileBox1 = ProfilParty.createProfileBox2(avatarFileName1, playerName1, rank1, true);
        VBox profileBox2 = ProfilParty.createProfileBox2(avatarFileName2, playerName2, rank2, false);
        HBox hbox1 = new HBox(); // HBox pour le profil 1
        HBox hbox4 = new HBox(); // HBox pour le profil 2
        hbox1.getChildren().add(profileBox1);
        hbox4.getChildren().add(profileBox2);
        hbox1.setAlignment(Pos.BOTTOM_LEFT);
        hbox4.setAlignment(Pos.BOTTOM_RIGHT);
        setMargin(hbox1, new Insets(0, 0, 10, 15));
        setMargin(hbox4, new Insets(0, 15, 10, 0));

        // Positionnement des VBox
        setAlignment(profileBox1, Pos.BOTTOM_LEFT);
        setAlignment(profileBox2, Pos.BOTTOM_RIGHT);

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
                turns++; // Augmenter le compteur de tours après que les deux joueurs aient placé leur pion

                // Désactiver les interactions avec la souris ou le pavé tactile
                disableMouseInteractions(gridpane, true);

                // Ajouter un délai avant que le joueur 2 ne place son pion
                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(e -> {
                    placeRandomPlayerImage(gridpane); // Appel pour placer l'image du joueur 2
                    disableMouseInteractions(gridpane, false); // Réactiver les interactions avec la souris ou le pavé tactile
                });
                pause.play();
            } else {
                placeRandomPlayerImage(gridpane);
                turns++;
                currentPlayer = 1;
            }
        } else {
            // Vérifier si le bouton cliqué appartient à la liste des boutons autorisés à être sélectionnés par le joueur actuel
            if (currentPlayer == 1 && (buttonsJ1.contains(button) || button.getGraphic() == null)) {
                handleSelection(buttonsJ1, button);
            } else if (currentPlayer == 2 && (buttonsJ2.contains(button) || button.getGraphic() == null)) {
                handleSelection(buttonsJ2, button);
            }
        }

        // Vérifier et changer la couleur des boutons
        String[][] buttonCombinations = {{"A", "B", "C"}, {"D", "E", "F"}, {"G", "H", "I"}, {"J", "K", "L"}, {"M", "N", "O"}, {"P", "Q", "R"}, {"S", "T", "U"}, {"V", "W", "X"}, {"A", "J", "V"}, {"D", "K", "S"}, {"G", "L", "P"}, {"B", "E", "H"}, {"Q", "T", "W"}, {"I", "M", "R"}, {"F", "N", "U"}, {"C", "O", "X"}};
        for (String[] combination : buttonCombinations) {
            checkAndChangeButtonColor(combination[0], combination[1], combination[2], gridpane);
        }
    }

    private void placeRandomPlayerImage(GridPane gridpane) {
        while (true) {
            int randomRow = random.nextInt(7); // Choisir une ligne aléatoire
            int randomCol = random.nextInt(7); // Choisir une colonne aléatoire
            Button randomButton = (Button) getNodeByRowColumnIndex(randomRow, randomCol, gridpane); // Obtenir le bouton correspondant à la ligne et à la colonne aléatoires
            if (randomButton != null && randomButton.getGraphic() == null) { // Vérifier si le bouton n'est pas nul et s'il est vide
                placePlayerImage(randomButton, rightVBox); // Placer le pion du joueur 2 sur le bouton aléatoire
                buttonsJ2.add(randomButton); // Ajouter le bouton à la liste des boutons du joueur 2
                break;
            }
        }
    }


    // Méthode pour placer l'image du joueur sur un bouton
    private void placePlayerImage(Button button, VBox playerVBox) {
        // Obtenir le GridPane enfant de la VBox
        GridPane gridPane = (GridPane) playerVBox.getChildren().get(0);

        // Vérifier si le GridPane contient des images
        if (!gridPane.getChildren().isEmpty()) {
            // Récupérer l'image correspondant à l'index du joueur actuel
            ImageView imageView = (ImageView) gridPane.getChildren().get(0);

            // Vérifier si le bouton n'a pas déjà d'image
            if (button.getGraphic() == null) {
                // Créer une copie de l'image pour la placer sur le bouton
                ImageView imageViewCopy = new ImageView(imageView.getImage());
                imageViewCopy.setFitWidth(button.getWidth());
                imageViewCopy.setFitHeight(button.getHeight());

                // Appliquer l'image sur le bouton
                button.setGraphic(imageViewCopy);

                // Passer au joueur suivant après chaque tour complet
                currentPlayer = currentPlayer == 1 ? 2 : 1;

                // Vérifier s'il reste des images à placer
                if (gridPane.getChildren().size() > 1) {
                    gridPane.getChildren().remove(0);
                } else {
                    gridPane.getChildren().clear();
                    currentImageIndex = 0; // Réinitialiser l'index d'image
                }
            }
        } else {
            // Afficher un message d'erreur ou effectuer une autre action appropriée
            System.err.println("Erreur : Aucune image à placer sur le bouton.");
        }
    }

    // Méthode pour gérer la sélection du bouton
    private void handleSelection(List<Button> buttons ,Button clickedButton) {
        if (selectedButton == null) {
            if (buttons.contains(clickedButton)) {
                selectedButton = clickedButton;// Sélectionner le bouton actuel
                selectButton(selectedButton);
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
            deselectButton(selectedButton);
            selectedButton = null;
        }

    }

    private void savePlayer2Positions(GridPane gridpane) {
        for (Node node : gridpane.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                if (button.getGraphic() instanceof ImageView) {
                    // Vérifier si le pion appartient au joueur 2
                    if (buttonsJ2.contains(button)) {
                        // Enregistrer la position du pion
                        String position = GridPane.getRowIndex(button) + "-" + GridPane.getColumnIndex(button);
                        // Faites quelque chose avec la position enregistrée (par exemple, ajoutez-la à une liste)
                        // Par exemple :
                        player2Positions.add(position);
                    }
                }
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






