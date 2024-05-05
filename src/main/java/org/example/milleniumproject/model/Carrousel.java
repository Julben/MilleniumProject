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

public class Carrousel extends StackPane {
    private ImageView imageView;
    private Label label;
    private int currentIndex;
    private final String[] contents;
    private final boolean isImage;

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
            label.setFont(Font.font("Cardo", FontWeight.BOLD, 30));
        }

        Button previousButton = createNavigationButton("<", event -> {SoundPlayer.soundPlay(); showPrevious();});
        Button nextButton = createNavigationButton(">", event -> {SoundPlayer.soundPlay(); showNext();});

        HBox buttonsBox = new HBox(120, previousButton, nextButton);
        buttonsBox.setAlignment(Pos.CENTER);
        this.getChildren().addAll(isImage ? imageView : label, buttonsBox);

        // Charger le contenu sauvegardé dès la création
        showContent(savedIndex);
    }

    private Button createNavigationButton(String text, EventHandler<ActionEvent> eventHandler) {
        Button button = new Button(text);
        button.setOnAction(eventHandler);
        button.setPrefSize(20, 20);
        button.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(5), null)));
        button.setTextFill(Color.WHITE);
        button.setFont(Font.font("Cardo", FontWeight.BOLD, 50));
        return button;
    }

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

    public void showNext() {
        currentIndex = (currentIndex + 1) % contents.length;
        showContent(currentIndex);
    }

    public void showPrevious() {
        currentIndex = (currentIndex - 1 + contents.length) % contents.length;
        showContent(currentIndex);
    }

    public int getCurrentIndex() {
        return currentIndex;
    }
}