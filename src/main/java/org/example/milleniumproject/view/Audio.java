package org.example.milleniumproject.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.milleniumproject.model.AudioData;
import org.example.milleniumproject.presentation.BG;
import org.example.milleniumproject.model.BackButtons;
import org.example.milleniumproject.model.MusicPlayer;
import org.example.milleniumproject.model.SoundPlayer;
import javafx.scene.Node;

/**
 * La classe Audio représente la vue de la page de configuration audio de l'application.
 * Elle permet à l'utilisateur de régler les volumes de la musique et des effets sonores.
 */
public class Audio extends StackPane {

    private SliderWithControls sliderWithControls2;
    private SliderWithControls sliderWithControls3;

    /**
     * Constructeur de la classe Audio.
     *
     * @param primaryStage La fenêtre principale de l'application.
     */
    public Audio(Stage primaryStage) {
        // Créer un fond d'écran
        BG ground = new BG("src/main/resources/BGAUDIO.png");
        setBackground(ground.getCustomBackground());

        // Créer un bouton retour
        Button retourButton = BackButtons.createBackButton(primaryStage);

        // Création des labels
        Label volumeMusiqueLabel = createStyledLabel("Volume de la Musique");
        Label volumeEffetsLabel = createStyledLabel("Volume des Sons");

        VBox leftbox = new VBox(80);
        leftbox.setAlignment(Pos.CENTER_LEFT);
        leftbox.getChildren().addAll(volumeMusiqueLabel, volumeEffetsLabel);

        // Création des boutons, du slider et du label de valeur
        SliderWithControls sliderWithControls2 = new SliderWithControls();
        SliderWithControls sliderWithControls3 = new SliderWithControls();

        // Restaure le volume de la musique à partir de AudioData
        sliderWithControls2.getSlider().setValue(AudioData.getMusicVolume() * 100);

        // Ajout d'un écouteur pour détecter les changements de volume de la musique et les enregistrer dans AudioData
        sliderWithControls2.getSlider().valueProperty().addListener((observable, oldValue, newValue) -> {
            double volume = newValue.doubleValue() / 100.0; // Convertit la valeur du slider en une valeur de volume entre 0 et 1
            AudioData.setMusicVolume(volume); // Enregistre le volume de la musique dans AudioData
            MusicPlayer.setVolume(volume); // Met à jour le volume de la musique dans MusicPlayer
        });

        // Restaure le volume des sons à partir de AudioData
        sliderWithControls3.getSlider().setValue(AudioData.getSoundVolume() * 100);

        // Ajout d'un écouteur pour détecter les changements de volume des sons et les enregistrer dans AudioData
        sliderWithControls3.getSlider().valueProperty().addListener((observable, oldValue, newValue) -> {
            double volume = newValue.doubleValue() / 100.0; // Convertit la valeur du slider en une valeur de volume entre 0 et 1
            AudioData.setSoundVolume(volume); // Enregistre le volume des sons dans AudioData
        });

        // Restaure la position du slider à partir de AudioData
        sliderWithControls3.getSlider().setValue(AudioData.getSliderPosition() * 100);

        // Ajout d'un écouteur pour détecter les changements de position du slider 3 et les enregistrer dans AudioData
        sliderWithControls3.getSlider().valueProperty().addListener((observable, oldValue, newValue) -> {
            AudioData.setSliderPosition(newValue.doubleValue() / 100.0); // Enregistre la position du slider dans AudioData
        });

        // Création des HBox
        HBox hbox2 = new HBox(0, sliderWithControls2.getBtnLeft(), sliderWithControls2.getSlider(), sliderWithControls2.getBtnRight(), sliderWithControls2.getValueLabel());
        HBox hbox3 = new HBox(0, sliderWithControls3.getBtnLeft(), sliderWithControls3.getSlider(), sliderWithControls3.getBtnRight(), sliderWithControls3.getValueLabel());

        // Alignement horizontal des éléments dans les HBox
        hbox2.setAlignment(Pos.CENTER);
        hbox3.setAlignment(Pos.CENTER);

        // Stylisation des boutons "<" et ">"
        stylizeButton(sliderWithControls2.getBtnLeft());
        stylizeButton(sliderWithControls2.getBtnRight());
        stylizeButton(sliderWithControls3.getBtnLeft());
        stylizeButton(sliderWithControls3.getBtnRight());

        // Ajout d'un effet de drop shadow aux boutons
        addDropShadowEffect(sliderWithControls2.getBtnLeft());
        addDropShadowEffect(sliderWithControls2.getBtnRight());
        addDropShadowEffect(sliderWithControls3.getBtnLeft());
        addDropShadowEffect(sliderWithControls3.getBtnRight());

        // Ajout d'un effet de drop shadow aux labels
        addDropShadowEffect(volumeMusiqueLabel);
        addDropShadowEffect(volumeEffetsLabel);

        // Ajout d'un effet de drop shadow aux nombres des sliders
        addDropShadowEffect(sliderWithControls2.getSlider());
        addDropShadowEffect(sliderWithControls3.getSlider());

        // Création de la VBox et ajout des HBox
        VBox rightbox = new VBox(43, hbox2, hbox3);
        rightbox.setAlignment(Pos.CENTER_LEFT);

        HBox finalhbox = new HBox(30, leftbox, rightbox);
        setMargin(finalhbox, new Insets(130, 0, 0, 650));

        // Ajout de tous les éléments à la pile principale
        getChildren().addAll(finalhbox, retourButton);
    }

    // Méthode pour créer un label stylisé
    private static Label createStyledLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-family: Cardo; -fx-text-fill: #FFFFFFFF; -fx-font-size: 24px;");
        addDropShadowEffect(label);
        return label;
    }

    // Méthode pour styliser un bouton
    private void stylizeButton(Button button) {
        button.setStyle("-fx-font-family: Cardo; -fx-background-color: transparent; -fx-text-fill: #FFFFFFFF; -fx-font-size: 35px;");
    }

    // Méthode pour ajouter un effet de drop shadow à un bouton
    private static void addDropShadowEffect(Node node) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(3);
        dropShadow.setOffsetX(3);
        dropShadow.setOffsetY(3);
        node.setEffect(dropShadow);
    }

    // Classe interne pour gérer le slider avec les boutons et le label de valeur
    public static class SliderWithControls {
        private Slider slider;
        private Button btnLeft;
        private Button btnRight;
        private Label valueLabel;

        public SliderWithControls() {
            slider = new Slider(0, 100, 50);
            btnLeft = new Button("<");
            btnRight = new Button(">");
            valueLabel = createStyledLabel("50");

            // Fixer la largeur préférée du label de valeur
            valueLabel.setPrefWidth(40);

            // Ajout des écouteurs d'événements sur les boutons
            btnLeft.setOnAction(event -> {
                SoundPlayer.soundPlay();
                slider.setValue(slider.getValue() - 1);
            });
            btnRight.setOnAction(event -> {
                SoundPlayer.soundPlay();
                slider.setValue(slider.getValue() + 1);
            });

            // Met à jour le label de valeur lorsque la valeur du slider change
            slider.valueProperty().addListener((observable, oldValue, newValue) -> valueLabel.setText(String.valueOf(newValue.intValue())));
        }

        public Slider getSlider() {
            return slider;
        }

        public Button getBtnLeft() {
            return btnLeft;
        }

        public Button getBtnRight() {
            return btnRight;
        }

        public Label getValueLabel() {
            return valueLabel;
        }
    }
}