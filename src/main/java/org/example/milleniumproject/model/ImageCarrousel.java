package org.example.milleniumproject.model;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.example.milleniumproject.view.Profil;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ImageCarrousel extends StackPane {
    private ImageView imageView;
    private int currentIndex;
    private String[] imagePaths;

    public ImageCarrousel(String[] imagePaths) {
        this.imagePaths = imagePaths;

        imageView = new ImageView();
        imageView.setFitWidth(Profil.AVATAR_SIZE);
        imageView.setPreserveRatio(true);

        Button previousButton = new Button("<");
        previousButton.setOnAction(event -> showPreviousImage());
        Button nextButton = new Button(">");
        nextButton.setOnAction(event -> showNextImage());

        nextButton.setPrefSize(50, 50); // Taille préférée des boutons (largeur x hauteur)
        nextButton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(5), Insets.EMPTY)));
        nextButton.setTextFill(Color.WHITE); // Couleur du texte
        nextButton.setFont(Font.font("Cardo", FontWeight.BOLD, 50)); // Police et taille du texte

        previousButton.setPrefSize(50, 50); // Taille préférée des boutons (largeur x hauteur)
        previousButton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(5), Insets.EMPTY)));
        previousButton.setTextFill(Color.WHITE); // Couleur du texte
        previousButton.setFont(Font.font("Cardo", FontWeight.BOLD, 50)); // Police et taille du texte

        GridPane.setConstraints(previousButton, 0, 0);
        GridPane.setConstraints(nextButton, 2, 0);
        GridPane.setHalignment(previousButton, HPos.CENTER);
        GridPane.setHalignment(nextButton, HPos.CENTER);

        GridPane buttonsGrid = new GridPane();
        buttonsGrid.setHgap(45);
        buttonsGrid.setAlignment(Pos.CENTER);
        buttonsGrid.getChildren().addAll(previousButton, nextButton);

        getChildren().addAll(imageView, buttonsGrid);

        // Charger la première image dès la création du Carrousel
        loadImage(0);
    }

    private void loadImage(int index) {
        try {
            Image image = new Image(new FileInputStream(imagePaths[index]));
            imageView.setImage(image);
            currentIndex = index; // Mettre à jour l'index courant
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void showNextImage() {
        currentIndex = (currentIndex + 1) % imagePaths.length;
        loadImage(currentIndex);
    }

    private void showPreviousImage() {
        currentIndex = (currentIndex - 1 + imagePaths.length) % imagePaths.length;
        loadImage(currentIndex);
    }
}