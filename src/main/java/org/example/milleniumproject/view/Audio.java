package org.example.milleniumproject.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.example.milleniumproject.model.BG;
import org.example.milleniumproject.model.BackButtons;

public class Audio extends StackPane {

    public Audio(Stage primaryStage) {
        BG ground = new BG("src/main/resources/BGAUDIO.png");
        setBackground(ground.getCustomBackground());

        // Créer un bouton retour et l'ajouter à la pile
        Button retourButton = BackButtons.createBackButton(primaryStage);

        // Création des sliders pour les volumes
        Slider principalSlider = createVolumeSlider();
        Slider musicSlider = createVolumeSlider();
        Slider effectsSlider = createVolumeSlider();

        // Agrandir la taille de sliders
        principalSlider.setPrefWidth(250);
        musicSlider.setPrefWidth(250);
        effectsSlider.setPrefWidth(250);

        // Création de la VBox pour les sliders et boutons
        VBox slidersVBox = new VBox(60);
        slidersVBox.setAlignment(Pos.CENTER_LEFT);
        slidersVBox.getChildren().addAll(
                createVolumeBox(principalSlider, "Volume Principal"),
                createVolumeBox(musicSlider, "Volume de la Musique"),
                createVolumeBox(effectsSlider, "Volume des Effets")
        );

        // Création de la HBox pour contenir les VBox
        HBox hbox = new HBox(0);
        hbox.setAlignment(Pos.CENTER);
        //HBox.setMargin(labelsVBox, new Insets(150, 0, 0, 100)); // Marge de 50 pixels à gauche
        HBox.setMargin(slidersVBox, new Insets(150, 0, 0, 0)); // Marge de 50 pixels à gauche

        hbox.getChildren().addAll(slidersVBox);

        // Ajout de tous les éléments à la pile principale
        getChildren().addAll(hbox, retourButton);
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("Cardo", 24));
        return label;
    }

    private Slider createVolumeSlider() {
        Slider slider = new Slider(0, 100, 80);
        slider.setShowTickLabels(false);
        slider.setShowTickMarks(false);
        slider.setBlockIncrement(1);

        return slider;
    }

    private VBox createVolumeBox(Slider slider, String label) {
        Button decreaseButton = new Button("<");
        decreaseButton.setBackground(Background.EMPTY);
        decreaseButton.setTextFill(Color.WHITE);
        decreaseButton.setFont(Font.font(25)); // Taille de police plus grande
        decreaseButton.setOnAction(event -> slider.setValue(slider.getValue() - 10));

        Button increaseButton = new Button(">");
        increaseButton.setBackground(Background.EMPTY);
        increaseButton.setTextFill(Color.WHITE);
        increaseButton.setFont(Font.font(25)); // Taille de police plus grande
        increaseButton.setOnAction(event -> slider.setValue(slider.getValue() + 10));

        HBox adjustmentBox = new HBox(5);
        adjustmentBox.getChildren().addAll(decreaseButton, slider, increaseButton);
        adjustmentBox.setAlignment(Pos.CENTER_LEFT); // Aligner les éléments à gauche

        // Créer un BorderPane pour entourer la paire de label et de slider
        BorderPane borderPane = new BorderPane();
        Label labelNode = createLabel(label);
        BorderPane.setAlignment(labelNode, Pos.CENTER); // Centrer le label horizontalement
        borderPane.setLeft(labelNode);
        borderPane.setCenter(adjustmentBox);
        borderPane.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, new CornerRadii(10), Insets.EMPTY)));

        VBox volumeBox = new VBox(20);
        volumeBox.setAlignment(Pos.CENTER_LEFT); // Aligner les VBox à gauche
        volumeBox.getChildren().addAll(borderPane);
        return volumeBox;
    }
}
