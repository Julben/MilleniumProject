package org.example.milleniumproject.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.example.milleniumproject.model.AudioData;
import org.example.milleniumproject.presentation.BackGround;
import org.example.milleniumproject.model.BackButtons;
import org.example.milleniumproject.model.MusicPlayer;
import org.example.milleniumproject.model.SoundPlayer;
import javafx.scene.Node;

import static org.example.milleniumproject.model.Constant.screenHeight;
import static org.example.milleniumproject.model.Constant.screenWidth;

/**
 * La classe Audio permet de gèrer tout les sons du jeu.
 * Elle permet au joueur de modifier le volume de la musique et des effets sonores.
 */
public class Audio extends StackPane {

    private SliderWithControls sliderWithControls2;
    private SliderWithControls sliderWithControls3;

    /**
     * Crée la classe Audio.
     *
     * @param primaryStage La scène en premier plan.
     */
    public Audio(Stage primaryStage) {
        BackGround ground = new BackGround("src/main/resources/BGAUDIO.png");
        setBackground(ground.getCustomBackground());

        Button retourButton = BackButtons.createBackButton(primaryStage);

        Label volumeMusiqueLabel = createStyledLabel("Volume de la Musique");
        Label volumeEffetsLabel = createStyledLabel("Volume des Sons");

        VBox leftbox = new VBox(0.111111*screenHeight);
        leftbox.setAlignment(Pos.CENTER_LEFT);
        leftbox.getChildren().addAll(volumeMusiqueLabel, volumeEffetsLabel);

        SliderWithControls sliderWithControls2 = new SliderWithControls();
        SliderWithControls sliderWithControls3 = new SliderWithControls();

        sliderWithControls2.getSlider().setValue(AudioData.getMusicVolume() * 100);

        sliderWithControls2.getSlider().valueProperty().addListener((observable, oldValue, newValue) -> {
            double volume = newValue.doubleValue() / 100.0;
            AudioData.setMusicVolume(volume);
            MusicPlayer.setVolume(volume);
        });

        sliderWithControls3.getSlider().setValue(AudioData.getSoundVolume() * 100);

        sliderWithControls3.getSlider().valueProperty().addListener((observable, oldValue, newValue) -> {
            double volume = newValue.doubleValue() / 100.0;
            AudioData.setSoundVolume(volume);
        });

        sliderWithControls3.getSlider().setValue(AudioData.getSliderPosition() * 100);

        sliderWithControls3.getSlider().valueProperty().addListener((observable, oldValue, newValue) -> {
            AudioData.setSliderPosition(newValue.doubleValue() / 100.0);
        });

        HBox hbox2 = new HBox(0, sliderWithControls2.getBtnLeft(), sliderWithControls2.getSlider(), sliderWithControls2.getBtnRight(), sliderWithControls2.getValueLabel());
        HBox hbox3 = new HBox(0, sliderWithControls3.getBtnLeft(), sliderWithControls3.getSlider(), sliderWithControls3.getBtnRight(), sliderWithControls3.getValueLabel());

        hbox2.setAlignment(Pos.CENTER);
        hbox3.setAlignment(Pos.CENTER);

        stylizeButton(sliderWithControls2.getBtnLeft());
        stylizeButton(sliderWithControls2.getBtnRight());
        stylizeButton(sliderWithControls3.getBtnLeft());
        stylizeButton(sliderWithControls3.getBtnRight());

        addDropShadowEffect(sliderWithControls2.getBtnLeft());
        addDropShadowEffect(sliderWithControls2.getBtnRight());
        addDropShadowEffect(sliderWithControls3.getBtnLeft());
        addDropShadowEffect(sliderWithControls3.getBtnRight());

        addDropShadowEffect(volumeMusiqueLabel);
        addDropShadowEffect(volumeEffetsLabel);

        addDropShadowEffect(sliderWithControls2.getSlider());
        addDropShadowEffect(sliderWithControls3.getSlider());

        VBox rightbox = new VBox(0.0597*screenHeight, hbox2, hbox3);
        rightbox.setAlignment(Pos.CENTER_LEFT);

        HBox finalhbox = new HBox(0.023437*screenWidth, leftbox, rightbox);
        setMargin(finalhbox, new Insets(0.18055*screenHeight, 0, 0, 0.5078*screenWidth));

        getChildren().addAll(finalhbox, retourButton);
    }
    /**
     * Crée un label stylisé.
     *
     * @param text Le texte du label.
     * @return Le label.
     */
    private static Label createStyledLabel(String text) {
        Label label = new Label(text);
        label.setFont(Font.font("Cardo", FontWeight.BOLD, 0.034722*screenHeight));
        label.setStyle("-fx-text-fill: #FFFFFFFF;");
        addDropShadowEffect(label);
        return label;
    }
    /**
     * Applique un style aux boutons.
     *
     * @param button Le bouton à styliser.
     */
    private void stylizeButton(Button button) {
        button.setFont(Font.font("Cardo", FontWeight.BOLD, 0.034722*screenHeight));
        button.setStyle("-fx-background-color: transparent; -fx-text-fill: #FFFFFFFF;");
    }
    /**
     * Ajoute un effet d'ombre.
     *
     * @param node Le nœud auquel appliquer l'effet d'ombre.
     */
    private static void addDropShadowEffect(Node node) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(3);
        dropShadow.setOffsetX(3);
        dropShadow.setOffsetY(3);
        node.setEffect(dropShadow);
    }
    /**
     * Crée la classe SliderWithControls pour avoir des sliders.
     */
    public static class SliderWithControls {
        private Slider slider;
        private Button btnLeft;
        private Button btnRight;
        private Label valueLabel;

        /**
         * Constructeur de la classe SliderWithControls.
         * Permet de modifier les différents volumes
         */
        public SliderWithControls() {
            slider = new Slider(0, 100, 50);
            btnLeft = new Button("<");
            btnRight = new Button(">");
            valueLabel = createStyledLabel("50");

            valueLabel.setPrefWidth(0.0328125*screenWidth);

            btnLeft.setOnAction(event -> {
                SoundPlayer.soundPlay();
                slider.setValue(slider.getValue() - 1);
            });
            btnRight.setOnAction(event -> {
                SoundPlayer.soundPlay();
                slider.setValue(slider.getValue() + 1);
            });

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