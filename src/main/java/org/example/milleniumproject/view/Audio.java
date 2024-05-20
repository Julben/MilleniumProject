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
        BG ground = new BG("src/main/resources/BGAUDIO.png");
        setBackground(ground.getCustomBackground());

        Button retourButton = BackButtons.createBackButton(primaryStage);

        Label volumeMusiqueLabel = createStyledLabel("Volume de la Musique");
        Label volumeEffetsLabel = createStyledLabel("Volume des Sons");

        VBox leftbox = new VBox(80);
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

        VBox rightbox = new VBox(43, hbox2, hbox3);
        rightbox.setAlignment(Pos.CENTER_LEFT);

        HBox finalhbox = new HBox(30, leftbox, rightbox);
        setMargin(finalhbox, new Insets(130, 0, 0, 650));

        getChildren().addAll(finalhbox, retourButton);
    }

    private static Label createStyledLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-family: Cardo; -fx-text-fill: #FFFFFFFF; -fx-font-size: 24px;");
        addDropShadowEffect(label);
        return label;
    }

    private void stylizeButton(Button button) {
        button.setStyle("-fx-font-family: Cardo; -fx-background-color: transparent; -fx-text-fill: #FFFFFFFF; -fx-font-size: 35px;");
    }

    private static void addDropShadowEffect(Node node) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(3);
        dropShadow.setOffsetX(3);
        dropShadow.setOffsetY(3);
        node.setEffect(dropShadow);
    }

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

            valueLabel.setPrefWidth(40);

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