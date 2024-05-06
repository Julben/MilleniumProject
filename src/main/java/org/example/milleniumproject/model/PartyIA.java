package org.example.milleniumproject.model;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import org.example.milleniumproject.view.Menu;
import java.util.ArrayList;
import java.util.List;

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

    public PartyIA(Stage primaryStage, ToggleGroup toggleGroup3, HBox hbox3) {
        this.toggleGroup3 = toggleGroup3;
        this.hbox3 = hbox3;

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
        VBox profileBox1 = createProfileBox(avatarFileName1, playerName1, rank1, true);
        VBox profileBox2 = createProfileBox(avatarFileName2, playerName2, rank2, false);
        setMargin(profileBox1, new Insets(0, 950, 20, 0));
        setMargin(profileBox2, new Insets(0, 0, 20, 950));

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

        getChildren().addAll(hBox, profileBox1, profileBox2, gridPane, pauseMenu, quitterMenu, pauseButton);

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
            //checkAlignment();
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

    private void checkAndChangeButtonColor(String buttonLabel1, String buttonLabel2, String buttonLabel3, GridPane gridPane) {
        // Variables pour stocker les URLs des boutons
        String url1 = null;
        String url2 = null;
        String url3 = null;

        // Obtenez tous les nœuds enfants du GridPane
        ObservableList<Node> children = gridPane.getChildren();

        // Parcourez chaque nœud pour obtenir les informations de chaque bouton
        for (Node node : children) {
            if (node instanceof Button) {
                Button currentButton = (Button) node;
                String buttonText = getButtonText(currentButton);
                String imageUrl = getImageUrlFromButton(currentButton);

                // Stockez les URLs des boutons
                if (buttonText.equals(buttonLabel1)) {
                    url1 = imageUrl;
                } else if (buttonText.equals(buttonLabel2)) {
                    url2 = imageUrl;
                } else if (buttonText.equals(buttonLabel3)) {
                    url3 = imageUrl;
                }
            }
        }

        // Vérifiez si les URLs des boutons sont identiques
        if (url1 != null && url2 != null && url3 != null && url1.equals(url2) && url1.equals(url3)) {
            // Parcourez à nouveau chaque nœud pour changer la couleur des boutons
            for (Node node : children) {
                if (node instanceof Button) {
                    Button currentButton = (Button) node;
                    String buttonText = getButtonText(currentButton);
                    // Si le bouton est l'un des boutons spécifiés, changez sa couleur en rouge
                    if (buttonText.equals(buttonLabel1) || buttonText.equals(buttonLabel2) || buttonText.equals(buttonLabel3)) {
                        currentButton.setStyle("-fx-background-color: yellow;"+
                                "-fx-background-radius: 50%; " + // Rendre les coins ronds
                                "-fx-min-width: 65px; " + // Définir la largeur
                                "-fx-min-height: 65px; " + // Définir la hauteur
                                "-fx-max-width: 65px; " + // Limiter la largeur
                                "-fx-max-height: 65px;");
                    }
                }
            }
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

    // Instance pour séléctionner un bouton
    private void selectButton(Button button) {
        ButtonSelector.selectButton(button);
    }

    // Instance pour désélectionner un bouton
    private void deselectButton(Button button) {
        ButtonSelector.deselectButton(button);
    }


    // Méthode pour vérifier si deux boutons sont voisins dans le GridPane
    private boolean isNeighbourButton(Button button1, Button button2) {
        GridPane gridPane = (GridPane) button1.getParent();
        Integer rowIndex1 = GridPane.getRowIndex(button1);
        Integer colIndex1 = GridPane.getColumnIndex(button1);
        Integer rowIndex2 = GridPane.getRowIndex(button2);
        Integer colIndex2 = GridPane.getColumnIndex(button2);

        // Vérifier si les boutons sont dans les mêmes colonnes
        if (colIndex1.equals(colIndex2)) {
            // Parcourir les lignes entre les deux boutons
            int startRow = Math.min(rowIndex1, rowIndex2);
            int endRow = Math.max(rowIndex1, rowIndex2);
            for (int row = startRow + 1; row < endRow; row++) {
                if (row == 3 && colIndex1 == 3) {
                    return false; // Arrêter le scan si la coordonnée (3,3) est un mur
                }
                Node node = getNodeByRowColumnIndex(row, colIndex1, gridPane);
                if (node instanceof Button) {
                    return false; // Il y a un bouton entre les deux, donc ils ne sont pas voisins
                }
            }
            return true;
        }
        // Vérifier si les boutons sont dans les mêmes lignes
        else if (rowIndex1.equals(rowIndex2)) {
            // Parcourir les colonnes entre les deux boutons
            int startCol = Math.min(colIndex1, colIndex2);
            int endCol = Math.max(colIndex1, colIndex2);
            for (int col = startCol + 1; col < endCol; col++) {
                if (rowIndex1 == 3 && col == 3) {
                    return false; // Arrêter le scan si la coordonnée (3,3) est un mur
                }
                Node node = getNodeByRowColumnIndex(rowIndex1, col, gridPane);
                if (node instanceof Button) {
                    return false; // Il y a un bouton entre les deux, donc ils ne sont pas voisins
                }
            }
            return true;
        }

        return false;
    }

    private String getImageUrlFromButton(Button button) {
        // Vérifier si le bouton contient une image
        if (button.getGraphic() instanceof ImageView) {
            ImageView imageView = (ImageView) button.getGraphic();
            Image image = imageView.getImage();

            // Récupérer l'URL de l'image
            String imageUrl = image.getUrl();
            return imageUrl;
        } else {
            // Le bouton ne contient pas d'image
            return null;
        }
    }

    private String getButtonText(Button button) {
        // Récupérer le texte du bouton
        String buttonText = button.getText();
        return buttonText;
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

            // Incrémenter l'index d'image pour le prochain joueur
            currentImageIndex++;

            // Passer au joueur suivant après chaque tour complet
            if (currentImageIndex >= gridPane.getChildren().size()) {
                currentPlayer = currentPlayer == 1 ? 2 : 1;
                currentImageIndex = 0; // Réinitialiser l'index d'image
            }
        }
    }

    // Instance pour créer un bouton stylisé
    private Button createStyledButton(String text) {
        return ButtonSelector.createStyledButton(text);
    }

    private void highlightAlignedButtons(GridPane gridPane) {
        // Parcourir chaque ligne et chaque colonne de la GridPane
        for (int i = 0; i < gridPane.getRowCount(); i++) {
            highlightAlignedButtonsInRow(gridPane, i);
        }
        for (int i = 0; i < gridPane.getColumnCount(); i++) {
            highlightAlignedButtonsInColumn(gridPane, i);
        }
    }

    private void highlightAlignedButtonsInRow(GridPane gridPane, int rowIndex) {
        Button[] buttonsInRow = new Button[gridPane.getColumnCount()];
        // Récupérer les boutons dans la ligne
        for (int colIndex = 0; colIndex < gridPane.getColumnCount(); colIndex++) {
            Node node = getNodeByRowColumnIndex(rowIndex, colIndex, gridPane);
            if (node instanceof Button) {
                buttonsInRow[colIndex] = (Button) node;
            }
        }
        // Vérifier si les trois boutons alignés ont des images
        if (buttonsInRow[0] != null && buttonsInRow[1] != null && buttonsInRow[2] != null
                && buttonsInRow[0].getGraphic() != null && buttonsInRow[1].getGraphic() != null && buttonsInRow[2].getGraphic() != null) {
            // Vérifier si les images des boutons sont les mêmes
            ImageView imageView1 = (ImageView) buttonsInRow[0].getGraphic();
            ImageView imageView2 = (ImageView) buttonsInRow[1].getGraphic();
            ImageView imageView3 = (ImageView) buttonsInRow[2].getGraphic();
            if (imageView1.getImage().equals(imageView2.getImage()) && imageView1.getImage().equals(imageView3.getImage())) {
                // Mettre en surbrillance les boutons alignés
                for (Button button : buttonsInRow) {
                    button.setStyle("-fx-background-color: yellow;");
                }
            }
        }
    }

    private void highlightAlignedButtonsInColumn(GridPane gridPane, int colIndex) {
        Button[] buttonsInColumn = new Button[gridPane.getRowCount()];
        // Récupérer les boutons dans la colonne
        for (int rowIndex = 0; rowIndex < gridPane.getRowCount(); rowIndex++) {
            Node node = getNodeByRowColumnIndex(rowIndex, colIndex, gridPane);
            if (node instanceof Button) {
                buttonsInColumn[rowIndex] = (Button) node;
            }
        }
        // Vérifier si les trois boutons alignés ont des images
        if (buttonsInColumn[0] != null && buttonsInColumn[1] != null && buttonsInColumn[2] != null
                && buttonsInColumn[0].getGraphic() != null && buttonsInColumn[1].getGraphic() != null && buttonsInColumn[2].getGraphic() != null) {
            // Vérifier si les images des boutons sont les mêmes
            ImageView imageView1 = (ImageView) buttonsInColumn[0].getGraphic();
            ImageView imageView2 = (ImageView) buttonsInColumn[1].getGraphic();
            ImageView imageView3 = (ImageView) buttonsInColumn[2].getGraphic();
            if (imageView1.getImage().equals(imageView2.getImage()) && imageView1.getImage().equals(imageView3.getImage())) {
                // Mettre en surbrillance les boutons alignés
                for (Button button : buttonsInColumn) {
                    button.setStyle("-fx-background-color: yellow;");
                }
            }
        }
    }

    private Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> children = gridPane.getChildren();
        for (Node node : children) {
            if (gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }
        return result;
    }


    // Méthode pour créer une VBox avec des images répétées
    private VBox createVBoxWithImages(String imageLink, int count) {
        VBox vBox = new VBox(10); // Espacement vertical entre les images
        vBox.setPadding(new Insets(10)); // Espacement autour des images
        vBox.setAlignment(Pos.CENTER); // Centrer les images dans la VBox

        List<ImageView> imageViews = new ArrayList<>();

        Image image = new Image(imageLink);
        for (int i = 0; i < count; i++) {
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(0.04 * Constant.screenWidth); // Largeur de l'image réduite
            imageView.setFitHeight(0.04 * Constant.screenWidth); // Hauteur de l'image réduite
            imageViews.add(imageView);
        }

        // Création d'une grille de 3x3 images
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        int rowIndex = 0;
        int colIndex = 0;
        for (ImageView imageView : imageViews) {
            gridPane.add(imageView, colIndex, rowIndex);
            colIndex++;
            if (colIndex > 2) {
                colIndex = 0;
                rowIndex++;
            }
        }

        vBox.getChildren().add(gridPane);

        return vBox;
    }

    // Méthode pour créer une VBox affichant le profil d'un joueur avec l'avatar à côté des labels
    private VBox createProfileBox(String avatarFileName, String playerName, String rank, boolean isPlayer1) {
        VBox profileBox = new VBox(0); // Espacement vertical entre les éléments du profil
        profileBox.setAlignment(Pos.BOTTOM_CENTER); // Alignement au centre et en bas

        // Création d'une HBox pour contenir l'avatar, le nom et le rang
        HBox hbox = new HBox(10); // Espacement horizontal entre les éléments
        hbox.setAlignment(Pos.CENTER); // Centrage horizontal des éléments

        // Création d'une ImageView pour l'avatar
        ImageView avatarImageView;
        if (isPlayer1) {
            // Pour le joueur 1, charger l'image depuis le fichier
            avatarImageView = new ImageView(new Image(avatarFileName));
            hbox.getChildren().add(avatarImageView);
        } else {
            // Pour le joueur 2, utiliser l'image constante
            avatarImageView = new ImageView(new Image("9.png"));
        }
        avatarImageView.setFitWidth(150); // Taille de l'avatar
        avatarImageView.setFitHeight(150);

        // Création d'une VBox pour contenir le nom et le rang
        VBox labelsVBox = new VBox(0); // Espacement vertical entre les labels
        if (isPlayer1) {
            labelsVBox.setAlignment(Pos.CENTER_LEFT); // Alignement à droite des labels pour le joueur 1
        } else {
            labelsVBox.setAlignment(Pos.CENTER_RIGHT); // Alignement à gauche des labels pour le joueur 2
            // Définir le nom et le rang par défaut pour le joueur 2
            playerName = "Robot";
            rank = "IA";
        }

        // Ajout du nom du joueur
        Label nameLabel = new Label(playerName);
        nameLabel.setFont(Font.font("Cardo", 35)); // Définition de la police et de la taille
        nameLabel.setTextFill(Color.WHITE); // Définition de la couleur du text
        labelsVBox.getChildren().add(nameLabel);

        // Ajout du rang du joueur
        Label rankLabel = new Label(rank);
        rankLabel.setFont(Font.font("Cardo", 20)); // Définition de la police et de la taille
        rankLabel.setTextFill(Color.WHITE); // Définition de la couleur du text
        labelsVBox.getChildren().add(rankLabel);

        hbox.getChildren().add(labelsVBox); // Ajout de la VBox des labels à la HBox

        // Ajout de l'avatar à droite pour le joueur 2
        if (!isPlayer1) {
            hbox.getChildren().add(avatarImageView); // Ajout de l'avatar à droite pour le joueur 2
        }

        // Ajout de la HBox au VBox principal
        profileBox.getChildren().add(hbox);

        return profileBox;
    }

    private VBox createPauseMenu(Stage primaryStage) {
        VBox menu = new VBox(15); // Conteneur pour les boutons du menu pause

        // Ajout des boutons nécessaires (Reprendre, Options, Quitter, etc.)
        Button resumeButton = new Button("Reprendre");
        Button regles = new Button("Règles");
        Button parametres = new Button("Paramètres");
        Button quitter = new Button("Quitter la Partie");

        // Stylisation des boutons du menu pause
        resumeButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14pt;");
        regles.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-size: 14pt;");
        parametres.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-size: 14pt;");
        quitter.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white; -fx-font-size: 14pt;");

        // Ajout d'une action pour le bouton "Reprendre" pour masquer le menu pause
        resumeButton.setOnAction(e -> {
            // Masquer le menu pause
            menu.setVisible(false);
        });

        quitter.setOnAction(e -> {
            quitterMenu.setVisible(true);
        });

        quitterMenu = boutonquitter(primaryStage);

        // Ajout des boutons au menu
        menu.getChildren().addAll(resumeButton, regles, parametres, quitter);

        // Stylisation du menu pause avec un arrière-plan semi-transparent
        menu.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); -fx-padding: 20px;");

        // Positionnement du menu pause au centre de l'écran
        menu.setAlignment(Pos.CENTER);

        return menu;
    }

    private VBox boutonquitter(Stage primaryStage){
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






