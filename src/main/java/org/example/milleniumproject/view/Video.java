package org.example.milleniumproject.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.milleniumproject.model.VideoData;
import org.example.milleniumproject.presentation.BackGround;
import org.example.milleniumproject.model.BackButtons;
import java.util.prefs.Preferences;
import static org.example.milleniumproject.model.Constant.screenHeight;
import static org.example.milleniumproject.model.Constant.screenWidth;

/**
 * La classe Video permet d'afficher les paramètres vidéos.
 * Cela permet au joueur d'activer ou de désactiver les vidéos de pré-lancement et les animations.
 */
public class Video extends StackPane {

    private Preferences preferences;

    /**
     * Crée la l'affichage des paramètres vidéos.
     *
     * @param primaryStage La scène en premier plan.
     */
    public Video(Stage primaryStage) {
        preferences = Preferences.userNodeForPackage(this.getClass());

        BackGround ground = new BackGround("src/main/resources/BGVIDEO.png");
        setBackground(ground.getCustomBackground());

        Button retourButton = BackButtons.createBackButton(primaryStage);
        setAlignment(retourButton, Pos.TOP_RIGHT);
        setMargin(retourButton, new Insets(0.0208 * screenHeight, 0.0117 * screenWidth, 0, 0));

        CheckBox videoCheckBox = new CheckBox("Activer vidéos de pré-lancement");
        CheckBox animationCheckBox = new CheckBox("Activer animations");

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(3);
        dropShadow.setOffsetX(2);
        dropShadow.setOffsetY(2);
        videoCheckBox.setEffect(dropShadow);
        animationCheckBox.setEffect(dropShadow);

        VBox choicesBox = new VBox(0.069444*screenHeight);
        choicesBox.getChildren().addAll(videoCheckBox, animationCheckBox);

        choicesBox.setTranslateX(0.1*screenWidth);
        choicesBox.setTranslateY(0.35*screenHeight);

        setMargin(choicesBox, new Insets(0.097222*screenHeight, 0, 0, 0));

        getChildren().addAll(choicesBox, retourButton);

        String checkBoxStyle = "-fx-text-fill: white; -fx-font-family: Cardo; -fx-font-weight: bold; -fx-font-size: 35px;";

        videoCheckBox.setStyle(checkBoxStyle);
        animationCheckBox.setStyle(checkBoxStyle);

        videoCheckBox.setSelected(VideoData.isVideoChoose());
        animationCheckBox.setSelected(VideoData.isAnimation());

        videoCheckBox.setOnAction(event -> {
            VideoData.setVideoChoose(videoCheckBox.isSelected());
        });

        animationCheckBox.setOnAction(event -> {
            VideoData.setAnimation(animationCheckBox.isSelected());
        });
    }
}