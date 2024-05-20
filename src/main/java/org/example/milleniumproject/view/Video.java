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
import org.example.milleniumproject.presentation.BG;
import org.example.milleniumproject.model.BackButtons;
import java.util.prefs.Preferences;
import static org.example.milleniumproject.model.Constant.screenHeight;
import static org.example.milleniumproject.model.Constant.screenWidth;


/**
 * La classe Video représente la vue de la page de configuration des vidéos et des animations.
 * Cette page permet à l'utilisateur d'activer ou de désactiver les vidéos de pré-lancement et les animations.
 */
public class Video extends StackPane {
    private Preferences preferences;

    /**
     * Constructeur de la classe Video.
     *
     * @param primaryStage La fenêtre principale de l'application.
     */
    public Video(Stage primaryStage) {
        // Initialiser les préférences
        preferences = Preferences.userNodeForPackage(this.getClass());

        // Créer un fond d'écran
        BG ground = new BG("src/main/resources/BGVIDEO.png");
        setBackground(ground.getCustomBackground());

        // Créer un bouton retour
        Button retourButton = BackButtons.createBackButton(primaryStage);
        setAlignment(retourButton, Pos.TOP_RIGHT); // Positionner le bouton retour en haut à droite
        setMargin(retourButton, new Insets(0.0208 * screenHeight, 0.0117 * screenWidth, 0, 0));

        // Créer les CheckBox pour activer/désactiver les vidéos et les animations
        CheckBox videoCheckBox = new CheckBox("Activer vidéos de pré-lancement");
        CheckBox animationCheckBox = new CheckBox("Activer animations");

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(3);
        dropShadow.setOffsetX(2);
        dropShadow.setOffsetY(2);
        videoCheckBox.setEffect(dropShadow);
        animationCheckBox.setEffect(dropShadow);

        // Mettre les CheckBox dans une VBox pour les organiser l'une au-dessus de l'autre
        VBox choicesBox = new VBox(0.069444*screenHeight); // Espacement vertical entre les CheckBox
        choicesBox.getChildren().addAll(videoCheckBox, animationCheckBox);

        choicesBox.setTranslateX(0.1*screenWidth);
        choicesBox.setTranslateY(0.35*screenHeight);

        setMargin(choicesBox, new Insets(0.097222*screenHeight, 0, 0, 0));

        getChildren().addAll(choicesBox, retourButton);

        // Définir le style des CheckBox
        String checkBoxStyle = "-fx-text-fill: white; -fx-font-family: Cardo; -fx-font-weight: bold; -fx-font-size: 35px;";

        // Appliquer le style aux CheckBox
        videoCheckBox.setStyle(checkBoxStyle);
        animationCheckBox.setStyle(checkBoxStyle);

        // Initialiser les CheckBox avec les valeurs sauvegardées
        videoCheckBox.setSelected(VideoData.isVideoChoose());
        animationCheckBox.setSelected(VideoData.isAnimation());

        // Ajouter des écouteurs d'événements pour mettre à jour les préférences lorsque la sélection change
        videoCheckBox.setOnAction(event -> {
            VideoData.setVideoChoose(videoCheckBox.isSelected());
        });

        animationCheckBox.setOnAction(event -> {
            VideoData.setAnimation(animationCheckBox.isSelected());
        });
    }
}