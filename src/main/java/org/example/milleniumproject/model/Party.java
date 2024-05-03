package org.example.milleniumproject.model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

    public Party(Stage primaryStage){
        // Création du fond d'écran
        BG ground = new BG("src/main/resources/BackgroundTestBoutons.png");
        setBackground(ground.getCustomBackground());

        // Création du GridPane
        GridPane gridPane = new GridPane();
<<<<<<< Updated upstream
        gridPane.setHgap(60); // Espacement horizontal entre les boutons
        gridPane.setVgap(60);
        gridPane.setAlignment(Pos.CENTER);// Espacement vertical entre les boutons
=======
        gridPane.setHgap(27); // Espacement horizontal entre les boutons
        gridPane.setVgap(26); // Espacement vertical entre les boutons
>>>>>>> Stashed changes

        // Ajout des boutons au GridPane avec leurs positions
        String[] buttonLabels = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X"};
        int[] rowIndices = {0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 6};
        int[] colIndices = {0, 3, 6, 1, 3, 5, 2, 3, 4, 0, 1, 2, 4, 5, 6, 2, 3, 4, 1, 3, 5, 0, 3, 6, 6};

        for (int i = 0; i < buttonLabels.length; i++) {
            Button button = createStyledButton(buttonLabels[i]);
            gridPane.add(button, colIndices[i], rowIndices[i]);
        }

        String str = ProfileData.getShip(1);
        int lastIndex = str.lastIndexOf('/');
        String vaisseau1 = str.substring(lastIndex + 1);

        String str2 = ProfileData.getShip(2);
        int lastIndex2 = str2.lastIndexOf('/');
        String vaisseau2 = str2.substring(lastIndex2 + 1);

        // Ajout des images des vaisseaux à gauche et à droite du GridPane
        VBox leftVBox = createVBoxWithImages(vaisseau1, 9);
        VBox rightVBox = createVBoxWithImages(vaisseau2, 9);

        HBox hBox = new HBox(450); // Espacement horizontal entre les Vbox
        hBox.getChildren().addAll(leftVBox, rightVBox);
        hBox.setAlignment(Pos.CENTER);

        // Ajout des VBox à la StackPane
<<<<<<< Updated upstream
        getChildren().addAll(leftVBox, rightVBox);

        // Ajout du GridPane à la StackPane et le centrer
        getChildren().add(gridPane);
        setAlignment(gridPane, Pos.CENTER);
=======
        getChildren().addAll(hBox, gridPane);
>>>>>>> Stashed changes
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

        // Positionnement du GridPane au centre de la StackPane
        setAlignment(gridPane, Pos.CENTER);

        vBox.getChildren().add(gridPane);

        return vBox;
    }
}