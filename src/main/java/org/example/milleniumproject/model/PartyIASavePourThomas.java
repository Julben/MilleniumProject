/*
package org.example.milleniumproject.model;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PartyIA extends StackPane {
    // Déclarations des variables d'instance
    private int currentPlayer = 1;
    private VBox leftVBox;
    private VBox rightVBox;
    private int turns = 0;
    private ToggleGroup toggleGroup3;
    private HBox hbox3;
    private ToggleGroup toggleGroup2;
    private HBox hbox2;
    private Button selectedButton = null;
    private List<Button> buttonsJ1 = new ArrayList<>();
    private List<Button> buttonsJ2 = new ArrayList<>();
    private GridPane gridPane;
    private boolean isRemovePieceMode = false; // Variable pour activer/désactiver le mode de suppression de pion
    private boolean isNewAlignment = true; // Variable pour suivre si un nouvel alignement de trois pions a été formé
    boolean boutonlibre = false;

    private static final List<String[]> neighbourList = Arrays.asList(
            new String[]{"A", "B"}, new String[]{"A", "J"}, new String[]{"B", "C"}, new String[]{"B", "E"},
            new String[]{"C", "O"}, new String[]{"D", "E"}, new String[]{"D", "K"}, new String[]{"E", "F"},
            new String[]{"E", "H"}, new String[]{"F", "N"}, new String[]{"G", "H"}, new String[]{"G", "L"},
            new String[]{"H", "I"}, new String[]{"I", "M"}, new String[]{"J", "K"}, new String[]{"J", "V"},
            new String[]{"K", "L"}, new String[]{"K", "S"}, new String[]{"L", "P"}, new String[]{"M", "R"},
            new String[]{"M", "N"}, new String[]{"N", "O"}, new String[]{"N", "U"}, new String[]{"O", "X"},
            new String[]{"P", "Q"}, new String[]{"Q", "R"}, new String[]{"Q", "T"}, new String[]{"S", "T"},
            new String[]{"T", "U"}, new String[]{"T", "W"}, new String[]{"V", "W"}, new String[]{"W", "X"}
    );

    public PartyIA(Stage primaryStage, ToggleGroup toggleGroup3, HBox hbox3, ToggleGroup toggleGroup2, HBox hbox2) {
        this.toggleGroup3 = toggleGroup3;
        this.hbox3 = hbox3;

        int selectedIndex = PrePartyIA.getSelectedIndex(toggleGroup3, hbox3);

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

        int selectedIndexchrono = PrePartyIA.getSelectedIndexchrono(toggleGroup2, hbox2);
        System.out.println("selectedIndexchrono: " + selectedIndexchrono);

        // Création du GridPane
        gridPane = new GridPane();
        gridPane.setHgap(36); // Espacement horizontal entre les boutons
        gridPane.setVgap(36); // Espacement vertical entre les boutons
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

        leftVBox = ProfilParty.createVBoxWithImages(vaisseau1, 9);
        rightVBox = ProfilParty.createVBoxWithImages(vaisseau2, 9);

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
        VBox profileBox1 = ProfilParty.createProfileBox(avatarFileName1, playerName1, rank1, true, false);
        VBox profileBox2 = ProfilParty.createProfileBox(avatarFileName2, playerName2, rank2, false, true);
        setMargin(profileBox1, new Insets(0, 0, 15, 20));
        setMargin(profileBox2, new Insets(0, 20, 15, 0));

        // Positionnement des VBox
        setAlignment(profileBox1, Pos.BOTTOM_LEFT);
        setAlignment(profileBox2, Pos.BOTTOM_RIGHT);

        getChildren().addAll(hBox, profileBox1, profileBox2, gridPane);

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
        System.out.print(currentPlayer);
        System.out.print(isRemovePieceMode);
        System.out.println(isNewAlignment);
        System.out.println(turns);
        if (isRemovePieceMode) {
            // Si le mode de suppression de pion est activé
            removePiece(button);
        } else {
            // Vérifier si le bouton n'a pas déjà d'image et si tous les tours n'ont pas été joués
            if (button.getGraphic() == null && turns < 18) {
                // Placer l'image du joueur sur le bouton en fonction du joueur actuel
                if (currentPlayer == 1) {
                    placePlayerImage(button, leftVBox);
                    buttonsJ1.add(button);
                    // Vérifier les combinaisons après chaque placement de pion
                    checkButtonCombinations();
                    if(isRemovePieceMode){
                        currentPlayer = 1;
                    } else {
                        currentPlayer = 2;
                    }

                    turns++;
                }
                else {
                    placePlayerImage(button, rightVBox);
                    buttonsJ2.add(button);
                    // Vérifier les combinaisons après chaque placement de pion
                    checkButtonCombinations();
                    if(isRemovePieceMode){
                        currentPlayer = 2;
                    } else {
                        currentPlayer = 1;
                    }
                    turns++;
                }
            }
            else {
                // Vérifier si le bouton cliqué appartient à la liste des boutons autorisés à être sélectionnés par le joueur actuel
                if (currentPlayer == 1 && (buttonsJ1.contains(button) || button.getGraphic() == null)) {
                    handleSelection(buttonsJ1, button);
                } else if (currentPlayer == 2 && (buttonsJ2.contains(button) || button.getGraphic() == null)) {
                    handleSelection(buttonsJ2, button);
                }
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

            // Appliquer l'image sur le bouton
            button.setGraphic(imageView);

            // Supprimer l'image du GridPane
            gridPane.getChildren().remove(imageView);
        }
    }




    // Méthode pour gérer la sélection du bouton
    private void handleSelection(List<Button> buttons, Button clickedButton) {
        if (selectedButton == null) {
            if (buttons.contains(clickedButton)) {
                selectedButton = clickedButton;// Sélectionner le bouton actuel
                selectButton(selectedButton);
            }
        }
        else {
            // Vérifier si le bouton actuel est voisin du bouton sélectionné
            if (isNeighbourButton(selectedButton, clickedButton)) {
                // Échanger les images des boutons
                if (clickedButton.getGraphic() == null) {
                    ImageView imageView = (ImageView) selectedButton.getGraphic();
                    clickedButton.setGraphic(imageView);
                    selectedButton.setGraphic(null);
                    buttons.remove(selectedButton);
                    buttons.add(clickedButton);

                    // Après le déplacement, réinitialiser la couleur des boutons formant une ligne complète
                    resetButtonColorsForMovedButton(selectedButton);

                    // Vérifier si une nouvelle ligne de trois pions a été formée après le déplacement
                    checkButtonCombinations();

                    if(!isRemovePieceMode) {
                        currentPlayer = currentPlayer == 1 ? 2 : 1;
                    }
                }
            }
            // Désélectionner le bouton sélectionné
            deselectButton(selectedButton);
            selectedButton = null;
        }
    }

    // Méthode pour retirer un pion adverse
    private void removePiece(Button button) {
        // Vérifier si le bouton cliqué contient une image
        if (button.getGraphic() instanceof ImageView) {
            if(currentPlayer==1) {
                for(Button b : buttonsJ2){
                    if (!b.getStyle().contains("-fx-background-radius: 50%; -fx-min-width: 50px; -fx-min-height: 50px; -fx-max-width: 50px; -fx-max-height: 50px; -fx-background-color: green; -fx-border-color: transparent")){
                        boutonlibre = true;
                    }
                }
                if(boutonlibre && buttonsJ2.contains(button) && !button.getStyle().contains("-fx-background-radius: 50%; -fx-min-width: 50px; -fx-min-height: 50px; -fx-max-width: 50px; -fx-max-height: 50px; -fx-background-color: green; -fx-border-color: transparent")){
                    button.setGraphic(null);
                    buttonsJ2.remove(button);
                    currentPlayer = currentPlayer == 1 ? 2 : 1;
                    isRemovePieceMode = false;
                    boutonlibre = false;
                }
                else if(!boutonlibre && buttonsJ2.contains(button)) {
                    button.setGraphic(null);
                    buttonsJ2.remove(button);
                    resetButtonColorsForMovedButton(button);
                    currentPlayer = currentPlayer == 1 ? 2 : 1;
                    isRemovePieceMode = false;
                    boutonlibre = false;
                }
            }
            else if(currentPlayer==2) {
                for(Button b : buttonsJ1){
                    if (!b.getStyle().contains("-fx-background-radius: 50%; -fx-min-width: 50px; -fx-min-height: 50px; -fx-max-width: 50px; -fx-max-height: 50px; -fx-background-color: green; -fx-border-color: transparent")){
                        boutonlibre = true;
                    }
                }
                if(boutonlibre && buttonsJ1.contains(button) && !button.getStyle().contains("-fx-background-radius: 50%; -fx-min-width: 50px; -fx-min-height: 50px; -fx-max-width: 50px; -fx-max-height: 50px; -fx-background-color: green; -fx-border-color: transparent")){
                    button.setGraphic(null);
                    buttonsJ1.remove(button);
                    currentPlayer = currentPlayer == 1 ? 2 : 1;
                    isRemovePieceMode = false;
                    boutonlibre = false;
                }
                else if(!boutonlibre && buttonsJ1.contains(button)) {
                    button.setGraphic(null);
                    buttonsJ1.remove(button);
                    resetButtonColorsForMovedButton(button);
                    currentPlayer = currentPlayer == 1 ? 2 : 1;
                    isRemovePieceMode = false;
                    boutonlibre = false;
                }
            }
        }
    }







    // Méthode pour vérifier si deux boutons sont voisins
    public static boolean isNeighbourButton(Button button1, Button button2) {
        String id1 = button1.getId();
        String id2 = button2.getId();

        // Vérifier si les boutons sont dans la liste des voisins
        for (String[] neighbours : neighbourList) {
            if ((neighbours[0].equals(id1) && neighbours[1].equals(id2)) ||
                    (neighbours[0].equals(id2) && neighbours[1].equals(id1))) {
                return true;
            }
        }

        return false;
    }

    // Méthode pour récupérer un bouton à partir de son identifiant
    private Button getButtonById(String buttonId) {
        ObservableList<Node> children = gridPane.getChildren();
        for (Node node : children) {
            if (node instanceof Button) {
                Button button = (Button) node;
                if (button.getId().equals(buttonId)) {
                    return button;
                }
            }
        }
        return null;
    }

    // Méthode pour vérifier les combinaisons de boutons et changer leur couleur si une combinaison est trouvée
    private void checkButtonCombinations() {
        String[][] buttonCombinations = {{"A", "B", "C"}, {"D", "E", "F"}, {"G", "H", "I"}, {"J", "K", "L"}, {"M", "N", "O"}, {"P", "Q", "R"}, {"S", "T", "U"}, {"V", "W", "X"}, {"A", "J", "V"}, {"D", "K", "S"}, {"G", "L", "P"}, {"B", "E", "H"}, {"Q", "T", "W"}, {"I", "M", "R"}, {"F", "N", "U"}, {"C", "O", "X"}};
        for (String[] combination : buttonCombinations) {
            if (checkAndChangeButtonColor(combination[0], combination[1], combination[2])) {
                // Activer le mode de suppression de pion pour le joueur actuel uniquement si un nouvel alignement est formé
                if (isNewAlignment) {
                    isRemovePieceMode = true;
                }
            }
        }
    }





    // Méthode pour vérifier les combinaisons de boutons et changer leur couleur si une combinaison est trouvée
    private boolean checkAndChangeButtonColor(String buttonId1, String buttonId2, String buttonId3) {
        Button button1 = getButtonById(buttonId1);
        Button button2 = getButtonById(buttonId2);
        Button button3 = getButtonById(buttonId3);

        if (button1.getGraphic() != null && button2.getGraphic() != null && button3.getGraphic() != null) {
            if (((ImageView) button1.getGraphic()).getImage().getUrl().equals(((ImageView) button2.getGraphic()).getImage().getUrl()) &&
                    ((ImageView) button2.getGraphic()).getImage().getUrl().equals(((ImageView) button3.getGraphic()).getImage().getUrl())) {
                if (button1.getStyle().contains("-fx-background-radius: 50%; -fx-min-width: 50px; -fx-min-height: 50px; -fx-max-width: 50px; -fx-max-height: 50px; -fx-background-color: green; -fx-border-color: transparent") && button2.getStyle().contains("-fx-background-radius: 50%; -fx-min-width: 50px; -fx-min-height: 50px; -fx-max-width: 50px; -fx-max-height: 50px; -fx-background-color: green; -fx-border-color: transparent") && button3.getStyle().contains("-fx-background-radius: 50%; -fx-min-width: 50px; -fx-min-height: 50px; -fx-max-width: 50px; -fx-max-height: 50px; -fx-background-color: green; -fx-border-color: transparent")){
                    // Si l'alignement n'était pas déjà formé lors du dernier tour, mettre isNewAlignment à true
                    isNewAlignment = false;
                } else {
                    isNewAlignment = true;
                }
                // Si les trois boutons forment une ligne, changer leur couleur de fond
                button1.setStyle("-fx-background-radius: 50%; -fx-min-width: 50px; -fx-min-height: 50px; -fx-max-width: 50px; -fx-max-height: 50px; -fx-background-color: green; -fx-border-color: transparent");
                button2.setStyle("-fx-background-radius: 50%; -fx-min-width: 50px; -fx-min-height: 50px; -fx-max-width: 50px; -fx-max-height: 50px; -fx-background-color: green; -fx-border-color: transparent");
                button3.setStyle("-fx-background-radius: 50%; -fx-min-width: 50px; -fx-min-height: 50px; -fx-max-width: 50px; -fx-max-height: 50px; -fx-background-color: green; -fx-border-color: transparent");

                // Renvoyer vrai pour indiquer qu'une rangée de 3 pions a été trouvée
                return true;
            }
        }
        return false;
    }

    // Méthode pour réinitialiser la couleur des boutons de la ligne où le dernier bouton a été déplacé
    private void resetButtonColorsForMovedButton(Button movedButton) {
        // Obtenir les coordonnées du dernier bouton déplacé
        int rowIndex = GridPane.getRowIndex(movedButton);
        int colIndex = GridPane.getColumnIndex(movedButton);

        resetButtonColor(movedButton);

        // Parcourir les boutons dans la même ligne que le bouton déplacé
        ObservableList<Node> children = gridPane.getChildren();
        for (Node node : children) {
            if (node instanceof Button) {
                Button button = (Button) node;
                int buttonRowIndex = GridPane.getRowIndex(button);
                int buttonColIndex = GridPane.getColumnIndex(button);
                // Si le bouton est dans la même ligne que le bouton déplacé et n'est pas le bouton déplacé lui-même, réinitialiser sa couleur
                if (buttonRowIndex == rowIndex && buttonColIndex != colIndex) {
                    resetButtonColor(button);
                }
            }
        }
    }





    // Méthode pour créer et styliser les boutons
    private Button createStyledButton(String label) {
        Button button = new Button(label);
        button.setId(label);
        button.setPrefSize(50, 50); // Taille préférée des boutons
        button.setStyle("-fx-background-radius: 50%; " + // Rendre les coins ronds
                "-fx-min-width: 50px; " + // Définir la largeur
                "-fx-min-height: 50px; " + // Définir la hauteur
                "-fx-max-width: 50px; " + // Limiter la largeur
                "-fx-max-height: 50px;" +
                "-fx-background-color: transparent; -fx-border-color: transparent;"); // Fond transparent
        return button;
    }

    // Méthode pour réinitialiser la couleur d'un bouton
    private void resetButtonColor(Button button) {
        button.setStyle("-fx-background-radius: 50%; " +
                "-fx-min-width: 50px; " +
                "-fx-min-height: 50px; " +
                "-fx-max-width: 50px; " +
                "-fx-max-height: 50px;" +
                "-fx-background-color: transparent; -fx-border-color: transparent;");
    }

    // Méthode pour changer le style d'un bouton sélectionné
    public static void selectButton(Button button) {
        if(button.getStyle().contains("-fx-background-radius: 50%; -fx-min-width: 50px; -fx-min-height: 50px; -fx-max-width: 50px; -fx-max-height: 50px; -fx-background-color: green; -fx-border-color: transparent")){
            button.setStyle("-fx-background-radius: 50%; -fx-min-width: 50px; -fx-min-height: 50px; -fx-max-width: 50px; -fx-max-height: 50px; -fx-background-color: #80DD1AFF; -fx-border-color: transparent");
        }
        else {
            button.setStyle("-fx-background-radius: 50%; -fx-min-width: 50px; -fx-min-height: 50px; -fx-max-width: 50px; -fx-max-height: 50px; -fx-background-color: yellow; -fx-border-color: transparent");
        }

    }

    // Méthode pour changer le style d'un bouton désélectionné
    public static void deselectButton(Button button) {
        if(button.getStyle().contains("-fx-background-radius: 50%; -fx-min-width: 50px; -fx-min-height: 50px; -fx-max-width: 50px; -fx-max-height: 50px; -fx-background-color: #80DD1AFF; -fx-border-color: transparent")){
            button.setStyle("-fx-background-radius: 50%; -fx-min-width: 50px; -fx-min-height: 50px; -fx-max-width: 50px; -fx-max-height: 50px; -fx-background-color: green; -fx-border-color: transparent");
        }
        else{
            button.setStyle("-fx-background-radius: 50%; -fx-min-width: 50px; -fx-min-height: 50px; -fx-max-width: 50px; -fx-max-height: 50px; -fx-background-color: transparent; -fx-border-color: transparent"); // Bordure transparente
        }
    }
}*/