package org.example.milleniumproject.model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class Party extends StackPane {

    public Party(Stage primaryStage) {
        // Création du fond d'écran
        BG ground = new BG("src/main/resources/BackgroundTestBoutons.png");
        setBackground(ground.getCustomBackground());

        // Création du GridPane
        GridPane gridPane = new GridPane();
        gridPane.setHgap(27); // Espacement horizontal entre les boutons
        gridPane.setVgap(26); // Espacement vertical entre les boutons

        // Positionnement du GridPane au centre de la StackPane
        gridPane.setAlignment(Pos.CENTER);

        // Ajout des boutons au GridPane avec leurs positions
        String[] buttonLabels = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X"};
        int[] rowIndices = {0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 6};
        int[] colIndices = {0, 3, 6, 1, 3, 5, 2, 3, 4, 0, 1, 2, 4, 5, 6, 2, 3, 4, 1, 3, 5, 0, 3, 6, 6};

        for (int i = 0; i < buttonLabels.length; i++) {
            Button button = createStyledButton(buttonLabels[i]);
            gridPane.add(button, colIndices[i], rowIndices[i]);
        }

        // Ajout des lignes entre certains boutons
        addLineBetweenButtons(gridPane, colIndices, rowIndices, "A", "B");
        addLineBetweenButtons(gridPane, colIndices, rowIndices, "B", "C");
        addLineBetweenButtons(gridPane, colIndices, rowIndices, "D", "E");
        addLineBetweenButtons(gridPane, colIndices, rowIndices, "E", "F");
        addLineBetweenButtons(gridPane, colIndices, rowIndices, "G", "H");
        addLineBetweenButtons(gridPane, colIndices, rowIndices, "H", "I");
        addLineBetweenButtons(gridPane, colIndices, rowIndices, "J", "K");
        addLineBetweenButtons(gridPane, colIndices, rowIndices, "K", "L");
        addLineBetweenButtons(gridPane, colIndices, rowIndices, "M", "N");
        addLineBetweenButtons(gridPane, colIndices, rowIndices, "N", "O");
        addLineBetweenButtons(gridPane, colIndices, rowIndices, "P", "Q");
        addLineBetweenButtons(gridPane, colIndices, rowIndices, "Q", "R");
        addLineBetweenButtons(gridPane, colIndices, rowIndices, "S", "T");
        addLineBetweenButtons(gridPane, colIndices, rowIndices, "T", "U");
        addLineBetweenButtons(gridPane, colIndices, rowIndices, "V", "W");
        addLineBetweenButtons(gridPane, colIndices, rowIndices, "W", "X");
        addLineBetweenButtons(gridPane, colIndices, rowIndices, "A", "J");
        addLineBetweenButtons(gridPane, colIndices, rowIndices, "J", "V");
        addLineBetweenButtons(gridPane, colIndices, rowIndices, "D", "K");
        addLineBetweenButtons(gridPane, colIndices, rowIndices, "K", "S");
        addLineBetweenButtons(gridPane, colIndices, rowIndices, "G", "L");
        addLineBetweenButtons(gridPane, colIndices, rowIndices, "L", "P");
        addLineBetweenButtons(gridPane, colIndices, rowIndices, "B", "E");
        addLineBetweenButtons(gridPane, colIndices, rowIndices, "E", "H");
        addLineBetweenButtons(gridPane, colIndices, rowIndices, "Q", "T");
        addLineBetweenButtons(gridPane, colIndices, rowIndices, "T", "W");
        addLineBetweenButtons(gridPane, colIndices, rowIndices, "C", "M");
        addLineBetweenButtons(gridPane, colIndices, rowIndices, "M", "R");
        addLineBetweenButtons(gridPane, colIndices, rowIndices, "F", "N");
        addLineBetweenButtons(gridPane, colIndices, rowIndices, "N", "U");
        addLineBetweenButtons(gridPane, colIndices, rowIndices, "O", "C");
        addLineBetweenButtons(gridPane, colIndices, rowIndices, "O", "X");

        // Ajout des images des vaisseaux à gauche et à droite du GridPane
        VBox leftVBox = createVBoxWithImages("src/main/resources/Pion/PionTfighter.png", 9);
        VBox rightVBox = createVBoxWithImages("src/main/resources/Pion/PionXwing.png", 9);

        // Positionnement des VBox à gauche et à droite du GridPane
        StackPane.setAlignment(leftVBox, Pos.CENTER_LEFT);
        StackPane.setAlignment(rightVBox, Pos.CENTER_RIGHT);

        // Ajout des VBox à la StackPane
        getChildren().addAll(gridPane, leftVBox, rightVBox);
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-radius: 50px; " +
                "-fx-min-width: 45px; " +
                "-fx-min-height: 45px; " +
                "-fx-max-width: 45px; " +
                "-fx-max-height: 45px;");
        return button;
    }

    private VBox createVBoxWithImages(String imageLink, int count) {
        VBox vBox = new VBox(10); // Espacement vertical entre les images
        vBox.setPadding(new Insets(10)); // Espacement autour des images
        vBox.setAlignment(Pos.CENTER); // Centrer les images dans la VBox

        List<ImageView> imageViews = new ArrayList<>();

        Image image = new Image(imageLink);
        for (int i = 0; i < count; i++) {
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(50); // Largeur de l'image réduite
            imageView.setFitHeight(50); // Hauteur de l'image réduite
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

    private void addLineBetweenButtons(GridPane gridPane, int[] colIndices, int[] rowIndices, String buttonText1, String buttonText2) {
        int index1 = -1;
        int index2 = -1;
        for (int i = 0; i < colIndices.length; i++) {
            if (buttonText1.equals(gridPane.getChildren().get(i).toString())) {
                index1 = i;
            }
            if (buttonText2.equals(gridPane.getChildren().get(i).toString())) {
                index2 = i;
            }
        }
        if (index1 != -1 && index2 != -1) {
            Line line = new Line();
            line.setStartX(0);
            line.setStartY(0);
            line.setEndX(80);
            line.setEndY(0);
            gridPane.add(line, colIndices[index1] + 1, rowIndices[index1]);
        }
    }
}