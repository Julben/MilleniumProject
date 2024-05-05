package org.example.milleniumproject.model;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BG extends StackPane {

    private Background background;

    //Création d'une méthode qui permet d'ajouter un fond d'écran
    public BG(String imagePath) {

        try {
            Image image = new Image(new FileInputStream(imagePath)); // Renommage de la variable
            BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
            BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, backgroundSize); // Utilisation de la variable renommée
            this.background = new Background(backgroundImage);
            setBackground(this.background);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public Background getCustomBackground() {
        return background;
    }
}

