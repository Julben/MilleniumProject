package org.example.milleniumproject.view;

import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Video extends Pane {

    private Stage primaryStage; // référence de la scène principale

    public Video(Stage primaryStage) {
        this.primaryStage = primaryStage; // Initialiser la référence primaryStage
        Image backgroundImage = null;
        try {
            backgroundImage = new Image(new FileInputStream("src/main/resources/BGVIDEO.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, backgroundSize);
        setBackground(new Background(background));
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        };
    }


