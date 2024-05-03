package org.example.milleniumproject.model;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LabelCarrousel extends StackPane {
    private Label label;
    private int currentIndex;
    private String[] labelContents;

    public LabelCarrousel(String[] labelContents) {
        this.labelContents = labelContents;

        label = new Label();
        label.setTextFill(Color.WHITE); // Couleur du texte
        label.setFont(Font.font("Cardo", FontWeight.BOLD, 30)); // Police et taille du texte

        Button previousButton = new Button("<");
        previousButton.setOnAction(event -> showPreviousLabel());
        Button nextButton = new Button(">");
        nextButton.setOnAction(event -> showNextLabel());

        nextButton.setPrefSize(20, 20); // Taille préférée des boutons (largeur x hauteur)
        nextButton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(5), null)));
        nextButton.setTextFill(Color.WHITE); // Couleur du texte
        nextButton.setFont(Font.font("Cardo", FontWeight.BOLD, 50)); // Police et taille du texte

        previousButton.setPrefSize(20, 20); // Taille préférée des boutons (largeur x hauteur)
        previousButton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(5), null)));
        previousButton.setTextFill(Color.WHITE); // Couleur du texte
        previousButton.setFont(Font.font("Cardo", FontWeight.BOLD, 50)); // Police et taille du texte

        HBox buttonsBox = new HBox(120, previousButton, nextButton);
        buttonsBox.setAlignment(Pos.CENTER);

        this.getChildren().addAll(label, buttonsBox);

        // Charger le premier contenu de label dès la création du Carrousel
        loadLabelContent(0);
    }

    private void loadLabelContent(int index) {
        if (index >= 0 && index < labelContents.length) {
            label.setText(labelContents[index]);
            currentIndex = index;
        }
    }

    public void showNextLabel() {
        currentIndex = (currentIndex + 1) % labelContents.length;
        loadLabelContent(currentIndex);
    }

    public void showPreviousLabel() {
        currentIndex = (currentIndex - 1 + labelContents.length) % labelContents.length;
        loadLabelContent(currentIndex);
    }
}