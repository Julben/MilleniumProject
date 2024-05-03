package org.example.milleniumproject.view;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Charger extends Pane {
    private Stage primaryStage; // référence de la scène principale

    public Charger(Stage primaryStage) {
        this.primaryStage = primaryStage;// Utilisation de Pane au lieu de StackPane
    }

    public Charger() {
        Image backgroundImage = null;
        try {
            backgroundImage = new Image(new FileInputStream("BGAUDIO.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, backgroundSize);
        setBackground(new Background(background));
    }
}
