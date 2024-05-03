package org.example.milleniumproject.view;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.layout.Pane;

public class Audio extends Pane {
    private Stage primaryStage; // référence de la scène principale

    public Audio(Stage primaryStage) {
        this.primaryStage = primaryStage;// Utilisation de Pane au lieu de StackPane
    }
    public Audio() {
        Image backgroundImage = null;
        try {
            backgroundImage = new Image(new FileInputStream("src/main/resources/BGAUDIO.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, backgroundSize);
        setBackground(new Background(background));
    }
}


