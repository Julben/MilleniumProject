package org.example.milleniumproject.model;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.event.ActionEvent;
import org.example.milleniumproject.view.Profil;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.example.milleniumproject.model.Constant.screenHeight;
import static org.example.milleniumproject.model.Constant.screenWidth;

/**
 * Carrousel est une classe personnalisée qui représente un composant permettant de naviguer
 * à travers une série d'éléments, qu'ils soient des images ou des textes.
 */
public class Carrousel extends StackPane {
    private ImageView imageView;
    private Label label;
    private int currentIndex;
    private final String[] contents;
    private final boolean isImage;

    /**
     * Constructeur de la classe Carrousel.
     *
     * @param contents    Le tableau des contenus à afficher dans le carrousel.
     * @param isImage     Un booléen indiquant si les contenus sont des images ou des textes.
     * @param savedIndex  L'index de départ sauvegardé.
     */
    public Carrousel(String[] contents, boolean isImage, int savedIndex) {
        this.contents = contents;
        this.isImage = isImage;
        this.currentIndex = savedIndex;

        if (isImage) {
            imageView = new ImageView();
            imageView.setFitWidth(Profil.AVATAR_SIZE);
            imageView.setPreserveRatio(true);
        } else {
            label = new Label();
            label.setTextFill(Color.WHITE);
            label.setFont(Font.font("Cardo", FontWeight.BOLD, 0.04167*screenHeight));
        }

        Button previousButton = createNavigationButton("<", event -> {SoundPlayer.soundPlay(); showPrevious();});
        Button nextButton = createNavigationButton(">", event -> {SoundPlayer.soundPlay(); showNext();});

        HBox buttonsBox = new HBox(0.09375*screenWidth, previousButton, nextButton);
        buttonsBox.setAlignment(Pos.CENTER);
        this.getChildren().addAll(isImage ? imageView : label, buttonsBox);

        // Charger le contenu sauvegardé dès la création
        showContent(savedIndex);
    }

    /**
     * Crée un bouton de navigation pour le carrousel.
     *
     * @param text           Le texte affiché sur le bouton.
     * @param eventHandler  L'objet EventHandler<ActionEvent> gérant l'événement de clic sur le bouton.
     * @return Le bouton créé.
     */
    private Button createNavigationButton(String text, EventHandler<ActionEvent> eventHandler) {
        Button button = new Button(text);
        button.setOnAction(eventHandler);
        button.setPrefSize(0.0156*screenWidth, 0.02778*screenHeight);
        button.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(5), null)));
        button.setTextFill(Color.WHITE);
        button.setFont(Font.font("Cardo", FontWeight.BOLD, 0.0694*screenHeight));
        return button;
    }

    /**
     * Affiche le contenu à l'index spécifié.
     *
     * @param index L'index du contenu à afficher.
     */
    private void showContent(int index) {
        if (index >= 0 && index < contents.length) {
            if (isImage) {
                try {
                    Image image = new Image(new FileInputStream(contents[index]));
                    imageView.setImage(image);
                } catch (FileNotFoundException e) {
                    e.printStackTrace(); // Gérer l'exception
                }
            } else {
                label.setText(contents[index]);
            }
            currentIndex = index;
        }
    }

    /**
     * Affiche le contenu suivant dans le carrousel.
     */
    public void showNext() {
        currentIndex = (currentIndex + 1) % contents.length;
        showContent(currentIndex);
    }

    /**
     * Affiche le contenu précédent dans le carrousel.
     */
    public void showPrevious() {
        currentIndex = (currentIndex - 1 + contents.length) % contents.length;
        showContent(currentIndex);
    }

    /**
     * Récupère l'index actuel du contenu affiché dans le carrousel.
     *
     * @return L'index actuel.
     */
    public int getCurrentIndex() {
        return currentIndex;
    }
}