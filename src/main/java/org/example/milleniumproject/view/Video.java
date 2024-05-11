package org.example.milleniumproject.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.milleniumproject.model.BG;
import org.example.milleniumproject.model.BackButtons;
import org.example.milleniumproject.view.VideoData;

import java.util.prefs.Preferences;

import static org.example.milleniumproject.model.Constant.screenHeight;
import static org.example.milleniumproject.model.Constant.screenWidth;

public class Video extends StackPane {
    private Preferences preferences;

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

        // Mettre les CheckBox dans une VBox pour les organiser l'une au-dessus de l'autre
        VBox choicesBox = new VBox(30); // Espacement vertical entre les CheckBox
        choicesBox.getChildren().addAll(videoCheckBox, animationCheckBox);

        // Positionner la VBox
        double vboxX = (0.1*screenWidth); // Calculer la position X
        double vboxY = (0.35*screenHeight); // Calculer la position Y
        choicesBox.setTranslateX(vboxX);
        choicesBox.setTranslateY(vboxY);

        getChildren().addAll(choicesBox, retourButton);

        // Définir le style des CheckBox
        String checkBoxStyle = "-fx-text-fill: white; " +
                "-fx-font-family: Cardo; " +
                "-fx-font-weight: bold; " +
                "-fx-font-size: 35px;";

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
