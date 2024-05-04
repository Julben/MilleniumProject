package org.example.milleniumproject.model;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Party extends StackPane {
    // Déclarations des variables d'instance
    private int currentPlayer = 1;
    private VBox leftVBox;
    private VBox rightVBox;
    private int turns = 0;
    private int currentImageIndex = 0;

    public Party(Stage primaryStage) {
        // Création du fond d'écran
        BG ground = new BG("src/main/resources/FENABOO.png");
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

        getChildren().addAll(hBox, gridPane);

        // Gestionnaire d'événements pour les boutons du GridPane
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                button.setOnAction(e -> handleButtonClick(button));
            }
        }
    }

    // Méthode pour gérer les clics sur les boutons du GridPane
    private void handleButtonClick(Button button) {
        // Vérifier si le bouton n'a pas déjà d'image et si tous les tours n'ont pas été joués
        if (button.getGraphic() == null && turns < 9) {
            // Vérifier le joueur actuel
            if (currentPlayer == 1) {
                // Placez l'image du joueur 1 sur le bouton
                placePlayerImage(button, leftVBox);
                // Passez au tour du joueur 2
                currentPlayer = 2;
            } else {
                // Placez l'image du joueur 2 sur le bouton
                placePlayerImage(button, rightVBox);
                // Passez au tour du joueur 1
                currentPlayer = 1;
                // Incrémenter le nombre de tours
                turns++;
            }
            // Ajouter la vérification des boutons alignés après chaque tour
            highlightAlignedButtons((GridPane) ((StackPane) button.getParent().getParent()).getChildren().get(1));
        }
    }

    // Méthode pour placer l'image du joueur sur un bouton
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


    // Méthode pour créer un bouton stylisé
    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-radius: 50%; " + // Rendre les coins ronds
                "-fx-min-width: 65px; " + // Définir la largeur
                "-fx-min-height: 65px; " + // Définir la hauteur
                "-fx-max-width: 65px; " + // Limiter la largeur
                "-fx-max-height: 65px;"); // Limiter la hauteur
        return button;
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
}