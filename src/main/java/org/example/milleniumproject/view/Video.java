package org.example.milleniumproject.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.milleniumproject.model.BG;
import org.example.milleniumproject.model.BackButtons;

import static org.example.milleniumproject.model.Constant.screenHeight;
import static org.example.milleniumproject.model.Constant.screenWidth;

public class Video extends StackPane {
    private final ColorAdjust colorAdjust = new ColorAdjust();
    private final Slider luminositeSlider;

    public Video(Stage primaryStage) {
        // Créer un fond d'écran
        BG ground = new BG("src/main/resources/BGVIDEO.png");
        setBackground(ground.getCustomBackground());

        // Créer un bouton retour
        Button retourButton = BackButtons.createBackButton(primaryStage);
        setAlignment(retourButton, Pos.TOP_RIGHT); // Positionner le bouton retour en haut à droite
        setMargin(retourButton, new Insets(0.0208 * screenHeight, 0.0117 * screenWidth, 0, 0));

        // Création du label
        Label luminositeLabel = createStyledLabel("Luminosité", 30);

        // Création du slider pour ajuster la luminosité
        luminositeSlider = new Slider(-1.0, 0.0, VideoData.getBrightness());
        luminositeSlider.setPrefWidth(350); // Définir une largeur préférée pour le slider
        DropShadow dropShadow = new DropShadow(3, 3, 3, null);
        luminositeSlider.setEffect(dropShadow);

        luminositeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            colorAdjust.setBrightness(newValue.doubleValue());
            VideoData.setBrightness(newValue.doubleValue()); // Sauvegarder la luminosité après modification du slider
        });

        // Création d'un HBox pour centrer le slider
        HBox hboxSlider = new HBox(luminositeSlider);
        hboxSlider.setAlignment(Pos.CENTER);

        // Création de la VBox pour organiser les éléments
        VBox vbox = new VBox(25, luminositeLabel, hboxSlider);
        vbox.setAlignment(Pos.CENTER);
        setMargin(vbox, new Insets(0, 610, 0, 0)); // Ajuster la marge de la VBox

        // Appliquer l'ajustement de couleur à la pile principale
        setEffect(colorAdjust);

        // Ajouter tous les éléments à la pile principale
        getChildren().addAll(vbox, retourButton);
    }

    // Méthode pour créer un label stylisé
    private Label createStyledLabel(String text, double fontSize) {
        Label label = new Label(text);
        label.setStyle("-fx-font-family: Cardo; -fx-text-fill: #FFFFFFFF; -fx-font-size: " + fontSize + "px;");
        label.setEffect(new DropShadow(3, 3, 3, null)); // Appliquer l'effet DropShadow
        return label;
    }
}